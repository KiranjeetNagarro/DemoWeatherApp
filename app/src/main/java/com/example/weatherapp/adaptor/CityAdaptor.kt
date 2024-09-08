package com.example.weatherapp.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemCitiesBinding
import com.example.weatherapp.uidatamodel.CityModel

class CityAdaptor(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<CityAdaptor.CityViewHolder>() {
    private var cityList: MutableList<CityModel>? = null

    inner class CityViewHolder(private val binding: ItemCitiesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setCity(model: CityModel) {
            binding.model = model
            binding.clickListener = clickListener
        }
    }

    fun deleteItem(position: Int) {
        this.cityList?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setList(list: List<CityModel>) {
        this.cityList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding: ItemCitiesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cities,
            parent,
            false
        )
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        cityList?.let {
            holder.setCity(it[position])
        }
    }

    override fun getItemCount(): Int {
        return cityList?.size ?: 0
    }

}

interface OnItemClickListener {
    fun onItemClick(model: CityModel)
    fun onDeleteClick(model: CityModel)
}