import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Created by juli on 28.07.16.
 */
public class gui extends JFrame {


    private JPanel login;
    private Boolean check;

    public gui(){
        super("MMDB desktop client");//titel der Fensters
        login =new JPanel();
        login.setLayout(new BoxLayout(login,BoxLayout.PAGE_AXIS));
        createElements();
    }

    private void createElements() {

        JLabel test= new JLabel("login Screen");
        JPasswordField pass= new JPasswordField("password");
        JTextField name = new JTextField();
        name.setSize(50,15);
        JButton weiter= new JButton("login");
        JLabel falsch= new JLabel("Falscher Login");
        falsch.setVisible(false);

        weiter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                  check= main.checkPw(name.getText(),pass.getPassword());
                    falsch.setVisible(false);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(check){
                    //TODO
                    }else {
                    falsch.setVisible(true);
                }
            }

        });
        login.add(test);
        login.add(name);
        login.add(pass);
        login.add(falsch);
        login.add(weiter);
        add(login);
    }




}
