package nl.jaysh.brugnl.core.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    @Bean
    @Throws(java.io.FileNotFoundException::class, java.io.IOException::class)
    fun initializeFirebase(): FirebaseAuth {
        val fileName = "service-account.json"
        val serviceAccount = FileInputStream(fileName)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
        return FirebaseAuth.getInstance()
    }
}