package com.anguyen.weatherforecast_demo.repositories

import com.anguyen.weatherforecast_demo.utils.ConfigApi
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {

    @GET(ConfigApi.Api.SAMPLE_WEATHER)
    fun testApi(): Call<JsonObject>

    @GET(ConfigApi.Api.TODAY_WEATHER)
    fun getTodayWeatherInfo(@Path("cityPath")cityPath: String): Call<JsonObject>
}