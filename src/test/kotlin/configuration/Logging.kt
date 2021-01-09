package configuration

import okhttp3.logging.HttpLoggingInterceptor.Level
import org.aeonbits.owner.Config

@Config.Sources("classpath:logging.properties")
interface Logging : Config {
    fun httpLogging(): Boolean
    fun loggingLevel(): Level
}