package com.xheghun.voyatrip.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xheghun.voyatrip.data.models.Location
import com.xheghun.voyatrip.data.models.Trip
import com.xheghun.voyatrip.domain.repo.TripsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

enum class DatePickerState {
    START_DATE, END_DATE
}

class TripViewModel(private val tripsRepo: TripsRepo) : TripsPresenter, ViewModel() {

    init {
       // getTrips()
    }

    private val _options = MutableStateFlow(listOf("Planned Trips", "Completed Trips"))
    val options = _options.asStateFlow()

    private val _travelStyleOptions = MutableStateFlow(listOf("Solo", "Couple", "Family", "Group"))
    val travelStyleOptions = _travelStyleOptions.asStateFlow()

    private val _trips = MutableStateFlow<List<Trip>>(
        listOf(
            Trip(
                "Bahamas Holiday",
                "Solo",
                "Trip to the Bahamas by myself",
                Location("image", "NG", "Ikeja", "Lagos, Nigeria"),
                "2024-07-30",
                "2024-08-10",
                "10 Days"
            )
        )
    )
    val trips = _trips.asStateFlow()

    private val _isOptionsExpanded = MutableStateFlow(false)
    val isOptionsExpanded = _isOptionsExpanded.asStateFlow()

    private val _isTravelStyleOptionsExpanded = MutableStateFlow(false)
    val isTravelStyleOptionsExpanded = _isTravelStyleOptionsExpanded.asStateFlow()

    private val _selectedOption = MutableStateFlow(options.value[0])
    val selectedOption = _selectedOption.asStateFlow()

    val defaulTravel = "Select your travel type"

    private val _selectedTravelStyle = MutableStateFlow(defaulTravel)
    val selectedTravelStyle = _selectedTravelStyle.asStateFlow()

    private val _isSelectCityExpanded = MutableStateFlow(false)
    val isSelectCityExpanded = _isSelectCityExpanded.asStateFlow()

    private val _isCreateTripExpanded = MutableStateFlow(false)
    val isCreateTripExpanded = _isCreateTripExpanded.asStateFlow()

    private val _selectedCity = MutableStateFlow<Location?>(null)
    val selectedCity = _selectedCity.asStateFlow()

    private val _isChooseDateExpanded = MutableStateFlow(false)
    val isChooseDateExpanded = _isChooseDateExpanded.asStateFlow()

    private val _selectedStartDate = MutableStateFlow("Enter Date")
    val selectedStartDate = _selectedStartDate.asStateFlow()

    private val _selectedEndDate = MutableStateFlow("Enter Date")
    val selectedEndDate = _selectedEndDate.asStateFlow()

    private val _datePickerState = MutableStateFlow(DatePickerState.START_DATE)
    val datePickerState = _datePickerState.asStateFlow()

    private val _tripName = MutableStateFlow("")
    val tripName = _tripName.asStateFlow()

    private val _tripDescription = MutableStateFlow("")
    val tripDescription = _tripDescription.asStateFlow()

    private val _isBusy = MutableStateFlow(false)
    val isBusy = _isBusy.asStateFlow()

    private val _selectedTrip = MutableStateFlow<Trip?>(null)
    val selectedTrip = _selectedTrip.asStateFlow()

    fun updateSelectedTrip(trip: Trip) {
        _selectedTrip.value = trip
    }

    fun updateTripName(value: String) {
        _tripName.value = value
    }

    fun updateTripDescription(value: String) {
        _tripDescription.value = value
    }

    fun updateIsCreateTripExpanded(value: Boolean = !_isCreateTripExpanded.value) {
        _isCreateTripExpanded.value = value
    }

    fun updateDatePickerState(state: DatePickerState) {
        _datePickerState.value = state
    }

    fun updateSelectedCity(city: Location) {
        _selectedCity.value = city
    }

    fun updateIsChooseDateExpanded(value: Boolean = !_isChooseDateExpanded.value) {
        _isChooseDateExpanded.value = value
    }

    fun toggleOptions(value: Boolean = !_isOptionsExpanded.value) {
        _isOptionsExpanded.value = value
    }

    fun toggleTravelStyleOptions(value: Boolean = !_isTravelStyleOptionsExpanded.value) {
        _isTravelStyleOptionsExpanded.value = value
    }

    fun updateSelectedOption(option: String) {
        _selectedOption.value = option
    }

    fun updateSelectedTravelStyle(travelStyle: String) {
        _selectedTravelStyle.value = travelStyle

    }

    fun updateIsSelectCityExpanded(value: Boolean = !_isSelectCityExpanded.value) {
        _isSelectCityExpanded.value = value
    }

    fun updateSelectedDate(date: LocalDate) {
        if (datePickerState.value == DatePickerState.START_DATE) {
            _selectedStartDate.value = date.toString()
        } else {
            _selectedEndDate.value = date.toString()
        }
    }

    fun getInferredSelectedDate(): StateFlow<String> {
        return if (datePickerState.value == DatePickerState.START_DATE) {
            selectedStartDate
        } else {
            selectedEndDate
        }
    }

    override fun createTrip(onSuccess: () -> Unit, onError: () -> Unit) {
        _isBusy.value = true
        val trip = Trip(
            tripName = tripName.value,
            description = tripDescription.value,
            travelStyle = selectedTravelStyle.value,
            startDate = selectedStartDate.value,
            endDate = selectedEndDate.value,
            duration = "",
            location = selectedCity.value!!
        )

        viewModelScope.launch {
            val result = runCatching { tripsRepo.createTrip(trip) }
            result
                .onSuccess {
                    _isBusy.value = false
                    Log.d("TripViewModel", "Trip created successfully")
                    onSuccess.invoke()
                }
                .onFailure {
                    _isBusy.value = false
                    Log.e("TripViewModel", "Error creating trip", it)
                    onError.invoke()
                }
        }
    }

    override fun getTrips() {
        viewModelScope.launch {
            val result = runCatching { tripsRepo.getTrips() }
            result.onSuccess {
                _trips.value = it
            }.onFailure {
                Log.e("TripViewModel", "Error getting trips", it)
            }
        }
    }

    override fun canDisplayCreateTripForm(): Boolean {
        //TODO("There's a better way to handle this validation")
        return (_selectedCity.value != null &&
                (_selectedStartDate.value != "Enter Date" && _selectedStartDate.value.isNotEmpty())
                && (_selectedEndDate.value != "Enter Date" && _selectedEndDate.value.isNotEmpty())
                )
    }

    override fun isTripValid(): Boolean {
        //TODO("There's a better way to handle this validation")
        return (
                tripName.value.isNotEmpty() &&
                        tripDescription.value.isNotEmpty() &&
                        (selectedTravelStyle.value != defaulTravel && selectedTravelStyle.value.isNotEmpty())
                        && selectedCity.value != null
                        && canDisplayCreateTripForm())
    }
}