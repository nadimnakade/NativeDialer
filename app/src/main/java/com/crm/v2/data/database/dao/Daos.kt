package com.crm.v2.data.database.dao

import androidx.room.*
import com.crm.v2.data.database.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LeadDao {
    @Query("SELECT * FROM leads WHERE userKey = :userKey ORDER BY cachedAt DESC")
    fun getLeads(userKey: String): Flow<List<LeadEntity>>

    @Query("SELECT * FROM leads WHERE key = :key")
    suspend fun getLeadByKey(key: String): LeadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeads(leads: List<LeadEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLead(lead: LeadEntity): Long

    @Query("DELETE FROM leads WHERE userKey = :userKey")
    suspend fun deleteLeadsForUser(userKey: String): Int
}

@Dao
interface CallLogDao {
    @Query("SELECT * FROM call_logs WHERE userKey = :userKey ORDER BY callDate DESC LIMIT :limit")
    fun getCallLogs(userKey: String, limit: Int = 100): Flow<List<CallLogEntity>>

    @Query("SELECT * FROM call_logs WHERE synced = 0")
    suspend fun getUnsyncedCallLogs(): List<CallLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCallLogs(logs: List<CallLogEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCallLog(log: CallLogEntity): Long

    @Query("UPDATE call_logs SET synced = 1 WHERE id IN (:ids)")
    suspend fun markAsSynced(ids: List<Long>): Int

    @Query("DELETE FROM call_logs WHERE synced = 1 AND cachedAt < :before")
    suspend fun deleteOldSyncedLogs(before: Long): Int
}

@Dao
interface DialerDataDao {
    @Query("SELECT * FROM dialer_data WHERE userKey = :userKey ORDER BY cachedAt DESC")
    fun getDialerData(userKey: String): Flow<List<DialerDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDialerData(data: List<DialerDataEntity>): List<Long>

    @Query("DELETE FROM dialer_data WHERE userKey = :userKey")
    suspend fun deleteForUser(userKey: String): Int

    @Query("DELETE FROM dialer_data WHERE key = :key")
    suspend fun deleteByKey(key: String): Int
}

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE name LIKE '%' || :query || '%' OR phoneNumbers LIKE '%' || :query || '%'")
    fun searchContacts(query: String): Flow<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<ContactEntity>): List<Long>

    @Query("DELETE FROM contacts")
    suspend fun deleteAll(): Int
}

@Dao
interface SyncQueueDao {
    @Query("SELECT * FROM sync_queue ORDER BY createdAt ASC")
    suspend fun getPendingItems(): List<SyncQueueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SyncQueueEntity): Long

    @Query("DELETE FROM sync_queue WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query("UPDATE sync_queue SET retryCount = retryCount + 1 WHERE id = :id")
    suspend fun incrementRetry(id: Long): Int

    @Query("DELETE FROM sync_queue WHERE retryCount > :maxRetries")
    suspend fun deleteFailedItems(maxRetries: Int = 5): Int
}
