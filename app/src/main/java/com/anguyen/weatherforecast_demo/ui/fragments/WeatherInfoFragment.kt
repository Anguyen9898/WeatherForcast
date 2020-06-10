package com.anguyen.weatherforecast_demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.models.WeatherJSONObjDetail
import kotlinx.android.synthetic.main.fragment_weather_info.*
import java.text.SimpleDateFormat
import java.util.*

const val STR_TEMP_UNIT = "°C"
const val STR_WIND_SPEED_UNIT = "m/s"
const val STR_PRESSURE_UNIT = "hpa"
const val STR_HUMIDITY_UNIT = "%"

class WeatherInfoFragment(private val weatherData: WeatherJSONObjDetail?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI(){

        val weatherElement = weatherData?.weather?.get(0)?.asJsonObject
        val mainElement = weatherData?.main?.asJsonObject
        val sysElement = weatherData?.sys?.asJsonObject

        //Set address TextView
        txt_address.text = "${weatherData?.name?.asString}, ${sysElement?.get("country")?.asString}"

        //Set date TextView
        val date = Date((weatherData?.dt?.asLong!!)*1000)
        val updateAt = SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)
        txt_updated_at.text = updateAt

        //Set status TextView
        txt_status.text = weatherElement?.get("main")?.asString

        //Set temps TextView
        txt_temp.text = "${mainElement?.get("temp")?.asDouble?.toInt()}$STR_TEMP_UNIT"
        txt_temp_max.text = "${mainElement?.get("temp_max")?.asDouble?.toInt()}$STR_TEMP_UNIT"
        txt_temp_min.text = "${mainElement?.get("temp_min")?.asDouble?.toInt()}$STR_TEMP_UNIT"

        //Set sunrise TextView
        val sunriseElement = Date((sysElement?.get("sunrise")?.asLong!!)*1000)
        val sunriseTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(sunriseElement)
        txt_sunrise.text = sunriseTime
        //Set sunset TextView
        val sunsetElement = Date((sysElement.get("sunset")?.asLong!!)*1000)
        val sunsetTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(sunsetElement)
        txt_sunset.text = sunsetTime

        //Set wind speed TextView
        val windElement = weatherData.wind?.asJsonObject
        txt_wind.text = "${windElement?.get("speed")?.asString}$STR_WIND_SPEED_UNIT"

        //Set pressure TextView
        txt_pressure.text = "${mainElement?.get("pressure")?.asString}$STR_PRESSURE_UNIT"

        //Set humidity TextView
        txt_humidity.text = "${mainElement?.get("humidity")?.asString}$STR_HUMIDITY_UNIT"

    }

}
