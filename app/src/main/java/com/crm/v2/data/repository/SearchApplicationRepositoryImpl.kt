package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.SearchApplicationApi
import com.crm.v2.data.network.dto.search.SearchApplicationDto
import com.crm.v2.domain.model.SearchApplication
import com.crm.v2.domain.repository.SearchApplicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchApplicationRepositoryImpl @Inject constructor(
    private val api: SearchApplicationApi,
) : SearchApplicationRepository {

    override suspend fun searchApplications(
        userKey: String,
        pageIndex: Int,
        pageSize: Int,
        search: String,
    ): Resource<List<SearchApplication>> = withContext(Dispatchers.IO) {
        try {
            val response = api.searchApplications(
                userKey = userKey,
                displayStart = pageIndex * pageSize,
                displayLength = pageSize,
                search = search,
            )

            if (response.isSuccessful) {
                val body = response.body()?.string()
                if (body != null) {
                    val items = parseResponse(body)
                    Resource.Success(items)
                } else {
                    Resource.Error("Empty response from server")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Resource.Error(errorBody ?: "Failed to load applications (${response.code()})")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error occurred")
        }
    }

    private fun parseResponse(body: String): List<SearchApplication> {
        return try {
            val json = JSONObject(body)
            val dataArray = json.getJSONArray("aaData")
            val items = mutableListOf<SearchApplication>()

            for (i in 0 until dataArray.length()) {
                val obj = dataArray.getJSONObject(i)
                items.add(
                    SearchApplication(
                        uniqId = obj.optString("UniqId", ""),
                        appNo = obj.optString("app_no", ""),
                        appDate = obj.optString("app_date", ""),
                        losNo = obj.optString("los_no", ""),
                        loanName = obj.optString("loan_name", ""),
                        loanAmount = obj.optString("loan_amnt", ""),
                        clientName = obj.optString("client_name", ""),
                        bankName = obj.optString("bank_name", ""),
                        disbursedAmount = obj.optString("disbursed_amnt", ""),
                        disbursalDate = obj.optString("disbursal_date", ""),
                        callerName = obj.optString("tel_caller_name", ""),
                        cpvStatus = obj.optString("cpv_status", ""),
                        remarks = obj.optString("remarks", ""),
                        statusDesc = obj.optString("StatusDesc", ""),
                        updatedOn = obj.optString("UpdatedOn", ""),
                    )
                )
            }
            items
        } catch (e: Exception) {
            emptyList()
        }
    }
}
