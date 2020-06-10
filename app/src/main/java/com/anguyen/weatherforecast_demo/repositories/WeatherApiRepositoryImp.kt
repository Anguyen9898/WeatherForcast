package com.anguyen.weatherforecast_demo.repositories

import android.content.Context
import android.util.Log
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.utils.CallBackData
import com.anguyen.weatherforecast_demo.utils.ClientApi
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder

const val tag = "WeatherData"
const val CODE_SUCCESS_RESPOND = 200

class WeatherApiRepositoryImp(private val mContext: Context): WeatherApiRepository {

    /**
     * This's test api function, remove it after project is finished
     */
    override fun testApi(callBackData: CallBackData<JsonObject>) {
        val clientApi = ClientApi()
        val bodyCall =  clientApi.weatherApiService()?.testApi()

        bodyCall?.enqueue(object: Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                val code = response.code()

                if(code == CODE_SUCCESS_RESPOND){
                    if(response.body() != null) {
                        //Logcat Writing
                        Log.d(tag, response.body().toString())

                    }else{
                        Log.d(tag, mContext.getString(R.string.weather_api_error_message))
                    }
                }else{
                    Log.d(tag, mContext.getString(R.string.weather_api_error_message))
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                //Logcat Writing
                Log.d(tag, "Fail")
                //Setup Result
                Log.d(tag, t.message!!)
            }

        })
    }

    override fun getTodayWeatherInfo(cityName: String?, callBackData: CallBackData<JsonObject>) {
        val clientApi = ClientApi()
        val path = "weather?q=$cityName&units=metric&appid=df8bb39ce8f8c5317ac715c67575f2e3"
        val bodyCall =  clientApi.weatherApiService()?.getTodayWeatherInfo(path)

        bodyCall?.enqueue(object: Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                val code = response.code()

                if(code == CODE_SUCCESS_RESPOND){
                    if(response.body() != null) {
                        //Logcat Writing
                        Log.d(tag, response.body().toString())
                        //Setup Result
                        callBackData.onSuccess(response.body()!!)
                    }else{
                        callBackData.onFail(mContext.getString(R.string.weather_api_error_message))
                    }
                }else{
                    callBackData.onFail(mContext.getString(R.string.weather_api_error_message))
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                //Logcat Writing
                Log.d(tag, "Fail")
                //Setup Result
                callBackData.onFail(t.message)
            }

        })
    }

}