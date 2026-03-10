package com.merlottv.app.data.parser

import android.util.Xml
import com.merlottv.app.domain.model.Programme
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Streaming XMLTV parser.
 *
 * XMLTV format:
 * ```xml
 * <tv>
 *   <channel id="BBC1">...</channel>
 *   <programme start="20240101120000 +0000" stop="20240101130000 +0000" channel="BBC1">
 *     <title>News</title>
 *     <desc>...</desc>
 *     <category>News</category>
 *     <icon src="https://..." />
 *     <rating system="BBFC"><value>PG</value></rating>
 *   </programme>
 * </tv>
 * ```
 *
 * Uses Android's built-in XmlPullParser — no external XML lib required.
 * Streams the input rather than loading into memory, suitable for large (50+ MB) EPG files.
 */
@Singleton
class XmltvParser @Inject constructor() {

    private val dateFormats = listOf(
        SimpleDateFormat("yyyyMMddHHmmss Z",  Locale.US),
        SimpleDateFormat("yyyyMMddHHmmss",    Locale.US).also { it.timeZone = TimeZone.getTimeZone("UTC") },
        SimpleDateFormat("yyyyMMddHHmmss z",  Locale.US),
    )

    /**
     * Parse the XMLTV stream and return all Programme objects.
     * Channel id→name mapping is also collected but programmes reference id directly.
     */
    fun parse(stream: InputStream): List<Programme> {
        val parser = Xml.newPullParser().apply {
            setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            setInput(stream, null)
        }

        val programmes = mutableListOf<Programme>()
        var eventType = parser.eventType

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "programme") {
                parseProgramme(parser)?.let { programmes += it }
            }
            eventType = parser.next()
        }
        return programmes
    }

    private fun parseProgramme(parser: XmlPullParser): Programme? {
        val channelId = parser.getAttributeValue(null, "channel")?.trim() ?: return null
        val startRaw  = parser.getAttributeValue(null, "start") ?: return null
        val stopRaw   = parser.getAttributeValue(null, "stop")  ?: return null
        val start     = parseDate(startRaw) ?: return null
        val stop      = parseDate(stopRaw)  ?: return null

        var title       = ""
        var description = ""
        var category    = ""
        var iconUrl     = ""
        var rating      = ""

        var depth = 1
        while (depth > 0) {
            when (parser.next()) {
                XmlPullParser.START_TAG -> {
                    depth++
                    when (parser.name) {
                        "title"    -> title       = parser.nextText().trim()
                        "desc"     -> description = parser.nextText().trim()
                        "category" -> category    = parser.nextText().trim()
                        "icon"     -> iconUrl     = parser.getAttributeValue(null, "src") ?: ""
                        "value"    -> if (rating.isEmpty()) rating = parser.nextText().trim()
                    }
                }
                XmlPullParser.END_TAG -> depth--
            }
        }

        return if (title.isBlank()) null
        else Programme(
            channelId   = channelId,
            title       = title,
            description = description,
            startTime   = start,
            endTime     = stop,
            category    = category,
            iconUrl     = iconUrl,
            rating      = rating,
        )
    }

    private fun parseDate(raw: String): Long? {
        val cleaned = raw.trim()
        for (fmt in dateFormats) {
            try { return fmt.parse(cleaned)?.time } catch (_: Exception) {}
        }
        return null
    }
}
