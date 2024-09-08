package com.example.weatherapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.adaptor.CityAdaptor
import com.example.weatherapp.adaptor.OnItemClickListener
import com.example.weatherapp.databinding.FragmentSearchWeatherForecastBinding
import com.example.weatherapp.uidatamodel.CityModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchWeatherForecastFragment : Fragment(), OnItemClickListener {

    private val viewModel: SearchWeatherForecastViewModel by viewModels()
    private var binding: FragmentSearchWeatherForecastBinding? = null
    private lateinit var cityAdaptor: CityAdaptor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_weather_forecast,
            container,
            false
        )
        binding?.viewModel = viewModel
        cityAdaptor = CityAdaptor(this@SearchWeatherForecastFragment)
        binding?.rvCities?.adapter = cityAdaptor
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "Search Weather Forecast"
        initObservers()
        initListeners()
        viewModel.fetchCities()
        return binding?.root
    }

    private fun initListeners() {
        binding?.let { viewBinding ->
            viewBinding.btnSearch.setOnClickListener {
                viewModel.cityName.value?.let { city -> viewModel.getWeatherForecast(city) }
            }
            viewBinding.btnDeleteAll.setOnClickListener {
                viewModel.deleteAllCities()
            }
        }
    }


    private fun initObservers() {

        viewModel.cities.observe(viewLifecycleOwner) { list ->
            cityAdaptor.setList(list)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.something_went_wrong, Toast.LENGTH_LONG)
                .show()
        }

        viewModel.deleteSuccessEvent.observe(viewLifecycleOwner) {
            viewModel.fetchCities()
            Toast.makeText(requireContext(), R.string.delete_success, Toast.LENGTH_LONG)
                .show()
        }

        viewModel.weatherResponse.observe(viewLifecycleOwner) { entity ->
            entity?.let {
                findNavController().navigate(
                    SearchWeatherForecastFragmentDirections.actionNavToFragmentWeatherForecastDetail(
                        it
                    )
                )
            }
        }
    }

    override fun onItemClick(model: CityModel) {
        viewModel.getWeatherForecast(model.cityName)
    }

    override fun onDeleteClick(model: CityModel) {
        viewModel.deleteCity(model.cityName)
    }
}