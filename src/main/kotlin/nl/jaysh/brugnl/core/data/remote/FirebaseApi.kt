package nl.jaysh.brugnl.core.data.remote

import nl.jaysh.brugnl.core.data.dto.FirebaseAuthenticationRequestDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseAuthenticationResponseDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseRefreshRequestDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseRefreshResponseDTO
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken

interface FirebaseApi {
    suspend fun register(request: FirebaseAuthenticationRequestDTO): FirebaseAuthenticationResponseDTO
    suspend fun login(request: FirebaseAuthenticationRequestDTO): FirebaseAuthenticationResponseDTO
    suspend fun refreshToken(request: FirebaseRefreshRequestDTO): FirebaseRefreshResponseDTO
    fun verify(idToken: String): AuthenticationToken
    suspend fun logout()
}