package com.crm.v2.common.log;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0010\b\u0002\u0010\u000b\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\rJ\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J*\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004J*\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0016\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/crm/v2/common/log/ApiLogger;", "", "()V", "TAG", "", "timeFormat", "Ljava/text/SimpleDateFormat;", "error", "", "tag", "message", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "event", "request", "method", "url", "body", "response", "code", "", "ts", "app_release"})
public final class ApiLogger {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "API_LOG";
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat timeFormat = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.common.log.ApiLogger INSTANCE = null;
    
    private ApiLogger() {
        super();
    }
    
    private final java.lang.String ts() {
        return null;
    }
    
    public final void request(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String method, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String body) {
    }
    
    public final void response(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, int code, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String body) {
    }
    
    public final void error(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Exception e) {
    }
    
    public final void event(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}