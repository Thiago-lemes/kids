package com.idf.kids.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfigurations(
    private val securityFilter: SecurityFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().apply {
            addAllowedOrigin("http://localhost:3000")
            allowCredentials = true
            addAllowedMethod(HttpMethod.POST)
            addAllowedMethod(HttpMethod.GET)
            addAllowedMethod(HttpMethod.PUT)
            addAllowedMethod(HttpMethod.DELETE)
            addAllowedMethod(HttpMethod.OPTIONS)
            addAllowedHeader("*")
            addAllowedHeader("Authorization")
            addAllowedHeader("Content-Type")
            addAllowedHeader("Access-Control-Allow-Origin")
            addExposedHeader("Access-Control-Allow-Origin")
        }
        source.registerCorsConfiguration("/**", config)

        return http
            .cors { it.configurationSource(source) }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**").permitAll()
                it.requestMatchers("/usuario/**").permitAll()
                it.requestMatchers("/auth/me").authenticated()
                it.requestMatchers("/aluno/register").authenticated()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
