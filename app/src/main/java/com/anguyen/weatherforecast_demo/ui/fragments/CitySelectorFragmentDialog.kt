package com.anguyen.weatherforecast_demo.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.adapters.CitySelectorRecycleAdapter
import com.anguyen.weatherforecast_demo.adapters.OnRecycleCityClick
import com.anguyen.weatherforecast_demo.commons.*
import com.anguyen.weatherforecast_demo.models.CitiesDetail
import com.anguyen.weatherforecast_demo.presenters.CitySelectorDialogPresenter
import com.anguyen.weatherforecast_demo.views.CitySelectorDialogView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.fragment_selected_cities_dialog.*

interface NotifyDataChange{
    fun onChanging()
}

class CitySelectorFragmentDialog(
    private val mNotifyDataChange: NotifyDataChange
) : BottomSheetDialogFragment(),
    CitySelectorDialogView,
    OnRecycleCityClick
{

    private lateinit var mPresenter: CitySelectorDialogPresenter

    private lateinit var mAdapter: CitySelectorRecycleAdapter
    private lateinit var mCities: ArrayList<CitiesDetail>

    private lateinit var citiesData: JsonArray

    companion object{
        const val TAG = "CitySelectorFragmentDialog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_cities_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAll()
    }

    private fun setupAll(){

        mPresenter = CitySelectorDialogPresenter(context!!, this)

        //Setup data for adapter
        mPresenter.getAllCities()

        //SetupSearchView
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        search_city.apply {
            //set info
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            //set hint
            queryHint = getString(R.string.hint_search)
            //set on searching handler
            onQueryRequest(
                onQueryTextChange = {
                    if(it != ""){
                        setupRecyclerView(it)
                    }else{
                        setupRecyclerView(null)
                    }
                },
                onQueryTextSubmit = {}
            )
        }

        //Set cancel click
        txt_cancel.onClick { dismiss() }

    }

    override fun onGetDataSuccess(data: JsonArray?) {
        citiesData = data!!
    }

    private fun setupRecyclerView(strSearch: String?){

        mCities = ArrayList()

        if(strSearch != null){
            citiesData.forEach { element ->

                val id = element?.asJsonObject?.get("apiId")?.asString!!
                val cityName = element.asJsonObject?.get("detail")?.asString!!
                val country = element.asJsonObject?.get("country")?.asString!!

                if (id.contains(strSearch, true) or cityName.contains(strSearch, true)){
                    mCities.add(CitiesDetail(id, "$cityName, $country"))
                }
            }
        }

        mAdapter = CitySelectorRecycleAdapter(context!!, mCities, this)

        if(mAdapter.itemCount == 0){
            onFindCityFailure()
        }

        recycle_cities.setupVertically(context!!, mAdapter)

    }

    override fun onItemClickHandler(cityId: String?, cityName: String?) {
        mPresenter.saveCityToStorage(cityId, cityName)
        mNotifyDataChange.onChanging()
    }

    override fun onFindCityFailure() {
        showToast(context!!, "Not Found")
    }


    override fun onGetDataFailure(message: String?) {
        showDataGettingFailed(context!!, message!!)
    }

    override fun onCityInfoSavingSuccess() {
        dismiss()
    }

    override fun onCityInfoSavingFailure() {
        showGeneralErrorDialog(context!!)
    }
    
}