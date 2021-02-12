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
     * @return ������� ���������� � ��
     * @throws SQLException ������� ����������, ���� �� ����������
     */
    public static DataBase createConnection() throws SQLException {
        if (!isBase) {
            //��������� ������� JDBC �������� ��� ������ � ��
            DriverManager.registerDriver(new org.postgresql.Driver());
            //������� ���������� ���������� � ����� ������
            Connection connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("���������� � �� ���������.");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select color from colors");
            if (!rs.next()) {
                System.out.println("���� ������ �����, ��������� ����������");
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
     * @param connection ��������� ����������
     */
    private DataBase(Connection connection) {
        this.connection = connection;
    }

    /**
     * �������� ����������
     */
    @Override
    public void close() {
        try {
            connection.close();
            isBase = false;
            System.out.println("���������� ������� �������");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return ������� ������������ �����������
     * @throws SQLException ������� ����������, ���� ��� �����������
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
     * @param color ���� �������
     * @param x ������� �������
     * @throws SQLException ������� ����������, ���� ��� �����������
     */
    public void insertPixel(String color, int x, int y) throws SQLException {
        connection.createStatement().executeUpdate("UPDATE colors SET color[" + x + "][" + y + "]='" + color +"'");
    }
}
