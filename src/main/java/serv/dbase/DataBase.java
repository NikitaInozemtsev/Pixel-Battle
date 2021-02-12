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
            //��������� ������� JDBC �������� ��� ������ � ��
            DriverManager.registerDriver(new org.postgresql.Driver());
            //������� ���������� ���������� � ����� ������
            connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("���������� � �� ���������.");
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
     * �������� ����������
     */

    public void close() {
        try {
            connection.close();
            System.out.println("���������� ������� �������");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return ������� ������������ �����������
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
     * @param color ���� �������
     * @throws SQLException ������� ����������, ���� ��� �����������
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
