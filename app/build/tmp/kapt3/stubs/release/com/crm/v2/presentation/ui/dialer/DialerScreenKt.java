package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\f\n\u0000\u001aN\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u001e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a8\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a2\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0003\u001a\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0003\u001a \u0010\u001d\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a@\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001aF\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00112\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a&\u0010&\u001a\u00020\u00012\u0006\u0010\'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u00172\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0010\u0010)\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020*H\u0002\u00a8\u0006+"}, d2 = {"AutoDialTab", "", "uiState", "Lcom/crm/v2/presentation/ui/dialer/DialerUiState;", "onStart", "Lkotlin/Function0;", "onStop", "onCallLead", "Lkotlin/Function1;", "Lcom/crm/v2/domain/model/DialerRecord;", "onRefresh", "ContactItem", "contact", "Lcom/crm/v2/domain/model/Contact;", "onClick", "ContactTab", "onSearchChanged", "", "onCallContact", "DialKey", "key", "onPress", "isBackspace", "", "modifier", "Landroidx/compose/ui/Modifier;", "DialerMainContent", "viewModel", "Lcom/crm/v2/presentation/ui/dialer/DialerViewModel;", "DialerScreen", "onNavigateBack", "LeadTab", "onLoadMore", "ManualDialTab", "phoneNumber", "onNumberChanged", "onClear", "onCall", "QueueLeadCard", "lead", "isCurrent", "manualDialLetters", "", "app_release"})
public final class DialerScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DialerScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    com.crm.v2.presentation.ui.dialer.DialerViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void DialerMainContent(com.crm.v2.presentation.ui.dialer.DialerUiState uiState, com.crm.v2.presentation.ui.dialer.DialerViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AutoDialTab(com.crm.v2.presentation.ui.dialer.DialerUiState uiState, kotlin.jvm.functions.Function0<kotlin.Unit> onStart, kotlin.jvm.functions.Function0<kotlin.Unit> onStop, kotlin.jvm.functions.Function1<? super com.crm.v2.domain.model.DialerRecord, kotlin.Unit> onCallLead, kotlin.jvm.functions.Function0<kotlin.Unit> onRefresh) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QueueLeadCard(com.crm.v2.domain.model.DialerRecord lead, boolean isCurrent, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void LeadTab(com.crm.v2.presentation.ui.dialer.DialerUiState uiState, kotlin.jvm.functions.Function1<? super com.crm.v2.domain.model.DialerRecord, kotlin.Unit> onCallLead, kotlin.jvm.functions.Function0<kotlin.Unit> onRefresh, kotlin.jvm.functions.Function0<kotlin.Unit> onLoadMore) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ContactTab(com.crm.v2.presentation.ui.dialer.DialerUiState uiState, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSearchChanged, kotlin.jvm.functions.Function1<? super com.crm.v2.domain.model.Contact, kotlin.Unit> onCallContact) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ContactItem(com.crm.v2.domain.model.Contact contact, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ManualDialTab(java.lang.String phoneNumber, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNumberChanged, kotlin.jvm.functions.Function0<kotlin.Unit> onClear, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCall) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void DialKey(java.lang.String key, kotlin.jvm.functions.Function0<kotlin.Unit> onPress, boolean isBackspace, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String manualDialLetters(char key) {
        return null;
    }
}