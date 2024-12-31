package nl.jaysh.brugnl.core.data.remote

import kotlinx.coroutines.test.runTest
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.AUTHENTICATION_RESPONSE
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class InMemoryAuthenticationApiTest {

    private lateinit var inMemoryAuthenticationApi: InMemoryAuthenticationApi

    private val testEmail: String = "testmail@example.com"
    private val testPassword: String = "testPass123$"

    @BeforeEach
    fun setup() {
        inMemoryAuthenticationApi = InMemoryAuthenticationApi()
    }

    @Test
    fun testRegisterSuccess() = runTest {
        val result = inMemoryAuthenticationApi.register(testEmail, testPassword)
        val expected = AUTHENTICATION_RESPONSE.copy(email = testEmail)

        assertEquals(expected, result)
    }

    @Test
    fun testRegisterAlreadyExists() = runTest {
        inMemoryAuthenticationApi.register(testEmail, testPassword)

        assertThrows<IllegalArgumentException> {
            inMemoryAuthenticationApi.register(testEmail, testPassword)
        }
    }

    @Test
    fun testLoginSuccess() = runTest {
        inMemoryAuthenticationApi.register(testEmail, testPassword)
        val result = inMemoryAuthenticationApi.login(testEmail, testPassword)
        val expected = AUTHENTICATION_RESPONSE.copy(email = testEmail)

        assertEquals(expected, result)
    }

    @Test
    fun testLoginPasswordIncorrect() = runTest {
        inMemoryAuthenticationApi.register(testEmail, testPassword)

        assertThrows<IllegalArgumentException> {
            inMemoryAuthenticationApi.login(email = testEmail, password = "invalid_password")
        }
    }

    @Test
    fun testLoginUserNotExists() = runTest {
        assertThrows<IllegalArgumentException> {
            inMemoryAuthenticationApi.login(email = testEmail, password = testPassword)
        }
    }
}