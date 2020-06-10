package com.anguyen.weatherforecast_demo.views

import com.google.gson.JsonArray

interface CitySelectorDialogView {

    fun onGetDataSuccess(data: JsonArray?)

    fun onFindCityFailure()

    fun onGetDataFailure(message: String?)

    fun onCityInfoSavingSuccess()

    fun onCityInfoSavingFailure()

}