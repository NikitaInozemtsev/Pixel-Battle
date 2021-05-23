package serv.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Конфигурация программы*/
@Configuration
@EnableJpaRepositories(basePackages = "serv.repositories")
public class Config {
}
