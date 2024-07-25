package com.xheghun.voyatrip.presentation.viewmodel

import com.xheghun.voyatrip.data.models.Trip

interface TripsPresenter {
    fun createTrip(trip: Trip)
    fun getTrips()
}