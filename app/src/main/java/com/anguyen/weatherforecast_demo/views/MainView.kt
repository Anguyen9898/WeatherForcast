package com.anguyen.weatherforecast_demo.views

import com.anguyen.weatherforecast_demo.models.WeatherJSONObjDetail

interface MainView {

    fun showInternetError()

    fun updateUI(data: List<WeatherJSONObjDetail?>)

    fun showDataGettingFailed(message: String?)

}