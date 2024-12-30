package nl.jaysh.brugnl.features.authentication.service

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
        coEvery { authenticationRepository.register(email = any(), password = any()) } returns AUTHENTICATION_RESPONSE
        authenticationService.register(email = "test@example.com", password = "testPass123$")
        coVerify { authenticationRepository.register(any(), any()) }
    }

    @Test
    fun `test login user`() = runTest {
        coEvery { authenticationRepository.login(email = any(), password = any()) } returns AUTHENTICATION_RESPONSE
        authenticationService.login(email = "test@example.com", password = "testPass123$")
        coVerify { authenticationRepository.login(any(), any()) }
    }

    @Test
    fun `test refresh token`() = runTest {
        coEvery { authenticationRepository.refresh(any()) } returns REFRESH_RESPONSE
        authenticationService.refresh(token = "testToken")
        coVerify { authenticationRepository.refresh(any()) }
    }

    @Test
    fun `test verify`() {
        TODO("TODO: TEST VERIFY")
    }

    @Test
    fun `test logout`() {
        TODO("TODO: TEST LOGOUT")
    }
}