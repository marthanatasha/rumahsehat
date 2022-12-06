package apap.tugas.akhir.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/bootstrap/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/appointment").hasAnyAuthority("ADMIN", "DOKTER")
//                .antMatchers("/api/v1/appointment/**").hasAuthority("PASIEN") // TODO: kalo udah ada jwt2an, tambahin ini
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Autowired
    // public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    // }

}
