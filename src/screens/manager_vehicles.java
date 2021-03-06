package screens;

import controller.DBM;
import controller.main;
import objects.Manager;
import objects.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Observable;


/**
 * Created by Andi on 04.08.2016.
 */
public class manager_vehicles extends JPanel implements ListSelectionListener {

    private static final int X_SIGNEDINTEXT = 450;//Start-X Logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start-Y Logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Label-Länge
    private static final int BOX_HEIGHT = 40; //Label-Höhe
    private Manager manager;
    private JButton create;

    private JTextField type = new JTextField();
    private JTextField size = new JTextField();

    private ArrayList<Vehicle> VehicleArrayList;
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);
    private JScrollPane listScroll = new JScrollPane(list);




    public manager_vehicles(Manager manager) {
        this.manager=manager;
        this.setLayout(null);
        createInfoSignedIn();
        createVehicleTexts();
        createVehicleDatafields();
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

    private void createVehicleTexts() {

        JLabel makeVehicle = new JLabel("Fahrzeug hinzufügen");
        JLabel type = new JLabel("Typ");
        JLabel size = new JLabel("Frachtraum");

        type.setBounds(X_FIELDS,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        size.setBounds(X_FIELDS,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        makeVehicle.setBounds(X_FIELDS,Y_FIELDSSTART-Y_GAP,BOX_LENGTH*2,BOX_HEIGHT);
        makeVehicle.setFont(new Font(makeVehicle.getFont().getName(), Font.PLAIN, makeVehicle.getFont().getSize()*2));

        add(type);
        add(size);
        add(makeVehicle);
    }

    private void createVehicleDatafields() {

        type.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        size.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        listScroll.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);

        EmptyBorder border = new EmptyBorder(2,5,2,5);

        type.setOpaque(true);
        type.setBorder(border);
        type.setBackground(Color.white);

        size.setOpaque(true);
        size.setBorder(border);
        size.setBackground(Color.white);

        add(type);
        add(size);
        add(listScroll);


    }

    private void updateList() {
        listModel =new DefaultListModel<>();
        fillList();
        list.setModel(listModel);

    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.logout();
            }
        });

        create = new JButton("Typ eintragen");
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                   String typ=type.getText();
                    int größe=Integer.parseInt(size.getText());
                    if(list.isSelectionEmpty()){
                        try {
                            DBM.insertVehicle(typ,größe);
                            manager_drivers.updateDropdown();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        type.setText("");
                        size.setText("");}
                else{
                        try {
                            DBM.updateVehicle(VehicleArrayList.get(list.getSelectedIndex()).getVehicle_id(),typ,größe);
                            manager_drivers.updateDropdown();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        create.setText("Typ eintragen");
                        type.setText("");
                        size.setText("");
                }
                updateList();
            }
        });

        JButton clearFields = new JButton("Felder leeren");
        clearFields.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                type.setText("");
                size.setText("");
                list.clearSelection();
                create.setText("Typ eintragen");
            }
        });

        clearFields.setBackground(Color.lightGray);
        clearFields.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH*2,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);

        create.setBackground(Color.LIGHT_GRAY);
        create.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

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
            VehicleArrayList = DBM.getAllVehicles();
            for (Vehicle v: VehicleArrayList)
                listModel.addElement(v.getVehicle_id());
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){
            if (list.getSelectedIndex() == -1) {
                //TODO nothing
            }else{
                create.setText("Typ updaten");
                type.setText(String.valueOf(VehicleArrayList.get(list.getSelectedIndex()).getType()));
                size.setText(String.valueOf(VehicleArrayList.get(list.getSelectedIndex()).getSpace()));
            }
        }
    }
}
