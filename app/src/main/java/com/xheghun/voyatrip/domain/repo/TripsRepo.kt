package com.xheghun.voyatrip.domain.repo

import com.xheghun.voyatrip.data.models.Trip

interface TripsRepo {
    suspend fun getTrips(): List<Trip>
    suspend fun createTrip(trip: Trip)
}