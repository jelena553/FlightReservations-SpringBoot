package airline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		   
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests() 
	    .antMatchers("/auth/**", "/styles/**", "/vidljivo/**", "/rezervacija/pronadjiLetove","/rezervacija/pretraga/**","/", "/slike/**", "/login.jsp?error=1", "/registracija/**", "/excep/**").permitAll()
	    .antMatchers("/zaposleni/**", "/radnik/**").hasRole("ZAPOSLENI")
	    .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/registracija/login.jsp")
        .permitAll()
        .loginProcessingUrl("/login")
        .usernameParameter("username")
        .passwordParameter("password")
 //       .defaultSuccessUrl("/Aero/pocetna")
        .failureUrl("/registracija/login.jsp?error=1")
        .and()
        .csrf()
  //     .and().exceptionHandling()
//        .accessDeniedPage("/access_denied");
	    .and()
	    .logout()
	    .logoutSuccessUrl("/");
	    
	}


}
