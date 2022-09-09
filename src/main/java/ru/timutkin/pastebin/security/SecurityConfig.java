package ru.timutkin.pastebin.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import ru.timutkin.pastebin.store.entity.UserEntity;
import ru.timutkin.pastebin.store.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            UserEntity user = userRepository.findByUsername(username);

            if (user == null){
                throw new UsernameNotFoundException(String.format("User with username = {%s} not found",username));
            }

            return user;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                .antMatchers("/api/v1/pastebin/").permitAll()
                .antMatchers("/register").permitAll()
                .and()
                    .csrf()
                    .csrfTokenRepository(csrfTokenRepository())
                .and()
                .build();

    }


    public CookieCsrfTokenRepository csrfTokenRepository() {
        final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setSecure(true);
        return repository;
    }


}
