/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.boot;

/**
 *
 * @author maxim
 */

import java.security.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.config.AppConfiguration;


@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class Boot {
        
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AppConfiguration.class);
		app.setLogStartupInfo(false);
		// on la lance
		ConfigurableApplicationContext context = app.run(args);
	}
        
        @RequestMapping("/")
	public Principal user(Principal user) {
		return user;
	}
        
        

}
