package com.example.retrofitapi.apiInterface

import com.example.retrofitapi.model.Huesped
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
    "http://10.0.2.2:8080/api/v1/"

interface hotelApiInterface {
    @GET("huespedes")
    @Headers("Accept: application/json")
    suspend fun getAllHuespedes(): Response<List<Huesped>>

    @POST("huespedes")
    suspend fun createHuesped(@Body huesped: Huesped) : Response<Void>

    @DELETE("huespedes/delete/{id}")
    suspend fun deleteHuesped(@Path("id") id : Int) : Response<Void>

    @PUT("huespedes/put/{id}")
    suspend fun updateHuesped(@Path("id") id: Int, @Body huesped: Huesped): Response<Void>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object HotelApi{
    val retrofitService : hotelApiInterface by lazy {
        retrofit.create(hotelApiInterface::class.java)
    }
}