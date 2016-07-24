import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.fastinfoset.sax.Properties;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Christoph on 06.07.16.
 */
public class main {


    private final static String USER="j.dietz";
    private final static String PASSWORD ="mmdb";

    public static void main (String args[]){
    System.out.print("test");

        createDbConnection();
        test();
    }

    private static void test() {
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
    }

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
}
