package com.anguyen.weatherforecast_demo.utils

class ConfigApi {

    companion object{
        const val BASE_URL = "http://api.openweathermap.org/"
    }

    interface Api{
        companion object{
            const val SAMPLE_WEATHER = "data/2.5/weather?q=Bac Giang&units=metric&appid=3265ce131abbe389a416268d3cd91e0b"
        }
    }

}