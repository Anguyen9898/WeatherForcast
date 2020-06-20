package com.anguyen.weatherforecast_demo.ui.cityselector

import com.google.gson.JsonArray

interface CitySelectorDialogView {

    fun onGetAllCitiesSuccess(data: JsonArray?)

    fun onGetDataFailure(message: String?)

    fun onFindCityFailure()

    fun onCityInfoSavingSuccess()

    fun onCityInfoSavingFailure()

}