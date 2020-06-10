package com.anguyen.weatherforecast_demo.utils

class ConfigApi {

    companion object{
        val BASE_URL = "http://api.openweathermap.org/"
    }

    interface Api{
        companion object{

            const val SAMPLE_WEATHER = "data/2.5/weather?q=Cao Bang&units=metric&appid=df8bb39ce8f8c5317ac715c67575f2e3"

            const val TODAY_WEATHER = "data/2.5/{cityPath}"

        }
    }

}