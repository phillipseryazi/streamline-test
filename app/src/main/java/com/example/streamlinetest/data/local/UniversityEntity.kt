package com.example.streamlinetest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniversityEntity(
    val domains: List<String>,
    val country: String,
    val alphaTwoCode: String,
    val webPages: List<String>,
    val stateProvince: String?,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
