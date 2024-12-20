package lk.talentfort.health_information_system.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(antMatchers(HttpMethod.POST, "/authenticate")).permitAll();
                    auth.requestMatchers(antMatchers(HttpMethod.POST, "/register")).permitAll();
                    auth.requestMatchers("/error/**").permitAll();
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");  // Use "ADMIN", not "ROLE_ADMIN"
                    auth.requestMatchers("/users/**").hasAnyRole("USER_LEVEL1","ADMIN");    // Use "USER", not "ROLE_USER"
                    auth.requestMatchers("/special_users/**").hasAnyRole("USER_LEVEL2","ADMIN");
                    auth.requestMatchers("/nurses/**").hasAnyRole("NURSE_LEVEL1","ADMIN");
                    auth.requestMatchers("/special_nurses/**").hasAnyRole("NURSE_LEVEL2","ADMIN");
                    auth.requestMatchers("/doctors/**").hasAnyRole("DOCTOR_LEVEL1","ADMIN");
                    auth.requestMatchers("/special_doctors/**").hasAnyRole("DOCTOR_LEVEL2","ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    private RequestMatcher antMatchers(HttpMethod httpMethod, String antPattern) {
        return new AntPathRequestMatcher(antPattern, httpMethod.name());
    }
}
