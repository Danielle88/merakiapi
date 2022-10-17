package br.com.fiap.merakiapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
            .authorizeHttpRequests()
                //usuario
                .antMatchers(HttpMethod.GET, "/api/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuario").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/usuario/**").authenticated()
                //diário
                .antMatchers(HttpMethod.GET, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/diario/**").authenticated()

                //Login
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()

                .antMatchers("/h2-console/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
                //REST - stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .headers().frameOptions().disable()
            .and()
                .addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();

    }

    // @Bean
    // public UserDetailsService users(){
    //     UserDetails user = User.builder()
    //         .username("joao@fiap.com.br")
    //         .password("$2a$12$UNKg1nr3qa8M0uwIS02Hse4LSEf.9ixMEihTkQa96no7fxoskIpD2")
    //         .roles("USER")
    //         .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
