package controller;



import javax.swing.*;

/**
 * Created by Christoph on 06.07.16.
 */
public class main{

    private static GUI window;

    public static void main(String args[]){
        initGui();

    }

    private static void initGui() {
        window = new GUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//bei x --> schließen
        window.setSize(400,400);
        window.setVisible(true);
        //   window.openDriver();
    }

    public static void driver() {
        window.openDriver();
    }
}