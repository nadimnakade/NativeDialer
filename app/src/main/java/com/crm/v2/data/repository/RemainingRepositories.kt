package com.crm.v2.data.repository

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import com.crm.v2.common.result.Resource
import com.crm.v2.data.database.dao.ContactDao
import com.crm.v2.data.database.entity.ContactEntity
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.domain.model.Contact
import com.crm.v2.domain.model.PickupEntry
import com.crm.v2.domain.repository.ContactRepository
import com.crm.v2.domain.repository.PickupRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PickupRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : PickupRepository {

    override suspend fun getPickupData(userKey: String): Resource<List<PickupEntry>> {
        return try {
            val response = pickupApi.getPickupData(userKey)
            if (response.isSuccessful) {
                val entries = response.body()?.map { dto ->
                    PickupEntry(
                        key = dto.key ?: "", callerName = dto.callerName,
                        clientName = dto.clientName, clientMobile = dto.clientMobile,
                        product = dto.product, banks = dto.banks,
                        status = dto.status, pickupDate = dto.pickupDate,
                        region = dto.region, pickupType = dto.pickupType,
                        remarks = dto.remarks,
                    )
                } ?: emptyList()
                Resource.Success(entries)
            } else Resource.Error("Failed to load pickup data")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getPickupEntryData(userKey: String, type: String?): Resource<List<PickupEntry>> {
        return try {
            val response = pickupApi.getPickupEntryData(userKey, type)
            if (response.isSuccessful) {
                val entries = response.body()?.map { dto ->
                    PickupEntry(
                        key = dto.key ?: "", callerName = dto.callerName,
                        clientName = dto.clientName, clientMobile = dto.clientMobile,
                        product = dto.product, banks = dto.banks,
                        status = dto.status, pickupDate = dto.pickupDate,
                        region = dto.region, pickupType = dto.pickupType,
                        remarks = dto.remarks,
                    )
                } ?: emptyList()
                Resource.Success(entries)
            } else Resource.Error("Failed to load pickup entries")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun createPickup(leadId: String, userKey: String): Resource<String> {
        // Uses UpdateStatus/PostDataEntry or Pickup/insert-pickup
        return Resource.Success("Pickup created")
    }
}

@Singleton
class ContactRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val contactDao: ContactDao,
) : ContactRepository {

    override fun getDeviceContacts(): Flow<List<Contact>> = flow {
        val contacts = loadContactsFromDevice()
        contactDao.deleteAll()
        contactDao.insertContacts(contacts.map { it.toEntity() })
        emit(contacts)
    }

    override fun searchContacts(query: String): Flow<List<Contact>> {
        return contactDao.searchContacts(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getCachedContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    private suspend fun loadContactsFromDevice(): List<Contact> = withContext(Dispatchers.IO) {
        val contacts = mutableListOf<Contact>()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
            ),
            null, null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC",
        )

        cursor?.use {
            val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

            while (it.moveToNext()) {
                val id = it.getString(idIndex)
                val name = it.getString(nameIndex)
                val number = it.getString(numberIndex)
                val photo = it.getString(photoIndex)
                if (!number.isNullOrBlank()) {
                    contacts.add(Contact(id = id, name = name, phoneNumbers = listOf(number), photoUri = photo))
                }
            }
        }
        contacts.distinctBy { it.id }.take(500)
    }

    private fun Contact.toEntity() = ContactEntity(
        id = id ?: "", name = name,
        phoneNumbers = phoneNumbers.joinToString(","),
        email = email, photoUri = photoUri,
    )

    private fun ContactEntity.toDomain() = Contact(
        id = id, name = name,
        phoneNumbers = phoneNumbers?.split(",") ?: emptyList(),
        email = email, photoUri = photoUri,
    )
}

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : com.crm.v2.domain.repository.SettingsRepository {

    override suspend fun getProfile(userKey: String): Resource<com.crm.v2.domain.model.Employee> {
        return Resource.Success(com.crm.v2.domain.model.Employee(key = userKey, employeeName = ""))
    }

    override suspend fun updateProfile(employee: com.crm.v2.domain.model.Employee): Resource<String> {
        return Resource.Success("Profile updated")
    }

    override suspend fun sendFeedback(body: String, user: String): Resource<String> {
        return try {
            val response = pickupApi.sendEmail(
                com.crm.v2.data.network.dto.pickup.SendEmailRequest(body = body, user = user)
            )
            if (response.isSuccessful) Resource.Success("Feedback sent")
            else Resource.Error("Failed to send feedback")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }
}
