package com.xheghun.voyatrip.data.api

import com.google.gson.annotations.SerializedName
import com.xheghun.voyatrip.data.models.Trip
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TripsService {
    @POST("/api/trips/create")
    suspend fun createTrip(@Body trip: Trip): Response<CreateTripResponse>

    @GET("/api/trips/")
    suspend fun getTrips(): List<Trip>
}

class CreateTripResponse(
    @SerializedName("message")
    val message: List<String>
)