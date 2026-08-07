package com.crm.v2.presentation.ui.callhistory;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/crm/v2/presentation/ui/callhistory/CallHistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "callLogDao", "Lcom/crm/v2/data/database/dao/CallLogDao;", "(Lcom/crm/v2/data/database/dao/CallLogDao;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/callhistory/CallHistoryUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CallHistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.database.dao.CallLogDao callLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.presentation.ui.callhistory.CallHistoryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.callhistory.CallHistoryUiState> uiState = null;
    
    @javax.inject.Inject()
    public CallHistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.dao.CallLogDao callLogDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.callhistory.CallHistoryUiState> getUiState() {
        return null;
    }
}