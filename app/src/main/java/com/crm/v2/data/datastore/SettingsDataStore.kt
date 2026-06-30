package com.crm.v2.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsStore by preferencesDataStore(name = "crm_settings")

class SettingsDataStore(private val context: Context) {

    val darkMode: Flow<DarkModePreference> = context.settingsStore.data.map { prefs ->
        val value = prefs[DARK_MODE_KEY] ?: DarkModePreference.SYSTEM.name
        try { DarkModePreference.valueOf(value) } catch (_: Exception) { DarkModePreference.SYSTEM }
    }

    val autoDialInterval: Flow<Int> = context.settingsStore.data.map { prefs ->
        prefs[AUTO_DIAL_INTERVAL_KEY] ?: DEFAULT_DIAL_INTERVAL
    }

    val callRecordingEnabled: Flow<Boolean> = context.settingsStore.data.map { prefs ->
        prefs[CALL_RECORDING_KEY] ?: true
    }

    val lastSyncTimestamp: Flow<Long> = context.settingsStore.data.map { prefs ->
        prefs[LAST_SYNC_KEY] ?: 0L
    }

    suspend fun setDarkMode(mode: DarkModePreference) {
        context.settingsStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = mode.name
        }
    }

    suspend fun setAutoDialInterval(seconds: Int) {
        context.settingsStore.edit { prefs ->
            prefs[AUTO_DIAL_INTERVAL_KEY] = seconds
        }
    }

    suspend fun setCallRecordingEnabled(enabled: Boolean) {
        context.settingsStore.edit { prefs ->
            prefs[CALL_RECORDING_KEY] = enabled
        }
    }

    suspend fun setLastSyncTimestamp(timestamp: Long) {
        context.settingsStore.edit { prefs ->
            prefs[LAST_SYNC_KEY] = timestamp
        }
    }

    companion object {
        private val DARK_MODE_KEY = stringPreferencesKey("dark_mode")
        private val AUTO_DIAL_INTERVAL_KEY = intPreferencesKey("auto_dial_interval")
        private val CALL_RECORDING_KEY = booleanPreferencesKey("call_recording_enabled")
        private val LAST_SYNC_KEY = longPreferencesKey("last_sync_timestamp")
        private const val DEFAULT_DIAL_INTERVAL = 10
    }
}

enum class DarkModePreference { LIGHT, DARK, SYSTEM }
