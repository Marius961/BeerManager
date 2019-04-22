package ua.product.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.product.manager.entities.Role;
import ua.product.manager.security.JWTAuthenticationFilter;
import ua.product.manager.security.JWTAuthorizationFilter;
import ua.product.manager.services.UserService;

import static ua.product.manager.security.SecurityConstants.SIGN_IN_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    private JWTAuthenticationFilter configuredJwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl(SIGN_IN_URL);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/categories",
                        "/api/subcategories/{id}",
                        "/api/product",
                        "/api/product/{id}",
                        "/api/product/measurement-unit",
                        "/api/product/measurement-unit/**",
                        "/img/**"
                ).permitAll()

                .antMatchers("/api/cart",
                        "/api/cart/**"
                ).hasAuthority(Role.USER.getAuthority())

                .antMatchers(HttpMethod.POST,
                        "/api/product",
                        "/api/product/check"
                ).hasAnyAuthority(Role.USER.getAuthority())

                .antMatchers(
                        "/api/categories**",
                        "/api/categories/{id}",
                        "/api/categories/**",
                        "/api/subcategories**",
                        "/api/subcategories/**",
                        "/api/subcategories/{id}",
                        "/api/product**",
                        "/api/product/**"
                ).hasAuthority(Role.ADMIN.getAuthority())

                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilter(configuredJwtAuthenticationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}