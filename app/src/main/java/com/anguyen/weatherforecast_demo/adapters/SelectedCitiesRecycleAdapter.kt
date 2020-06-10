package com.anguyen.weatherforecast_demo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anguyen.weatherforecast_demo.R
import com.anguyen.weatherforecast_demo.commons.bgColorChangeAnimation
import com.anguyen.weatherforecast_demo.commons.onClick
import com.anguyen.weatherforecast_demo.models.CitiesDetail
import kotlinx.android.synthetic.main.selecleted_city_item.view.*

class SelectedCitiesRecycleAdapter(
    private val mContext: Context,
    private val mCities: List<CitiesDetail>,
    private val mOnClickListener: OnRecycleCityClick
): RecyclerView.Adapter<SelectedCitiesRecycleAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.selecleted_city_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mCities.size

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val city = mCities[i]
        holder.txtId.text = city.id
        holder.txtCityName.text = city.name
        holder.txtTemp.text = city.temp

        holder.onItemClick(holder.txtId.text.toString(), holder.txtCityName.text.toString())

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtId= itemView.txt_id!!
        val txtCityName = itemView.txt_city_name!!
        val txtTemp = itemView.txt_temp!!

        fun onItemClick(cityId: String?, cityName: String?){
            itemView.onClick {
                mOnClickListener.onItemClickHandler(cityId, cityName)
            }
        }
    }

}