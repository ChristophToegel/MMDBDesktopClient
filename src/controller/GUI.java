package controller;

import screens.login_driver;
import screens.login_manager;
import screens.login_screen;

import javax.swing.*;


public class GUI extends JFrame {


    private JPanel login;
    private JPanel manager;
    private JPanel driver;
    private Boolean check;

    public GUI(){
        super("MMDB desktop client");//titel der Fensters
        openLogin();
    }


    public void openLogin(){
        login = new login_screen();
        add(login);
    }

    public void openManager(){
      remove(login);
        manager = new login_manager();
        add(manager);

    }

    public void opernDriver(){
        remove(login);
        driver = new login_driver();
        add(driver);
    }




}
