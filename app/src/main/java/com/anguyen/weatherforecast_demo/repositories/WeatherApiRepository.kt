package com.anguyen.weatherforecast_demo.repositories

import com.anguyen.weatherforecast_demo.utils.CallBackData
import com.google.gson.JsonObject

interface WeatherApiRepository {

    fun testApi(callBackData: CallBackData<JsonObject>)

    fun getTodayWeatherInfo(cityName: String?, callBackData: CallBackData<JsonObject>)

}