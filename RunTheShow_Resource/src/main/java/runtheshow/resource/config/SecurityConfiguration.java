    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author maxim
 */
@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        // l'authentification est faite par jdbc authentication en attendant de pouvoir utiliser appUserDetailservice
        // le mot de passe est crypté par l'algorithme de hachage BCrypt

        AppConfiguration uneConfiguration = new AppConfiguration();
        DataSource ds = uneConfiguration.dataSource();

        final String findUserQuery = "select user_login,user_password,user_enabled "
                + "from users "
                + "where user_login = ?";

        final String findRoles = "select u.user_login,r.role_name "
                + "from roles r, users u, users_roles ur "
                + "where u.user_login = ? and u.id = ur.user_id and ur.role_id = r.id";

        registry.jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery(findUserQuery)
                .authoritiesByUsernameQuery(findRoles).passwordEncoder(new BCryptPasswordEncoder(12));
        
        //registry.userDetailsService(appUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/user/add").permitAll()
                .antMatchers("/user/update", "/user/current").authenticated()
                .antMatchers("/user/**").hasAnyRole("ADMIN")
                .antMatchers("/event/**").authenticated()
                .antMatchers("/sousEvent/**").authenticated()
                .antMatchers("/lieu/**").authenticated()
                .anyRequest().permitAll();
    }
}
