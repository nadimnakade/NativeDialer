package com.crm.v2.telephony

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import okio.ByteString.Companion.toByteString
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RtpAudioManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private const val TAG = "RtpAudioManager"
        private const val SAMPLE_RATE = 8000
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        private const val FRAME_SIZE = 160
    }

    private var audioRecord: AudioRecord? = null
    private var audioTrack: AudioTrack? = null
    private var webSocket: WebSocket? = null
    private var recordingThread: Thread? = null
    private var playbackThread: Thread? = null
    private val isRunning = AtomicBoolean(false)
    private val isRecording = AtomicBoolean(false)

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .pingInterval(5, TimeUnit.SECONDS)
        .build()

    fun start(audioWebSocketUrl: String) {
        if (isRunning.get()) {
            Log.w(TAG, "Already running")
            return
        }

        Log.d(TAG, "Starting audio manager, connecting to: $audioWebSocketUrl")
        isRunning.set(true)

        val request = Request.Builder()
            .url(audioWebSocketUrl)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "WebSocket connected")
                val startMsg = "{\"command\":\"start\"}"
                webSocket.send(startMsg)
                startAudio()
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                playPcmAudio(bytes.toByteArray())
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "Text message: $text")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e(TAG, "WebSocket failure: ${t.message}")
                stop()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, "WebSocket closed: $code $reason")
                stop()
            }
        })
    }

    private fun startAudio() {
        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "RECORD_AUDIO permission not granted")
            return
        }

        val bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)
        val safeBufferSize = maxOf(bufferSize, FRAME_SIZE * 4)

        try {
            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.VOICE_COMMUNICATION,
                SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                safeBufferSize
            )

            val trackBufferSize = AudioTrack.getMinBufferSize(
                SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                AUDIO_FORMAT
            )
            val safeTrackBufferSize = maxOf(trackBufferSize, FRAME_SIZE * 4)

            audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setSampleRate(SAMPLE_RATE)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .setEncoding(AUDIO_FORMAT)
                        .build()
                )
                .setBufferSizeInBytes(safeTrackBufferSize)
                .setTransferMode(AudioTrack.MODE_STREAM)
                .build()

            audioRecord?.startRecording()
            audioTrack?.play()
            isRecording.set(true)

            recordingThread = Thread({ recordAndSendLoop() }, "AudioRecord").also { it.start() }
            playbackThread = Thread({ playLoop() }, "AudioTrack").also { it.start() }

            Log.d(TAG, "Audio started - recording and playing")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start audio", e)
            stop()
        }
    }

    private fun recordAndSendLoop() {
        val buffer = ShortArray(FRAME_SIZE)
        Log.d(TAG, "Record loop started")

        while (isRecording.get() && isRunning.get()) {
            try {
                val read = audioRecord?.read(buffer, 0, FRAME_SIZE) ?: -1
                if (read > 0) {
                    val pcmBytes = ByteArray(read * 2)
                    ByteBuffer.wrap(pcmBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(buffer, 0, read)
                    val ws = webSocket
                    if (ws != null) {
                        ws.send(pcmBytes.toByteString(0, pcmBytes.size))
                    }
                }
            } catch (e: Exception) {
                if (isRunning.get()) {
                    Log.e(TAG, "Record error", e)
                }
            }
        }
        Log.d(TAG, "Record loop ended")
    }

    private val playBuffer = mutableListOf<Short>()
    private val playLock = Object()

    private fun playPcmAudio(pcmData: ByteArray) {
        if (!isRunning.get()) return
        val shortBuffer = ByteBuffer.wrap(pcmData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer()
        val shorts = ShortArray(shortBuffer.remaining())
        shortBuffer.get(shorts)
        synchronized(playLock) {
            playBuffer.addAll(shorts.toList())
            playLock.notifyAll()
        }
    }

    private fun playLoop() {
        val buffer = ShortArray(FRAME_SIZE)
        Log.d(TAG, "Play loop started")

        while (isRunning.get()) {
            try {
                synchronized(playLock) {
                    while (playBuffer.isEmpty() && isRunning.get()) {
                        playLock.wait(100)
                    }
                }

                synchronized(playLock) {
                    val available = minOf(playBuffer.size, FRAME_SIZE)
                    if (available > 0) {
                        for (i in 0 until available) {
                            buffer[i] = playBuffer.removeFirst()
                        }
                        audioTrack?.write(buffer, 0, available)
                    }
                }
            } catch (e: Exception) {
                if (isRunning.get()) {
                    Log.e(TAG, "Play error", e)
                }
            }
        }
        Log.d(TAG, "Play loop ended")
    }

    fun stop() {
        if (!isRunning.compareAndSet(true, false)) return
        Log.d(TAG, "Stopping audio manager")

        isRecording.set(false)

        try { audioRecord?.stop() } catch (e: Exception) { Log.w(TAG, "AudioRecord stop error", e) }
        try { audioRecord?.release() } catch (e: Exception) { Log.w(TAG, "AudioRecord release error", e) }
        audioRecord = null

        try { audioTrack?.stop() } catch (e: Exception) { Log.w(TAG, "AudioTrack stop error", e) }
        try { audioTrack?.release() } catch (e: Exception) { Log.w(TAG, "AudioTrack release error", e) }
        audioTrack = null

        recordingThread?.join(1000)
        playbackThread?.join(1000)
        recordingThread = null
        playbackThread = null

        try {
            webSocket?.close(1000, "Call ended")
        } catch (e: Exception) {
            Log.w(TAG, "WebSocket close error", e)
        }
        webSocket = null

        synchronized(playLock) {
            playBuffer.clear()
            playLock.notifyAll()
        }

        Log.d(TAG, "Audio manager stopped")
    }
}
