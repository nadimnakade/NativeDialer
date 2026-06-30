package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\b\u0010\u000b\u001a\u00020\u0001H\u0003\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0005H\u0003\u001a:\u0010\u000e\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u001ar\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00112\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u00a8\u0006 "}, d2 = {"AutoDialTab", "", "isAutoDialing", "", "currentLead", "Lcom/crm/v2/domain/model/DialerRecord;", "queue", "", "onStart", "Lkotlin/Function0;", "onStop", "ContactTab", "DialerLeadCard", "lead", "DialerScreen", "onActiveCall", "Lkotlin/Function1;", "", "onUpdateStatus", "viewModel", "Lcom/crm/v2/presentation/ui/dialer/DialerViewModel;", "ManualDialTab", "phoneNumber", "error", "isLoading", "callState", "Lcom/crm/v2/telephony/CallState;", "lastCallMessage", "onNumberChanged", "onCall", "onHangup", "onClearError", "app_debug"})
public final class DialerScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DialerScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onActiveCall, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onUpdateStatus, @org.jetbrains.annotations.NotNull()
    com.crm.v2.presentation.ui.dialer.DialerViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AutoDialTab(boolean isAutoDialing, com.crm.v2.domain.model.DialerRecord currentLead, java.util.List<com.crm.v2.domain.model.DialerRecord> queue, kotlin.jvm.functions.Function0<kotlin.Unit> onStart, kotlin.jvm.functions.Function0<kotlin.Unit> onStop) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DialerLeadCard(com.crm.v2.domain.model.DialerRecord lead) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ManualDialTab(java.lang.String phoneNumber, java.lang.String error, boolean isLoading, com.crm.v2.telephony.CallState callState, java.lang.String lastCallMessage, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNumberChanged, kotlin.jvm.functions.Function0<kotlin.Unit> onCall, kotlin.jvm.functions.Function0<kotlin.Unit> onHangup, kotlin.jvm.functions.Function0<kotlin.Unit> onClearError) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ContactTab() {
    }
}