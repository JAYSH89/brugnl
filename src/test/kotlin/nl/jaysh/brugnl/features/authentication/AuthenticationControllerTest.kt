package nl.jaysh.brugnl.features.authentication

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.AUTHENTICATION_RESPONSE
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.REFRESH_RESPONSE
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.VALID_AUTHENTICATION_REQUEST
import nl.jaysh.brugnl.features.helper.AuthenticationHelper.VALID_REFRESH_REQUEST
import nl.jaysh.brugnl.features.helper.toJson
import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AuthenticationController::class)
@Import(AuthenticationService::class)
@ImportAutoConfiguration(exclude = [DataSourceAutoConfiguration::class, ExposedAutoConfiguration::class])
class AuthenticationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: AuthenticationService

    @Test
    fun `login valid credential should 200 OK`() = runTest {
        every { service.login(any(), any()) } returns AUTHENTICATION_RESPONSE

        mockMvc.perform(
            post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_AUTHENTICATION_REQUEST.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.kind").value(AUTHENTICATION_RESPONSE.kind))
            .andExpect(jsonPath("$.localId").value(AUTHENTICATION_RESPONSE.localId))
            .andExpect(jsonPath("$.email").value(AUTHENTICATION_RESPONSE.email))
            .andExpect(jsonPath("$.displayName").value(AUTHENTICATION_RESPONSE.displayName!!))
            .andExpect(jsonPath("$.idToken").value(AUTHENTICATION_RESPONSE.idToken))
            .andExpect(jsonPath("$.registered").value(AUTHENTICATION_RESPONSE.registered!!))
            .andExpect(jsonPath("$.refreshToken").value(AUTHENTICATION_RESPONSE.refreshToken))
            .andExpect(jsonPath("$.expiresIn").value(AUTHENTICATION_RESPONSE.expiresIn))

        verify { service.login(any(), any()) }
    }

    @Test
    fun `register valid credential should 200 OK`() = runTest {
        every { service.register(any(), any()) } returns AUTHENTICATION_RESPONSE

        mockMvc.perform(
            post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_AUTHENTICATION_REQUEST.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.kind").value(AUTHENTICATION_RESPONSE.kind))
            .andExpect(jsonPath("$.localId").value(AUTHENTICATION_RESPONSE.localId))
            .andExpect(jsonPath("$.email").value(AUTHENTICATION_RESPONSE.email))
            .andExpect(jsonPath("$.displayName").value(AUTHENTICATION_RESPONSE.displayName!!))
            .andExpect(jsonPath("$.idToken").value(AUTHENTICATION_RESPONSE.idToken))
            .andExpect(jsonPath("$.registered").value(AUTHENTICATION_RESPONSE.registered!!))
            .andExpect(jsonPath("$.refreshToken").value(AUTHENTICATION_RESPONSE.refreshToken))
            .andExpect(jsonPath("$.expiresIn").value(AUTHENTICATION_RESPONSE.expiresIn))

        verify { service.register(any(), any()) }
    }

    @Test
    fun `refresh valid credential should 200 OK`() = runTest {
        every { service.refresh(any()) } returns REFRESH_RESPONSE

        mockMvc.perform(
            post("/api/v1/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_REFRESH_REQUEST.toJson())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.accessToken").value(REFRESH_RESPONSE.accessToken))
            .andExpect(jsonPath("$.expiresIn").value(REFRESH_RESPONSE.expiresIn))
            .andExpect(jsonPath("$.tokenType").value(REFRESH_RESPONSE.tokenType))
            .andExpect(jsonPath("$.refreshToken").value(REFRESH_RESPONSE.refreshToken))
            .andExpect(jsonPath("$.idToken").value(REFRESH_RESPONSE.idToken))
            .andExpect(jsonPath("$.userId").value(REFRESH_RESPONSE.userId))
            .andExpect(jsonPath("$.projectId").value(REFRESH_RESPONSE.projectId))

        verify { service.refresh(any()) }
    }
}