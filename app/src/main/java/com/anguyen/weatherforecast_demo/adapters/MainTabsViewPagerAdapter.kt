package com.anguyen.weatherforecast_demo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.models.WeatherJSONObjDetail
import com.anguyen.weatherforecast_demo.ui.fragments.*
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_weather_info.*
import kotlinx.android.synthetic.main.fragment_weather_info.view.*
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_address
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_humidity
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_pressure
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_status
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_sunrise
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_sunset
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_temp
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_temp_max
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_temp_min
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_updated_at
import kotlinx.android.synthetic.main.fragment_weather_info.view.txt_wind
import java.text.SimpleDateFormat
import java.util.*

class MainTabsViewPagerAdapter(
    private val mContext: Context,
    private val mWeatherData: List<WeatherJSONObjDetail?>
): RecyclerView.Adapter<MainTabsViewPagerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_weather_info, parent, false))
    }

    override fun getItemCount(): Int = mWeatherData.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        val weatherData = mWeatherData[i]

        val weatherElement = weatherData?.weather?.get(0)?.asJsonObject
        val mainElement = weatherData?.main?.asJsonObject
        val sysElement = weatherData?.sys?.asJsonObject

        //Set address TextView
        holder.txtAddress.text = "${weatherData?.name?.asString}, ${sysElement?.get("country")?.asString}"

        //Set date TextView
        val date = Date((weatherData?.dt?.asLong!!)*1000)
        val updateAt = SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date)
        holder.txtUpdateAt.text = updateAt

        //Set status TextView
        holder.txtStatus.text = weatherElement?.get("main")?.asString

        //Set temps TextView
        holder.txtTemp.text = "${mainElement?.get("temp")?.asDouble?.toInt()}$STR_TEMP_UNIT"
        holder.txtMax.text = "${mainElement?.get("temp_max")?.asDouble?.toInt()}$STR_TEMP_UNIT"
        holder.txtMin.text = "${mainElement?.get("temp_min")?.asDouble?.toInt()}$STR_TEMP_UNIT"

        //Set sunrise TextView
        val sunriseElement = Date((sysElement?.get("sunrise")?.asLong!!)*1000)
        val sunriseTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(sunriseElement)
        holder.txtSunRise.text = sunriseTime
        //Set sunset TextView
        val sunsetElement = Date((sysElement.get("sunset")?.asLong!!)*1000)
        val sunsetTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(sunsetElement)
        holder.txtSunSet.text = sunsetTime

        //Set wind speed TextView
        val windElement = weatherData.wind?.asJsonObject
        holder.txtWind.text = "${windElement?.get("speed")?.asString}$STR_WIND_SPEED_UNIT"

        //Set pressure TextView
        holder.txtPressure.text = "${mainElement?.get("pressure")?.asString}$STR_PRESSURE_UNIT"

        //Set humidity TextView
        holder.txtHumidity.text = "${mainElement?.get("humidity")?.asString}$STR_HUMIDITY_UNIT"

    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtAddress = itemView.txt_address!!
        val txtUpdateAt = itemView.txt_updated_at!!
        val txtStatus = itemView.txt_status!!
        val txtTemp = itemView.txt_temp!!
        val txtMax = itemView.txt_temp_max!!
        val txtMin = itemView.txt_temp_min!!
        val txtSunRise = itemView.txt_sunrise!!
        val txtSunSet = itemView.txt_sunset!!
        val txtWind = itemView.txt_wind!!
        val txtPressure = itemView.txt_pressure!!
        val txtHumidity = itemView.txt_humidity!!
    }


}