package screens;


import controller.DBM;
import controller.main;
import objects.Driver;
import objects.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * Created by juli on 29.07.16.
 */

public class login_screen extends JLayeredPane {

    private Boolean pwcheck;
    private static final int X_START = 150; //Start x logintext
    private static final int Y_START  = 75;  //Start y logintext
    private static final int Y_GAP = 5;
    private static final int BOX_LENGTH = 90; //Labellength
    private static final int BOX_HEIGHT = 20; //Labelheight

    public login_screen(){
        createElements();
    }

    private void createElements() {

        JLabel title= new JLabel("Anmelden");
        JPasswordField password= new JPasswordField("");
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                password.setText("");
            }

        });
        JTextField username = new JTextField("UserID");
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username.setText("");
            }

        });
        JButton loginButton= new JButton("Login");
        JLabel wrongData= new JLabel("Falscher Login");
        title.setBounds(X_START,Y_START,BOX_LENGTH,BOX_HEIGHT);
        username.setBounds(X_START,Y_START+BOX_HEIGHT+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        password.setBounds(X_START,Y_START+(BOX_HEIGHT+Y_GAP)*2,BOX_LENGTH,BOX_HEIGHT);
        loginButton.setBounds(X_START,Y_START+(BOX_HEIGHT+Y_GAP)*3,BOX_LENGTH,BOX_HEIGHT);
        wrongData.setBounds(X_START,Y_START+(BOX_HEIGHT+Y_GAP)*4,BOX_LENGTH,BOX_HEIGHT);

        wrongData.setVisible(false);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    wrongData.setVisible(false);
                    pwcheck= DBM.getLogin(username.getText(),password.getPassword());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(pwcheck){
                    try {
                        if(DBM.DriverOrManager(username.getText())) {
                            Driver Driver = DBM.getDriverData(username.getText());
                        main.driver(Driver);
                            } else {
                            Manager manager = DBM.getManagerData(username.getText());
                           main.manager(manager);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    wrongData.setVisible(true);
                }
            }
        });
        add(title);
        add(username);
        add(password);
        add(wrongData);
        add(loginButton);

    }

}
