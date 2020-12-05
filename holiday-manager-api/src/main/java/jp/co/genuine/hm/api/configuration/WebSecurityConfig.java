package jp.co.genuine.hm.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jp.co.genuine.hm.api.security.SimpleAuthenticationEntryPoint;
import jp.co.genuine.hm.api.security.SimpleAuthenticationFailureHandler;
import jp.co.genuine.hm.api.security.SimpleAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
				.mvcMatchers("/prelogin", "/ping")
					.permitAll()
				.mvcMatchers("/user/**")
					.hasRole("USER")
				.anyRequest()
					.authenticated()
			.and()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler())
			.and()
			.formLogin()
				.loginProcessingUrl("/login").permitAll()
					.usernameParameter("email")
					.passwordParameter("pass")
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
			.and()
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessHandler(logoutSuccessHandler())
			.and()
			.csrf()
				.csrfTokenRepository(new CookieCsrfTokenRepository());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
			@Qualifier("simpleUserDetailsService") UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) throws Exception {
		auth.eraseCredentials(true)
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private LogoutSuccessHandler logoutSuccessHandler() {
		return new HttpStatusReturningLogoutSuccessHandler();
	}

	private AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleAuthenticationFailureHandler();
	}

	private AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleAuthenticationSuccessHandler();
	}

	private AccessDeniedHandler accessDeniedHandler() {
		return new SimpleAccessDeniedHandler();
	}

	private AuthenticationEntryPoint authenticationEntryPoint() {
		return new SimpleAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**");
	}
}
