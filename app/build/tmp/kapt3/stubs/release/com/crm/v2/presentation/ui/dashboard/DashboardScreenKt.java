package com.crm.v2.presentation.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\u001aJ\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0010\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0003\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0003\u001a0\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0003\u001a2\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u0010\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001dH\u0003\u001a\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0003\u001a*\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\b\u0010%\u001a\u00020\u0001H\u0003\u001a\u0010\u0010&\u001a\u00020\u00132\u0006\u0010\'\u001a\u00020(H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"DashboardScreen", "", "onNavigateToDialer", "Lkotlin/Function0;", "onNavigateToLeads", "onNavigateToFollowUps", "onNavigateToReports", "viewModel", "Lcom/crm/v2/presentation/ui/dashboard/DashboardViewModel;", "DisbursalBreakdownSection", "data", "Lcom/crm/v2/domain/model/DashboardData;", "LiveCountSection", "liveCount", "Lcom/crm/v2/domain/model/LiveCount;", "QuickActionCard", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "onClick", "modifier", "Landroidx/compose/ui/Modifier;", "QuickActionsRow", "onDialer", "onLeads", "onFollowUps", "StatCardRow", "stat", "Lcom/crm/v2/presentation/ui/dashboard/StatItem;", "StatsGridSection", "StatusPill", "value", "color", "Landroidx/compose/ui/graphics/Color;", "StatusPill-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "WelcomeSection", "formatAmount", "amount", "", "app_release"})
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
    private static final void WelcomeSection() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuickActionsRow(kotlin.jvm.functions.Function0<kotlin.Unit> onDialer, kotlin.jvm.functions.Function0<kotlin.Unit> onLeads, kotlin.jvm.functions.Function0<kotlin.Unit> onFollowUps) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuickActionCard(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatsGridSection(com.crm.v2.domain.model.DashboardData data) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatCardRow(com.crm.v2.presentation.ui.dashboard.StatItem stat) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LiveCountSection(com.crm.v2.domain.model.LiveCount liveCount) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DisbursalBreakdownSection(com.crm.v2.domain.model.DashboardData data) {
    }
    
    private static final java.lang.String formatAmount(double amount) {
        return null;
    }
}