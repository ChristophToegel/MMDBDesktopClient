package screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Andi on 04.08.2016.
 */
public class manager_drivers extends JPanel {

    private static final int X_SIGNEDINTEXT = 450;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight




    public manager_drivers() {

        this.setLayout(null);
        createInfoSignedIn();
        createAssignmentTexts();
        createAssignmentDatafields();
        createButtons();
        createList();
    }



    private void createInfoSignedIn() {
        JLabel signedInText = new JLabel("Angemeldet als: ");
        JLabel signedInAs = new JLabel("Insert....");

        signedInText.setBounds(X_SIGNEDINTEXT,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);
        signedInAs.setBounds(X_SIGNEDINTEXT+BOX_LENGTH,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);

        add(signedInText);
        add(signedInAs);

    }

    private void createAssignmentTexts() {

        JLabel makeDriver = new JLabel("Neuen Fahrer erstellen");
        JLabel preName = new JLabel("Vorname");
        JLabel surName = new JLabel("Nachname");
        JLabel vehicle = new JLabel("Fahrzeug");
        JLabel password = new JLabel("Passwort");
        JLabel driverSince = new JLabel("Fahrer seit");
        JLabel driverID= new JLabel("FahrerID");

        preName.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        surName.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        vehicle.setBounds(X_FIELDS,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        password.setBounds(X_FIELDS,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSince.setBounds(X_FIELDS,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverID.setBounds(X_FIELDS,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(preName);
        add(surName);
        add(vehicle);
        add(password);
        add(driverSince);
        add(driverID);

        makeDriver.setBounds(X_FIELDS,Y_FIELDSSTART-Y_GAP,BOX_LENGTH*2,BOX_HEIGHT);

        makeDriver.setFont(new Font(makeDriver.getFont().getName(), Font.PLAIN, makeDriver.getFont().getSize()*2));

        add(makeDriver);


    }

    private void createAssignmentDatafields() {
        JTextField preName = new JTextField("VORNAME");
        JTextField surName = new JTextField("NACHNAME");
        JTextField vehicle = new JTextField("FAHRZEUG");
        JTextField password = new JTextField("PASSWORT");
        JTextField driverSince = new JTextField("FAHRER SEIT");
        JTextField driverID = new JTextField("FAHRERID");

        preName.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        surName.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        vehicle.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        password.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSince.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverID.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        preName.setOpaque(true);
        preName.setBorder(border);
        preName.setBackground(Color.white);

        surName.setOpaque(true);
        surName.setBorder(border);
        surName.setBackground(Color.white);

        vehicle.setOpaque(true);
        vehicle.setBorder(border);
        vehicle.setBackground(Color.white);

        password.setOpaque(true);
        password.setBorder(border);
        password.setBackground(Color.white);

        driverSince.setOpaque(true);
        driverSince.setBorder(border);
        driverSince.setBackground(Color.white);

        driverID.setOpaque(true);
        driverID.setBorder(border);
        driverID.setBackground(Color.white);

        add(preName);
        add(surName);
        add(vehicle);
        add(password);
        add(driverSince);
        add(driverID);



    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        JButton create = new JButton("Fahrer eintragen");

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH*2,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+6*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(logout);
        add(create);
    }

    private void createList() {
        JPanel list = new JPanel();
        list.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);
        list.setBackground(Color.lightGray);
        add(list);
    }
}
