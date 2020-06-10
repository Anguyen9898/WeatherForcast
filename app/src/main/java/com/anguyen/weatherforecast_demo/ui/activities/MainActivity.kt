package com.anguyen.weatherforecast_demo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.adapters.MainTabsViewPagerAdapter
import com.anguyen.weatherforecast_demo.commons.*
import com.anguyen.weatherforecast_demo.models.WeatherJSONObjDetail
import com.anguyen.weatherforecast_demo.presenters.MainPresenter
import com.anguyen.weatherforecast_demo.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit  var mPresenter: MainPresenter

    private lateinit var mWeatherData: List<WeatherJSONObjDetail?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAll()
    }

    private fun setupAll(){
        //tab_container.setupWithViewPager(view_container as ViewPager)

        mPresenter = MainPresenter(this, this)

        //Test Open Weather Api
        //mPresenter.testApi()

        //Setup today-weather data
        mPresenter.getCitiesWeatherInfo()
        
        img_menu.onClick {
            startActivity(Intent(this, SelectedCitiesActivity::class.java))
        }

    }

//    override fun onResume(){
//        super.onResume()
//        if(intent.extras?.getString("cityId") != null){
//            val cityId = intent.extras?.getString("cityId")
//            showToast(this, cityId!!)
//        }
//    }

    override fun updateUI(data: List<WeatherJSONObjDetail?>) {
        mWeatherData = data
        view_container.adapter = MainTabsViewPagerAdapter(this, mWeatherData)
    }

    override fun showDataGettingFailed(message: String?) {
        showDataGettingFailed(this, message!!)
    }

    override fun showInternetError() {
        showInternetErrorDialog(this)
    }

}
