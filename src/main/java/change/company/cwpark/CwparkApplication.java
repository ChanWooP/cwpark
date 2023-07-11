package change.company.cwpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"change.company.cwpark.data.repository"})
public class CwparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CwparkApplication.class, args);
	}

}