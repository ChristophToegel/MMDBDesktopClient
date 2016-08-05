package screens;

import controller.DBM;
import controller.main;
import objects.Driver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andi on 04.08.2016.
 */
public class manager_drivers extends JPanel implements ListSelectionListener{

    private static final int X_SIGNEDINTEXT = 530;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 25;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight

    ArrayList<Driver> DriverArrayList;
    DefaultListModel listModel = new DefaultListModel();
    JList list = new JList(listModel);

    JTextField firstNameData = new JTextField("VORNAME");
    JTextField lastNameData = new JTextField("NACHNAME");
    JTextField vehicleData = new JTextField("FAHRZEUG");
    JTextField passwordData = new JTextField("PASSWORT");
    JTextField driverSinceData = new JTextField("FAHRER SEIT");
    JTextField driverIDData = new JTextField("FAHRERID");
    JScrollPane listScroll = new JScrollPane(list);




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
        JLabel signedInAs = new JLabel("Insert....");//TODO

        signedInText.setBounds(X_SIGNEDINTEXT,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);
        signedInAs.setBounds(635,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);

        add(signedInText);
        add(signedInAs);

    }

    private void createAssignmentTexts() {

        JLabel makeDriver = new JLabel("Neuen Fahrer erstellen");
        JLabel firstNameData = new JLabel("Vorname");
        JLabel lastNameData = new JLabel("Nachname");
        JLabel vehicle = new JLabel("Fahrzeug");
        JLabel password = new JLabel("Passwort");
        JLabel driverSince = new JLabel("Fahrer seit");
        JLabel driverID= new JLabel("FahrerID");

        firstNameData.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        lastNameData.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        vehicle.setBounds(X_FIELDS,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        password.setBounds(X_FIELDS,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSince.setBounds(X_FIELDS,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverID.setBounds(X_FIELDS,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        makeDriver.setBounds(X_FIELDS,Y_FIELDSSTART-Y_GAP,BOX_LENGTH*2,BOX_HEIGHT);

        makeDriver.setFont(new Font(makeDriver.getFont().getName(), Font.PLAIN, makeDriver.getFont().getSize()*2));

        add(firstNameData);
        add(lastNameData);
        add(vehicle);
        add(password);
        add(driverSince);
        add(driverID);
        add(makeDriver);


    }

    private void createAssignmentDatafields() {

        firstNameData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        lastNameData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        vehicleData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        passwordData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSinceData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverIDData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        listScroll.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        firstNameData.setOpaque(true);
        firstNameData.setBorder(border);
        firstNameData.setBackground(Color.white);

        lastNameData.setOpaque(true);
        lastNameData.setBorder(border);
        lastNameData.setBackground(Color.white);

        vehicleData.setOpaque(true);
        vehicleData.setBorder(border);
        vehicleData.setBackground(Color.white);

        passwordData.setOpaque(true);
        passwordData.setBorder(border);
        passwordData.setBackground(Color.white);

        driverSinceData.setOpaque(true);
        driverSinceData.setBorder(border);
        driverSinceData.setBackground(Color.white);

        driverIDData.setOpaque(true);
        driverIDData.setBorder(border);
        driverIDData.setBackground(Color.white);

        add(firstNameData);
        add(lastNameData);
        add(vehicleData);
        add(passwordData);
        add(driverSinceData);
        add(driverIDData);
        add(listScroll);
    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.logout();

            }
        });
        JButton create = new JButton("Fahrer eintragen"); //TODO

        JButton clearFields = new JButton("Felder leeren");
        clearFields.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firstNameData.setText("");
                lastNameData.setText("");
                vehicleData.setText("");
                passwordData.setText("");
                driverSinceData.setText("");
                driverIDData.setText("");
            }
        });

        clearFields.setBackground(Color.lightGray);
        clearFields.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+7*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+6*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(logout);
        add(create);
        add(clearFields);
    }

    private void createList() {
        list.setBackground(Color.lightGray);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        fillList();
        list.setSelectedIndex(-1);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(-1);

    }

    private void fillList()  {

        try {
            DriverArrayList = DBM.getDriverList();
            for (Driver d: DriverArrayList)
                listModel.addElement(d.getDriver_id());
        } catch(SQLException e){
            e.printStackTrace();
        }




    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){
            if (list.getSelectedIndex() == -1) {
                //TODO nothing
            }else{
                firstNameData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).getFirstname()));
                lastNameData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).getLastname()));
                vehicleData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).getVehicle_id()));
                passwordData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).getPassword()));
                //driverSinceData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).get)) //TODO Datum fehlt
                driverIDData.setText(String.valueOf(DriverArrayList.get(list.getSelectedIndex()).getDriver_id()));

            }
        }
    }
}
