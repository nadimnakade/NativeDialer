package com.crm.v2.common.log

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ApiLogger {
    private const val TAG = "API_LOG"
    private val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    private fun ts(): String = timeFormat.format(Date())

    fun request(tag: String, method: String, url: String, body: String? = null) {
        Log.d(TAG, "[${ts()}] >>> $tag $method $url")
        if (body != null) {
            Log.d(TAG, "[${ts()}] >>> BODY: $body")
        }
    }

    fun response(tag: String, code: Int, url: String, body: String? = null) {
        Log.d(TAG, "[${ts()}] <<< $tag $code $url")
        if (body != null) {
            val chunked = body.chunked(3000)
            chunked.forEachIndexed { i, part ->
                Log.d(TAG, "[${ts()}] <<< BODY[$i]: $part")
            }
        }
    }

    fun error(tag: String, message: String, e: Exception? = null) {
        Log.e(TAG, "[${ts()}] !!! $tag $message", e)
    }

    fun event(tag: String, message: String) {
        Log.d(TAG, "[${ts()}] --- $tag $message")
    }
}
