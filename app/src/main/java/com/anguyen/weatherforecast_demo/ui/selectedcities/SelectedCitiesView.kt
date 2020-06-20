package com.anguyen.weatherforecast_demo.ui.selectedcities

import com.anguyen.weatherforecast_demo.models.CitiesModel
import com.anguyen.weatherforecast_demo.models.CurrentWeatherModel

interface SelectedCitiesView {

    fun onGettingSuccess(callbackData: List<CitiesModel>?)

    fun onGettingEmptyData()

    fun showInternetError()

}