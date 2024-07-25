package com.xheghun.voyatrip.di

import com.xheghun.voyatrip.domain.repo.TripsRepo
import com.xheghun.voyatrip.domain.repo.TripsRepoImpl
import com.xheghun.voyatrip.presentation.viewmodel.TripViewModel
import com.xheghun.voyatrip.presentation.viewmodel.TripsPresenter
import org.koin.dsl.module

fun appModule() = module {
    single { TripsRepoImpl(get()) as TripsRepo } // repository
    single { TripViewModel(get()) as TripsPresenter }
}