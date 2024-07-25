package com.xheghun.voyatrip.data.api

import com.xheghun.voyatrip.data.models.Trip
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TripsService {
    @POST("/api/users/")
    fun createTrip(@Body trip: Trip): Response<*>

    @GET("/api/users/")
    fun getTrips(): List<Trip>
}