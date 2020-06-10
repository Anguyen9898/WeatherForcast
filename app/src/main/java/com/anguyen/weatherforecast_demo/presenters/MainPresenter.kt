package com.anguyen.weatherforecast_demo.presenters

import android.content.Context
import com.anguyen.weatherforecast_demo.commons.GeneralConstValue
import com.anguyen.weatherforecast_demo.commons.isNetworkConnected
import com.anguyen.weatherforecast_demo.models.WeatherJSONObjDetail
import com.anguyen.weatherforecast_demo.models.WeatherRespondedDetail
import com.anguyen.weatherforecast_demo.repositories.WeatherApiRepositoryImp
import com.anguyen.weatherforecast_demo.utils.CallBackData
import com.anguyen.weatherforecast_demo.views.MainView
import com.google.gson.JsonObject

class MainPresenter(
    private val mContext: Context,
    private val mView: MainView?
) {

    private val weatherApiRepository = WeatherApiRepositoryImp(mContext)

    private val sharePref = mContext.getSharedPreferences(
        GeneralConstValue.CITES_SHARE_PREF_TAG,
        Context.MODE_PRIVATE
    )

    private val mWeatherData = ArrayList<WeatherJSONObjDetail?>()

    fun testApi(){
        weatherApiRepository.testApi(object: CallBackData<JsonObject> {
            override fun onSuccess(respondedData: JsonObject) {}
            override fun onFail(message: String?) {}
        })
    }

    fun getCitiesWeatherInfo(){

        if (!isNetworkConnected(mContext)!!){
            mView?.showInternetError()
            return
        }

        if(sharePref.all.keys.size != 0){

            val selectedCitesApiNames = sharePref.all.keys
            selectedCitesApiNames.forEach {

                weatherApiRepository.getTodayWeatherInfo(it, object: CallBackData<JsonObject> {

                    override fun onSuccess(respondedData: JsonObject) {
                        mWeatherData.add(WeatherRespondedDetail(respondedData).convert())
                    }

                    override fun onFail(message: String?) {
                        mView?.showDataGettingFailed(message)
                        return
                    }

                })

            }

        }

        mView?.updateUI(mWeatherData)

    }

}