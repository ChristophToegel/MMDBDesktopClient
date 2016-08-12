package screens;

import controller.DBM;
import controller.main;
import objects.Assignment;
import objects.Driver;
import objects.Location;
import objects.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Christoph on 01.08.16.
 */
public class login_driver extends JLayeredPane {


    private static final int LEFT_ALLIGN = 30; //linke Ausrichtung von "text"
    private static final int LEFT_ALLIGN_2 = 180; //linke Ausrichtung von "data"
    private static final int BOX_LENGTH = 170; //Label-Länge
    private static final int BOX_HEIGHT = 30; //Label-Höhe
    private static final int DATA_TOP = 80; //First Dataline
    private static final int DATA_GAP = 35; //Space between Datalines
    private static final int DATA_BOT = 460; //First Dataline for Driver
    private Vehicle vehicle;
    private Location location;
    private Driver driver;
    private Assignment ass;

    private JLabel sizeData = new JLabel();
    private JLabel datecreatedData = new JLabel();
    private JLabel getaddressData = new JLabel();
    private JLabel destaddressData = new JLabel();
    private JLabel finaldateData = new JLabel();
    private JLabel curAssignmentData = new JLabel();


    public login_driver(Driver driver) throws SQLException{
        this.driver=driver;
        location =DBM.getLocation(driver.getLocation_id());
        vehicle = DBM.getVehicle(driver.getVehicle_id());
        ass=DBM.getAssignmentData(driver.getDriver_id());
        if(ass==null) {
            ass = getBestAssignment(DBM.getOpenAssignments(vehicle.getSpace()), driver);
            if(ass!=null){
                DBM.updateAssDriver_id(ass.getAss_id(),driver.getDriver_id());
            }
        }
        createElements();
    }

    private void createElements() throws SQLException {
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
        JLabel getaddressText = new JLabel("Abholaddresse:");
        JLabel destaddressText = new JLabel("Zieladdresse:");
        JLabel finaldateText = new JLabel("Abliefern bis:");
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
        JLabel loggedInAsData = new JLabel(driver.getFirstname()+" "+driver.getLastname());
        loggedInAsData.setBounds(635, 25, BOX_LENGTH,BOX_HEIGHT);
        loggedInAsData.setForeground(Color.BLACK);
        add(loggedInAsData);

        updateAssData();

        sizeData.setBounds(LEFT_ALLIGN_2, DATA_TOP, BOX_LENGTH,BOX_HEIGHT);
        datecreatedData.setBounds(LEFT_ALLIGN_2, DATA_TOP+DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        getaddressData.setBounds(LEFT_ALLIGN_2, DATA_TOP+2*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        destaddressData.setBounds(LEFT_ALLIGN_2, DATA_TOP+3*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);
        finaldateData.setBounds(LEFT_ALLIGN_2, DATA_TOP+4*DATA_GAP,BOX_LENGTH, BOX_HEIGHT);

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

        curAssignmentData.setFont(new Font(curAssignmentData.getFont().getName(), Font.PLAIN, curAssignmentData.getFont().getSize()*2));

        add(sizeData);
        add(datecreatedData);
        add(getaddressData);
        add(destaddressData);
        add(finaldateData);

        add(curAssignmentData);
    }

    //All the buttons
    public void createButtons(){
        JButton logoutB= new JButton("Logout");
        JButton acceptB= new JButton("Annehmen");
        JButton deliveredB = new JButton("Erledigt");


        logoutB.setBackground(Color.lightGray);
        logoutB.setBounds(780,20, 100,50);
        logoutB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               main.logout();
            }
        });
        acceptB.setBackground(Color.lightGray);
        acceptB.setBounds(200,275,100,50);
        if(ass==null){acceptB.setText("Aktualisieren");}
        acceptB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(ass!=null) {
                        DBM.updateAssDriver_id(ass.getAss_id(),driver.getDriver_id());
                        DBM.updateAssStatus("executing", ass.getAss_id());
                        deliveredB.setVisible(true);
                        acceptB.setVisible(false);
                    }else{
                     acceptB.setText("Aktualisieren");
                        ass=getBestAssignment(DBM.getOpenAssignments(vehicle.getSpace()),driver);
                        if (ass!=null){
                            acceptB.setText("Annehmen");
                            updateAssData();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        deliveredB.setVisible(false);
        deliveredB.setBackground(Color.lightGray);
        deliveredB.setBounds(200, 325, 100,50);
        deliveredB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    DBM.updateDriverPos(ass.getDeliveryLocation().getLocation_id(),driver.getDriver_id());
                    DBM.updateAssStatus("finished",ass.getAss_id());
                    ass=getBestAssignment(DBM.getOpenAssignments(vehicle.getSpace()),driver);
                    if(ass==null) {
                        acceptB.setText("Aktualisieren");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                updateAssData();
                deliveredB.setVisible(false);
                acceptB.setVisible(true);

            }
        });

        add(logoutB);
        add(acceptB);
        add(deliveredB);
    }

    //updates Textfields
    private void updateAssData(){
        if(ass!=null) {
            sizeData.setText(String.valueOf(ass.getSize()));
            datecreatedData.setText(String.valueOf(ass.getDate_created()));
            getaddressData.setText(ass.getAddress_pickup());
            destaddressData.setText(ass.getAddress_delivery());
            finaldateData.setText(String.valueOf(ass.getDate_desired()));
            curAssignmentData.setText("ID" + ass.getAss_id());
        }else{
            //TODO Button aktualisieren??? wenn kein Auftrag mehr vorhanden ist
            sizeData.setText("-----");
            datecreatedData.setText("-----");
            getaddressData.setText("-----");
            destaddressData.setText("-----");
            finaldateData.setText("-----");
            curAssignmentData.setText("ID ");
        }
    }

    //All Data for the Driver
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

    //All changing Data for the Driver
    public void createDriverData() throws SQLException {

        JLabel driverIdData = new JLabel(Integer.toString(driver.getDriver_id()));
        JLabel emp_signData = new JLabel(driver.getEmp_sign());
        JLabel vehicle_typData = new JLabel(vehicle.getType());
        JLabel vspaceData = new JLabel(Integer.toString(vehicle.getSpace()));
        JLabel positionData = new JLabel(location.toText());

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

    public Assignment getBestAssignment(ArrayList<Assignment> openList, Driver driver) throws SQLException {
        if(openList.size()==0) {
            log.debug.printout("Keine Aufträge vorhanden");
            ass = null;
            updateAssData();
            return ass;
        }else {
                Location driLoc = DBM.getLocation(driver.getLocation_id());
                int currentStreet = driLoc.getStreet();
                int currentAvenue = driLoc.getAvenue();
                Assignment ass = null;
                for(Assignment a: openList) {
                    if(ass==null || a.Manhattan(currentStreet,currentAvenue)<ass.Manhattan(currentStreet,currentAvenue)) {
                        ass=a;
                    }
                }
                return ass;
        }
    }

}

