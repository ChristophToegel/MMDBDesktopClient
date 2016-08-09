package screens;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import controller.*;
import objects.*;

/**
 * Created by juli on 29.07.16.
 */

public class login_screen extends JPanel {

    private Boolean pwcheck;
    private JPanel login_window;

    public login_screen(){
        login_window =new JPanel();
        login_window.setLayout(new BoxLayout(login_window,BoxLayout.PAGE_AXIS));
        createElements();
    }

    private void createElements() {

        JLabel title= new JLabel("Anmelden");
        JPasswordField password= new JPasswordField("test");
        JTextField username = new JTextField();
        JButton loginButton= new JButton("Login");
        JLabel wrongData= new JLabel("Falscher Login");
        username.setSize(50,15);
        password.setSize(50,15);
        loginButton.setSize(50,15);
        wrongData.setSize(50,15);
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

                        main.driver(Driver);} else {
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
        login_window.add(title);
        login_window.add(username);
        login_window.add(password);
        login_window.add(wrongData);
        login_window.add(loginButton);
        add(login_window);
    }


}
