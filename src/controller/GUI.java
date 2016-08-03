package controller;

import screens.login_driver;
import screens.login_manager;
import screens.login_screen;
import objects.*;

import javax.swing.*;
import java.sql.SQLException;


public class GUI extends JFrame {


    private JPanel login;
    private JPanel manager;
    private JLayeredPane driver_screen;
    private Boolean check;

    public GUI(){
        super("MMDB desktop client");//titel der Fensters
        openLogin();
    }


    public void openLogin(){
        setSize(400,400);
        login = new login_screen();//login_screen
        add(login); //login_screen wird angezeigt
    }

    public void openManager(){
        remove(login);
        manager = new login_manager();
        add(manager);

    }

    public void openDriver(Driver driver) throws SQLException{
        remove(login);
        setSize(900,700);
        driver_screen = new login_driver(driver);
        add(driver_screen);
        System.out.print("open objects");
    }




}
