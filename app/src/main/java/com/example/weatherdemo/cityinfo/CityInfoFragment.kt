package com.example.weatherdemo.cityinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weatherdemo.R
import com.example.weatherdemo.adapters.DailyWeatherAdapter
import com.example.weatherdemo.base.BaseFragment
import com.example.weatherdemo.databinding.FragmentCityInfoBinding
import com.example.weatherdemo.util.EventObserver
import com.example.weatherdemo.util.NetworkNotifier
import com.example.weatherdemo.util.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityInfoFragment : BaseFragment<CityInfoViewModel, FragmentCityInfoBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_city_info

    override val viewModel: CityInfoViewModel by viewModels()

    private val args: CityInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerDailyForecast.adapter = DailyWeatherAdapter()
        viewModel.onCityIdObtained(args.cityId)
        setObservers()
    }

    private fun setObservers() = with(viewModel) {
        message.observe(viewLifecycleOwner, EventObserver { context?.showMessage(it) })
        NetworkNotifier.networkRestoredEvent.observe(viewLifecycleOwner, EventObserver {
            context?.showMessage(getString(R.string.text_connection_restored))
            viewModel.onNetworkRestored()
        })
    }
}