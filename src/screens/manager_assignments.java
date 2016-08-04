package screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Andi on 04.08.2016.
 */
public class manager_assignments extends JPanel {

    private static final int X_SIGNEDINTEXT = 450;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight


    public manager_assignments() {

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

        JLabel makeAssignment = new JLabel("Neuen Auftrag erstellen");
        JLabel size = new JLabel("Größe");
        JLabel date = new JLabel("Lieferdatum");
        JLabel getAddress = new JLabel("Abholadresse");
        JLabel destAddress = new JLabel("Lieferadresse");
        JLabel driver = new JLabel("Fahrer");
        JLabel status = new JLabel("Status");

        size.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        date.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        getAddress.setBounds(X_FIELDS,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        destAddress.setBounds(X_FIELDS,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driver.setBounds(X_FIELDS,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        status.setBounds(X_FIELDS,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(size);
        add(date);
        add(getAddress);
        add(destAddress);
        add(driver);
        add(status);

        makeAssignment.setBounds(X_FIELDS,Y_FIELDSSTART-Y_GAP,BOX_LENGTH*2,BOX_HEIGHT);

        makeAssignment.setFont(new Font(makeAssignment.getFont().getName(), Font.PLAIN, makeAssignment.getFont().getSize()*2));

        add(makeAssignment);


    }

    private void createAssignmentDatafields() {
        JLabel sizeData = new JLabel("SIZE");
        JLabel dateData = new JLabel("DATE");
        JLabel getAddressData = new JLabel("GETADDRESS");
        JLabel destAddressData = new JLabel("DESTADDRESS");
        JLabel driverData = new JLabel("DRIVER");
        JLabel statusData = new JLabel("STATUS");

        sizeData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        dateData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        getAddressData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        destAddressData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        statusData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        sizeData.setOpaque(true);
        sizeData.setBorder(border);
        sizeData.setBackground(Color.white);

        dateData.setOpaque(true);
        dateData.setBorder(border);
        dateData.setBackground(Color.white);

        getAddressData.setOpaque(true);
        getAddressData.setBorder(border);
        getAddressData.setBackground(Color.white);

        destAddressData.setOpaque(true);
        destAddressData.setBorder(border);
        destAddressData.setBackground(Color.white);

        driverData.setOpaque(true);
        driverData.setBorder(border);
        driverData.setBackground(Color.white);

        statusData.setOpaque(true);
        statusData.setBorder(border);
        statusData.setBackground(Color.white);

        add(sizeData);
        add(dateData);
        add(getAddressData);
        add(destAddressData);
        add(driverData);
        add(statusData);



    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        JButton assign = new JButton("Auftrag abgeben");

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH*2,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        assign.setBackground(Color.LIGHT_GRAY);
        assign.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+6*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(logout);
        add(assign);
    }

    private void createList() {
        JPanel list = new JPanel();
        list.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);
        list.setBackground(Color.lightGray);
        add(list);
    }
}
