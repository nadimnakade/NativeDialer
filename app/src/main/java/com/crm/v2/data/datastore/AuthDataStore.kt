package com.crm.v2.data.datastore

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AuthDataStore(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREFS_FILE,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var accessToken: String?
        get() = prefs.getString(KEY_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_TOKEN, value).apply()

    var refreshToken: String?
        get() = prefs.getString(KEY_REFRESH_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_REFRESH_TOKEN, value).apply()

    var userKey: String?
        get() = prefs.getString(KEY_USER_KEY, null)
        set(value) = prefs.edit().putString(KEY_USER_KEY, value).apply()

    var employeeName: String?
        get() = prefs.getString(KEY_EMPLOYEE_NAME, null)
        set(value) = prefs.edit().putString(KEY_EMPLOYEE_NAME, value).apply()

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    var tokenExpiry: Long
        get() = prefs.getLong(KEY_TOKEN_EXPIRY, 0L)
        set(value) = prefs.edit().putLong(KEY_TOKEN_EXPIRY, value).apply()

    var pinHash: String?
        get() = prefs.getString(KEY_PIN_HASH, null)
        set(value) = prefs.edit().putString(KEY_PIN_HASH, value).apply()

    var fcmToken: String?
        get() = prefs.getString(KEY_FCM_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_FCM_TOKEN, value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_FILE = "crm_auth_secure"
        private const val KEY_TOKEN = "jwt_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_USER_KEY = "user_key"
        private const val KEY_EMPLOYEE_NAME = "employee_name"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_TOKEN_EXPIRY = "token_expiry"
        private const val KEY_PIN_HASH = "pin_hash"
        private const val KEY_FCM_TOKEN = "fcm_token"
    }
}
