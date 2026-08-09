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

    var userName: String?
        get() = prefs.getString(KEY_USER_NAME, null)
        set(value) = prefs.edit().putString(KEY_USER_NAME, value).apply()

    var designationDesc: String?
        get() = prefs.getString(KEY_DESIGNATION_DESC, null)
        set(value) = prefs.edit().putString(KEY_DESIGNATION_DESC, value).apply()

    var roleDesc: String?
        get() = prefs.getString(KEY_ROLE_DESC, null)
        set(value) = prefs.edit().putString(KEY_ROLE_DESC, value).apply()

    var reportingManagerName: String?
        get() = prefs.getString(KEY_REPORTING_MANAGER, null)
        set(value) = prefs.edit().putString(KEY_REPORTING_MANAGER, value).apply()

    var officeContactNo: String?
        get() = prefs.getString(KEY_OFFICE_CONTACT, null)
        set(value) = prefs.edit().putString(KEY_OFFICE_CONTACT, value).apply()

    var dateOfBirth: String?
        get() = prefs.getString(KEY_DATE_OF_BIRTH, null)
        set(value) = prefs.edit().putString(KEY_DATE_OF_BIRTH, value).apply()

    var dateOfJoining: String?
        get() = prefs.getString(KEY_DATE_OF_JOINING, null)
        set(value) = prefs.edit().putString(KEY_DATE_OF_JOINING, value).apply()

    var bankAccountNo: String?
        get() = prefs.getString(KEY_BANK_ACCOUNT, null)
        set(value) = prefs.edit().putString(KEY_BANK_ACCOUNT, value).apply()

    var address: String?
        get() = prefs.getString(KEY_ADDRESS, null)
        set(value) = prefs.edit().putString(KEY_ADDRESS, value).apply()

    var photoUrl: String?
        get() = prefs.getString(KEY_PHOTO_URL, null)
        set(value) = prefs.edit().putString(KEY_PHOTO_URL, value).apply()

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
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_DESIGNATION_DESC = "designation_desc"
        private const val KEY_ROLE_DESC = "role_desc"
        private const val KEY_REPORTING_MANAGER = "reporting_manager"
        private const val KEY_OFFICE_CONTACT = "office_contact"
        private const val KEY_DATE_OF_BIRTH = "date_of_birth"
        private const val KEY_DATE_OF_JOINING = "date_of_joining"
        private const val KEY_BANK_ACCOUNT = "bank_account"
        private const val KEY_ADDRESS = "address"
        private const val KEY_PHOTO_URL = "photo_url"
    }
}
