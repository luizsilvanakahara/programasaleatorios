package br.edu.cruzeirodosul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
//				.permitAll().antMatchers("/poc/lista/**")
//				.access("hasRole('ADMIN')").antMatchers("/db/**")
//				.access("hasRole('ADMIN') and hasRole('DBA')").anyRequest()
//				.authenticated().and().requestCache()
//				.requestCache(new NullRequestCache()) //
				.and().httpBasic() //
				.and().csrf().disable();
	}

}
