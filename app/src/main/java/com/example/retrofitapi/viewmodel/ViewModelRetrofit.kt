package com.example.retrofitapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapi.apiInterface.HotelApi.retrofitService
import com.example.retrofitapi.model.Huesped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class estadoApi{
    IDLE, LOADING, SUCCESS, ERROR
}

class ViewModelRetrofit : ViewModel() {
    private val _listaHuesped : MutableStateFlow<List<Huesped>?> = MutableStateFlow(null)
    var listaHuesped = _listaHuesped.asStateFlow()

    private val _estadoLlamada : MutableStateFlow<estadoApi> = MutableStateFlow(estadoApi.IDLE)
    var estadoLlamada = _estadoLlamada.asStateFlow()

    init {
        obtenerHuesped()
    }

    fun obtenerHuesped(){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            var respuesta = retrofitService.getAllHuespedes()

            if (respuesta.isSuccessful){
                _listaHuesped.value = respuesta.body()
                _estadoLlamada.value = estadoApi.SUCCESS
                println(respuesta.body().toString())
            }else{
                println("NO SE HAN PODIDO CARGAR LOS DATOS" + respuesta.errorBody())
            }
        }
    }

    fun postHuesped(huesped: Huesped){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                val response = retrofitService.createHuesped(huesped)

                if(response.isSuccessful){
                    obtenerHuesped()
                    println("SE HA CREADO EL HUESPED")
                    _estadoLlamada.value = estadoApi.SUCCESS
                }else{
                    println("NO SE HA PODIDO CREAR EL HUESPED: ${response.errorBody()}")
                    _estadoLlamada.value = estadoApi.ERROR
                }

            }catch (e:Exception){
                println("HA OCURRIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }

    fun eliminarHuesped(id: Int){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                val response = retrofitService.deleteHuesped(id)

                if (response.isSuccessful){
                    obtenerHuesped()
                    println("SE HA ELIMINADO EL HUESPED CON EL ID: " + id)
                    _estadoLlamada.value = estadoApi.SUCCESS
                }else{
                    println("NO SE HA PODIDO ELIMINAR EL HUESPED CON EL ID: " + id)
                    _estadoLlamada.value = estadoApi.ERROR
                }
            }catch (e: Exception){
                println("SE HA PRODUCIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }

    fun actulizarHuesped(id: Int, huesped: Huesped){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            try {
                val response = retrofitService.updateHuesped(id,huesped)
                if(response.isSuccessful){
                    obtenerHuesped()
                    println("HUESPED ACTULIZADO")
                    _estadoLlamada.value = estadoApi.SUCCESS
                }else{
                    println("NO SE HA PODIDO ACTUALIZAR: ${response.errorBody()}")
                    _estadoLlamada.value = estadoApi.ERROR
                }
            }catch (e:Exception){
                println("SE HA PRODUCIDO UN ERROR: ${e.message}")
                _estadoLlamada.value = estadoApi.ERROR
            }
        }
    }
}