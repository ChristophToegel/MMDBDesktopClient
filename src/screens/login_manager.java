package screens;

import objects.Manager;

import javax.swing.*;
import java.sql.SQLException;


/**
 * Created by Christoph on 01.08.16.
 */
public class login_manager extends JTabbedPane {

    private manager_assignments assignmentScreen;
    private manager_drivers driverScreen;
    private manager_vehicles vehicleScreen;

    public login_manager(Manager manager) throws SQLException {

        assignmentScreen = new manager_assignments(manager);
        driverScreen = new manager_drivers(manager);
        vehicleScreen = new manager_vehicles(manager);
        addTab("Aufträge", assignmentScreen);
        addTab("Fahrer", driverScreen);
        addTab("Fahrzeuge", vehicleScreen);
    }
}
