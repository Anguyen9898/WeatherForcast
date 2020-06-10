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
import kotlinx.android.synthetic.main.city_selector_item.view.*

class CitySelectorRecycleAdapter(
    private val mContext: Context,
    private val mCities: List<CitiesDetail>,
    private val mOnClickListener: OnRecycleCityClick
): RecyclerView.Adapter<CitySelectorRecycleAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.city_selector_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mCities.size

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val city = mCities[i]

        holder.txtCityId.text = city.id
        holder.txtCityName.text = city.name

        holder.onItemClick(holder.txtCityId.text.toString(), holder.txtCityName.text.toString())
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtCityId = itemView.txt_id!!
        val txtCityName = itemView.txt_city_name!!

        fun onItemClick(cityId: String?, cityName: String?){
            itemView.onClick {
                mOnClickListener.onItemClickHandler(cityId, cityName)
                //set color animation
                itemView.bgColorChangeAnimation(ContextCompat.getColor(mContext, R.color.halfTrans))
            }
        }

    }

}