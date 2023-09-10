package lab.base;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaAuditing
public class LabBaseApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        // Lấy các biến môi trường cần thiết
        String databaseUrl = dotenv.get("DATABASE_URL");
        String databaseUsername = dotenv.get("DATABASE_USERNAME");
        String databasePassword = dotenv.get("DATABASE_PASSWORD");

        // Sử dụng các biến môi trường trong ứng dụng của bạn, ví dụ:
        System.setProperty("spring.datasource.url", databaseUrl);
        System.setProperty("spring.datasource.username", databaseUsername);
        System.setProperty("spring.datasource.password", databasePassword);
        SpringApplication.run(LabBaseApplication.class, args);
    }

}
