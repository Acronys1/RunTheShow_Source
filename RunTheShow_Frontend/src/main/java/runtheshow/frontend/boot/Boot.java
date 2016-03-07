package runtheshow.frontend.boot;

import java.security.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.frontend.config.AppConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
@EnableZuulProxy
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
public class Boot {

    public static void main(String[] args) {
        // on prépare la configuration
        SpringApplication app = new SpringApplication(AppConfiguration.class);
        app.setLogStartupInfo(false);
        // on la lance
        ConfigurableApplicationContext context = app.run(args);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
