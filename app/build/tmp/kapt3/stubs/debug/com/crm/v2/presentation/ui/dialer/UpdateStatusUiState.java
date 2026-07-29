package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b=\b\u0087\b\u0018\u00002\u00020\u0001B\u00db\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u001bJ\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u000eH\u00c6\u0003J\t\u00103\u001a\u00020\u000eH\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u000eH\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u0003H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u000eH\u00c6\u0003J\u00df\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010G\u001a\u00020\u000e2\b\u0010H\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010I\u001a\u00020\u0007H\u00d6\u0001J\t\u0010J\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001fR\u0011\u0010\u0019\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001f\u00a8\u0006K"}, d2 = {"Lcom/crm/v2/presentation/ui/dialer/UpdateStatusUiState;", "", "leadKey", "", "clientName", "mobileNo", "callDuration", "", "selectedStatus", "Lcom/crm/v2/common/constants/StatusOption;", "callbackDate", "callbackTime", "isHLLAPBL", "isCibilBad", "", "isRealEstate", "addDailyLogin", "losNo", "appNo", "banks", "product", "region", "loginAmount", "remarks", "whatsappMessage", "isLoading", "error", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/crm/v2/common/constants/StatusOption;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAddDailyLogin", "()Z", "getAppNo", "()Ljava/lang/String;", "getBanks", "getCallDuration", "()I", "getCallbackDate", "getCallbackTime", "getClientName", "getError", "getLeadKey", "getLoginAmount", "getLosNo", "getMobileNo", "getProduct", "getRegion", "getRemarks", "getSelectedStatus", "()Lcom/crm/v2/common/constants/StatusOption;", "getWhatsappMessage", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class UpdateStatusUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String leadKey = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String clientName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mobileNo = null;
    private final int callDuration = 0;
    @org.jetbrains.annotations.Nullable()
    private final com.crm.v2.common.constants.StatusOption selectedStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callbackDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String callbackTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String isHLLAPBL = null;
    private final boolean isCibilBad = false;
    private final boolean isRealEstate = false;
    private final boolean addDailyLogin = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String losNo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String appNo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String banks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String product = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String region = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String loginAmount = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String remarks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String whatsappMessage = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public UpdateStatusUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String leadKey, @org.jetbrains.annotations.NotNull()
    java.lang.String clientName, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, int callDuration, @org.jetbrains.annotations.Nullable()
    com.crm.v2.common.constants.StatusOption selectedStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String callbackDate, @org.jetbrains.annotations.NotNull()
    java.lang.String callbackTime, @org.jetbrains.annotations.NotNull()
    java.lang.String isHLLAPBL, boolean isCibilBad, boolean isRealEstate, boolean addDailyLogin, @org.jetbrains.annotations.NotNull()
    java.lang.String losNo, @org.jetbrains.annotations.NotNull()
    java.lang.String appNo, @org.jetbrains.annotations.NotNull()
    java.lang.String banks, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String region, @org.jetbrains.annotations.NotNull()
    java.lang.String loginAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String remarks, @org.jetbrains.annotations.NotNull()
    java.lang.String whatsappMessage, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLeadKey() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClientName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMobileNo() {
        return null;
    }
    
    public final int getCallDuration() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.crm.v2.common.constants.StatusOption getSelectedStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallbackDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCallbackTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String isHLLAPBL() {
        return null;
    }
    
    public final boolean isCibilBad() {
        return false;
    }
    
    public final boolean isRealEstate() {
        return false;
    }
    
    public final boolean getAddDailyLogin() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLosNo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppNo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBanks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRegion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLoginAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRemarks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWhatsappMessage() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public UpdateStatusUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final boolean component20() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.crm.v2.common.constants.StatusOption component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.presentation.ui.dialer.UpdateStatusUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String leadKey, @org.jetbrains.annotations.NotNull()
    java.lang.String clientName, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, int callDuration, @org.jetbrains.annotations.Nullable()
    com.crm.v2.common.constants.StatusOption selectedStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String callbackDate, @org.jetbrains.annotations.NotNull()
    java.lang.String callbackTime, @org.jetbrains.annotations.NotNull()
    java.lang.String isHLLAPBL, boolean isCibilBad, boolean isRealEstate, boolean addDailyLogin, @org.jetbrains.annotations.NotNull()
    java.lang.String losNo, @org.jetbrains.annotations.NotNull()
    java.lang.String appNo, @org.jetbrains.annotations.NotNull()
    java.lang.String banks, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String region, @org.jetbrains.annotations.NotNull()
    java.lang.String loginAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String remarks, @org.jetbrains.annotations.NotNull()
    java.lang.String whatsappMessage, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}