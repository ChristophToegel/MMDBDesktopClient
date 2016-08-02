package screens;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christoph on 01.08.16.
 */
public class login_driver extends JLayeredPane {




    public login_driver(){
        createElements();

    }


    private void createElements() {

        JLabel fahrerdata = new JLabel("fahrername, ");
        fahrerdata.setBounds(300,20,100,50);
        add(fahrerdata);
        JButton logout= new JButton("Logout");
        logout.setBackground(Color.lightGray);
        logout.setBounds(350,20, 100,50);
        add(logout);
    }



}
