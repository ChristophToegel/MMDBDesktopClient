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


    private JLayeredPane login;
    private JTabbedPane manager_screen;
    private JLayeredPane driver_screen;

    public GUI(){
        super("MMDB Desktop Client");//Titel der Fenster
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

        login = new login_screen();//Login-Screen
        add(login); //Login-Screen wird hinzufügt und angezeigt
    }

    public void openManager(Manager manager) throws SQLException{
        remove(login);
        setSize(900,700);
        manager_screen = new login_manager(manager);
        add(manager_screen, BorderLayout.CENTER);
        debug.printout("Authentification successful!");
        debug.printout("Open manager screen...");

    }

    public void openDriver(Driver Driver) throws SQLException{
        remove(login);
        setSize(900,700);
        driver_screen = new login_driver(Driver);
        add(driver_screen);
        debug.printout("Authentification successful!");
        debug.printout("Open driver screen...");
    }




}
