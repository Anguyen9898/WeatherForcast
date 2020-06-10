package com.anguyen.weatherforecast_demo.presenters

import android.content.Context
import com.anguyen.weatherforecast_demo.commons.GeneralConstValue
import com.anguyen.weatherforecast_demo.views.SelectedCitiesView

class SelectedCitiesPresenter(
    mContext: Context,
    private val mView: SelectedCitiesView
) {
    private val sharePref = mContext.getSharedPreferences(
        GeneralConstValue.CITES_SHARE_PREF_TAG,
        Context.MODE_PRIVATE
    )

    fun getSelectedCitiesFromStorage(){
        val citiesData = sharePref.all
        if(citiesData != null){
            mView.onGetSuccess(citiesData)
        }else{
            mView.onGetFailure()
        }
    }

}