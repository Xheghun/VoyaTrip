package com.xheghun.voyatrip.data.models

data class Location(val img: String, val code: String, val city: String, val countryRegion: String)

fun dummyLocations(size: Int = 10): List<Location> {
    return buildList {
        for (i in 1..size) {
            add(Location("image", "NG", "Ikeja", "Lagos, Nigeria"))
        }
    }
}