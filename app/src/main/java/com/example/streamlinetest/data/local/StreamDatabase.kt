package com.example.streamlinetest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UniversityEntity::class],
    version = 1
)
@TypeConverters(
    ListConverter::class
)
abstract class StreamDatabase : RoomDatabase() {
    abstract fun getUniversityDao(): UniversityDao
}
