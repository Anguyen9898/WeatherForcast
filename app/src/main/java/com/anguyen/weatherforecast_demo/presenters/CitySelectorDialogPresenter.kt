package com.anguyen.weatherforecast_demo.presenters

import android.content.Context
import com.anguyen.weatherforecast_demo.commons.GeneralConstValue
import com.anguyen.weatherforecast_demo.commons.put
import com.anguyen.weatherforecast_demo.views.CitySelectorDialogView
import com.google.gson.JsonParser
import java.io.FileNotFoundException
import java.io.IOException
import java.text.ParseException

class CitySelectorDialogPresenter(
    private val mContext: Context,
    private val mView: CitySelectorDialogView?
){

    private val sharePref = mContext.getSharedPreferences(
        GeneralConstValue.CITES_SHARE_PREF_TAG,
        Context.MODE_PRIVATE
    )

    fun getAllCities(){

       loadCitiesJsonData { dataCallback ->
           try {
               mView?.onGetDataSuccess(JsonParser().parse(dataCallback).asJsonArray)
           }catch (ex: FileNotFoundException){
               mView?.onGetDataFailure(ex.message)
           }catch (ex: IOException){
               mView?.onGetDataFailure(ex.message)
           }catch (ex: ParseException){
               mView?.onGetDataFailure(ex.message)
           }
       }

    }

    private fun loadCitiesJsonData(onResult: (String) -> Unit){
        try {
            val inputStream = mContext.assets.open("cities.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            onResult(String(buffer, charset("UTF-8")))

        } catch (ex: IOException){
            mView?.onGetDataFailure(ex.message)
        }
    }

    fun saveCityToStorage(cityId: String?, cityName: String?){

        if(sharePref.put(cityId!!, cityName)){
            mView?.onCityInfoSavingSuccess()
        }else{
            mView?.onCityInfoSavingFailure()
        }

    }

}