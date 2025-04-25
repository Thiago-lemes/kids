package com.idf.kids.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.idf.kids.entity.UsuarioEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {

    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(user: UsuarioEntity): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.create()
                .withIssuer("IDF")
                .withSubject(user.email)
                .withClaim("tipo", user.tipo.name)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
        } catch (e: JWTCreationException) {
            throw RuntimeException("Error while generating token", e)
        }
    }

    fun validateToken(token: String): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                .withIssuer("IDF")
                .build()
                .verify(token)
                .subject
        } catch (e: JWTVerificationException) {
            ""
        }
    }

    private fun genExpirationDate(): Instant =
        LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
}
