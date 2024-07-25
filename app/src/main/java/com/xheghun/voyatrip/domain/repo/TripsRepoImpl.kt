package com.xheghun.voyatrip.domain.repo

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
            responseDeferred.await()
        }

    override suspend fun createTrip(trip: Trip): Unit = withContext(Dispatchers.IO) {
        val responseDeferred = async { tripsApi.createTrip(trip) }
        responseDeferred.await()
    }
}