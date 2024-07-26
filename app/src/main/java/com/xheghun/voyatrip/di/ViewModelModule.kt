package com.xheghun.voyatrip.di

import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { TripViewModel(get()) }
}