package com.apk.productInventory.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apk.productInventory.filter.JwtRequestFilter;
import com.apk.productInventory.service.MyUserDetailsService;

import ch.qos.logback.core.db.dialect.MySQLDialect;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Following implementation : authorization through JWT token

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/authenticate")
			.permitAll().anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// Following implementation is custom config (role based access) for spring security
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http .authorizeHttpRequests((authz) -> authz
	 * .anyRequest().authenticated() ) .httpBasic(withDefaults()); return
	 * http.build(); }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // Set your configuration on the auth object
	 * auth.inMemoryAuthentication() .withUser("blah" ) .password("blah")
	 * .roles("USER") .and() .withUser("foo") .password("foo") .roles("ADMIN"); }
	 */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests() .antMatchers("/admin").hasRole("ADMIN")
	 * .antMatchers("/user").hasAnyRole("USER", "ADMIN")
	 * .antMatchers("/").permitAll() .and().formLogin(); }
	 */

}
