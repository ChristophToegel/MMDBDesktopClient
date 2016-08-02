package controller;

import screens.login_driver;
import screens.login_manager;
import screens.login_screen;

import javax.swing.*;


public class GUI extends JFrame {


    private JPanel login;
    private JPanel manager;
    private JLayeredPane driver;
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

    public void openDriver(){
        remove(login);
        setSize(900,700);
        driver = new login_driver();

        add(driver);
        System.out.print("open driver");
    }




}
