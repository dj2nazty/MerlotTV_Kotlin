package com.merlottv.app.data.parser

import com.merlottv.app.domain.model.Channel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Parses M3U / M3U+ / M3U8 extended playlists.
 *
 * Supports the full range of IPTV-specific directives:
 *  #EXTM3U          — header
 *  #EXTINF          — duration + attributes line
 *  tvg-id           — EPG channel ID
 *  tvg-name         — EPG name (may differ from display name)
 *  tvg-logo         — logo URL
 *  group-title      — group / category
 *  radio            — marks radio channels
 *  user-agent       — per-stream user-agent override
 *  referrer         — HTTP Referer header override
 */
@Singleton
class M3uParser @Inject constructor() {

    companion object {
        private val EXTINF_REGEX  = Regex("""#EXTINF:-?\d+\s*(.*),\s*(.+)""")
        private val ATTR_REGEX    = Regex("""([\w-]+)="([^"]*?)"""")
        private val ATTR_NOQUOTE  = Regex("""([\w-]+)=([\S]+)""")
    }

    /** Parse from an InputStream (URL download or file open). */
    fun parse(stream: InputStream): List<Channel> {
        val reader  = BufferedReader(InputStreamReader(stream, Charsets.UTF_8))
        val channels = mutableListOf<Channel>()
        var currentAttrs: Map<String, String>? = null
        var currentName  = ""

        reader.forEachLine { raw ->
            val line = raw.trim()
            when {
                line.startsWith("#EXTINF") -> {
                    val match = EXTINF_REGEX.find(line) ?: return@forEachLine
                    val attrString = match.groupValues[1]
                    currentName    = match.groupValues[2].trim()
                    currentAttrs   = parseAttributes(attrString)
                }
                line.isNotEmpty() && !line.startsWith("#") && currentAttrs != null -> {
                    val attrs = currentAttrs!!
                    val id    = attrs["tvg-id"]?.takeIf { it.isNotBlank() }
                                ?: "${currentName.hashCode()}_${UUID.randomUUID()}"
                    channels += Channel(
                        id       = id,
                        name     = currentName,
                        url      = line,
                        logoUrl  = attrs["tvg-logo"]  ?: "",
                        group    = attrs["group-title"]?.takeIf { it.isNotBlank() } ?: "Uncategorised",
                        tvgName  = attrs["tvg-name"]  ?: currentName,
                        isRadio  = attrs["radio"]?.lowercase() == "true",
                        userAgent = attrs["user-agent"] ?: "",
                        referrer  = attrs["referrer"]  ?: attrs["tvg-referrer"] ?: "",
                    )
                    currentAttrs = null
                    currentName  = ""
                }
            }
        }
        return channels
    }

    private fun parseAttributes(attrString: String): Map<String, String> {
        val result = mutableMapOf<String, String>()
        // quoted values first
        ATTR_REGEX.findAll(attrString).forEach { result[it.groupValues[1].lowercase()] = it.groupValues[2] }
        // unquoted fall-through
        ATTR_NOQUOTE.findAll(attrString).forEach { m ->
            val key = m.groupValues[1].lowercase()
            if (!result.containsKey(key)) result[key] = m.groupValues[2]
        }
        return result
    }
}
