package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.dto.pickup.InsertDailyLoginRequest
import com.crm.v2.domain.model.DailyLogin
import com.crm.v2.domain.repository.DailyLoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyLoginRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : DailyLoginRepository {

    override suspend fun insertDailyLogin(
        clientName: String, mobileNo: String, banks: String,
        product: String, region: String, losNo: String,
        appNo: String, loginAmt: Double, remarks: String?, userKey: String,
    ): Resource<String> {
        return try {
            val response = pickupApi.insertDailyLogin(
                InsertDailyLoginRequest(
                    clientName = clientName, mobileNo = mobileNo, banks = banks,
                    product = product, region = region, losNo = losNo, appNo = appNo,
                    loginAmt = loginAmt, remarks = remarks, userKey = userKey,
                )
            )
            if (response.isSuccessful) Resource.Success("Daily login saved")
            else Resource.Error("Failed to save daily login")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDailyLoginList(userKey: String): Resource<List<DailyLogin>> {
        return try {
            val response = pickupApi.getDailyLoginList(userKey)
            if (response.isSuccessful) {
                val list = response.body()?.map { dto ->
                    DailyLogin(
                        key = dto.key, clientName = dto.clientName,
                        mobileNo = dto.mobileNo, product = dto.product,
                        banks = dto.banks, losNo = dto.losNo, appNo = dto.appNo,
                        region = dto.region, loginAmount = dto.loginAmount ?: 0.0,
                        remarks = dto.remarks,
                    )
                } ?: emptyList()
                Resource.Success(list)
            } else Resource.Error("Failed to load daily login list")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }
}
