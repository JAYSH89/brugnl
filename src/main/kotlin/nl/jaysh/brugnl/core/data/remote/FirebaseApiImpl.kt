package nl.jaysh.brugnl.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import nl.jaysh.brugnl.core.config.ApiConfig
import nl.jaysh.brugnl.core.data.dto.FirebaseAuthenticationRequestDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseAuthenticationResponseDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseRefreshRequestDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseRefreshResponseDTO
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder
import java.net.URISyntaxException

@Component
class FirebaseApiImpl(
    private val firebase: FirebaseAuth,
    private val webClient: WebClient,
    private val apiConfig: ApiConfig,
) : FirebaseApi {

    override suspend fun register(request: FirebaseAuthenticationRequestDTO): FirebaseAuthenticationResponseDTO {
        val path = "/accounts:signUp"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        return webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseAuthenticationResponseDTO>()
    }

    @Throws(URISyntaxException::class)
    override suspend fun login(request: FirebaseAuthenticationRequestDTO): FirebaseAuthenticationResponseDTO {
        val path = "/accounts:signInWithPassword"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        return webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseAuthenticationResponseDTO>()
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

    override suspend fun refreshToken(request: FirebaseRefreshRequestDTO): FirebaseRefreshResponseDTO {
        val path = "/token"
        val url = apiConfig.tokenBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        return webClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .awaitBody<FirebaseRefreshResponseDTO>()
    }

    override suspend fun logout() = TODO()
}