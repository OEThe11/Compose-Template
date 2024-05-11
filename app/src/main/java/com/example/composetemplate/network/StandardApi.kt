package com.example.composetemplate.network

import com.example.composetemplate.models.GetStandardJSONResponseItem
import com.example.composetemplate.network.Endpoints.ENDPOINT_TODO
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface StandardApi {
    @GET(ENDPOINT_TODO)
    suspend fun getAllStandardList(): Response<List<GetStandardJSONResponseItem>>
}