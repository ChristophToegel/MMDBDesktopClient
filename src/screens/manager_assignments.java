
package screens;
import controller.DBM;
import log.debug;
import objects.Assignment;
import controller.main;
import objects.Driver;
import objects.Manager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Andi on 04.08.2016.
 */

public class manager_assignments extends JPanel implements ListSelectionListener{

    private static final int X_SIGNEDINTEXT = 450;//Start x logintext
    private static final int Y_SIGNEDINTEXT = 0;  //Start y logintext
    private static final int X_FIELDS = 100;
    private static final int Y_FIELDSSTART = 125;
    private static final int Y_GAP = 50;
    private static final int X_GAP = 35;
    private static final int BOX_LENGTH = 200; //Labellength
    private static final int BOX_HEIGHT = 40; //Labelheight
    private Manager manager;
    private JButton assign;

    private ArrayList<Assignment> AssignmentArrayList;
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);
    private JTextField sizeData = new JTextField();
    private JTextField dateYear;
    private JTextField dateMonth;
    private JTextField dateDay;
    private JTextField getAddressStreet;
    private JTextField getAddressAvenue;
    private JTextField destAddressStreet;
    private JTextField destAddressAvenue;
    private JTextField driverData = new JTextField();
    private JTextField statusData = new JTextField();
    private JScrollPane listScroll = new JScrollPane(list);




    public manager_assignments(Manager manager) {
        this.manager = manager;
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

        sizeData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART,BOX_LENGTH,BOX_HEIGHT);
        createDate();
        createGetAddress();
        createDestAddress();
        driverData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+4*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        statusData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+5*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        listScroll.setBounds(X_FIELDS+BOX_LENGTH*2+X_GAP,Y_FIELDSSTART,BOX_LENGTH+BOX_LENGTH/2,5*Y_GAP+BOX_HEIGHT);


        EmptyBorder border = new EmptyBorder(2,5,2,5);

        sizeData.setOpaque(true);
        sizeData.setBorder(border);
        sizeData.setBackground(Color.white);

        driverData.setOpaque(true);
        driverData.setBorder(border);
        driverData.setBackground(Color.lightGray);

        statusData.setOpaque(true);
        statusData.setBorder(border);
        statusData.setBackground(Color.lightGray);

        driverData.setEditable(false);
        statusData.setEditable(false);
        add(sizeData);
        add(driverData);
        add(statusData);
        add(listScroll);
    }

    private void createDate() {
        JPanel DateData =new JPanel();
        DateData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        DateData.setOpaque(true);
        EmptyBorder border = new EmptyBorder(2,5,2,5);
        DateData.setBorder(border);
        DateData.setBackground(Color.white);
        JLabel year = new JLabel("J:");
        dateYear = new JTextField("");
        JLabel month = new JLabel("M:");
        dateMonth = new JTextField("");
        JLabel day = new JLabel("T:");
        dateDay = new JTextField("");
        dateYear.setPreferredSize( new Dimension( 60, BOX_HEIGHT-10 ) );
        dateMonth.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );
        dateDay.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );

        DateData.add(day);
        DateData.add(dateDay);
        DateData.add(month);
        DateData.add(dateMonth);
        DateData.add(year);
        DateData.add(dateYear);
        add(DateData);
    }

    private void createGetAddress() {
        JPanel getAddressData =new JPanel();
        getAddressData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+2*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        getAddressData.setOpaque(true);
        EmptyBorder border = new EmptyBorder(2,5,2,5);
        getAddressData.setBorder(border);
        getAddressData.setBackground(Color.white);
        JLabel street = new JLabel("Street");
        getAddressStreet = new JTextField("");
        JLabel avenue = new JLabel("Avenue");
        getAddressAvenue = new JTextField("");

        getAddressStreet.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );
        getAddressAvenue.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );
        getAddressData.add(getAddressStreet);
        getAddressData.add(street);
        getAddressData.add(getAddressAvenue);
        getAddressData.add(avenue);
        add(getAddressData);
    }

    private void createDestAddress() {
        JPanel destAddressData =new JPanel();
        destAddressData.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+3*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        destAddressData.setOpaque(true);
        EmptyBorder border = new EmptyBorder(2,5,2,5);
        destAddressData.setBorder(border);
        destAddressData.setBackground(Color.white);

        JLabel street = new JLabel("Street");
        destAddressStreet = new JTextField("");
        JLabel avenue = new JLabel("Avenue");
        destAddressAvenue = new JTextField("");

        destAddressStreet.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );
        destAddressAvenue.setPreferredSize( new Dimension( 30, BOX_HEIGHT-10 ) );
        destAddressData.add(destAddressStreet);
        destAddressData.add(street);
        destAddressData.add(destAddressAvenue);
        destAddressData.add(avenue);
        add(destAddressData);
    }

    private void createButtons() {
        JButton logout = new JButton("Logout");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.logout();
            }
        });

        assign = new JButton("Auftrag abgeben");
        assign.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    int größe=Integer.parseInt(sizeData.getText());
                    String date=dateYear.getText()+"-"+dateMonth.getText()+"-"+dateDay.getText();
                    Date date_desired =Date.valueOf(date);
                    log.debug.printout(date_desired);
                    int add_get=getAddressID(getAddressStreet.getText(),getAddressAvenue.getText());
                    int add_dest=getAddressID(destAddressStreet.getText(),destAddressAvenue.getText());
                    String status="open";
                    java.util.Date utilDate = new java.util.Date();
                    Date date_created = new java.sql.Date(utilDate.getTime());
                    clearAllFields();

                if(list.isSelectionEmpty()){
                    debug.printout("insert");
                   try {
                        DBM.InsertAssignment(manager.getManager_id(),größe,status,add_get,add_dest,date_created,date_desired);
                        clearAllFields();
                    } catch (SQLException e1) {
                       e1.printStackTrace();
                    }

                }
                else{
                    debug.printout("updaten");
                    int ass_id =AssignmentArrayList.get(list.getSelectedIndex()).getAss_id();
                    try {
                        DBM.UpdateAssignment(ass_id,manager.getManager_id(),größe,status,add_get,add_dest,date_created,date_desired);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    clearAllFields();
                }
                updateList();
            }
        });

        JButton clearFields = new JButton("Felder leeren");
        clearFields.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearAllFields();
            }
        });

        clearFields.setBackground(Color.lightGray);
        clearFields.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+7*Y_GAP,BOX_LENGTH,BOX_HEIGHT);
        logout.setBackground(Color.LIGHT_GRAY);
        logout.setBounds(X_SIGNEDINTEXT+(BOX_LENGTH/2)*3,Y_SIGNEDINTEXT,BOX_LENGTH/2,BOX_HEIGHT);
        assign.setBackground(Color.LIGHT_GRAY);
        assign.setBounds(X_FIELDS+BOX_LENGTH/2+X_GAP,Y_FIELDSSTART+6*Y_GAP,BOX_LENGTH,BOX_HEIGHT);

        add(clearFields);
        add(logout);
        add(assign);
    }

    private void updateList() {
        listModel =new DefaultListModel<>();
        fillList();
        list.setModel(listModel);

    }

    private void clearAllFields() {
        assign.setText("Auftrag abgeben");
        sizeData.setText("");
        dateDay.setText("");
        dateMonth.setText("");
        dateYear.setText("");
        getAddressAvenue.setText("");
        getAddressStreet.setText("");
        destAddressAvenue.setText("");
        destAddressStreet.setText("");
        driverData.setText("");
        statusData.setText("open");
        list.clearSelection();
    }

    private int getAddressID(String stringStreet, String stringAvenue) {
        int street= Integer.parseInt(stringStreet);
        int avenue= Integer.parseInt(stringAvenue);
        debug.printout("Street"+street+"  Avenue"+avenue);
        //TODO eventuell noch fehlerabfangen ob zahlen möglich sind
        int add_id= 0;
        try {
            add_id = DBM.getAddressId(street,avenue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return add_id;
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

    private void fillList(){
        try {
            AssignmentArrayList = DBM.getOpenAssignments(Integer.MAX_VALUE);
            for (Assignment a : AssignmentArrayList)
                listModel.addElement(a.getAss_id());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){
            if (list.getSelectedIndex() == -1) {
                //TODO nothing
            }else{
                assign.setText("Auftrag ändern");
                sizeData.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getSize()));
                Calendar cal = Calendar.getInstance();
                cal.setTime(AssignmentArrayList.get(list.getSelectedIndex()).getDate_desired());
                dateDay.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                dateMonth.setText(String.valueOf(cal.get(Calendar.MONTH)+1));
                dateYear.setText(String.valueOf(cal.get(Calendar.YEAR)));
                getAddressStreet.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getPickupLocation().getStreet()));
                getAddressAvenue.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getPickupLocation().getAvenue()));
                destAddressStreet.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getDeliveryLocation().getStreet()));
                destAddressAvenue.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getDeliveryLocation().getAvenue()));
                log.debug.printout(AssignmentArrayList.get(list.getSelectedIndex()).getDriver_id());
                int ass_id=AssignmentArrayList.get(list.getSelectedIndex()).getDriver_id();
               if(ass_id!=0){
                    Driver driver=null;
                try {
                     driver= DBM.getDriverById(AssignmentArrayList.get(list.getSelectedIndex()).getDriver_id());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                    driverData.setText(String.valueOf(driver.getFirstname() + " " + driver.getLastname()));
                }else {
                    driverData.setText("-------");
                }
                statusData.setText(String.valueOf(AssignmentArrayList.get(list.getSelectedIndex()).getStatus()));
            }
        }
    }
}