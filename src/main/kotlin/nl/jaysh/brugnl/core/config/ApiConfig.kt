package nl.jaysh.brugnl.core.config

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class ApiConfig(private val environment: Environment) {
    val apiKey: String = System.getenv("API_KEY")
    val firebaseBaseUrl: String = System.getenv("FIREBASE_BASE_URL")
    val tokenBaseUrl: String = System.getenv("TOKEN_BASE_URL")
}