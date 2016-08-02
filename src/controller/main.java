package controller;

import controller.GUI;

import javax.swing.*;
import Objekts.*;

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
    }

    public static void driver(driver driver) {
        window.openDriver(driver);
    }

}
