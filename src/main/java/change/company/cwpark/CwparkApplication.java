package change.company.cwpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableJpaAuditing
public class CwparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CwparkApplication.class, args);
	}

}