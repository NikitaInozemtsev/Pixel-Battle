package serv.dbase;

import java.io.Closeable;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;

public class DataBase implements Closeable {

    private static final String DB_URL = "jdbc:postgresql://:5432/postgres";
    private static final String user = "lvovtr";
    private static final String password = "Tjed_913";
    private static boolean isBase;
    private final Connection connection;

    /**
     *
     * @return Создает соединение с БД
     * @throws SQLException Бросает исключение, если не получилось
     */
    public static DataBase createConnection() throws SQLException {
        if (!isBase) {
            //Проверяем наличие JDBC драйвера для работы с БД
            DriverManager.registerDriver(new org.postgresql.Driver());
            //Попытка установить соединение с базой данных
            Connection connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Соединение с БД выполнено.");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select color from colors");
            if (!rs.next()) {
                System.out.println("База данных пуста, первичное заполнение");
                String arr = "{";
                for (int i = 0; i < 400; i++) {
                    arr += "{";
                    for (int j = 0; j < 400; j++) {
                        if(j != 399) {
                            arr += "\"#FFFFFF\",";
                        }
                        else {
                            arr += "\"#FFFFFF\"";
                        }
                    }
                    if(i != 399) {
                        arr += "},";
                    }
                    else {
                        arr += "}";
                    }
                }
                arr += "}";
                System.out.println(arr);
                st.executeUpdate("INSERT INTO colors (color) VALUES ('" + arr + "');");

            }
            isBase = true;
            return new DataBase(connection);
        }
        return null;
    }

    /**
     *
     * @param connection Получение соединения
     */
    private DataBase(Connection connection) {
        this.connection = connection;
    }

    /**
     * Закрытие соединения
     */
    @Override
    public void close() {
        try {
            connection.close();
            isBase = false;
            System.out.println("Соединение успешно закрыто");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return Возврат обновленного изображения
     * @throws SQLException Бросает исключение, если нет подключения
     */
    public Array getPixelMap() {
        Array res = null;
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery("select color from colors");
            if(rs.next()) {
                res = rs.getArray(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    /**
     *
     * @param color Цвет пикселя
     * @param x Позиция пикселя
     * @throws SQLException Бросает исключение, если нет подключения
     */
    public void insertPixel(String color, int x, int y) throws SQLException {
        connection.createStatement().executeUpdate("UPDATE colors SET color[" + x + "][" + y + "]='" + color +"'");
    }
}
