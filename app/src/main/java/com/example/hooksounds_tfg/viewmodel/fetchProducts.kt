package com.example.hooksounds_tfg.viewmodel


import com.example.hooksounds_tfg.data.remote.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun fetchProducts() {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = ServiceBuilder.apiService.getProducts()
            println("Productos obtenidos: ${response.products.size}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

