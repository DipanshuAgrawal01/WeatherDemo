package com.example.weatherdemo.util

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

enum class MessageLength { SHORT, LONG }

fun Context.showMessage(message: String, duration: MessageLength = MessageLength.LONG) {
    val toastDuration = if (duration == MessageLength.LONG) {
        Toast.LENGTH_LONG
    } else {
        Toast.LENGTH_SHORT
    }
    Toast.makeText(this, message, toastDuration).show()
}

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

suspend inline fun <reified T> Retrofit.convertResponseBody(responseBody: ResponseBody?): T? =
    withContext(Dispatchers.Default) {
        responseBody ?: return@withContext null
        val converter: Converter<ResponseBody, T> =
            responseBodyConverter(T::class.java, emptyArray())
        converter.convert(responseBody)
    }

fun List<Long>.composeString(): String {
    var result = ""
    forEach { result += "$it," }
    return result.dropLast(1)
}

fun Double?.getTemperatureString(): String = if (this == null || this == 0.0) {
    ""
} else {
    String.format("%.1f", this) + "°"
}

fun Long.getDateText(): String {
    if (this == 0L) return ""
    val searchCalendar =
        Calendar.getInstance().apply { timeInMillis = this@getDateText * MILLIS_IN_SECOND }
    val formatPattern = "E, d MMM yyyy"
    return SimpleDateFormat(formatPattern, Locale.getDefault()).format(searchCalendar.time)
}

fun Long.getTimeText(): String {
    if (this == 0L) return ""
    val searchCalendar =
        Calendar.getInstance().apply { timeInMillis = this@getTimeText * MILLIS_IN_SECOND }
    val formatPattern = "HH:mm:ss"
    return SimpleDateFormat(formatPattern, Locale.getDefault()).format(searchCalendar.time)
}

fun Int.getPressureText(): String {
    if (this == 0) return ""
    return (this / 1.333).roundToInt().toString() + " мм рт. ст."
}