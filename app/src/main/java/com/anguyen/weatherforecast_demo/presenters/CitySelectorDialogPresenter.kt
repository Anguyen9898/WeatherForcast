package com.anguyen.weatherforecast_demo.presenters

import android.content.Context
import android.util.Log
import com.anguyen.weatherforecast_demo.models.CitiesModel
import com.anguyen.weatherforecast_demo.rooms.managers.SelectedCitiesManager
import com.anguyen.weatherforecast_demo.ui.cityselector.CitySelectorDialogView
import com.google.gson.JsonParser
import java.io.FileNotFoundException
import java.io.IOException
import java.text.ParseException

class CitySelectorDialogPresenter(
    private val mContext: Context,
    private val mView: CitySelectorDialogView?
){

    private val mManager = SelectedCitiesManager(mContext)

    private fun getCitiesJsonData(onResult: (String) -> Unit){
        try {
            //val inputStream = mContext.assets.open("cities.json")
            val inputStream = mContext.assets.open("city.list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            onResult(String(buffer, charset("UTF-8")))

        } catch (ex: IOException){
            mView?.onGetDataFailure(ex.message)
        }
    }


    fun getAllCities(){

       getCitiesJsonData { dataCallback ->
           try {
               mView?.onGetAllCitiesSuccess(JsonParser().parse(dataCallback).asJsonArray)
           }catch (ex: FileNotFoundException){
               mView?.onGetDataFailure(ex.message)
           }catch (ex: IOException){
               mView?.onGetDataFailure(ex.message)
           }catch (ex: ParseException){
               mView?.onGetDataFailure(ex.message)
           }
       }

    }

    fun saveCityToDatabase(cityDetail: CitiesModel){
        mManager.insertCity(cityDetail, object: SelectedCitiesManager.OnDataCallback{
            override fun onSuccess(citiesModels: List<CitiesModel>?) {
                Log.d("Selector", citiesModels.toString())
                mView?.onCityInfoSavingSuccess()
            }

            override fun onFail() {
                mView?.onCityInfoSavingFailure()
            }
        })
    }

}