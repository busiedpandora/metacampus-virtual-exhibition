package metacampus2.config;

import metacampus2.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/users/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/spaces/display-panels")
                        .permitAll())
                .logout(logout -> logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/users/login")
                                .permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/resources/images").authenticated()
                        .requestMatchers("/resources/texts").authenticated()
                        .requestMatchers("/resources/audios").authenticated()
                        .requestMatchers("/spaces/display-panels").authenticated()
                        .requestMatchers("/spaces/text-panels").authenticated()
                        .requestMatchers("/users/login","/users/register").permitAll()
                        //.requestMatchers("/spaces/display-panels/*/delete").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/spaces/text-panels/*/delete").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/audios/*/delete").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/images/*/delete").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/texts/*/delete").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/spaces/display-panels/*/edit").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/spaces/text-panels/*/edit").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/audios/*/edit").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/images/*/edit").hasRole(Role.CREATOR.name())
                        //.requestMatchers("/resources/texts/*/edit").hasRole(Role.CREATOR.name())
                        .requestMatchers("/spaces/display-panels/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/spaces/text-panels/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/resources/audios/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/resources/images/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/resources/texts/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/metaverses/new").hasRole(Role.CREATOR.name())
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/fonts/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder BCPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}