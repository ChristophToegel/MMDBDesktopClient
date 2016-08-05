package screens;

import controller.DBM;
import controller.main;
import objects.Assignment;
import objects.Driver;
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


/**
 * Created by Andi on 04.08.2016.
 */
public class manager_vehicles extends JPanel implements ListSelectionListener{

    private static final int X_SIGNEDINTEXT = 530;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 25;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 170; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight
    private Manager manager;

    JTextField type = new JTextField("TYP");
    JTextField size = new JTextField("GRÖßE");

    ArrayList<Vehicle> VehicleArrayList;
    DefaultListModel listModel = new DefaultListModel();
    JList list = new JList(listModel);
    JScrollPane listScroll = new JScrollPane(list);




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
        signedInAs.setBounds(635,Y_SIGNEDINTEXT,BOX_LENGTH,BOX_HEIGHT);

        add(signedInText);
        add(signedInAs);

    }

    private void createVehicleTexts() {

        JLabel makeVehicle = new JLabel("Neuen Fahrzeugtyp erstellen");
        JLabel type = new JLabel("Typ");
        JLabel size = new JLabel("Größe");

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

    private void createButtons() {
        JButton logout = new JButton("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.logout();

            }
        });

        JButton create = new JButton("Typ eintragen"); //TODO

        JButton clearFields = new JButton("Felder leeren");
        clearFields.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                type.setText("");
                size.setText("");

            }
        });

        clearFields.setBackground(Color.lightGray);
        clearFields.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+BOX_LENGTH,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);

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
                type.setText(String.valueOf(VehicleArrayList.get(list.getSelectedIndex()).getType()));
                size.setText(String.valueOf(VehicleArrayList.get(list.getSelectedIndex()).getSpace()));

            }
        }
    }
}
