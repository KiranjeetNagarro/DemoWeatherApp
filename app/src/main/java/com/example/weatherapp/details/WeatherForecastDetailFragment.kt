package com.example.weatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.R
import com.example.weatherapp.adaptor.WeatherDetailAdaptor
import com.example.weatherapp.databinding.FragmentWeatherForecastDetailBinding
import com.example.weatherapp.extension.isTheSameDay
import com.example.weatherapp.extension.toDataModel
import com.example.weatherapp.extension.toDate
import com.example.weatherapp.uidatamodel.WeatherDetailUIModel
import java.util.Date

class WeatherForecastDetailFragment : Fragment() {

    private val viewModel: WeatherForecastDetailViewModel by viewModels()
    private var binding: FragmentWeatherForecastDetailBinding? = null

    private val args by navArgs<WeatherForecastDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_forecast_detail,
            container,
            false
        )
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "Weather Forecast"
        binding?.viewModel = viewModel
        viewModel.setData(args.entity)
        setAdaptor()
        return binding?.root
    }

    private fun setAdaptor() {

        val adapter = WeatherDetailAdaptor()
        adapter.setWeatherList(getList())
        binding?.rvWeatherList?.adapter = adapter
    }

    private fun getList(): List<WeatherDetailUIModel> {
        val currentDate = Date()
        return args.entity.weatherData.filterNot {
            it.dtTxt.toDate()?.isTheSameDay(currentDate) == true
        }.map {
            it.toDataModel()
        }.distinctBy {
            it.day
        }
    }
}