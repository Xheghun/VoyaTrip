package com.xheghun.voyatrip.domain.repo

import android.util.Log
import com.xheghun.voyatrip.data.api.CreateTripResponse
import com.xheghun.voyatrip.data.api.TripsService
import com.xheghun.voyatrip.data.models.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TripsRepoImpl(
    private val tripsApi: TripsService,
) : TripsRepo {
    override suspend fun getTrips(): List<Trip> =
        withContext(Dispatchers.IO) {
            val responseDeferred = async { tripsApi.getTrips() }
            responseDeferred.await() ?: listOf()
        }

    override suspend fun createTrip(trip: Trip): Any = withContext(Dispatchers.IO) {
        val response = tripsApi.createTrip(trip)

        if (response.isSuccessful) {
            Log.d("TripsRepoImpl", "Trip created successfully")
        } else {
            Log.e("TripsRepoImpl", "Failed to create trip ${response.raw()}")
        }

    }
}