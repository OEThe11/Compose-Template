package com.example.composetemplate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StandardDao {

    @Query("SELECT * FROM standard_entity")
    fun getAllInfo(): Flow<List<StandardEntity>>

    @Query("SELECT * FROM standard_entity WHERE id = :id")
    fun getInfo(id: Int): Flow<StandardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg info: StandardEntity)

}