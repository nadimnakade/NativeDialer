package com.crm.v2.data.datastore;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 J2\u00020\u0001:\u0001JB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010H\u001a\u00020IR(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR(\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR(\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR(\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR(\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR(\u0010\u001b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR(\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR$\u0010\"\u001a\u00020!2\u0006\u0010\u0005\u001a\u00020!8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010(\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b)\u0010\t\"\u0004\b*\u0010\u000bR(\u0010+\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b,\u0010\t\"\u0004\b-\u0010\u000bR(\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b/\u0010\t\"\u0004\b0\u0010\u000bR\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u00103\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b4\u0010\t\"\u0004\b5\u0010\u000bR(\u00106\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b7\u0010\t\"\u0004\b8\u0010\u000bR(\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010\u000bR$\u0010=\u001a\u00020<2\u0006\u0010\u0005\u001a\u00020<8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR(\u0010B\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bC\u0010\t\"\u0004\bD\u0010\u000bR(\u0010E\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bF\u0010\t\"\u0004\bG\u0010\u000b\u00a8\u0006K"}, d2 = {"Lcom/crm/v2/data/datastore/AuthDataStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "accessToken", "getAccessToken", "()Ljava/lang/String;", "setAccessToken", "(Ljava/lang/String;)V", "address", "getAddress", "setAddress", "bankAccountNo", "getBankAccountNo", "setBankAccountNo", "dateOfBirth", "getDateOfBirth", "setDateOfBirth", "dateOfJoining", "getDateOfJoining", "setDateOfJoining", "designationDesc", "getDesignationDesc", "setDesignationDesc", "employeeName", "getEmployeeName", "setEmployeeName", "fcmToken", "getFcmToken", "setFcmToken", "", "isLoggedIn", "()Z", "setLoggedIn", "(Z)V", "masterKey", "Landroidx/security/crypto/MasterKey;", "officeContactNo", "getOfficeContactNo", "setOfficeContactNo", "photoUrl", "getPhotoUrl", "setPhotoUrl", "pinHash", "getPinHash", "setPinHash", "prefs", "Landroid/content/SharedPreferences;", "refreshToken", "getRefreshToken", "setRefreshToken", "reportingManagerName", "getReportingManagerName", "setReportingManagerName", "roleDesc", "getRoleDesc", "setRoleDesc", "", "tokenExpiry", "getTokenExpiry", "()J", "setTokenExpiry", "(J)V", "userKey", "getUserKey", "setUserKey", "userName", "getUserName", "setUserName", "clear", "", "Companion", "app_release"})
public final class AuthDataStore {
    @org.jetbrains.annotations.NotNull()
    private final androidx.security.crypto.MasterKey masterKey = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_FILE = "crm_auth_secure";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOKEN = "jwt_token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_REFRESH_TOKEN = "refresh_token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_KEY = "user_key";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_EMPLOYEE_NAME = "employee_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_IS_LOGGED_IN = "is_logged_in";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOKEN_EXPIRY = "token_expiry";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PIN_HASH = "pin_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FCM_TOKEN = "fcm_token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_NAME = "user_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DESIGNATION_DESC = "designation_desc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ROLE_DESC = "role_desc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_REPORTING_MANAGER = "reporting_manager";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_OFFICE_CONTACT = "office_contact";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DATE_OF_BIRTH = "date_of_birth";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DATE_OF_JOINING = "date_of_joining";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BANK_ACCOUNT = "bank_account";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ADDRESS = "address";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PHOTO_URL = "photo_url";
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.datastore.AuthDataStore.Companion Companion = null;
    
    public AuthDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAccessToken() {
        return null;
    }
    
    public final void setAccessToken(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRefreshToken() {
        return null;
    }
    
    public final void setRefreshToken(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserKey() {
        return null;
    }
    
    public final void setUserKey(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmployeeName() {
        return null;
    }
    
    public final void setEmployeeName(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    public final void setLoggedIn(boolean value) {
    }
    
    public final long getTokenExpiry() {
        return 0L;
    }
    
    public final void setTokenExpiry(long value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPinHash() {
        return null;
    }
    
    public final void setPinHash(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFcmToken() {
        return null;
    }
    
    public final void setFcmToken(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserName() {
        return null;
    }
    
    public final void setUserName(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDesignationDesc() {
        return null;
    }
    
    public final void setDesignationDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRoleDesc() {
        return null;
    }
    
    public final void setRoleDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReportingManagerName() {
        return null;
    }
    
    public final void setReportingManagerName(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOfficeContactNo() {
        return null;
    }
    
    public final void setOfficeContactNo(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDateOfBirth() {
        return null;
    }
    
    public final void setDateOfBirth(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDateOfJoining() {
        return null;
    }
    
    public final void setDateOfJoining(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBankAccountNo() {
        return null;
    }
    
    public final void setBankAccountNo(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAddress() {
        return null;
    }
    
    public final void setAddress(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPhotoUrl() {
        return null;
    }
    
    public final void setPhotoUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final void clear() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/crm/v2/data/datastore/AuthDataStore$Companion;", "", "()V", "KEY_ADDRESS", "", "KEY_BANK_ACCOUNT", "KEY_DATE_OF_BIRTH", "KEY_DATE_OF_JOINING", "KEY_DESIGNATION_DESC", "KEY_EMPLOYEE_NAME", "KEY_FCM_TOKEN", "KEY_IS_LOGGED_IN", "KEY_OFFICE_CONTACT", "KEY_PHOTO_URL", "KEY_PIN_HASH", "KEY_REFRESH_TOKEN", "KEY_REPORTING_MANAGER", "KEY_ROLE_DESC", "KEY_TOKEN", "KEY_TOKEN_EXPIRY", "KEY_USER_KEY", "KEY_USER_NAME", "PREFS_FILE", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}