import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christoph on 06.07.16.
 */
public class main{


    private final static String USER="j.dietz";
    private final static String PASSWORD ="mmdb";

    public static void main (String args[]){
    System.out.print("test");
        initGui();
        createDbConnection();

    }

    private static void initGui() {
        gui window = new gui();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//bei x --> schließen
        window.setSize(400,400);
        window.setVisible(true);
    }

    /*private static void test() {
         Connection conn =createDbConnection();

        Statement test= null;
        try {
            test = (Statement) conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String string="INSERT INTO employee (firstname,lastname) values ('hallo1','test1');";

        try {
            test.executeUpdate(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    private static Connection createDbConnection() {
        Connection conn = null;
            try {
                conn = (Connection) DriverManager.getConnection( "jdbc:mysql://132.199.139.24:3306/mmdb16_juliandietz",
                        USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println("Connected to database");
        return conn;
    }

    public static boolean checkPw(String name, char[]  passwordchar) throws SQLException {
     System.out.println(name+"  "+ String.valueOf(passwordchar));
        Connection conn =createDbConnection();
        PreparedStatement pstmt = null;
        boolean check=false;

        try {
            String password=String.valueOf(passwordchar);
            pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM   employee  WHERE emp_sign = ? AND password = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.executeQuery();
            ResultSet rs = pstmt.getResultSet();

            System.out.print(rs.first());
            check=rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

      finally {
                conn.close();

        }

        return check;
    }
}
