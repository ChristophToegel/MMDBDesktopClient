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

        login = new login_screen();//login_screen
        add(login);//login_screen wird angezeigt
    }





}
