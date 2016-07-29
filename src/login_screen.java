import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * Created by juli on 29.07.16.
 */

public class login_screen extends JPanel {

    private Boolean check;
    private JPanel login;


    public login_screen(){
      //  super("MMDB desktop client");//titel der Fensters
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
