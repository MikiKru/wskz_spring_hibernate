package pl.wskz.spring_hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2     // http://localhost:8080/swagger-ui.html
@SpringBootApplication
public class SpringHibernateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateApplication.class, args);
    }
}
