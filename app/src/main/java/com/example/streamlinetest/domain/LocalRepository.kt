package com.example.streamlinetest.domain

import android.content.Context
import androidx.room.Room
import com.example.streamlinetest.data.local.StreamDatabase
import com.example.streamlinetest.data.local.UniversityEntity
import com.example.streamlinetest.data.remote.UniversityDTO

class LocalRepository(context: Context) {

    private var db: StreamDatabase

    init {
        db = Room.databaseBuilder(
            context,
            StreamDatabase::class.java, "database-stream"
        ).build()
    }

    private val dao = db.getUniversityDao()

    suspend fun insertUniversity(universityList: List<UniversityDTO>) {
        universityList.forEach { universityDTO ->
            val dbResult = dao.selectUniversityByName(universityDTO.name)

            if (dbResult != null) {
                dao.insertUniversity(
                    UniversityEntity(
                        domains = universityDTO.domains,
                        country = universityDTO.country,
                        alphaTwoCode = universityDTO.alphaTwoCode,
                        webPages = universityDTO.webPages,
                        stateProvince = universityDTO.stateProvince,
                        name = universityDTO.name
                    )
                )
            }
        }
    }

    suspend fun getAllUniversities(): List<UniversityEntity> {
        return dao.getAllUniversities()
    }

}
