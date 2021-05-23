package serv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/** Класс - точка входа в приложение.*/
@SpringBootApplication
public class RunApp {
    /**
     * Точка входа.
     * @param args аргументы
     */
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }
}
