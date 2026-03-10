package com.merlottv.app.presentation.main

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.merlottv.app.R
import com.merlottv.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single Activity host.
 *
 * Navigation graph:  res/navigation/nav_main.xml
 * Start destination: HomeFragment (Leanback BrowseSupportFragment)
 *
 * The five sections are top-level NavGraph destinations:
 *   home → iptv_channels → iptv_player (PlayerActivity)
 *   home → tv_guide
 *   home → vod_browse → vod_detail → vod_player (VodPlayerActivity)
 *   home → channel_checker
 *   home → settings
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
    }

    /** Let back-stack navigation handle the back key properly on TV. */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
