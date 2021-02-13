package serv.service;
import org.springframework.http.ResponseEntity;
import serv.dbase.DataBase;
import java.sql.Connection;




public class PBMain {


    private DataBase base;
    private Connection connection;

    /**
     *  Создание потоков работы на сервере
     */
    public PBMain(String DB_URL, String user, String password) {

        base = new DataBase(DB_URL, user, password);

    }

    /**
     *
     * @param color Команда, содержащая информацию о пикселе
     * @return Признак успеха
     */
    public void insert(String color) {

        base.insertPixel(color);

    }

    public ResponseEntity<String> select() {

        return base.getPixelMap();
    }




}
