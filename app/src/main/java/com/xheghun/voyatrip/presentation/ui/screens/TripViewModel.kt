package com.xheghun.voyatrip.presentation.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripViewModel : ViewModel() {
    private val _options = MutableStateFlow(listOf("Planned Trips", "Completed Trips"))
    val options = _options.asStateFlow()

    private val _isOptionsExpanded = MutableStateFlow(false)
    val isOptionsExpanded = _isOptionsExpanded.asStateFlow()

    private val _selectedOption = MutableStateFlow(options.value[0])
    val selectedOption = _selectedOption.asStateFlow()

    private val _isSelectCityExpanded = MutableStateFlow(false)
    val isSelectCityExpanded = _isSelectCityExpanded.asStateFlow()

    fun toggleOptions(value: Boolean = !_isOptionsExpanded.value) {
        _isOptionsExpanded.value = value
    }

    fun updateSelectedOption(option: String) {
        _selectedOption.value = option
    }

    fun toggleIsSelectCityExpanded(value: Boolean = !_isSelectCityExpanded.value) {
        _isSelectCityExpanded.value = value
    }



}