package com.example.streamlinetest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UniversityDao {

    @Insert
    suspend fun insertUniversity(universityEntity: UniversityEntity)

    @Query("SELECT * FROM UniversityEntity")
    suspend fun getAllUniversities(): List<UniversityEntity>

    @Query("SELECT * FROM UniversityEntity WHERE name=:name")
    suspend fun selectUniversityByName(name: String): UniversityEntity?

}
