package controller;

import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;
import objects.*;

import java.sql.SQLException;

/**
 * Created by Christoph on 06.07.16.
 */

public class main{

    private static GUI window;



    public static void main(String args[]) {
        initGui();
    }

    private static void initGui() {
        window = new GUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//bei x --> schließen
        window.setSize(400,400);
        window.setVisible(true);
    }

    public static void driver(Driver Driver) throws SQLException {
        window.openDriver(Driver);
    }

    public static void manager(Manager manager) throws SQLException {
        window.openManager(manager);
    }

    public static void logout() {
        window.openLogin();
    }


}
