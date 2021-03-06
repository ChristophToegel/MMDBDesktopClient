package screens;

import controller.DBM;
import controller.main;
import objects.Driver;
import objects.Manager;
import objects.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * Created by Andi on 04.08.2016.
 */
public class manager_drivers extends JPanel implements ListSelectionListener {

    private static final int X_SIGNEDINTEXT = 450;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight
    private Manager manager;
    private JButton create;

    private ArrayList<Driver> driverArrayList;
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);

    private JTextField firstNameData = new JTextField();
    private JTextField lastNameData = new JTextField();
    private JTextField passwordData = new JTextField();
    private JTextField driverSinceData = new JTextField();
    private JTextField driverIDData = new JTextField();
    private JScrollPane listScroll = new JScrollPane(list);
    private static JComboBox vehicBoxList = new JComboBox();

    public manager_drivers(Manager manager) {
        this.manager=manager;
        this.setLayout(null);

        createInfoSignedIn();
        createAssignmentTexts();
        createAssignmentDatafields();
        createButtons();
        createList();
    }



    private void createInfoSignedIn() {
        JLabel signedInText = new JLabel("Angemeldet als: ");
        JLabel signedInAs = new JLabel(manager.getFirstname()+" "+manager.getLastname());

        signedInText.setBounds(X_SIGNEDINTEXT,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);
        signedInAs.setBounds(X_SIGNEDINTEXT+105,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);

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
        JLabel driverID= new JLabel("Fahrer-ID");

        firstNameData.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        lastNameData.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        vehicle.setBounds(X_FIELDS,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        password.setBounds(X_FIELDS,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSince.setBounds(X_FIELDS,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverID.setBounds(X_FIELDS,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

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
        passwordData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverSinceData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        driverIDData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        listScroll.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);
        vehicBoxList.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        firstNameData.setOpaque(true);
        firstNameData.setBorder(border);
        firstNameData.setBackground(Color.white);


        lastNameData.setOpaque(true);
        lastNameData.setBorder(border);
        lastNameData.setBackground(Color.white);

        passwordData.setOpaque(true);
        passwordData.setBorder(border);
        passwordData.setBackground(Color.white);

        driverSinceData.setOpaque(true);
        driverSinceData.setBorder(border);
        driverSinceData.setBackground(Color.lightGray);
        driverSinceData.setEditable(false);

        driverIDData.setOpaque(true);
        driverIDData.setBorder(border);
        driverIDData.setBackground(Color.lightGray);
        driverIDData.setEditable(false);




        ArrayList<String> VehicleStrings =  getVehicleList();
        for(String s: VehicleStrings){
            vehicBoxList.addItem(s);
        }

        add(firstNameData);
        add(lastNameData);
        add(passwordData);
        add(driverSinceData);
        add(driverIDData);
        add(listScroll);
        add(vehicBoxList);
    }

    private static ArrayList getVehicleList(){
        ArrayList<String> VehicleStrings = new ArrayList<>();
        ArrayList<Vehicle> VehicleAList= null;
        try {
            VehicleAList = DBM.getAllVehicles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Vehicle v: VehicleAList){
            String vehicleS = v.getType();
            VehicleStrings.add(vehicleS);
        }
        return VehicleStrings;
    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.logout();

            }
        });
        create = new JButton("Fahrer eintragen");
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String firstname = firstNameData.getText();
                String lastname = lastNameData.getText();
                String vehicleType = vehicBoxList.getSelectedItem().toString();
                String password = passwordData.getText();

                if (list.isSelectionEmpty()) {
                    DBM.insertDriver(firstname, lastname, vehicleType, password);
                    clearAllFields();
                    updateList();
                } else {
                    int driverID = Integer.parseInt(driverIDData.getText());
                    try{
                        DBM.UpdateDriver(firstname,lastname,vehicleType,password,driverID);
                    } catch(SQLException a ) {
                        a.printStackTrace();
                    }
                    clearAllFields();
                    updateList();
                }
            }
        });


        JButton clearFields = new JButton("Felder leeren");
        clearFields.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearAllFields();
            }
        });

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH*2,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+6*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        clearFields.setBackground(Color.lightGray);
        clearFields.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+7*Y_GAP,BOX_LENGTH,BOX_HEIGHT);


        add(logout);
        add(create);
        add(clearFields);
    }

    private void clearAllFields() {
        create.setText("Fahrer eintragen");
        firstNameData.setText("");
        lastNameData.setText("");
        passwordData.setText("");
        driverSinceData.setText("");
        driverIDData.setText("");
        list.clearSelection();
        vehicBoxList.setSelectedIndex(-1);
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

    private void updateList() {
        listModel =new DefaultListModel<>();
        fillList();
        list.setModel(listModel);
    }

    private void fillList()  {

        try {
            driverArrayList = DBM.getDriverList();
            for (Driver d: driverArrayList)
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
                create.setText("Fahrer ändern");
                firstNameData.setText(String.valueOf(driverArrayList.get(list.getSelectedIndex()).getFirstname()));
                lastNameData.setText(String.valueOf(driverArrayList.get(list.getSelectedIndex()).getLastname()));
                vehicBoxList.setSelectedItem(driverArrayList.get(list.getSelectedIndex()).getVehicle().getType());
                passwordData.setText(String.valueOf(driverArrayList.get(list.getSelectedIndex()).getPassword()));
                driverSinceData.setText(String.valueOf(driverArrayList.get(list.getSelectedIndex()).getDriverSince())); //TODO Datum fehlt
                driverIDData.setText(String.valueOf(driverArrayList.get(list.getSelectedIndex()).getDriver_id()));
            }
        }
    }

    public static void updateDropdown() {
        vehicBoxList.removeAllItems();
        ArrayList<String> VehicleStrings = getVehicleList();
        for (String s : VehicleStrings) {
            vehicBoxList.addItem(s);
        }
    }

}
