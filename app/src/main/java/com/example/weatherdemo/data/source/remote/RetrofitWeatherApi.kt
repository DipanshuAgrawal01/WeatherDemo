package com.example.weatherdemo.data.source.remote

import com.example.weatherdemo.BuildConfig
import com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates.CurrentAndDailyWeatherResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids.CurrentWeatherListResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.CurrentWeatherForCityResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitWeatherApi {

    @GET("group")
    suspend fun getCurrentWeatherForCitiesByIds(
        @Query("id") idsString: String,
        @Query("units") measureUnits: String,
        @Query("lang") responseLanguage: String,
        @Query("appid") weatherApiKey: String
    ): Response<CurrentWeatherListResponse>

    @GET("weather")
    suspend fun getCurrentWeatherForCityByName(
        @Query("q") cityName: String,
        @Query("units") measureUnits: String,
        @Query("lang") responseLanguage: String,
        @Query("appid") weatherApiKey: String
    ): Response<CurrentWeatherForCityResponse>

    @GET("onecall")
    suspend fun getCurrentAndDailyWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") excludeFields: String,
        @Query("units") measureUnits: String,
        @Query("lang") responseLanguage: String,
        @Query("appid") weatherApiKey: String
    ): Response<CurrentAndDailyWeatherResponse>

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null

        private val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        fun getRetrofit(): Retrofit =
            retrofit ?: synchronized(Retrofit::class.java) {
                retrofit ?: Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
    }
}