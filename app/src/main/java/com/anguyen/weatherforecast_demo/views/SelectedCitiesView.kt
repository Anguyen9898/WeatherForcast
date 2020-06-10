package com.anguyen.weatherforecast_demo.views

interface SelectedCitiesView {
    fun onGetSuccess(callbackData: Map<String, *>?)

    fun onGetFailure()
}