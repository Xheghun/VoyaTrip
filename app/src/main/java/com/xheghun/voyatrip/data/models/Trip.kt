package com.xheghun.voyatrip.data.models

class Trip(
    val tripName: String,
    val travelStyle: String,
    val description: String,
    val location: Location,
    val startDate: String,
    val endDate: String,
    val duration: String
)

fun dummyTrips(size: Int = 10): List<Trip> {
    return buildList {
        for (i in 1..size) {
            add(
                Trip(
                    "Trip $i",
                    "",
                    "",
                    dummyLocations()[0],
                    "Date $i",
                    "End Date $i",
                    "Duration $i"
                )
            )
        }
    }
}