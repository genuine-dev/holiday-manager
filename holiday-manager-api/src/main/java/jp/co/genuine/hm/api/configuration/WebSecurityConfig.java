package jp.co.genuine.hm.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 認可の設定
		http.authorizeRequests()
				.antMatchers(
									"/",
									"/js/**",
									"/css/**",
									"/swagger-ui.html",
									"/**",
									"/user/**",
									"/user"
								).permitAll()
				.anyRequest().authenticated(); // それ以外は全て認証無しの場合アクセス不許可

		http.csrf().disable();
	}
}
