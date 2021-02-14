package serv.dbase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;

public class DataBase {

    private String DB_URL;
    private String user;
    private String password;
    private Connection connection = null;

    /**
     *
     *
     */
    public void createConnection(){
        try {
            //Проверяем наличие JDBC драйвера для работы с БД
            DriverManager.registerDriver(new org.postgresql.Driver());
            //Попытка установить соединение с базой данных
            connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Соединение с БД выполнено.");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    /**
     *
     *
     */
    public DataBase(String DB_URL, String user, String password) {
        this.DB_URL = DB_URL;
        this.user = user;
        this.password = password;
    }

    /**
     * Закрытие соединения
     */

    public void close() {
        try {
            connection.close();
            System.out.println("Соединение успешно закрыто");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return Возврат обновленного изображения
     */
    public ResponseEntity<String> getPixelMap() {
        String res = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            createConnection();
            st = connection.createStatement();
            rs = st.executeQuery("select color from public.colores");
            if(rs.next()) {
                res = rs.getString(1);
            }
            st.close();
            close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    /**
     *
     * @param color Цвет пикселя
     * @throws SQLException Бросает исключение, если нет подключения
     */
    public void insertPixel(String color) {
        Statement st = null;
        try {
            createConnection();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery("select color from public.colores");
            String sql = "";
            if(rs.next()) {
                sql = "UPDATE public.colores SET color = '" + color + "'";
            }
            else {
                sql = "INSERT INTO public.colores (color) Values('" + color + "')";
            }
            st.executeUpdate(sql);
            st.close();
            close();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
