# VoyaTravel

### User Interface
This app implements a smooth seamless user experience

#### Screenshots

<image src="https://github.com/user-attachments/assets/772f482e-be6b-4625-a49e-da7e98c7689d" height="450" width="250"/>

<image src="https://github.com/user-attachments/assets/a02d4106-fd56-4d7a-a923-f09511f52099" height="450" width="250"/>

<image src="https://github.com/user-attachments/assets/fcd4df27-fa79-4cf9-8da2-06c1d4bf06c2" height="450" width="250"/>

<image src="https://github.com/user-attachments/assets/37e978e5-3436-4ce3-8455-bc1f65ee2714" height="450" width="250"/>

<image src="https://github.com/user-attachments/assets/286e094c-d2d0-4808-82f4-f71ae5b61767" height="450" width="250"/>

<image src="https://github.com/user-attachments/assets/587b7998-4d47-4198-bd11-30edad3a336d" height="450" width="250"/>




### API
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
