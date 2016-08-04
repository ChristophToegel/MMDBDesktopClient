package controller;

import log.debug;
import screens.login_driver;
import screens.login_manager;
import screens.login_screen;
import objects.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class GUI extends JFrame {


    private JPanel login;
    private JTabbedPane manager_screen;
    private JLayeredPane driver_screen;
    private Boolean check;

    public GUI(){
        super("MMDB desktop client");//titel der Fensters
        openLogin();
    }


    public void openLogin(){
        setSize(400,400);
        if(manager_screen!=null){
            remove(manager_screen);
        }
        if(driver_screen!=null){
            remove(driver_screen);
        }

        login = new login_screen();//login_screen
        add(login); //login_screen wird angezeigt
    }

    public void openManager() throws SQLException{
        remove(login);
        setSize(900,700);
        manager_screen = new login_manager();
        add(manager_screen, BorderLayout.CENTER);
        debug.printout("open manager");

    }

    public void openDriver(Driver driver) throws SQLException{
        remove(login);
        setSize(900,700);
        driver_screen = new login_driver(driver);
        add(driver_screen);
        debug.printout("open driver");
    }




}
