package com.example.weatherapp.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherDetailBinding
import com.example.weatherapp.uidatamodel.WeatherDetailUIModel

class WeatherDetailAdaptor : RecyclerView.Adapter<WeatherDetailAdaptor.WeatherViewHolder>() {
    private var weatherList: List<WeatherDetailUIModel>? = null

    class WeatherViewHolder(private val binding: ItemWeatherDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setWeather(model: WeatherDetailUIModel) {
            binding.model = model
        }
    }

    fun setWeatherList(weatherList: List<WeatherDetailUIModel>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding: ItemWeatherDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_weather_detail,
            parent,
            false
        );
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        weatherList?.let {
            holder.setWeather(it[position])
        }
    }

    override fun getItemCount(): Int {
        return weatherList?.size ?: 0
    }

}