package com.merlottv.app.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.PermissionRequest
import android.webkit.RenderProcessGoneDetail
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewRenderProcess
import android.webkit.WebViewRenderProcessClient
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.merlottv.app.R
import com.merlottv.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    companion object {
        private const val TAG = "MerlotTV"
        // Minimum interval (ms) between evaluateJavascript() calls for key events.
        // This prevents overwhelming the WebView renderer with rapid D-pad presses.
        private const val KEY_THROTTLE_MS = 80L
        // Max renderer crashes before we stop auto-reloading (prevents infinite loop)
        private const val MAX_CRASH_RELOADS = 5
        // Reset crash counter after this many ms of stability
        private const val CRASH_RESET_MS = 30_000L
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var webView: WebView

    // Fullscreen custom view (for HTML5 video fullscreen)
    private var customView: View? = null
    private var customViewCallback: WebChromeClient.CustomViewCallback? = null

    // For file picker (M3U / EPG file upload)
    private var fileChooserCallback: ValueCallback<Array<Uri>>? = null
    private val FILE_CHOOSER_REQUEST = 100

    // Throttle: timestamp of last JS key event dispatched
    private var lastKeyFireTime = 0L

    // Track if WebView is alive (renderer hasn't crashed)
    private var webViewAlive = true

    // Track consecutive back presses to allow exit
    private var backPressCount = 0
    private var lastBackPressTime = 0L

    // Crash loop protection
    private var crashCount = 0
    private var lastCrashTime = 0L

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep screen on during playback
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView
        setupWebView()
        goFullscreen()

        webView.loadUrl("file:///android_asset/index.html")
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  WebView setup
    // ═══════════════════════════════════════════════════════════════════════

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val settings = webView.settings

        // JavaScript & storage — required for the app to function
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true      // localStorage (favourites, settings)
        settings.databaseEnabled = true

        // Media & hardware acceleration
        settings.mediaPlaybackRequiresUserGesture = false
        settings.allowFileAccess = true
        settings.allowContentAccess = true

        // Allow loading HTTP streams from the HTTPS local file context
        @Suppress("DEPRECATION")
        settings.allowUniversalAccessFromFileURLs = true
        @Suppress("DEPRECATION")
        settings.allowFileAccessFromFileURLs = true

        // Allow mixed content (HTTP streams from file:// context)
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        // Zoom disabled (TV app, not a browser)
        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false

        // Viewport
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true

        // Cache
        settings.cacheMode = WebSettings.LOAD_DEFAULT

        // Use NONE layer type — HARDWARE uses too much GPU memory and causes
        // renderer OOM crashes on low-memory TV devices and emulators.
        // Hardware acceleration is still enabled at the application level via manifest.
        webView.setLayerType(View.LAYER_TYPE_NONE, null)

        // Focus for D-pad
        webView.isFocusable = true
        webView.isFocusableInTouchMode = true
        webView.requestFocus()

        // JavaScript → Kotlin bridge
        webView.addJavascriptInterface(AndroidBridge(), "AndroidBridge")

        // ── WebViewRenderProcessClient (API 29+) ─────────────────────────
        // Monitor renderer health — do NOT terminate on unresponsive!
        // TV devices and emulators are slow; killing the renderer causes crash loops.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            webView.webViewRenderProcessClient = object : WebViewRenderProcessClient() {
                override fun onRenderProcessUnresponsive(
                    view: WebView,
                    renderer: WebViewRenderProcess?
                ) {
                    // Just log — do NOT terminate. The renderer may recover on its own.
                    // Previously we called renderer?.terminate() which caused crash loops
                    // on low-memory devices and emulators.
                    Log.w(TAG, "WebView renderer unresponsive — waiting for recovery")
                }

                override fun onRenderProcessResponsive(
                    view: WebView,
                    renderer: WebViewRenderProcess?
                ) {
                    Log.i(TAG, "WebView renderer became responsive again")
                }
            }
        }

        // ── WebViewClient ────────────────────────────────────────────────
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean = false   // keep everything inside the WebView

            // Handle renderer crash (API 26+)
            // This is the KEY crash recovery mechanism.
            // The system only calls this on API 26+; safe to override unconditionally.
            @SuppressLint("NewApi")
            override fun onRenderProcessGone(
                view: WebView,
                detail: RenderProcessGoneDetail?
            ): Boolean {
                val crashed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    detail?.didCrash() ?: true
                } else {
                    true
                }
                Log.e(TAG, "WebView render process gone — crashed=$crashed")
                webViewAlive = false

                // ── Crash loop protection ────────────────────────────────
                val now = SystemClock.elapsedRealtime()
                if (now - lastCrashTime > CRASH_RESET_MS) {
                    // Stable for 30s+ → reset crash counter
                    crashCount = 0
                }
                crashCount++
                lastCrashTime = now

                if (crashCount > MAX_CRASH_RELOADS) {
                    Log.e(TAG, "Renderer crashed $crashCount times — stopping auto-reload")
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "WebView keeps crashing. Please restart the app.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    // Still return true so the app doesn't crash — user can restart
                    return true
                }

                // Increasing delay for subsequent crashes (backoff)
                val delayMs = (500L * crashCount).coerceAtMost(3000L)
                Log.w(TAG, "Renderer crashed (#$crashCount) — will reload in ${delayMs}ms")

                view.postDelayed({
                    try {
                        webViewAlive = true
                        view.loadUrl("file:///android_asset/index.html")
                        Log.i(TAG, "Reloaded index.html after crash recovery (#$crashCount)")
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to reload after crash", e)
                    }
                }, delayMs)

                // Return true = we handled it, don't kill the app
                return true
            }
        }

        // ── WebChromeClient ──────────────────────────────────────────────
        webView.webChromeClient = object : WebChromeClient() {

            // HTML5 video fullscreen
            override fun onShowCustomView(view: View, callback: CustomViewCallback) {
                if (customView != null) {
                    callback.onCustomViewHidden()
                    return
                }
                customView = view
                customViewCallback = callback
                binding.fullscreenContainer.addView(view)
                binding.fullscreenContainer.visibility = View.VISIBLE
                webView.visibility = View.GONE
                goFullscreen()
            }

            override fun onHideCustomView() {
                val view = customView ?: return
                binding.fullscreenContainer.removeView(view)
                binding.fullscreenContainer.visibility = View.GONE
                webView.visibility = View.VISIBLE
                customView = null
                customViewCallback?.onCustomViewHidden()
                customViewCallback = null
                goFullscreen()
            }

            // Allow media autoplay permissions (for HLS/MPEG-TS streams)
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }

            // File chooser for M3U / EPG file picker
            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                fileChooserCallback?.onReceiveValue(null) // cancel any previous
                fileChooserCallback = filePathCallback
                val intent = fileChooserParams.createIntent()
                try {
                    startActivityForResult(intent, FILE_CHOOSER_REQUEST)
                } catch (e: Exception) {
                    fileChooserCallback = null
                    return false
                }
                return true
            }

            // Log WebView console messages for debugging
            override fun onConsoleMessage(message: ConsoleMessage): Boolean {
                Log.d(
                    TAG,
                    "${message.sourceId()}:${message.lineNumber()} > ${message.message()}"
                )
                return true
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  AndroidBridge — JS ↔ Kotlin interface
    // ═══════════════════════════════════════════════════════════════════════

    inner class AndroidBridge {

        @JavascriptInterface
        fun goFullscreen() {
            runOnUiThread {
                this@MainActivity.goFullscreen()
                safeEvaluateJs(
                    "(function(){var v=document.getElementById('vid');" +
                    "if(v&&v.requestFullscreen&&document.fullscreenElement!==v)" +
                    "{v.requestFullscreen().catch(function(){});}})();"
                )
            }
        }

        @JavascriptInterface
        fun getPlatform(): String = "android"

        @JavascriptInterface
        fun getVersion(): String {
            return try {
                packageManager.getPackageInfo(packageName, 0).versionName ?: "2.0"
            } catch (e: Exception) {
                "2.0"
            }
        }

        @JavascriptInterface
        fun openExternalUrl(url: String) {
            runOnUiThread {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }

        @JavascriptInterface
        fun isAndroid(): Boolean = true

        /**
         * Download an APK from the given URL and launch the system installer.
         * Called from JavaScript when user accepts an update.
         * Runs on a background thread to avoid blocking the UI.
         */
        @JavascriptInterface
        fun downloadAndInstallUpdate(apkUrl: String) {
            Thread {
                try {
                    runOnUiThread {
                        safeEvaluateJs("if(typeof _updateProgress==='function')_updateProgress('Downloading update...')")
                    }

                    val client = OkHttpClient.Builder()
                        .followRedirects(true)
                        .followSslRedirects(true)
                        .build()
                    val request = Request.Builder().url(apkUrl).build()
                    val response = client.newCall(request).execute()

                    if (!response.isSuccessful) {
                        runOnUiThread {
                            safeEvaluateJs("if(typeof _updateProgress==='function')_updateProgress('Download failed: HTTP ${response.code}')")
                        }
                        return@Thread
                    }

                    // Save APK to cache/updates/
                    val updateDir = File(cacheDir, "updates")
                    updateDir.mkdirs()
                    val apkFile = File(updateDir, "merlottv-update.apk")
                    response.body?.byteStream()?.use { input ->
                        FileOutputStream(apkFile).use { output ->
                            val buffer = ByteArray(8192)
                            var bytesRead: Int
                            var totalBytes = 0L
                            val contentLength = response.body?.contentLength() ?: -1
                            while (input.read(buffer).also { bytesRead = it } != -1) {
                                output.write(buffer, 0, bytesRead)
                                totalBytes += bytesRead
                                if (contentLength > 0) {
                                    val pct = (totalBytes * 100 / contentLength).toInt()
                                    runOnUiThread {
                                        safeEvaluateJs("if(typeof _updateProgress==='function')_updateProgress('Downloading... $pct%')")
                                    }
                                }
                            }
                        }
                    }

                    runOnUiThread {
                        safeEvaluateJs("if(typeof _updateProgress==='function')_updateProgress('Installing...')")
                    }

                    // Launch system installer via FileProvider
                    val apkUri = FileProvider.getUriForFile(
                        this@MainActivity,
                        "${packageName}.fileprovider",
                        apkFile
                    )
                    val installIntent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(apkUri, "application/vnd.android.package-archive")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    startActivity(installIntent)

                } catch (e: Exception) {
                    Log.e(TAG, "Update download/install failed", e)
                    runOnUiThread {
                        safeEvaluateJs("if(typeof _updateProgress==='function')_updateProgress('Update failed: ${e.message?.replace("'", "\\'")}')")
                    }
                }
            }.start()
        }

        /** Returns the current app version code (integer, for comparison) */
        @JavascriptInterface
        fun getVersionCode(): Int {
            return try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageManager.getPackageInfo(packageName, 0).longVersionCode.toInt()
                } else {
                    @Suppress("DEPRECATION")
                    packageManager.getPackageInfo(packageName, 0).versionCode
                }
            } catch (e: Exception) { 0 }
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Safe JavaScript evaluation
    //
    //  Wraps evaluateJavascript() in a try-catch so that if the WebView
    //  renderer is dead or in a bad state, we don't propagate the crash.
    // ═══════════════════════════════════════════════════════════════════════

    private fun safeEvaluateJs(script: String, callback: ((String) -> Unit)? = null) {
        if (!webViewAlive) {
            Log.w(TAG, "Skipping JS eval — renderer not alive")
            return
        }
        try {
            webView.evaluateJavascript(script) { result ->
                callback?.invoke(result ?: "")
            }
        } catch (e: Exception) {
            Log.e(TAG, "evaluateJavascript failed", e)
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  D-pad / TV Remote key mapping
    //
    //  CRITICAL: We intercept ALL D-pad keys here and CONSUME them
    //  (return true) so the WebView's native focus engine never sees them.
    //  The HTML has its own spatial navigation engine (onKey function)
    //  that handles all focus movement via JavaScript.
    //  If we let WebView also handle them → double movement / tab skipping.
    //
    //  THROTTLE: We enforce a minimum interval between consecutive JS
    //  key dispatches to avoid flooding the WebView renderer, which can
    //  cause a native Chromium crash (SIGTRAP in sandboxed_process).
    // ═══════════════════════════════════════════════════════════════════════

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode

        // ── Special handling for BACK key ────────────────────────────────
        // The HTML app uses Escape to navigate "back" within its own UI
        // (e.g., close modals, go back in menu hierarchy).
        // But we MUST also allow the user to actually exit the app.
        //
        // Strategy: On ACTION_DOWN for BACK, send Escape to JS.
        // If the user presses Back twice within 2 seconds, exit the app.
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.action == KeyEvent.ACTION_DOWN) {
                val now = SystemClock.elapsedRealtime()
                if (now - lastBackPressTime < 2000) {
                    backPressCount++
                } else {
                    backPressCount = 1
                }
                lastBackPressTime = now

                if (backPressCount >= 3) {
                    // Three rapid back presses → let the system handle it (exit app)
                    backPressCount = 0
                    return super.dispatchKeyEvent(event)
                }

                // Otherwise send Escape to JS
                throttledFireJsKeyEvent("Escape", 27)
            }
            // Consume both ACTION_DOWN and ACTION_UP
            return true
        }

        // ── All other mapped keys ────────────────────────────────────────
        if (event.action != KeyEvent.ACTION_DOWN) {
            // Consume key-up for all mapped keys so WebView doesn't handle them
            if (mapKeyToJs(keyCode) != null) return true
            return super.dispatchKeyEvent(event)
        }

        val mapping = mapKeyToJs(keyCode) ?: return super.dispatchKeyEvent(event)

        // Fire into JavaScript (throttled) and CONSUME the event
        throttledFireJsKeyEvent(mapping.first, mapping.second)
        return true
    }

    /**
     * Maps Android key codes to (JS key name, JS keyCode number).
     * The HTML D-pad engine checks BOTH e.key AND e.keyCode, so we must send both.
     *
     * HTML checks:
     *   ArrowUp:  key==='ArrowUp'  || kc===38 || kc===19
     *   ArrowDown: key==='ArrowDown' || kc===40 || kc===20
     *   Enter:    key==='Enter'     || kc===13 || kc===23 || kc===66
     *   Escape:   key==='Escape'    || kc===27 || kc===4
     */
    private fun mapKeyToJs(code: Int): Pair<String, Int>? = when (code) {
        // D-pad navigation
        KeyEvent.KEYCODE_DPAD_UP           -> "ArrowUp" to 38
        KeyEvent.KEYCODE_DPAD_DOWN         -> "ArrowDown" to 40
        KeyEvent.KEYCODE_DPAD_LEFT         -> "ArrowLeft" to 37
        KeyEvent.KEYCODE_DPAD_RIGHT        -> "ArrowRight" to 39
        KeyEvent.KEYCODE_DPAD_CENTER       -> "Enter" to 13
        KeyEvent.KEYCODE_ENTER             -> "Enter" to 13
        KeyEvent.KEYCODE_NUMPAD_ENTER      -> "Enter" to 13

        // Back/Escape handled separately in dispatchKeyEvent — NOT here
        // to avoid double-handling. KEYCODE_ESCAPE from a physical keyboard
        // still maps here as a fallback.
        KeyEvent.KEYCODE_ESCAPE            -> "Escape" to 27

        // Media controls
        KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE  -> "MediaPlayPause" to 179
        KeyEvent.KEYCODE_MEDIA_PLAY        -> "MediaPlay" to 179
        KeyEvent.KEYCODE_MEDIA_PAUSE       -> "MediaPause" to 179
        KeyEvent.KEYCODE_MEDIA_STOP        -> "MediaStop" to 178
        KeyEvent.KEYCODE_MEDIA_NEXT        -> "MediaTrackNext" to 176
        KeyEvent.KEYCODE_MEDIA_PREVIOUS    -> "MediaTrackPrevious" to 177
        KeyEvent.KEYCODE_MEDIA_FAST_FORWARD -> "MediaFastForward" to 228
        KeyEvent.KEYCODE_MEDIA_REWIND      -> "MediaRewind" to 227

        // Channel up/down
        KeyEvent.KEYCODE_CHANNEL_UP,
        KeyEvent.KEYCODE_PAGE_UP           -> "ChannelUp" to 33
        KeyEvent.KEYCODE_CHANNEL_DOWN,
        KeyEvent.KEYCODE_PAGE_DOWN         -> "ChannelDown" to 34

        // Extra TV buttons
        KeyEvent.KEYCODE_INFO              -> "Info" to 0
        KeyEvent.KEYCODE_GUIDE             -> "Guide" to 0
        KeyEvent.KEYCODE_MENU              -> "ContextMenu" to 93

        // Colour buttons
        KeyEvent.KEYCODE_PROG_RED          -> "ColorF0Red" to 403
        KeyEvent.KEYCODE_PROG_GREEN        -> "ColorF1Green" to 404
        KeyEvent.KEYCODE_PROG_YELLOW       -> "ColorF2Yellow" to 405
        KeyEvent.KEYCODE_PROG_BLUE         -> "ColorF3Blue" to 406

        // Number keys (for direct channel entry)
        KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_NUMPAD_0 -> "0" to 48
        KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_NUMPAD_1 -> "1" to 49
        KeyEvent.KEYCODE_2, KeyEvent.KEYCODE_NUMPAD_2 -> "2" to 50
        KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_NUMPAD_3 -> "3" to 51
        KeyEvent.KEYCODE_4, KeyEvent.KEYCODE_NUMPAD_4 -> "4" to 52
        KeyEvent.KEYCODE_5, KeyEvent.KEYCODE_NUMPAD_5 -> "5" to 53
        KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_NUMPAD_6 -> "6" to 54
        KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_NUMPAD_7 -> "7" to 55
        KeyEvent.KEYCODE_8, KeyEvent.KEYCODE_NUMPAD_8 -> "8" to 56
        KeyEvent.KEYCODE_9, KeyEvent.KEYCODE_NUMPAD_9 -> "9" to 57

        else -> null
    }

    /**
     * Throttled version of fireJsKeyEvent.
     * Drops key events that arrive faster than KEY_THROTTLE_MS apart.
     * This prevents the WebView Chromium renderer from being overwhelmed
     * by rapid D-pad presses which can cause a native SIGTRAP crash.
     */
    private fun throttledFireJsKeyEvent(jsKey: String, jsKeyCode: Int) {
        val now = SystemClock.elapsedRealtime()
        if (now - lastKeyFireTime < KEY_THROTTLE_MS) {
            // Drop this key event — too fast
            return
        }
        lastKeyFireTime = now
        fireJsKeyEvent(jsKey, jsKeyCode)
    }

    private fun fireJsKeyEvent(jsKey: String, jsKeyCode: Int) {
        val escaped = jsKey.replace("'", "\\'")
        // Dispatch with BOTH key and keyCode — the HTML D-pad engine checks both.
        // Only dispatch on document — the HTML onKey handler uses capture phase
        // on document, so dispatching on both document AND window caused
        // duplicate event handling. Single dispatch is sufficient and safer.
        safeEvaluateJs(
            "(function(){" +
            "var o={key:'$escaped',code:'$escaped',keyCode:$jsKeyCode,which:$jsKeyCode," +
            "bubbles:true,cancelable:true,composed:true};" +
            "document.dispatchEvent(new KeyboardEvent('keydown',o));" +
            "})();"
        )
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Fullscreen / immersive mode
    // ═══════════════════════════════════════════════════════════════════════

    private fun goFullscreen() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { ctrl ->
                ctrl.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                ctrl.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            )
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  File chooser result
    // ═══════════════════════════════════════════════════════════════════════

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_CHOOSER_REQUEST) {
            val result = if (resultCode == RESULT_OK && data != null) {
                WebChromeClient.FileChooserParams.parseResult(resultCode, data)
            } else {
                null
            }
            fileChooserCallback?.onReceiveValue(result)
            fileChooserCallback = null
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Lifecycle
    // ═══════════════════════════════════════════════════════════════════════

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) goFullscreen()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
        goFullscreen()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onDestroy() {
        webViewAlive = false
        try {
            webView.stopLoading()
            webView.destroy()
        } catch (e: Exception) {
            Log.e(TAG, "Error destroying WebView", e)
        }
        super.onDestroy()
    }
}
