package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.database.dao.LeadDao
import com.crm.v2.data.database.entity.LeadEntity
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.dto.pickup.InsertLeadRequest
import com.crm.v2.domain.model.Lead
import com.crm.v2.domain.model.LeadCount
import com.crm.v2.domain.repository.LeadRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeadRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
    private val leadDao: LeadDao,
) : LeadRepository {

    override suspend fun getLeads(userKey: String, pageIndex: Int): Resource<List<Lead>> {
        return try {
            val response = pickupApi.getLeads(userKey, pageIndex)
            if (response.isSuccessful) {
                val leads = response.body()?.map { it.toDomain() } ?: emptyList()
                leadDao.insertLeads(leads.map { it.toEntity() })
                Resource.Success(leads)
            } else {
                val cached = leadDao.getLeads(userKey)
                Resource.Error("Failed to fetch leads")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getLeadDetail(userKey: String, key: String): Resource<Lead> {
        return try {
            val response = pickupApi.getLeads(userKey, 0)
            if (response.isSuccessful) {
                val lead = response.body()?.find { it.key == key }?.toDomain()
                lead?.let { Resource.Success(it) }
                    ?: Resource.Error("Lead not found")
            } else {
                Resource.Error("Failed to fetch lead detail")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun createLead(
        name: String, mobileNo: String, product: String,
        callerId: String?, remarks: String?, userKey: String
    ): Resource<String> {
        return try {
            val response = pickupApi.addLead(
                mapOf(
                    "Name" to name,
                    "MobileNo" to mobileNo,
                    "Product" to product,
                    "CallerID" to (callerId ?: ""),
                    "Remarks" to (remarks ?: ""),
                    "UserKey" to userKey,
                )
            )
            if (response.isSuccessful) {
                Resource.Success("Lead created successfully")
            } else {
                Resource.Error("Failed to create lead")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getLeadCount(userKey: String, type: String?): Resource<LeadCount> {
        return try {
            val response = pickupApi.getLeadCount(userKey, type)
            if (response.isSuccessful) {
                val count = response.body()
                Resource.Success(
                    LeadCount(
                        daily = count?.daily ?: 0,
                        monthly = count?.monthly ?: 0,
                        total = count?.total ?: 0,
                    )
                )
            } else Resource.Error("Failed to get lead count")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getFollowUpLeads(userKey: String, type: String?): Resource<List<com.crm.v2.domain.model.DialerRecord>> {
        return try {
            val response = pickupApi.getLeadFollowup(userKey, type)
            if (response.isSuccessful) {
                val leads = response.body()?.map { it.toDialerDomain() } ?: emptyList()
                Resource.Success(leads)
            } else Resource.Error("Failed to fetch follow-up leads")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateLeadStatus(request: Map<String, String>): Resource<String> {
        return try {
            val response = pickupApi.manageLead(request)
            if (response.isSuccessful) {
                Resource.Success("Status updated")
            } else Resource.Error("Failed to update status")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }
}

private fun com.crm.v2.data.network.dto.pickup.LeadDto.toDomain() = Lead(
    key = key ?: "",
    clientName = clientName ?: "",
    contact = contact ?: "",
    product = product,
    salary = salary,
    company = company,
    status = status,
    callerName = callerName,
    callbackDate = callBackDate,
    isHLLAPBL = isHLLAPBL,
    isCibilBad = isCibilBad ?: false,
    isRealEstate = isRealEstate ?: false,
    remarks = remarks,
    createdDate = createdDate,
    userKey = userKey,
)

private fun Lead.toEntity() = LeadEntity(
    key = key,
    clientName = clientName,
    contact = contact,
    product = product,
    salary = salary,
    company = company,
    status = status,
    callerName = callerName,
    callbackDate = callbackDate,
    isHLLAPBL = isHLLAPBL,
    isCibilBad = isCibilBad,
    isRealEstate = isRealEstate,
    remarks = remarks,
    createdDate = createdDate,
    userKey = userKey,
)

private fun com.crm.v2.data.network.dto.pickup.DialerDataDto.toDialerDomain() = com.crm.v2.domain.model.DialerRecord(
    key = key ?: "",
    clientName = clientName ?: "",
    contact = contact ?: "",
    product = product,
    campaign = campaign,
    salary = salary,
    company = company,
    status = status,
    callbackDate = callBackDate,
    remarks = remarks,
    userKey = userKey,
)
