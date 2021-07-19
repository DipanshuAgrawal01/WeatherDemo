package com.example.weatherdemo.di

import android.content.Context
import com.example.weatherdemo.data.source.local.CitiesDao
import com.example.weatherdemo.data.source.local.WeatherDao
import com.example.weatherdemo.data.source.local.WeatherDemoDatabase
import com.example.weatherdemo.data.source.remote.RetrofitWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideCitiesDao(weatherDemoDatabase: WeatherDemoDatabase): CitiesDao =
        weatherDemoDatabase.citiesDao()

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDemoDatabase: WeatherDemoDatabase): WeatherDao =
        weatherDemoDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WeatherDemoDatabase =
        WeatherDemoDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideWeatherApiService(): RetrofitWeatherApi =
        RetrofitWeatherApi.getRetrofit().create(RetrofitWeatherApi::class.java)
}