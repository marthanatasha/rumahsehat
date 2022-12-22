package apap.tugas.akhir.rumahsehat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
                .antMatchers("/appointment/detail/**").hasAnyAuthority("ADMIN", "DOKTER")
                .antMatchers("/appointment/update/**").hasAuthority("DOKTER")
                .antMatchers("/api/v1/appointment/**").hasAuthority("PASIEN")
                .antMatchers("/api/v1/dokter").hasAuthority("PASIEN")
                .antMatchers("/resep/add/{kode}").hasAnyAuthority("DOKTER")
                .antMatchers("/resep").hasAnyAuthority("APOTEKER", "ADMIN")
                .antMatchers("/api/v1/resep/{id}").hasAnyAuthority("PASIEN")
                .antMatchers("/apoteker").hasAnyAuthority("ADMIN")
                .antMatchers("/dokter").hasAnyAuthority("ADMIN")
                .antMatchers("/pasien").hasAnyAuthority("ADMIN")
                .antMatchers("/apoteker/add").hasAnyAuthority("ADMIN")
                .antMatchers("/dokter/add").hasAnyAuthority("ADMIN")
                .antMatchers("/chart/*").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }

}
