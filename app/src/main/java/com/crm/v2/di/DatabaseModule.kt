package com.crm.v2.di

import android.content.Context
import androidx.room.Room
import com.crm.v2.data.database.AppDatabase
import com.crm.v2.data.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "crm_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides fun provideLeadDao(db: AppDatabase): LeadDao = db.leadDao()
    @Provides fun provideCallLogDao(db: AppDatabase): CallLogDao = db.callLogDao()
    @Provides fun provideDialerDataDao(db: AppDatabase): DialerDataDao = db.dialerDataDao()
    @Provides fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()
    @Provides fun provideSyncQueueDao(db: AppDatabase): SyncQueueDao = db.syncQueueDao()
}
