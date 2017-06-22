package com.ashokn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private DataSource dataSource;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/index","/css/**","/rs/**","/js/**","/register").permitAll()
//				.antMatchers("/login").not().authenticated()
                .antMatchers("/products","/persons","/allOrders","/editProduct/**","/addProduct","/persons","/addUser").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
            /*.httpBasic()
                .and()*/
            .formLogin()
                .loginPage("/login")
//                .defaultSuccessUrl("/secure")
                .failureUrl("/login?error")
            	.permitAll()
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/login?logout").permitAll()
                .and()
            .csrf()
                .ignoringAntMatchers("/rs/**");
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("super").password("pw").roles("ADMIN");
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select email,password, enabled from users where email=?")
				.authoritiesByUsernameQuery("select user_id, authority from authorities where user_id=(select id from users where email=?)");
	}
}