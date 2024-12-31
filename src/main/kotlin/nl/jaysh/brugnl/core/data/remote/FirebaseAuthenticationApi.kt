package nl.jaysh.brugnl.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import nl.jaysh.brugnl.core.config.ApiConfig
import nl.jaysh.brugnl.core.data.dto.*
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.context.annotation.Primary
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder
import java.net.URISyntaxException

@Component
@Primary
class FirebaseAuthenticationApi(
    private val firebase: FirebaseAuth,
    private val webClient: WebClient,
    private val apiConfig: ApiConfig,
) : AuthenticationApi {

    @Throws(URISyntaxException::class)
    override suspend fun register(email: String, password: String): AuthenticationResponse {
        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val path = "/accounts:signUp"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        val response = webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseAuthenticationResponseDTO>()

        return response.toAuthenticationResponse()
    }

    @Throws(URISyntaxException::class)
    override suspend fun login(email: String, password: String): AuthenticationResponse {
        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val path = "/accounts:signInWithPassword"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        val response = webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseAuthenticationResponseDTO>()

        return response.toAuthenticationResponse()
    }

    @Throws(FirebaseAuthException::class)
    override fun verify(idToken: String): AuthenticationToken {
        val firebaseToken = firebase.verifyIdToken(idToken)
        return AuthenticationToken(
            claims = firebaseToken.claims,
            uid = firebaseToken.uid,
            emailVerified = firebaseToken.isEmailVerified,
            email = firebaseToken.email,
            name = firebaseToken.name,
            issuer = firebaseToken.issuer,
            tenantId = firebaseToken.tenantId,
            picture = firebaseToken.picture,
        )
    }

    @Throws(URISyntaxException::class)
    override suspend fun refreshToken(refreshToken: String): RefreshResponse {
        val request = FirebaseRefreshRequestDTO(refreshToken = refreshToken)
        val path = "/token"
        val url = apiConfig.tokenBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        val response = webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseRefreshResponseDTO>()

        return response.toRefreshResponse()
    }

    override suspend fun logout() = TODO()
}