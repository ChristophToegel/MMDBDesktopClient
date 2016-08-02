package screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Christoph on 01.08.16.
 */
public class login_driver extends JLayeredPane {


    private static final int LEFT_ALLIGN = 30; //left allign of TEXT
    private static final int LEFT_ALLIGN_2 = 180; //left allign of DATA
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 30; //Labelheight
    private static final int DATA_TOP = 80; //First Dataline
    private static final int DATA_GAP = 35; //Space between Datalines
    private static final int DATA_BOT = 460; //First Dataline for driver
    private static final String TEST = "testiger Test";

    public login_driver(){
        createElements();

    }


    private void createElements() {
        createTexts();
        createButtons();
        createDataFields();
        createDriverData();
        createDriverText();
    }
    //These Labels don't change their TEXT
    public void createTexts(){
        JLabel signedInText = new JLabel("Angemeldet als:");
        JLabel sizeText = new JLabel("Größe:");
        JLabel datecreatedText = new JLabel("Erstellt am:");
        JLabel getaddressText = new JLabel("Abhohladdresse:");
        JLabel destaddressText = new JLabel("Zieladdresse:");
        JLabel finaldateText = new JLabel("Abliefern bis");
        JLabel curAssignmentText = new JLabel("Angenommener Auftrag:");

        signedInText.setBounds(530,25,150,BOX_HEIGHT);
        sizeText.setBounds(LEFT_ALLIGN,DATA_TOP,BOX_LENGTH,BOX_HEIGHT);
        datecreatedText.setBounds(LEFT_ALLIGN, DATA_TOP+DATA_GAP, BOX_LENGTH,BOX_HEIGHT);
        getaddressText.setBounds(LEFT_ALLIGN, DATA_TOP+2*DATA_GAP, BOX_LENGTH, BOX_HEIGHT);
        destaddressText.setBounds(LEFT_ALLIGN, DATA_TOP+3*DATA_GAP, BOX_LENGTH, BOX_HEIGHT);
        finaldateText.setBounds(LEFT_ALLIGN, DATA_TOP+4*DATA_GAP, BOX_LENGTH ,BOX_HEIGHT);
        curAssignmentText.setBounds(LEFT_ALLIGN ,25, BOX_LENGTH*2,BOX_HEIGHT);

        curAssignmentText.setFont(new Font(curAssignmentText.getFont().getName(), Font.PLAIN, curAssignmentText.getFont().getSize()*2));

        add(curAssignmentText);
        add(signedInText);
        add(sizeText);
        add(datecreatedText);
        add(getaddressText);
        add(destaddressText);
        add(finaldateText);

    }
    //These Lables change their TEXT based on the DB
    public void createDataFields(){
        JLabel sizeData = new JLabel(TEST);
        JLabel datecreatedData = new JLabel(TEST);
        JLabel getaddressData = new JLabel(TEST);
        JLabel destaddressData = new JLabel(TEST);
        JLabel finaldateData = new JLabel(TEST);
        JLabel loggedInAsData = new JLabel(TEST);
        JLabel curAssignmentData = new JLabel("ID 7590");

        sizeData.setBounds(LEFT_ALLIGN_2, DATA_TOP, BOX_LENGTH,BOX_HEIGHT);
        datecreatedData.setBounds(LEFT_ALLIGN_2, DATA_TOP+DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        getaddressData.setBounds(LEFT_ALLIGN_2, DATA_TOP+2*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        destaddressData.setBounds(LEFT_ALLIGN_2, DATA_TOP+3*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        finaldateData.setBounds(LEFT_ALLIGN_2, DATA_TOP+4*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        loggedInAsData.setBounds(635, 25, BOX_LENGTH,BOX_HEIGHT);
        curAssignmentData.setBounds(350, 25, BOX_LENGTH,BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        sizeData.setOpaque(true);
        sizeData.setBorder(border);
        sizeData.setBackground(Color.white);

        datecreatedData.setOpaque(true);
        datecreatedData.setBorder(border);
        datecreatedData.setBackground(Color.white);

        getaddressData.setOpaque(true);
        getaddressData.setBorder(border);
        getaddressData.setBackground(Color.white);

        destaddressData.setOpaque(true);
        destaddressData.setBorder(border);
        destaddressData.setBackground(Color.white);

        finaldateData.setOpaque(true);
        finaldateData.setBorder(border);
        finaldateData.setBackground(Color.white);

        loggedInAsData.setForeground(Color.GREEN);

        curAssignmentData.setFont(new Font(curAssignmentData.getFont().getName(), Font.PLAIN, curAssignmentData.getFont().getSize()*2));

        add(sizeData);
        add(datecreatedData);
        add(getaddressData);
        add(destaddressData);
        add(finaldateData);
        add(loggedInAsData);
        add(curAssignmentData);
    }

    //All the buttons
    public void createButtons(){
        JButton logoutB= new JButton("Logout");
        JButton acceptB= new JButton("Annehmen");
        JButton deliveredB = new JButton("Erledigt");


        logoutB.setBackground(Color.lightGray);
        logoutB.setBounds(780,20, 100,50);
        acceptB.setBackground(Color.lightGray);
        acceptB.setBounds(200,275,100,50);
        deliveredB.setBackground(Color.lightGray);
        deliveredB.setBounds(200, 325, 100,50);

        add(logoutB);
        add(acceptB);
        add(deliveredB);
    }

    //All Data for the driver
    public void createDriverText(){
        JLabel driverId = new JLabel("Fahrer-ID:");
        JLabel emp_sign = new JLabel("Personal-ID:");
        JLabel vehicle_typ = new JLabel("Fahrzeugtyp:");
        JLabel vspace = new JLabel("Frachtraum:");
        JLabel position = new JLabel("Position:");
        JLabel infoText = new JLabel("Eigene Daten:");

        driverId.setBounds(LEFT_ALLIGN, DATA_BOT, BOX_LENGTH, BOX_HEIGHT);
        emp_sign.setBounds(LEFT_ALLIGN,DATA_BOT+DATA_GAP ,BOX_LENGTH,BOX_HEIGHT);
        vehicle_typ.setBounds(LEFT_ALLIGN,DATA_BOT+2*DATA_GAP,BOX_LENGTH,BOX_HEIGHT);
        vspace.setBounds(LEFT_ALLIGN,DATA_BOT+3*DATA_GAP,BOX_LENGTH,BOX_HEIGHT);
        position.setBounds(LEFT_ALLIGN,DATA_BOT+4*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        infoText.setBounds(LEFT_ALLIGN,405,BOX_LENGTH*2, BOX_HEIGHT);

        infoText.setFont(new Font(infoText.getFont().getName(), Font.PLAIN, infoText.getFont().getSize()*2));

        add(driverId);
        add(emp_sign);
        add(vehicle_typ);
        add(vspace);
        add(position);
        add(infoText);

    }

    //All changing Data for the driver
    public void createDriverData(){
        JLabel driverIdData = new JLabel(TEST);
        JLabel emp_signData = new JLabel(TEST);
        JLabel vehicle_typData = new JLabel(TEST);
        JLabel vspaceData = new JLabel(TEST);
        JLabel positionData = new JLabel(TEST);

        driverIdData.setBounds(LEFT_ALLIGN_2, DATA_BOT, BOX_LENGTH, BOX_HEIGHT);
        emp_signData.setBounds(LEFT_ALLIGN_2,DATA_BOT+DATA_GAP ,BOX_LENGTH,BOX_HEIGHT);
        vehicle_typData.setBounds(LEFT_ALLIGN_2,DATA_BOT+2*DATA_GAP,BOX_LENGTH,BOX_HEIGHT);
        vspaceData.setBounds(LEFT_ALLIGN_2,DATA_BOT+3*DATA_GAP,BOX_LENGTH,BOX_HEIGHT);
        positionData.setBounds(LEFT_ALLIGN_2,DATA_BOT+4*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);

        add(driverIdData);
        add(emp_signData);
        add(vehicle_typData);
        add(vspaceData);
        add(positionData);
    }



}
