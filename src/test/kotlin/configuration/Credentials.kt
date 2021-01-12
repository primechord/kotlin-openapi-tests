package configuration

import org.aeonbits.owner.Config

@Config.Sources("classpath:credentials.properties")
interface Credentials : Config {
    fun baseUrl(): String
    fun apiKey(): String
}