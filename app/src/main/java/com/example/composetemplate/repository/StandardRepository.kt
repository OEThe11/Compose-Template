package com.example.composetemplate.repository

import com.example.composetemplate.data.Resource
import com.example.composetemplate.database.StandardDao
import com.example.composetemplate.database.StandardDatabase
import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.mapping.StandardMapper
import com.example.composetemplate.network.StandardApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StandardRepository @Inject constructor(
    private val api: StandardApi,
    private val standardDao: StandardDao
){

    suspend fun standardInfo(): Resource<List<StandardEntity>>{
        return try {
            val response = api.getAllStandardList()

            if (response.isSuccessful){
                val standardItems = response.body()?.map { StandardMapper.buildFrom(it) }

                if(standardItems.isNullOrEmpty()){
                    Resource.Error("Empty resopnse")
                }else{
                    standardDao.insertAll(*standardItems.toTypedArray())
                    Resource.Success(standardItems)
                }

                }else{
                    Resource.Error("API Error: ${response.message()}")
            }

            }catch (e: Exception){
                Resource.Error("Expectation: ${e.message ?: "Unknown error"}")
            }

    }

    suspend fun getById(id: Int): Flow<Resource<StandardEntity>> = flow {
        try {
            emit(Resource.Loading())
            val data = standardDao.getInfo(id).firstOrNull()
            if (data != null){
                emit(Resource.Success(data))
            }else{
                emit(Resource.Error("Data not found"))
            }
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "Unknown error"))
            }

    }


}

