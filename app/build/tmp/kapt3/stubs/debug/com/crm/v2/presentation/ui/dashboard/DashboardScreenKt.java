package com.crm.v2.presentation.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\u001aL\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a6\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001aJ\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0010\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0003\u001a*\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a\"\u0010!\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u0003\u001a\"\u0010\"\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u0003\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%H\u0003\u001a\u0010\u0010&\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"BentoActionCard", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "subtitle", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "accentColor", "Landroidx/compose/ui/graphics/Color;", "BentoActionCard-kKL39v8", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;J)V", "BigStatCard", "value", "BigStatCard-g2O1Hgs", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;J)V", "DashboardScreen", "onNavigateToDialer", "onNavigateToLeads", "onNavigateToFollowUps", "onNavigateToReports", "viewModel", "Lcom/crm/v2/presentation/ui/dashboard/DashboardViewModel;", "LiveCountBentoSection", "liveCount", "Lcom/crm/v2/domain/model/LiveCount;", "LiveStatItem", "color", "LiveStatItem-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "MediumStatCard", "SmallStatCard", "StatsBentoSection", "data", "Lcom/crm/v2/domain/model/DashboardData;", "formatAmount", "amount", "", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToDialer, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToLeads, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToFollowUps, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToReports, @org.jetbrains.annotations.NotNull()
    com.crm.v2.presentation.ui.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatsBentoSection(com.crm.v2.domain.model.DashboardData data) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SmallStatCard(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MediumStatCard(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LiveCountBentoSection(com.crm.v2.domain.model.LiveCount liveCount) {
    }
    
    private static final java.lang.String formatAmount(double amount) {
        return null;
    }
}