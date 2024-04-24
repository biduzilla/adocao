package com.ricky.adocao.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.ricky.adocao.security.JwtAuthFilter
import com.ricky.adocao.security.JwtService
import com.ricky.adocao.service.impl.UsuarioServiceImpl
import com.ricky.adocao.utils.I18n
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Lazy private val usuarioService: UsuarioServiceImpl,
    private val jwtService: JwtService,
    private val i18n: I18n,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun jwtFilter(): OncePerRequestFilter {
        return JwtAuthFilter(jwtService, usuarioService, i18n, objectMapper)
    }

    @Bean
    fun authenticationManager(
        http: HttpSecurity,
        passwordEncoder: PasswordEncoder,
        userDetailsService: UsuarioServiceImpl
    ): AuthenticationManager {
        val authManagerBuild: AuthenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)

        authManagerBuild
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)

        return authManagerBuild.build()
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.disable()
        }.headers {
            it.frameOptions { frame ->
                frame.disable()
            }
        }.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers("/usuario/login")?.permitAll()
                ?.requestMatchers("/usuario/find-all")?.hasAnyRole("ADMIN")
                ?.requestMatchers("/usuario/get-user/**")?.hasAnyRole("ADMIN", "USER")
                ?.requestMatchers("/usuario/update")?.hasAnyRole("ADMIN", "USER")
                ?.requestMatchers("/usuario/delete-user/**")?.hasAnyRole("ADMIN", "USER")
                ?.requestMatchers("/usuario/alterar-senha")?.permitAll()
                ?.requestMatchers("/messages/**")?.hasAnyRole("ADMIN", "USER")
                ?.requestMatchers(HttpMethod.POST,"/usuario/**")?.permitAll()
                ?.requestMatchers(HttpMethod.GET,"/usuario/**")?.permitAll()
                ?.requestMatchers(HttpMethod.POST,"/report/**")?.hasAnyRole("ADMIN","USER")
                ?.requestMatchers(HttpMethod.GET,"/report/**")?.hasAnyRole("ADMIN")
                ?.requestMatchers(HttpMethod.PUT,"/report/**")?.hasAnyRole("ADMIN")
                ?.requestMatchers(HttpMethod.DELETE,"/report/**")?.hasAnyRole("ADMIN")
                ?.requestMatchers("/h2-console/**")?.permitAll()
                ?.requestMatchers("/h2-console/")?.permitAll()
                ?.requestMatchers("/h2-console")?.permitAll()
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