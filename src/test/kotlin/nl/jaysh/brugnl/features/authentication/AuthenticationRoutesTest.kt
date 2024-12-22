package nl.jaysh.brugnl.features.authentication

import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.ServerRequest

@WebFluxTest
@Import(AuthenticationRoutes::class)
class AuthenticationRoutesTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    private lateinit var authHandler: AuthenticationHandler




}