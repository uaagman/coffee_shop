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
//                .antMatchers(HttpMethod.POST,"/save").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            /*.httpBasic()
                .and()*/
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/secure",true)
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
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username=(select id from users where username=?)");
	}
}