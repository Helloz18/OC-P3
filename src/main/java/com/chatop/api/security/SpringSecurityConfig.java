package com.chatop.api.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "${spring.application.name}",
                version = "${api.version}",
                description = "${api.description}"
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "${api.server.description}"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringSecurityConfig implements WebMvcConfigurer {

    /**
     * Jwt secret key.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${picture.file.path}")
    private String filePath;

    @Value("${image.endpoint.path}")
    private String imageEndpointPath;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers( imageEndpointPath+"**", "/api/auth/register", "/api/auth/login", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .formLogin(Customizer.withDefaults())
                .build();
    }

    /**
     * Allow the frontend to access the pictures folder defined in filePath.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( imageEndpointPath+"**")
                .addResourceLocations("file:"+filePath);
    }

    /**
     * managing CORS policy
     * @return
     */
    @Bean
    public WebMvcConfigurer configureCors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name())
                .allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);
            }
        };
    }
    /**
     * Creates an instance of BCryptPasswordEncoder in order to encrypt the password.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Use the jwtSecret key to encode the token
     * @return
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtSecret.getBytes()));
    }

    /**
     * Decode the Token with the jwtSecret key
     * @return
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtSecret.getBytes(), 0, this.jwtSecret.getBytes().length,"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    /**
     * Use our LoginRequest (Custom UserDetails) as provider for authentication.
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
