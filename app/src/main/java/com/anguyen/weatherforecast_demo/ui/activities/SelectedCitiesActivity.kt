package com.anguyen.weatherforecast_demo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.adapters.OnRecycleCityClick
import com.anguyen.weatherforecast_demo.adapters.SelectedCitiesRecycleAdapter
import com.anguyen.weatherforecast_demo.commons.onClick
import com.anguyen.weatherforecast_demo.commons.setupVertically
import com.anguyen.weatherforecast_demo.commons.showGeneralErrorDialog
import com.anguyen.weatherforecast_demo.models.CitiesDetail
import com.anguyen.weatherforecast_demo.presenters.SelectedCitiesPresenter
import com.anguyen.weatherforecast_demo.ui.fragments.CitySelectorFragmentDialog
import com.anguyen.weatherforecast_demo.ui.fragments.NotifyDataChange
import com.anguyen.weatherforecast_demo.views.SelectedCitiesView
import kotlinx.android.synthetic.main.activity_selected_cities.*

class SelectedCitiesActivity : AppCompatActivity(), SelectedCitiesView, NotifyDataChange, OnRecycleCityClick{

    private lateinit var mAdapter: SelectedCitiesRecycleAdapter
    private lateinit var mCities: ArrayList<CitiesDetail>
    private var mPresenter: SelectedCitiesPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_cities)

        setupAll()
    }

    private fun setupAll(){
        mPresenter = SelectedCitiesPresenter(this, this)
        mPresenter?.getSelectedCitiesFromStorage()

        //Button Handler
        btn_add_city.onClick {
            CitySelectorFragmentDialog(this).show(supportFragmentManager, CitySelectorFragmentDialog.TAG)
        }

    }

    override fun onChanging() {
        if(mPresenter != null){
            mPresenter?.getSelectedCitiesFromStorage()
        }
    }

    override fun onGetSuccess(callbackData: Map<String, *>?) {
        setupRecyclerView(callbackData)
    }

    private fun setupRecyclerView(citiesData: Map<String, *>?) {
        mCities = ArrayList()

        //Read callback data from SharePreference
        citiesData?.forEach {
            mCities.add(CitiesDetail(it.component1(), it.component2().toString()))
        }

        //Set up Adapter
        mAdapter = SelectedCitiesRecycleAdapter(this, mCities, this)

        //Set up RecycleView
        recycle_selected_cities.setupVertically(this, mAdapter)
    }

    override fun onItemClickHandler(cityId: String?, cityName: String?) {

    }

    override fun onGetFailure() {
        showGeneralErrorDialog(this)
    }

}