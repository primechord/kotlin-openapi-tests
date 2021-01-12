package configuration

import org.aeonbits.owner.ConfigFactory

/* need a review */
val creds: Credentials = ConfigFactory.create(Credentials::class.java)
val logging: Logging = ConfigFactory.create(Logging::class.java)
