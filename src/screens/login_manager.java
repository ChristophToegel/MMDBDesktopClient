package screens;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Christoph on 01.08.16.
 */
public class login_manager extends JTabbedPane {

    public login_manager() throws SQLException{

        manager_assignments assignmentScreen = new manager_assignments();
        manager_drivers driverScreen = new manager_drivers();
        manager_vehicles vehicleScreen = new manager_vehicles();
        addTab("Aufträge",assignmentScreen);
        addTab("Fahrer",driverScreen);
        addTab("Fahrzeuge",vehicleScreen);
    }

}
