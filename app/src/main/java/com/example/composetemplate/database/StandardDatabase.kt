package com.example.composetemplate.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StandardEntity::class], version = 1)
abstract class StandardDatabase : RoomDatabase() {
    abstract fun standardDao(): StandardDao

}