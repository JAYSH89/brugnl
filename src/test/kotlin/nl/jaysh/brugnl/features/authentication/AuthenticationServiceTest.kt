package nl.jaysh.brugnl.features.authentication

import io.mockk.*
import kotlinx.coroutines.test.runTest
import nl.jaysh.brugnl.core.data.repository.AuthenticationRepository
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.AUTHENTICATION_RESPONSE
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.REFRESH_RESPONSE
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthenticationServiceTest {

    private val authenticationRepository: AuthenticationRepository = mockk()
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setup() {
        authenticationService = AuthenticationService(repository = authenticationRepository)
    }

    @Test
    fun `test register user`() = runTest {
        every { authenticationRepository.register(email = any(), password = any()) } returns AUTHENTICATION_RESPONSE
        authenticationService.register(email = "test@example.com", password = "testPass123$")
        verify { authenticationRepository.register(any(), any()) }
    }

    @Test
    fun `test login user`() = runTest {
        every { authenticationRepository.login(email = any(), password = any()) } returns AUTHENTICATION_RESPONSE
        authenticationService.login(email = "test@example.com", password = "testPass123$")
        verify { authenticationRepository.login(any(), any()) }
    }

    @Test
    fun `test refresh token`() = runTest {
        every { authenticationRepository.refresh(any()) } returns REFRESH_RESPONSE
        authenticationService.refresh(token = "testToken")
        verify { authenticationRepository.refresh(any()) }
    }
}