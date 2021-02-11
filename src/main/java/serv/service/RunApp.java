package serv.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication

public class RunApp {

    /**
     *
     * @param args параметры командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }

}


/*package serv.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import serv.dbase.DataBase;

import java.sql.SQLException;
import java.util.Arrays;


@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class RunApp {

    public static void main(String[] args) throws SQLException {
        DataBase dataBase = DataBase.createConnection();
        System.out.println(Arrays.toString(dataBase.getPixelMap()));
        dataBase.close();
    }

}*/