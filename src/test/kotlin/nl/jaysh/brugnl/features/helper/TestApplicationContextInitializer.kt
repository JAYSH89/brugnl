package nl.jaysh.brugnl.features.helper

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class TestApplicationContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        System.setProperty("API_KEY", "api-key")
        System.setProperty("FIREBASE_BASE_URL", "firebase-base-url")
        System.setProperty("TOKEN_BASE_URL", "token-base-url")
    }
}