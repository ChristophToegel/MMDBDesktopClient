import controller.GUI;

import javax.swing.*;

/**
 * Created by Christoph on 06.07.16.
 */
public class main{




    public static void main (String args[]){
        initGui();

    }

    private static void initGui() {
        GUI window = new GUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//bei x --> schließen
        window.setSize(400,400);
        window.setVisible(true);
    }

}
