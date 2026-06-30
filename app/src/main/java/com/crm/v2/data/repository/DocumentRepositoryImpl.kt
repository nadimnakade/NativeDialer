package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.domain.model.ClientInfo
import com.crm.v2.domain.model.Document
import com.crm.v2.domain.repository.DocumentRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : DocumentRepository {

    override suspend fun saveDocument(
        fileBytes: ByteArray, fileName: String,
        clientName: String, caller: String,
        mobileNo: String, fileType: String,
    ): Resource<String> {
        return try {
            val requestBody = fileBytes.toRequestBody("application/octet-stream".toMediaType())
            val filePart = MultipartBody.Part.createFormData("file", fileName, requestBody)

            val response = pickupApi.saveDocument(
                file = filePart,
                fileName = fileName.toRequestBody("text/plain".toMediaType()),
                name = clientName.toRequestBody("text/plain".toMediaType()),
                caller = caller.toRequestBody("text/plain".toMediaType()),
                mobileNo = mobileNo.toRequestBody("text/plain".toMediaType()),
                fileType = fileType.toRequestBody("text/plain".toMediaType()),
            )
            if (response.isSuccessful) Resource.Success("Document saved")
            else Resource.Error("Failed to save document")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getCallerClients(userKey: String, mobileNo: String): Resource<List<ClientInfo>> {
        return try {
            val response = pickupApi.getCallerClients(userKey, mobileNo)
            if (response.isSuccessful) {
                val clients = response.body()?.map { dto ->
                    ClientInfo(key = dto.key ?: "", name = dto.clientName, mobileNo = dto.mobileNo)
                } ?: emptyList()
                Resource.Success(clients)
            } else Resource.Error("No clients found")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getClientDocuments(userKey: String, mobileNo: String): Resource<List<Document>> {
        return try {
            val response = pickupApi.getClientDocuments(userKey, mobileNo)
            if (response.isSuccessful) {
                val docs = response.body()?.map { dto ->
                    Document(
                        key = dto.key, clientName = dto.clientName,
                        mobileNo = dto.mobileNo, fileName = dto.fileName,
                        fileType = dto.fileType, fileData = dto.fileData,
                    )
                } ?: emptyList()
                Resource.Success(docs)
            } else Resource.Error("No documents found")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getPayslips(userKey: String): Resource<List<Document>> {
        return try {
            val clients = pickupApi.getCallerClients(userKey, "")
            if (clients.isSuccessful) {
                val allDocs = clients.body()?.flatMap { client ->
                    val docsResponse = pickupApi.getClientDocuments(userKey, client.mobileNo ?: "")
                    if (docsResponse.isSuccessful) docsResponse.body()?.filter { it.fileType?.contains("payslip", ignoreCase = true) == true } ?: emptyList()
                    else emptyList()
                } ?: emptyList()
                Resource.Success(allDocs.map { dto ->
                    Document(key = dto.key, clientName = dto.clientName, mobileNo = dto.mobileNo, fileName = dto.fileName, fileType = dto.fileType, fileData = dto.fileData)
                })
            } else Resource.Error("No payslips found")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }
}
