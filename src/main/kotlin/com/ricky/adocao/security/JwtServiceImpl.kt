package com.ricky.adocao.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class JwtServiceImpl : JwtService {

    @Value("\${security.jwt.expiracao}")
    private lateinit var expiracao: String

    @Value("\${security.jwt.key}")
    private lateinit var key: String

    override fun extractUserName(token: String): String {
        return getClaims(token).subject
    }

    override fun generateToken(userDetails: UserDetails): String {
        val exp: Long = expiracao.toLong()
        val dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp)
        val data: Date = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant())

        return Jwts.builder()
            .setSubject(userDetails.username)
            .setExpiration(data)
            .signWith(SignatureAlgorithm.HS512, key)
            .compact()
    }

    override fun isTokenValid(token: String): Boolean {
        val claims: Claims = getClaims(token)
        val data: Date = claims.expiration
        val dataExpiracao: LocalDateTime = data
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

        return !LocalDateTime.now().isAfter(dataExpiracao)
    }

    private fun getClaims(token: String?): Claims {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .body;
    }
}