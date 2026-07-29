package com.crm.v2;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.crm.v2.data.database.AppDatabase;
import com.crm.v2.data.database.dao.CallLogDao;
import com.crm.v2.data.database.dao.ContactDao;
import com.crm.v2.data.database.dao.LeadDao;
import com.crm.v2.data.database.dao.SyncQueueDao;
import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.data.datastore.SettingsDataStore;
import com.crm.v2.data.network.api.AuthApi;
import com.crm.v2.data.network.api.PickupApi;
import com.crm.v2.data.network.interceptor.AuthInterceptor;
import com.crm.v2.data.repository.AuthRepositoryImpl;
import com.crm.v2.data.repository.ContactRepositoryImpl;
import com.crm.v2.data.repository.DailyLoginRepositoryImpl;
import com.crm.v2.data.repository.DialerRepositoryImpl;
import com.crm.v2.data.repository.DocumentRepositoryImpl;
import com.crm.v2.data.repository.LeadRepositoryImpl;
import com.crm.v2.data.repository.PickupRepositoryImpl;
import com.crm.v2.data.repository.ReportingRepositoryImpl;
import com.crm.v2.data.repository.SettingsRepositoryImpl;
import com.crm.v2.di.AppModule_ProvideAuthDataStoreFactory;
import com.crm.v2.di.AppModule_ProvideSettingsDataStoreFactory;
import com.crm.v2.di.DatabaseModule_ProvideAppDatabaseFactory;
import com.crm.v2.di.DatabaseModule_ProvideCallLogDaoFactory;
import com.crm.v2.di.DatabaseModule_ProvideContactDaoFactory;
import com.crm.v2.di.DatabaseModule_ProvideLeadDaoFactory;
import com.crm.v2.di.DatabaseModule_ProvideSyncQueueDaoFactory;
import com.crm.v2.di.NetworkModule_ProvideAuthApiFactory;
import com.crm.v2.di.NetworkModule_ProvideOkHttpClientFactory;
import com.crm.v2.di.NetworkModule_ProvidePickupApiFactory;
import com.crm.v2.di.NetworkModule_ProvideRetrofitFactory;
import com.crm.v2.domain.usecase.auth.LoginUseCase;
import com.crm.v2.domain.usecase.auth.LogoutUseCase;
import com.crm.v2.presentation.MainActivity;
import com.crm.v2.presentation.MainActivity_MembersInjector;
import com.crm.v2.presentation.ui.agentstatus.AgentStatusViewModel;
import com.crm.v2.presentation.ui.agentstatus.AgentStatusViewModel_HiltModules;
import com.crm.v2.presentation.ui.agentstatus.AgentStatusViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.agentstatus.AgentStatusViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.auth.LoginViewModel;
import com.crm.v2.presentation.ui.auth.LoginViewModel_HiltModules;
import com.crm.v2.presentation.ui.auth.LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.auth.LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.callhistory.CallHistoryViewModel;
import com.crm.v2.presentation.ui.callhistory.CallHistoryViewModel_HiltModules;
import com.crm.v2.presentation.ui.callhistory.CallHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.callhistory.CallHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.callrecordings.CallRecordingsViewModel;
import com.crm.v2.presentation.ui.callrecordings.CallRecordingsViewModel_HiltModules;
import com.crm.v2.presentation.ui.callrecordings.CallRecordingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.callrecordings.CallRecordingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.contacts.ContactsViewModel;
import com.crm.v2.presentation.ui.contacts.ContactsViewModel_HiltModules;
import com.crm.v2.presentation.ui.contacts.ContactsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.contacts.ContactsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.dailylogin.DailyLoginViewModel;
import com.crm.v2.presentation.ui.dailylogin.DailyLoginViewModel_HiltModules;
import com.crm.v2.presentation.ui.dailylogin.DailyLoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.dailylogin.DailyLoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListViewModel;
import com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListViewModel_HiltModules;
import com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.dashboard.DashboardViewModel;
import com.crm.v2.presentation.ui.dashboard.DashboardViewModel_HiltModules;
import com.crm.v2.presentation.ui.dashboard.DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.dashboard.DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.dialer.DialerViewModel;
import com.crm.v2.presentation.ui.dialer.DialerViewModel_HiltModules;
import com.crm.v2.presentation.ui.dialer.DialerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.dialer.DialerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.documents.DocumentViewModel;
import com.crm.v2.presentation.ui.documents.DocumentViewModel_HiltModules;
import com.crm.v2.presentation.ui.documents.DocumentViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.documents.DocumentViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.documentupload.DocumentUploadViewModel;
import com.crm.v2.presentation.ui.documentupload.DocumentUploadViewModel_HiltModules;
import com.crm.v2.presentation.ui.documentupload.DocumentUploadViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.documentupload.DocumentUploadViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.followup.FollowUpViewModel;
import com.crm.v2.presentation.ui.followup.FollowUpViewModel_HiltModules;
import com.crm.v2.presentation.ui.followup.FollowUpViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.followup.FollowUpViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.leads.AddLeadViewModel;
import com.crm.v2.presentation.ui.leads.AddLeadViewModel_HiltModules;
import com.crm.v2.presentation.ui.leads.AddLeadViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.leads.AddLeadViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.leads.LeadDetailViewModel;
import com.crm.v2.presentation.ui.leads.LeadDetailViewModel_HiltModules;
import com.crm.v2.presentation.ui.leads.LeadDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.leads.LeadDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.leads.LeadListViewModel;
import com.crm.v2.presentation.ui.leads.LeadListViewModel_HiltModules;
import com.crm.v2.presentation.ui.leads.LeadListViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.leads.LeadListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.onemincalls.OneMinCallsViewModel;
import com.crm.v2.presentation.ui.onemincalls.OneMinCallsViewModel_HiltModules;
import com.crm.v2.presentation.ui.onemincalls.OneMinCallsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.onemincalls.OneMinCallsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.payslip.PayslipViewModel;
import com.crm.v2.presentation.ui.payslip.PayslipViewModel_HiltModules;
import com.crm.v2.presentation.ui.payslip.PayslipViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.payslip.PayslipViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.pickup.PickupViewModel;
import com.crm.v2.presentation.ui.pickup.PickupViewModel_HiltModules;
import com.crm.v2.presentation.ui.pickup.PickupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.pickup.PickupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.reports.ReportsViewModel;
import com.crm.v2.presentation.ui.reports.ReportsViewModel_HiltModules;
import com.crm.v2.presentation.ui.reports.ReportsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.reports.ReportsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.ringingcalls.RingingCallsViewModel;
import com.crm.v2.presentation.ui.ringingcalls.RingingCallsViewModel_HiltModules;
import com.crm.v2.presentation.ui.ringingcalls.RingingCallsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.ringingcalls.RingingCallsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.samedayfollowup.SameDayFollowUpViewModel;
import com.crm.v2.presentation.ui.samedayfollowup.SameDayFollowUpViewModel_HiltModules;
import com.crm.v2.presentation.ui.samedayfollowup.SameDayFollowUpViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.samedayfollowup.SameDayFollowUpViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsViewModel;
import com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsViewModel_HiltModules;
import com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.settings.SettingsViewModel;
import com.crm.v2.presentation.ui.settings.SettingsViewModel_HiltModules;
import com.crm.v2.presentation.ui.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.presentation.ui.whistleblower.WhistleblowerViewModel;
import com.crm.v2.presentation.ui.whistleblower.WhistleblowerViewModel_HiltModules;
import com.crm.v2.presentation.ui.whistleblower.WhistleblowerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.crm.v2.presentation.ui.whistleblower.WhistleblowerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.crm.v2.services.CrmFirebaseMessagingService;
import com.crm.v2.services.CrmFirebaseMessagingService_MembersInjector;
import com.crm.v2.services.ForegroundCallService;
import com.crm.v2.services.ForegroundCallService_MembersInjector;
import com.crm.v2.services.SyncWorker;
import com.crm.v2.services.SyncWorker_AssistedFactory;
import com.crm.v2.telephony.TestCallActivity;
import com.crm.v2.telephony.TestCallActivity_MembersInjector;
import com.crm.v2.telephony.provider.PjsipTelephonyProvider;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DaggerCrmApplication_HiltComponents_SingletonC {
  private DaggerCrmApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public CrmApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements CrmApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements CrmApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements CrmApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements CrmApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements CrmApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements CrmApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements CrmApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public CrmApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends CrmApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends CrmApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends CrmApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends CrmApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public void injectTestCallActivity(TestCallActivity testCallActivity) {
      injectTestCallActivity2(testCallActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(24).put(AddLeadViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AddLeadViewModel_HiltModules.KeyModule.provide()).put(AgentStatusViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AgentStatusViewModel_HiltModules.KeyModule.provide()).put(CallHistoryViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CallHistoryViewModel_HiltModules.KeyModule.provide()).put(CallRecordingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CallRecordingsViewModel_HiltModules.KeyModule.provide()).put(ContactsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ContactsViewModel_HiltModules.KeyModule.provide()).put(DailyLoginListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DailyLoginListViewModel_HiltModules.KeyModule.provide()).put(DailyLoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DailyLoginViewModel_HiltModules.KeyModule.provide()).put(DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DashboardViewModel_HiltModules.KeyModule.provide()).put(DialerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DialerViewModel_HiltModules.KeyModule.provide()).put(DocumentUploadViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DocumentUploadViewModel_HiltModules.KeyModule.provide()).put(DocumentViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DocumentViewModel_HiltModules.KeyModule.provide()).put(FollowUpViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FollowUpViewModel_HiltModules.KeyModule.provide()).put(LeadDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LeadDetailViewModel_HiltModules.KeyModule.provide()).put(LeadListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LeadListViewModel_HiltModules.KeyModule.provide()).put(LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LoginViewModel_HiltModules.KeyModule.provide()).put(OneMinCallsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, OneMinCallsViewModel_HiltModules.KeyModule.provide()).put(PayslipViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PayslipViewModel_HiltModules.KeyModule.provide()).put(PickupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PickupViewModel_HiltModules.KeyModule.provide()).put(ReportsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ReportsViewModel_HiltModules.KeyModule.provide()).put(RingingCallsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, RingingCallsViewModel_HiltModules.KeyModule.provide()).put(SameDayFollowUpViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SameDayFollowUpViewModel_HiltModules.KeyModule.provide()).put(SameDayFollowUpsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SameDayFollowUpsViewModel_HiltModules.KeyModule.provide()).put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide()).put(WhistleblowerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, WhistleblowerViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectAuthDataStore(instance, singletonCImpl.provideAuthDataStoreProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private TestCallActivity injectTestCallActivity2(TestCallActivity instance2) {
      TestCallActivity_MembersInjector.injectTelephonyProvider(instance2, singletonCImpl.pjsipTelephonyProvider.get());
      TestCallActivity_MembersInjector.injectAuthDataStore(instance2, singletonCImpl.provideAuthDataStoreProvider.get());
      return instance2;
    }
  }

  private static final class ViewModelCImpl extends CrmApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddLeadViewModel> addLeadViewModelProvider;

    private Provider<AgentStatusViewModel> agentStatusViewModelProvider;

    private Provider<CallHistoryViewModel> callHistoryViewModelProvider;

    private Provider<CallRecordingsViewModel> callRecordingsViewModelProvider;

    private Provider<ContactsViewModel> contactsViewModelProvider;

    private Provider<DailyLoginListViewModel> dailyLoginListViewModelProvider;

    private Provider<DailyLoginViewModel> dailyLoginViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DialerViewModel> dialerViewModelProvider;

    private Provider<DocumentUploadViewModel> documentUploadViewModelProvider;

    private Provider<DocumentViewModel> documentViewModelProvider;

    private Provider<FollowUpViewModel> followUpViewModelProvider;

    private Provider<LeadDetailViewModel> leadDetailViewModelProvider;

    private Provider<LeadListViewModel> leadListViewModelProvider;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<OneMinCallsViewModel> oneMinCallsViewModelProvider;

    private Provider<PayslipViewModel> payslipViewModelProvider;

    private Provider<PickupViewModel> pickupViewModelProvider;

    private Provider<ReportsViewModel> reportsViewModelProvider;

    private Provider<RingingCallsViewModel> ringingCallsViewModelProvider;

    private Provider<SameDayFollowUpViewModel> sameDayFollowUpViewModelProvider;

    private Provider<SameDayFollowUpsViewModel> sameDayFollowUpsViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<WhistleblowerViewModel> whistleblowerViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private LoginUseCase loginUseCase() {
      return new LoginUseCase(singletonCImpl.authRepositoryImplProvider.get());
    }

    private LogoutUseCase logoutUseCase() {
      return new LogoutUseCase(singletonCImpl.authRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addLeadViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.agentStatusViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.callHistoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.callRecordingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.contactsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.dailyLoginListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.dailyLoginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.dialerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.documentUploadViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.documentViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.followUpViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.leadDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.leadListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.oneMinCallsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
      this.payslipViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
      this.pickupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17);
      this.reportsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 18);
      this.ringingCallsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 19);
      this.sameDayFollowUpViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 20);
      this.sameDayFollowUpsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 21);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 22);
      this.whistleblowerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 23);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(24).put(AddLeadViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) addLeadViewModelProvider)).put(AgentStatusViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) agentStatusViewModelProvider)).put(CallHistoryViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) callHistoryViewModelProvider)).put(CallRecordingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) callRecordingsViewModelProvider)).put(ContactsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) contactsViewModelProvider)).put(DailyLoginListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) dailyLoginListViewModelProvider)).put(DailyLoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) dailyLoginViewModelProvider)).put(DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) dashboardViewModelProvider)).put(DialerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) dialerViewModelProvider)).put(DocumentUploadViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) documentUploadViewModelProvider)).put(DocumentViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) documentViewModelProvider)).put(FollowUpViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) followUpViewModelProvider)).put(LeadDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) leadDetailViewModelProvider)).put(LeadListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) leadListViewModelProvider)).put(LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) loginViewModelProvider)).put(OneMinCallsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) oneMinCallsViewModelProvider)).put(PayslipViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) payslipViewModelProvider)).put(PickupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) pickupViewModelProvider)).put(ReportsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) reportsViewModelProvider)).put(RingingCallsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) ringingCallsViewModelProvider)).put(SameDayFollowUpViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) sameDayFollowUpViewModelProvider)).put(SameDayFollowUpsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) sameDayFollowUpsViewModelProvider)).put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) settingsViewModelProvider)).put(WhistleblowerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) whistleblowerViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.crm.v2.presentation.ui.leads.AddLeadViewModel 
          return (T) new AddLeadViewModel(singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 1: // com.crm.v2.presentation.ui.agentstatus.AgentStatusViewModel 
          return (T) new AgentStatusViewModel(singletonCImpl.reportingRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 2: // com.crm.v2.presentation.ui.callhistory.CallHistoryViewModel 
          return (T) new CallHistoryViewModel(singletonCImpl.callLogDao());

          case 3: // com.crm.v2.presentation.ui.callrecordings.CallRecordingsViewModel 
          return (T) new CallRecordingsViewModel(singletonCImpl.documentRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 4: // com.crm.v2.presentation.ui.contacts.ContactsViewModel 
          return (T) new ContactsViewModel(singletonCImpl.contactRepositoryImplProvider.get());

          case 5: // com.crm.v2.presentation.ui.dailyloginlist.DailyLoginListViewModel 
          return (T) new DailyLoginListViewModel(singletonCImpl.dailyLoginRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 6: // com.crm.v2.presentation.ui.dailylogin.DailyLoginViewModel 
          return (T) new DailyLoginViewModel(singletonCImpl.dailyLoginRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 7: // com.crm.v2.presentation.ui.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.reportingRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 8: // com.crm.v2.presentation.ui.dialer.DialerViewModel 
          return (T) new DialerViewModel(singletonCImpl.dialerRepositoryImplProvider.get(), singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.contactRepositoryImplProvider.get(), singletonCImpl.pjsipTelephonyProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 9: // com.crm.v2.presentation.ui.documentupload.DocumentUploadViewModel 
          return (T) new DocumentUploadViewModel(singletonCImpl.documentRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get(), ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 10: // com.crm.v2.presentation.ui.documents.DocumentViewModel 
          return (T) new DocumentViewModel(singletonCImpl.documentRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 11: // com.crm.v2.presentation.ui.followup.FollowUpViewModel 
          return (T) new FollowUpViewModel(singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 12: // com.crm.v2.presentation.ui.leads.LeadDetailViewModel 
          return (T) new LeadDetailViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 13: // com.crm.v2.presentation.ui.leads.LeadListViewModel 
          return (T) new LeadListViewModel(singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 14: // com.crm.v2.presentation.ui.auth.LoginViewModel 
          return (T) new LoginViewModel(viewModelCImpl.loginUseCase(), viewModelCImpl.logoutUseCase());

          case 15: // com.crm.v2.presentation.ui.onemincalls.OneMinCallsViewModel 
          return (T) new OneMinCallsViewModel(singletonCImpl.reportingRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 16: // com.crm.v2.presentation.ui.payslip.PayslipViewModel 
          return (T) new PayslipViewModel(singletonCImpl.documentRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 17: // com.crm.v2.presentation.ui.pickup.PickupViewModel 
          return (T) new PickupViewModel(singletonCImpl.pickupRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 18: // com.crm.v2.presentation.ui.reports.ReportsViewModel 
          return (T) new ReportsViewModel(singletonCImpl.reportingRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 19: // com.crm.v2.presentation.ui.ringingcalls.RingingCallsViewModel 
          return (T) new RingingCallsViewModel(singletonCImpl.dialerRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 20: // com.crm.v2.presentation.ui.samedayfollowup.SameDayFollowUpViewModel 
          return (T) new SameDayFollowUpViewModel(singletonCImpl.leadRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 21: // com.crm.v2.presentation.ui.samedayfollowups.SameDayFollowUpsViewModel 
          return (T) new SameDayFollowUpsViewModel(singletonCImpl.dialerRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 22: // com.crm.v2.presentation.ui.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.provideSettingsDataStoreProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get(), singletonCImpl.authRepositoryImplProvider.get());

          case 23: // com.crm.v2.presentation.ui.whistleblower.WhistleblowerViewModel 
          return (T) new WhistleblowerViewModel(singletonCImpl.settingsRepositoryImplProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends CrmApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends CrmApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectCrmFirebaseMessagingService(
        CrmFirebaseMessagingService crmFirebaseMessagingService) {
      injectCrmFirebaseMessagingService2(crmFirebaseMessagingService);
    }

    @Override
    public void injectForegroundCallService(ForegroundCallService foregroundCallService) {
      injectForegroundCallService2(foregroundCallService);
    }

    @CanIgnoreReturnValue
    private CrmFirebaseMessagingService injectCrmFirebaseMessagingService2(
        CrmFirebaseMessagingService instance) {
      CrmFirebaseMessagingService_MembersInjector.injectAuthDataStore(instance, singletonCImpl.provideAuthDataStoreProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private ForegroundCallService injectForegroundCallService2(ForegroundCallService instance2) {
      ForegroundCallService_MembersInjector.injectTelephonyManager(instance2, singletonCImpl.pjsipTelephonyProvider.get());
      return instance2;
    }
  }

  private static final class SingletonCImpl extends CrmApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<AuthDataStore> provideAuthDataStoreProvider;

    private Provider<AuthInterceptor> authInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<PickupApi> providePickupApiProvider;

    private Provider<SyncWorker_AssistedFactory> syncWorker_AssistedFactoryProvider;

    private Provider<PjsipTelephonyProvider> pjsipTelephonyProvider;

    private Provider<LeadRepositoryImpl> leadRepositoryImplProvider;

    private Provider<ReportingRepositoryImpl> reportingRepositoryImplProvider;

    private Provider<DocumentRepositoryImpl> documentRepositoryImplProvider;

    private Provider<ContactRepositoryImpl> contactRepositoryImplProvider;

    private Provider<DailyLoginRepositoryImpl> dailyLoginRepositoryImplProvider;

    private Provider<DialerRepositoryImpl> dialerRepositoryImplProvider;

    private Provider<AuthApi> provideAuthApiProvider;

    private Provider<AuthRepositoryImpl> authRepositoryImplProvider;

    private Provider<PickupRepositoryImpl> pickupRepositoryImplProvider;

    private Provider<SettingsDataStore> provideSettingsDataStoreProvider;

    private Provider<SettingsRepositoryImpl> settingsRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private CallLogDao callLogDao() {
      return DatabaseModule_ProvideCallLogDaoFactory.provideCallLogDao(provideAppDatabaseProvider.get());
    }

    private SyncQueueDao syncQueueDao() {
      return DatabaseModule_ProvideSyncQueueDaoFactory.provideSyncQueueDao(provideAppDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return ImmutableMap.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>of("com.crm.v2.services.SyncWorker", ((Provider) syncWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private LeadDao leadDao() {
      return DatabaseModule_ProvideLeadDaoFactory.provideLeadDao(provideAppDatabaseProvider.get());
    }

    private ContactDao contactDao() {
      return DatabaseModule_ProvideContactDaoFactory.provideContactDao(provideAppDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 1));
      this.provideAuthDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<AuthDataStore>(singletonCImpl, 6));
      this.authInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 5));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 4));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 3));
      this.providePickupApiProvider = DoubleCheck.provider(new SwitchingProvider<PickupApi>(singletonCImpl, 2));
      this.syncWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SyncWorker_AssistedFactory>(singletonCImpl, 0));
      this.pjsipTelephonyProvider = DoubleCheck.provider(new SwitchingProvider<PjsipTelephonyProvider>(singletonCImpl, 7));
      this.leadRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<LeadRepositoryImpl>(singletonCImpl, 8));
      this.reportingRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ReportingRepositoryImpl>(singletonCImpl, 9));
      this.documentRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<DocumentRepositoryImpl>(singletonCImpl, 10));
      this.contactRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ContactRepositoryImpl>(singletonCImpl, 11));
      this.dailyLoginRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<DailyLoginRepositoryImpl>(singletonCImpl, 12));
      this.dialerRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<DialerRepositoryImpl>(singletonCImpl, 13));
      this.provideAuthApiProvider = DoubleCheck.provider(new SwitchingProvider<AuthApi>(singletonCImpl, 15));
      this.authRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepositoryImpl>(singletonCImpl, 14));
      this.pickupRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PickupRepositoryImpl>(singletonCImpl, 16));
      this.provideSettingsDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<SettingsDataStore>(singletonCImpl, 17));
      this.settingsRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<SettingsRepositoryImpl>(singletonCImpl, 18));
    }

    @Override
    public void injectCrmApplication(CrmApplication crmApplication) {
      injectCrmApplication2(crmApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private CrmApplication injectCrmApplication2(CrmApplication instance) {
      CrmApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.crm.v2.services.SyncWorker_AssistedFactory 
          return (T) new SyncWorker_AssistedFactory() {
            @Override
            public SyncWorker create(Context appContext, WorkerParameters workerParams) {
              return new SyncWorker(appContext, workerParams, singletonCImpl.callLogDao(), singletonCImpl.syncQueueDao(), singletonCImpl.providePickupApiProvider.get());
            }
          };

          case 1: // com.crm.v2.data.database.AppDatabase 
          return (T) DatabaseModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.crm.v2.data.network.api.PickupApi 
          return (T) NetworkModule_ProvidePickupApiFactory.providePickupApi(singletonCImpl.provideRetrofitProvider.get());

          case 3: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 4: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.authInterceptorProvider.get());

          case 5: // com.crm.v2.data.network.interceptor.AuthInterceptor 
          return (T) new AuthInterceptor(singletonCImpl.provideAuthDataStoreProvider.get());

          case 6: // com.crm.v2.data.datastore.AuthDataStore 
          return (T) AppModule_ProvideAuthDataStoreFactory.provideAuthDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.crm.v2.telephony.provider.PjsipTelephonyProvider 
          return (T) new PjsipTelephonyProvider(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 8: // com.crm.v2.data.repository.LeadRepositoryImpl 
          return (T) new LeadRepositoryImpl(singletonCImpl.providePickupApiProvider.get(), singletonCImpl.leadDao());

          case 9: // com.crm.v2.data.repository.ReportingRepositoryImpl 
          return (T) new ReportingRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          case 10: // com.crm.v2.data.repository.DocumentRepositoryImpl 
          return (T) new DocumentRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          case 11: // com.crm.v2.data.repository.ContactRepositoryImpl 
          return (T) new ContactRepositoryImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.contactDao());

          case 12: // com.crm.v2.data.repository.DailyLoginRepositoryImpl 
          return (T) new DailyLoginRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          case 13: // com.crm.v2.data.repository.DialerRepositoryImpl 
          return (T) new DialerRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          case 14: // com.crm.v2.data.repository.AuthRepositoryImpl 
          return (T) new AuthRepositoryImpl(singletonCImpl.provideAuthApiProvider.get(), singletonCImpl.provideAuthDataStoreProvider.get());

          case 15: // com.crm.v2.data.network.api.AuthApi 
          return (T) NetworkModule_ProvideAuthApiFactory.provideAuthApi(singletonCImpl.provideRetrofitProvider.get());

          case 16: // com.crm.v2.data.repository.PickupRepositoryImpl 
          return (T) new PickupRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          case 17: // com.crm.v2.data.datastore.SettingsDataStore 
          return (T) AppModule_ProvideSettingsDataStoreFactory.provideSettingsDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 18: // com.crm.v2.data.repository.SettingsRepositoryImpl 
          return (T) new SettingsRepositoryImpl(singletonCImpl.providePickupApiProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
