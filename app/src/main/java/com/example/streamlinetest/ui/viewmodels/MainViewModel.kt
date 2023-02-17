package com.example.streamlinetest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.streamlinetest.data.local.UniversityEntity
import com.example.streamlinetest.domain.LocalRepository
import com.example.streamlinetest.domain.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val applicationContext
        get() = getApplication<Application>()

    private val localRepository = LocalRepository(applicationContext)

    private val remoteRepository = RemoteRepository(applicationContext)

    init {
        getUniversitiesFromNetwork()
    }

    private val _universities = MutableLiveData<List<UniversityEntity>>()
    val universities: MutableLiveData<List<UniversityEntity>> get() = _universities

    private fun getUniversitiesFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getUniversities()?.let {
                localRepository.insertUniversity(it)
            }
        }
    }

    fun getAllUniversities() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getAllUniversities().collect {
                _universities.postValue(it)
            }
        }
    }
}
