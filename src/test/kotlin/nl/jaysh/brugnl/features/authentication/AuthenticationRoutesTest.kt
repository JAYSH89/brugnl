package nl.jaysh.brugnl.features.authentication

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import nl.jaysh.brugnl.features.authentication.service.AuthenticationService
import nl.jaysh.brugnl.features.helper.AuthenticationHelper
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
        coEvery { service.login() } returns "login"

        webTestClient.post()
            .uri("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(AuthenticationHelper.VALID_AUTHENTICATION_REQUEST)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.response").isEqualTo("login")

        coVerify { service.login() }
    }

    @Test
    fun `register valid credential should 200 OK`() = runTest {
        coEvery { service.register() } returns "register"

        webTestClient.post()
            .uri("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(AuthenticationHelper.VALID_AUTHENTICATION_REQUEST)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.response").isEqualTo("register")

        coVerify { service.register() }
    }
}