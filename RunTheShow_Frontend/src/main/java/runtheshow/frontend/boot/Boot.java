package runtheshow.frontend.boot;

import java.security.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import runtheshow.frontend.config.AppConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableZuulProxy
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
@Controller
public class Boot {

    public static void main(String[] args) {
        // on prépare la configuration
        SpringApplication app = new SpringApplication(AppConfiguration.class);
        app.setLogStartupInfo(false);
        // on la lance
        ConfigurableApplicationContext context = app.run(args);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }
    
    // Match everything without a suffix (so not a static resource)
	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}

}
