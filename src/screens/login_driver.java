package screens;

import controller.DBM;
import Objekts.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import log.debug;

/**
 * Created by Christoph on 01.08.16.
 */
public class login_driver extends JLayeredPane {

    private driver driver;

    public login_driver(driver driver){
        this.driver= driver;
        createElements();


    }


    private void createElements() {

        debug.printout(driver.getFirstname());
        JLabel fahrerdata = new JLabel("fahrername, ");
        fahrerdata.setBounds(300,20,100,50);
        add(fahrerdata);
        JButton logout= new JButton("Logout");
        logout.setBackground(Color.lightGray);
        logout.setBounds(350,20, 100,50);
        add(logout);
    }



}
