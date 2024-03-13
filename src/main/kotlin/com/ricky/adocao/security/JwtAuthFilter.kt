package com.ricky.adocao.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.ricky.adocao.dto.ErrorView
import com.ricky.adocao.service.impl.UsuarioServiceImpl
import com.ricky.adocao.utils.I18n
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtAuthFilter(
    private val jwtService: JwtService,
    private val usuarioService: UsuarioServiceImpl,
    private val i18n: I18n,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    @Throws(ExpiredJwtException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")

        if (authorization != null && authorization.startsWith("Bearer")) {
            try {
                val token = authorization.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                val isValid: Boolean = jwtService.isTokenValid(token)
                if (isValid) {
                    val loginUser: String = jwtService.extractUserName(token)
                    val user = usuarioService.loadUserByUsername(loginUser)
                    val usuario = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                    usuario.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usuario
                }
            } catch (e: Exception) {
                handleInvalidAuthorization(request, response)
                e.printStackTrace()
                return
            }
        }
        filterChain.doFilter(request, response)
    }

    @Throws(IOException::class)
    private fun handleInvalidAuthorization(request: HttpServletRequest, response: HttpServletResponse) {
        val error = ErrorView(
            status = HttpStatus.FORBIDDEN.value(),
            error = HttpStatus.FORBIDDEN.name,
            message = i18n.getMessage("token.invalido"),
            path = request.servletPath
        )

        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.writer.write(objectMapper.writeValueAsString(error))
    }
}