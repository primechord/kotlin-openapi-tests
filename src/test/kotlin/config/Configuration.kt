package config

import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC

object Configuration {
    const val httpLogging = true
    val loggingLevel = BASIC
}