package com.xheghun.voyatrip.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.xheghun.voyatrip.data.models.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate


enum class DatePickerState {
    START_DATE, END_DATE
}

class TripViewModel : ViewModel() {
    private val _options = MutableStateFlow(listOf("Planned Trips", "Completed Trips"))
    val options = _options.asStateFlow()

    private val _travelStyleOptions = MutableStateFlow(listOf("Solo", "Couple", "Family", "Group"))
    val travelStyleOptions = _travelStyleOptions.asStateFlow()

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


}