package com.ricky.adocao.configuration

import com.ricky.adocao.security.JwtAuthFilter
import com.ricky.adocao.security.JwtService
import com.ricky.adocao.service.impl.UsuarioServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val usuarioService: UsuarioServiceImpl,
    private val jwtService: JwtService
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    protected fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun jwtFilter(): OncePerRequestFilter {
        return JwtAuthFilter(jwtService, usuarioService)
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain  {
        http.authorizeHttpRequests {
            it.requestMatchers("/usuario")?.hasAuthority("ADMIN")
                ?.requestMatchers(antMatcher("/h2-console/**"))?.permitAll()
                ?.requestMatchers(antMatcher("h2-console/**"))?.permitAll()
                ?.requestMatchers(antMatcher("/h2-console/"))?.permitAll()
                ?.requestMatchers(antMatcher("/h2-console"))?.permitAll()
                ?.anyRequest()
                ?.authenticated()
        }.sessionManagement {
            it?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.formLogin {
            it?.disable()
        }.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)



        return http.build()
    }
}