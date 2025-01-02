package nl.jaysh.brugnl.core.data.remote.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import nl.jaysh.brugnl.core.config.ApiConfig
import nl.jaysh.brugnl.core.data.dto.*
import nl.jaysh.brugnl.core.data.dto.firebase.*
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URISyntaxException

@Component
@Primary
class FirebaseAuthenticationApi(
    private val firebase: FirebaseAuth,
    private val restTemplate: RestTemplate,
    private val apiConfig: ApiConfig,
) : AuthenticationApi {

    @Throws(URISyntaxException::class)
    override fun register(email: String, password: String): AuthenticationResponse {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }

        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val path = "/accounts:signUp"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()


        val authenticationResponse = restTemplate.exchange(
            uri,
            HttpMethod.POST,
            HttpEntity(request, headers),
            FirebaseAuthenticationResponseDTO::class.java,
        ).body

        requireNotNull(authenticationResponse)
        return authenticationResponse.toAuthenticationResponse()
    }

    @Throws(URISyntaxException::class)
    override fun login(email: String, password: String): AuthenticationResponse {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val path = "/accounts:signInWithPassword"
        val url = apiConfig.firebaseBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        val authenticationResponse = restTemplate.exchange(
            uri,
            HttpMethod.POST,
            HttpEntity(request, headers),
            FirebaseAuthenticationResponseDTO::class.java,
        ).body

        requireNotNull(authenticationResponse)

        return authenticationResponse.toAuthenticationResponse()
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
    override fun refreshToken(refreshToken: String): RefreshResponse {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val request = FirebaseRefreshRequestDTO(refreshToken = refreshToken)
        val path = "/token"
        val url = apiConfig.tokenBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("key", apiConfig.apiKey)
            .build()
            .toUri()

        val refreshResponse = restTemplate.exchange(
            uri,
            HttpMethod.POST,
            HttpEntity(request, headers),
            FirebaseRefreshResponseDTO::class.java,
        ).body

        requireNotNull(refreshResponse)

        return refreshResponse.toRefreshResponse()
    }

    override fun logout() = TODO()
}