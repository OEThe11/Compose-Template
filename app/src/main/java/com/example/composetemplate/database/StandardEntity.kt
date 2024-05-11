package com.example.composetemplate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "standard_entity")
data class StandardEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int,
    val completed: Boolean
)
