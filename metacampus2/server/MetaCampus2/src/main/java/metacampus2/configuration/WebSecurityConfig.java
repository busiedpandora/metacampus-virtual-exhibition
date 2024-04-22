package metacampus2.configuration;

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

        return http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/resources/texts").authenticated()
                        .requestMatchers("/resources/images").authenticated()
                        .requestMatchers("/resources/audios").authenticated()
                        .requestMatchers("/resources/texts/new").hasRole("CREATOR")
                        .requestMatchers("/resources/images/new").hasRole("CREATOR")
                        .requestMatchers("/resources/audios/new").hasRole("CREATOR")
                        .requestMatchers("/resources/texts/*/edit").hasRole("CREATOR")
                        .requestMatchers("/resources/images/*/edit").hasRole("CREATOR")
                        .requestMatchers("/resources/audios/*/edit").hasRole("CREATOR")
                        .requestMatchers("/resources/texts/*/delete").hasRole("CREATOR")
                        .requestMatchers("/resources/images/*/delete").hasRole("CREATOR")
                        .requestMatchers("/resources/audios/*/delete").hasRole("CREATOR")
                        .requestMatchers("/spaces/text-panels").authenticated()
                        .requestMatchers("/spaces/display-panels").authenticated()
                        .requestMatchers("/spaces/text-panels/new").hasRole("CREATOR")
                        .requestMatchers("/spaces/display-panels/new").hasRole("CREATOR")
                        .requestMatchers("/spaces/text-panels/*/edit").hasRole("CREATOR")
                        .requestMatchers("/spaces/display-panels/*/edit").hasRole("CREATOR")
                        .requestMatchers("/spaces/text-panels/*/delete").hasRole("CREATOR")
                        .requestMatchers("/spaces/display-panels/*/delete").hasRole("CREATOR")
                        .requestMatchers("/metaverses").authenticated()
                        .requestMatchers("/metaverses/new").hasRole("CREATOR")
                        .requestMatchers("/metaverses/*/edit").hasRole("CREATOR")
                        .requestMatchers("/metaverses/*/delete").hasRole("CREATOR")
                        .requestMatchers("/spaces/*/text-panels").permitAll()
                        .requestMatchers("/spaces/*/text-panels/*/texts/*/*").permitAll()
                        .requestMatchers("/spaces/*/display-panels").permitAll()
                        .requestMatchers("/spaces/*/display-panels/*/images/*/*").permitAll()
                        .requestMatchers("/spaces/*/display-panels/*/images/*/audios/*/*").permitAll()
                        .requestMatchers("/metaverses/metaversesList").permitAll()
                        .requestMatchers("/metaverses/*").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/fonts/**").permitAll()
                        .requestMatchers("/scripts/**").permitAll()
                        .requestMatchers("/icons/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
