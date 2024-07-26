# VoyaTravel

### Api
The Api Integrated with this app linked with a mock server having 2 endpoints

```kotlin
@POST("/api/trips/create")
suspend fun createTrip(@Body trip: Trip): Response<CreateTripResponse>

@GET("/api/trips/")
suspend fun getTrips(): List<Trip>
 ```
when fetching the trip(s) a pre-configured response would be returned 
```json

[
    {
        "description": "vacation with my friends",
        "duration": "12 Days",
        "endDate": "2024-09-15",
        "location": {
            "city": "Palm Jumeria",
            "code": "AE",
            "countryRegion": "Dubai, UAE",
            "img": "image"
        },
        "startDate": "2024-09-02",
        "travelStyle": "Group",
        "tripName": "Explore Dubai"
    },
  {
        "description": "exploring europe",
        "duration": "4 Days",
        "endDate": "2024-08-30",
        "location": {
            "city": "Vatican City",
            "code": "IT",
            "countryRegion": "Rome, Italy",
            "img": "image"
        },
        "startDate": "2024-07-30",
        "travelStyle": "Solo",
        "tripName": "Summer in Europe"
    }
]

```