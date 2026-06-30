package com.crm.v2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crm.v2.data.database.dao.*
import com.crm.v2.data.database.entity.*

@Database(
    entities = [
        LeadEntity::class,
        CallLogEntity::class,
        DialerDataEntity::class,
        ContactEntity::class,
        SyncQueueEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leadDao(): LeadDao
    abstract fun callLogDao(): CallLogDao
    abstract fun dialerDataDao(): DialerDataDao
    abstract fun contactDao(): ContactDao
    abstract fun syncQueueDao(): SyncQueueDao
}
