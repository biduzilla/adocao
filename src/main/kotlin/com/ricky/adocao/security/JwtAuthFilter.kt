package com.ricky.adocao.security

import com.ricky.adocao.service.impl.UsuarioServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthFilter(
    private val jwtService: JwtService,
    private val usuarioService: UsuarioServiceImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")

        if (authorization != null && authorization.startsWith("Bearer")) {
            val token = authorization.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val isValid: Boolean = jwtService.isTokenValid(token)
            if (isValid) {
                val loginUser: String = jwtService.extractUserName(token)
                val user = usuarioService.loadUserByUsername(loginUser)
                val usuario = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                usuario.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usuario
            }
        }

        filterChain.doFilter(request, response)
    }

}