package com.me.init.initialproject.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        // CSRF 해제
        http.csrf().disable()
        // formLogin 해제
        http.formLogin().disable()
            .httpBasic().disable()
//        // h2-console 은 모두 허가
//        http.authorizeHttpRequests()
//            .requestMatchers("/h2-console/**").permitAll()
//            .and()
//            .csrf().ignoringRequestMatchers("/h2-console/**")
        // X-Frame-Option 허용
        http.headers()
            .frameOptions()
            .sameOrigin().and()


        return http.build()
    }
}