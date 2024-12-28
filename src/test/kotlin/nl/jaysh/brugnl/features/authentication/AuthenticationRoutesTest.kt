package nl.jaysh.brugnl.features.authentication

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import nl.jaysh.brugnl.features.authentication.service.AuthenticationService
import nl.jaysh.brugnl.features.helper.AuthenticationHelper
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.AUTHENTICATION_RESPONSE
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.REFRESH_RESPONSE
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.VALID_REFRESH_REQUEST
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest
@Import(AuthenticationRoutes::class, AuthenticationHandler::class)
class AuthenticationRoutesTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var service: AuthenticationService

    @Test
    fun `login valid credential should 200 OK`() = runTest {
        coEvery { service.login(any(), any()) } returns AUTHENTICATION_RESPONSE

        webTestClient.post()
            .uri("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(AuthenticationHelper.VALID_AUTHENTICATION_REQUEST)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.kind").isEqualTo(AUTHENTICATION_RESPONSE.kind)
            .jsonPath("$.localId").isEqualTo(AUTHENTICATION_RESPONSE.localId)
            .jsonPath("$.email").isEqualTo(AUTHENTICATION_RESPONSE.email)
            .jsonPath("$.displayName").isEqualTo(AUTHENTICATION_RESPONSE.displayName!!)
            .jsonPath("$.idToken").isEqualTo(AUTHENTICATION_RESPONSE.idToken)
            .jsonPath("$.registered").isEqualTo(AUTHENTICATION_RESPONSE.registered!!)
            .jsonPath("$.refreshToken").isEqualTo(AUTHENTICATION_RESPONSE.refreshToken)
            .jsonPath("$.expiresIn").isEqualTo(AUTHENTICATION_RESPONSE.expiresIn)

        coVerify { service.login(any(), any()) }
    }

    @Test
    fun `register valid credential should 200 OK`() = runTest {
        coEvery { service.register(any(), any()) } returns AUTHENTICATION_RESPONSE

        webTestClient.post()
            .uri("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(AuthenticationHelper.VALID_AUTHENTICATION_REQUEST)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.kind").isEqualTo(AUTHENTICATION_RESPONSE.kind)
            .jsonPath("$.localId").isEqualTo(AUTHENTICATION_RESPONSE.localId)
            .jsonPath("$.email").isEqualTo(AUTHENTICATION_RESPONSE.email)
            .jsonPath("$.displayName").isEqualTo(AUTHENTICATION_RESPONSE.displayName!!)
            .jsonPath("$.idToken").isEqualTo(AUTHENTICATION_RESPONSE.idToken)
            .jsonPath("$.registered").isEqualTo(AUTHENTICATION_RESPONSE.registered!!)
            .jsonPath("$.refreshToken").isEqualTo(AUTHENTICATION_RESPONSE.refreshToken)
            .jsonPath("$.expiresIn").isEqualTo(AUTHENTICATION_RESPONSE.expiresIn)

        coVerify { service.register(any(), any()) }
    }

    @Test
    fun `refresh valid credential should 200 OK`() = runTest {
        coEvery { service.refresh(any()) } returns REFRESH_RESPONSE

        webTestClient.post()
            .uri("/api/v1/auth/refresh")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(VALID_REFRESH_REQUEST)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.accessToken").isEqualTo(REFRESH_RESPONSE.accessToken)
            .jsonPath("$.expiresIn").isEqualTo(REFRESH_RESPONSE.expiresIn)
            .jsonPath("$.tokenType").isEqualTo(REFRESH_RESPONSE.tokenType)
            .jsonPath("$.refreshToken").isEqualTo(REFRESH_RESPONSE.refreshToken)
            .jsonPath("$.idToken").isEqualTo(REFRESH_RESPONSE.idToken)
            .jsonPath("$.userId").isEqualTo(REFRESH_RESPONSE.userId)
            .jsonPath("$.projectId").isEqualTo(REFRESH_RESPONSE.projectId)

        coVerify { service.refresh(any()) }
    }
}