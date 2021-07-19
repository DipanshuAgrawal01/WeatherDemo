package com.example.weatherdemo.data.source.remote

import com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates.CurrentAndDailyWeatherResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids.CurrentWeatherListResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.CurrentWeatherForCityResponse
import com.example.weatherdemo.util.convertResponseBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRetrofitDataSource @Inject constructor(
    private val retrofitWeatherApi: RetrofitWeatherApi,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRemoteDataSource {

    override suspend fun getCurrentWeatherForCitiesByIds(
        idsString: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentWeatherListResponse> = withContext(ioDispatcher) {
        try {
            val response = retrofitWeatherApi.getCurrentWeatherForCitiesByIds(
                idsString, measureUnits, responseLanguage, weatherApiKey
            )
            val currentWeatherListResponse = response.body()
            if (response.isSuccessful && currentWeatherListResponse != null) {
                return@withContext Result.Success(currentWeatherListResponse)
            } else {
                val errorResponse = RetrofitWeatherApi.getRetrofit()
                    .convertResponseBody<BaseErrorResponse>(response.errorBody())
                var errorText = errorResponse?.message
                if (errorText.isNullOrEmpty()) errorText = GET_CURRENT_WEATHER_FOR_CITIES_ERROR
                return@withContext Result.Error(Exception(CustomException(errorText)))
            }
        } catch (t: Throwable) {
            return@withContext Result.Error(Exception(t))
        }
    }

    override suspend fun getCurrentWeatherForCityByName(
        cityName: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentWeatherForCityResponse> = withContext(ioDispatcher) {
        try {
            val response = retrofitWeatherApi.getCurrentWeatherForCityByName(
                cityName, measureUnits, responseLanguage, weatherApiKey
            )
            val currentWeatherForCityResponse = response.body()
            if (response.isSuccessful && currentWeatherForCityResponse != null) {
                return@withContext Result.Success(currentWeatherForCityResponse)
            } else {
                val errorResponse = RetrofitWeatherApi.getRetrofit()
                    .convertResponseBody<BaseErrorResponse>(response.errorBody())
                var errorText = errorResponse?.message
                if (errorText.isNullOrEmpty()) errorText = GET_CURRENT_WEATHER_FOR_CITY_ERROR
                return@withContext Result.Error(Exception(CustomException(errorText)))
            }
        } catch (t: Throwable) {
            return@withContext Result.Error(Exception(t))
        }
    }

    override suspend fun getCurrentAndDailyWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        excludeFields: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<CurrentAndDailyWeatherResponse> = withContext(ioDispatcher) {
        try {
            val response = retrofitWeatherApi.getCurrentAndDailyWeatherByCoordinates(
                latitude, longitude, excludeFields, measureUnits, responseLanguage, weatherApiKey
            )
            val currentAndDailyWeatherResponse = response.body()
            if (response.isSuccessful && currentAndDailyWeatherResponse != null) {
                return@withContext Result.Success(currentAndDailyWeatherResponse)
            } else {
                val errorResponse = RetrofitWeatherApi.getRetrofit()
                    .convertResponseBody<BaseErrorResponse>(response.errorBody())
                var errorText = errorResponse?.message
                if (errorText.isNullOrEmpty()) errorText = GET_CURRENT_AND_DAILY_WEATHER
                return@withContext Result.Error(Exception(CustomException(errorText)))
            }
        } catch (t: Throwable) {
            return@withContext Result.Error(Exception(t))
        }
    }

    companion object {
        private const val GET_CURRENT_WEATHER_FOR_CITIES_ERROR =
            "Не удалось получить данные о текущей погоде для городов"
        private const val GET_CURRENT_WEATHER_FOR_CITY_ERROR =
            "Не удалось получить данные о текущей погоде для города"
        private const val GET_CURRENT_AND_DAILY_WEATHER = "Не удалось получить данные о погоде"
    }
}