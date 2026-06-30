package com.crm.v2.data.network.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/crm/v2/data/network/api/CloudConnectApi;", "", "click2Call", "Lretrofit2/Response;", "Lcom/crm/v2/data/network/dto/cloudconnect/Click2CallResponse;", "url", "", "request", "Lcom/crm/v2/data/network/dto/cloudconnect/Click2CallRequest;", "(Ljava/lang/String;Lcom/crm/v2/data/network/dto/cloudconnect/Click2CallRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCallLog", "Lcom/crm/v2/data/network/dto/cloudconnect/CallLogResponse;", "Lcom/crm/v2/data/network/dto/cloudconnect/CallLogRequest;", "(Ljava/lang/String;Lcom/crm/v2/data/network/dto/cloudconnect/CallLogRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface CloudConnectApi {
    
    @retrofit2.http.POST()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object click2Call(@retrofit2.http.Url()
    @org.jetbrains.annotations.NotNull()
    java.lang.String url, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.dto.cloudconnect.Click2CallRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.crm.v2.data.network.dto.cloudconnect.Click2CallResponse>> $completion);
    
    @retrofit2.http.POST()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCallLog(@retrofit2.http.Url()
    @org.jetbrains.annotations.NotNull()
    java.lang.String url, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.dto.cloudconnect.CallLogRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.crm.v2.data.network.dto.cloudconnect.CallLogResponse>> $completion);
}