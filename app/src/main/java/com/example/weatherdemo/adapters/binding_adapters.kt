package com.example.weatherdemo.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherdemo.data.CityAndCurrentWeather
import com.example.weatherdemo.data.DailyWeather

@BindingAdapter("app:cities_and_current_weather")
fun RecyclerView.setCitiesAndCurrentWeather(citiesAndCurrentWeather: List<CityAndCurrentWeather>?) {
    (adapter as? CityAndCurrentWeatherAdapter)?.submitList(citiesAndCurrentWeather)
}

@BindingAdapter("app:daily_weather_list")
fun RecyclerView.setDailyWeatherList(dailyWeatherList: List<DailyWeather>?) {
    (adapter as? DailyWeatherAdapter)?.submitList(dailyWeatherList)
}

@BindingAdapter("app:image_from_url")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}