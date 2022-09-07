package ru.timutkin.pastebin.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.timutkin.pastebin.store.entity.UserEntity;
import ru.timutkin.pastebin.store.repository.UserRepository;

@Configuration
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

}
