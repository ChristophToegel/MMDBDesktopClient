package controller;

import javax.swing.*;

import log.debug;
import objects.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Christoph on 06.07.16.
 */

public class main{

    private static GUI window;



    public static void main(String args[])  {
        initGui();
    }

    private static void initGui() {
        window = new GUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//bei x --> schließen
        window.setSize(400,400);
        window.setVisible(true);
    }

    public static void driver(Driver driver) throws SQLException {
        window.openDriver(driver);
    }

    public static void manager() throws SQLException {
        window.openManager();
    }

    public static void logout() {
        window.openLogin();
    }


}
