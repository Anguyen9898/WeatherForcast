package com.anguyen.weatherforecast_demo.models

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive

data class WeatherJSONObjDetail(
    val coord: JsonObject? = null,
    val weather: JsonArray? = null,
    val base: JsonPrimitive? = null,
    val main: JsonObject? = null,
    val visibility: JsonPrimitive? = null,
    val wind: JsonObject? = null,
    val clouds: JsonObject? = null,
    val dt: JsonPrimitive? = null,
    val sys: JsonObject? = null,
    val timezone: JsonPrimitive? = null,
    val id: JsonPrimitive? = null,
    val name: JsonPrimitive? = null,
    val cod: JsonPrimitive? = null
){

}

data class WeatherRespondedDetail(private val objResponded: JsonObject){

    fun convert(): WeatherJSONObjDetail{
        return WeatherJSONObjDetail(
            objResponded["coord"].asJsonObject,
            objResponded["weather"].asJsonArray,
            objResponded["base"].asJsonPrimitive,
            objResponded["main"].asJsonObject,
            objResponded["visibility"].asJsonPrimitive,
            objResponded["wind"].asJsonObject,
            objResponded["clouds"].asJsonObject,
            objResponded["dt"].asJsonPrimitive,
            objResponded["sys"].asJsonObject,
            objResponded["timezone"].asJsonPrimitive,
            objResponded["id"].asJsonPrimitive,
            objResponded["name"].asJsonPrimitive,
            objResponded["cod"].asJsonPrimitive
        )
    }

}