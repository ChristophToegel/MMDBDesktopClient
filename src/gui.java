import javax.swing.*;
import java.awt.*;

/**
 * Created by juli on 28.07.16.
 */
public class gui extends JFrame {



    public gui(){
        super("MMDB desktop client");//titel der Fensters
        setLayout(new FlowLayout());
        createElements();
    }

    private void createElements() {
        JLabel test= new JLabel("login Screen");
        add(test);
    }
}
