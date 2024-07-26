package com.xheghun.voyatrip.presentation.viewmodel

interface TripsPresenter {
    fun createTrip(onSuccess: () -> Unit, onError: () -> Unit)
    fun getTrips()
    fun canDisplayCreateTripForm(): Boolean
    fun isTripValid(): Boolean
}