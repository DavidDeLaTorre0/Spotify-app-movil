package com.example.hooksounds_tfg.viewmodel

import androidx.lifecycle.*
import com.example.hooksounds_tfg.data.model.Product
import com.example.hooksounds_tfg.data.remote.ServiceBuilder
import kotlinx.coroutines.launch

class SongsViewModel : ViewModel() {

    private val _songs = MutableLiveData<List<Product>>()
    val songs: LiveData<List<Product>> get() = _songs

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.apiService.getProducts()
                _songs.postValue(response.products)
                _error.postValue(null)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(e.message)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}