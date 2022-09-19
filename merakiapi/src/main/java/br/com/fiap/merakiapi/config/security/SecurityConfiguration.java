package br.com.fiap.merakiapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.httpBasic()
            .and()
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/usuario/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/diario/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/diario/**").authenticated()
                .anyRequest().denyAll()
            .and()
                .csrf().disable()
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
}
