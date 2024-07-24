package com.xheghun.voyatrip.data.models

data class Trip(val name: String, val location: String, val date: String, val duration: String)

fun dummyTrips(size: Int = 10): List<Trip> {
    return buildList {
        for (i in 1..size) {
            add(Trip("Trip $i", "Location $i", "Date $i", "Duration $i"))
        }
    }
}