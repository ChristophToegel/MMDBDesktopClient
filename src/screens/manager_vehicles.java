package screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Andi on 04.08.2016.
 */
public class manager_vehicles extends JPanel {

    private static final int X_SIGNEDINTEXT = 450;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight




    public manager_vehicles() {

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

        JLabel makeVehicle = new JLabel("Neuen Fahrzeugtyp erstellen");
        JLabel type = new JLabel("Typ");
        JLabel size = new JLabel("Größe");



        type.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        size.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(type);
        add(size);

        makeVehicle.setBounds(X_FIELDS,Y_FIELDSSTART-Y_GAP,BOX_LENGTH*2,BOX_HEIGHT);

        makeVehicle.setFont(new Font(makeVehicle.getFont().getName(), Font.PLAIN, makeVehicle.getFont().getSize()*2));

        add(makeVehicle);


    }

    private void createAssignmentDatafields() {
        JLabel type = new JLabel("TYP");
        JLabel size = new JLabel("GRÖ?E");

        type.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        size.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        type.setOpaque(true);
        type.setBorder(border);
        type.setBackground(Color.white);

        size.setOpaque(true);
        size.setBorder(border);
        size.setBackground(Color.white);

        add(type);
        add(size);

    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        JButton create = new JButton("Typ eintragen");

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH*2,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

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
