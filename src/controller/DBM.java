package controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import log.debug;
import objects.*;

/**
 * Created by Christoph on 02.08.16.
 */

public class DBM {

    private static Connection conn;

    private final static String USER="j.dietz";
    private final static String PASSWORD ="mmdb";
    private final static String DATABASE ="jdbc:mysql://132.199.139.24:3306/mmdb16_juliandietz";

    public static void openDBConnection() {
        try {
            conn = (Connection) DriverManager.getConnection(DATABASE, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        debug.printout("Connected to database");
    }

    private static void closeDBConnection() throws SQLException{
        conn.close();
        debug.printout("Disconnected from database via debug");
    }


    public static boolean getLogin (String name, char[] passwordchar) throws SQLException{
        openDBConnection();
        PreparedStatement pstmt = null;
        boolean check=false;
        try {
            String password=String.valueOf(passwordchar);
            pstmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM   employee  WHERE emp_sign = ? AND password = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.executeQuery();
            ResultSet rs = pstmt.getResultSet();

            debug.printout(rs.first());

            check=rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            closeDBConnection();

        }

        return check;
    }

    public static boolean DriverOrManager (String name) throws SQLException {
        openDBConnection();
        String query = "SELECT * FROM driver INNER JOIN employee ON driver.emp_id=employee.emp_id WHERE emp_sign = ?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1,name);
        p.executeQuery();
        ResultSet rs = p.getResultSet();

        if(rs.first()) {
            closeDBConnection(); return true;} else {
            closeDBConnection(); return false;}

    }

    public static Driver getDriverData (String name) throws SQLException {
        Driver Driver =null;
        openDBConnection();
        String query = "SELECT * FROM employee INNER JOIN driver ON employee.emp_id=driver.emp_id WHERE emp_sign=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, name);
        p.executeQuery();
        ResultSet rs = p.getResultSet();
        if(rs.first()) {
            Driver = new Driver(rs);
            closeDBConnection();
         } else {
            closeDBConnection();}
        log.debug.printout(Driver.getEmp_sign());
        return Driver;
    }

    public static Driver getDriverById (int driver_id) throws SQLException {
        Driver Driver =null;
        openDBConnection();
        String query = "SELECT * FROM employee INNER JOIN driver ON employee.emp_id=driver.emp_id WHERE driver_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, String.valueOf(driver_id));
        p.executeQuery();
        ResultSet rs = p.getResultSet();
        if(rs.first()) {
            Driver = new Driver(rs);
            closeDBConnection();
        } else {
            closeDBConnection();}
        log.debug.printout(Driver.getEmp_sign());
        return Driver;
    }

    public static void updateDriverPos(int delivery_id, int driver_id) throws SQLException {
        openDBConnection();
        String query = "UPDATE driver SET location_id=? WHERE driver_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, String.valueOf(delivery_id));
        p.setString(2, String.valueOf(driver_id));
        p.executeUpdate();
        closeDBConnection();
    }

    public static void updateAssStatus(String status, int assignment_id) throws SQLException {
        openDBConnection();
        String query = "UPDATE assignment SET status=? WHERE assignment_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, status);
        p.setString(2, String.valueOf(assignment_id));
        p.executeUpdate();
        closeDBConnection();
    }


    public static Manager getManagerData (String name) throws SQLException {
        Manager manager=null;
        openDBConnection();
        String query = "SELECT * FROM employee e INNER JOIN manager m on e.emp_id=m.emp_id WHERE emp_sign=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, name);
        p.executeQuery();
        ResultSet rs = p.getResultSet();
        if(rs.first()) {
            manager= new Manager(rs);
            closeDBConnection();
        } else {
            closeDBConnection();}
        log.debug.printout(manager.getEmp_sign());
        return manager;
    }

    public static Location getLocation (int adress_id) throws SQLException {
        openDBConnection();
        String query = "SELECT avenue, street FROM address WHERE address_id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setInt(1,adress_id);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        int Avenue = rs.getInt(1);
        int Street = rs.getInt(2);
        int locId = adress_id;
        closeDBConnection();
        return new Location(locId,Avenue,Street);
    }

    public static Vehicle getVehicle(int vehicle_id) throws SQLException{
        openDBConnection();
        String query = "SELECT space, model FROM vehicle WHERE vehicle_id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setInt(1,vehicle_id);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        int space = rs.getInt(1);
        String type = rs.getString(2);
        closeDBConnection();
        return new Vehicle(vehicle_id, type, space);
    }


    public static Assignment getAssignmentData (int driver_id) throws SQLException {
        Assignment assignment=null;
        openDBConnection();
        String query = "SELECT * FROM assignment a INNER JOIN address ad ON a.address_delivery = ad.address_id INNER JOIN address ON a.address_pickup = address.address_id WHERE driver_id=? AND status='open'";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, String.valueOf(driver_id));
        p.executeQuery();
        ResultSet rs = p.getResultSet();
        if(rs.first()) {
            assignment = new Assignment(rs);
            closeDBConnection();
        } else {
            closeDBConnection();}
        return assignment;
    }

    public static ArrayList<Vehicle> getAllVehicles() throws SQLException{
        ArrayList<Vehicle> vehicles= new ArrayList<>();
        openDBConnection();
        String query = "SELECT * FROM vehicle";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            int vehicle_id= rs.getInt(1);
            int space = rs.getInt(2);
            String type = rs.getString(3);
            Vehicle aktvehicle=new Vehicle(vehicle_id, type ,space);
            vehicles.add(aktvehicle);
        }

        closeDBConnection();
        log.debug.printout(vehicles.size()+"Fahrzeuge");
        return vehicles;

    }


    public static ArrayList<Assignment> getAllAssignments() throws SQLException {
        ArrayList<Assignment> assignments=new ArrayList<>();
        openDBConnection();
        String query = "SELECT * FROM assignment a INNER JOIN address ad ON" +
                " a.address_delivery = ad.address_id INNER JOIN address ON a.address_pickup = address.address_id";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.executeQuery();
        ResultSet rs = p.getResultSet();

        while (rs.next()){
            Assignment aktassignment = new Assignment(rs);
            assignments.add(aktassignment);
        }
        log.debug.printout(assignments.size());
        closeDBConnection();
        log.debug.printout(assignments.size()+"Aufträge");
        return assignments;
    }

    public static ArrayList<Driver> getDriverList() throws SQLException {
        openDBConnection();
        ArrayList<Driver> liste = new ArrayList<Driver>();
        String query = "SELECT employee.firstname, employee.lastname, employee.password, driver.driver_id, vehicle.vehicle_id," +
                "vehicle.space, vehicle.model, driver.driverSince FROM employee INNER JOIN driver ON employee.emp_id=driver.emp_id LEFT JOIN vehicle ON " +
                "driver.vehicle_id=vehicle.vehicle_id";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        while(rs.next()) {
            Vehicle veh = new Vehicle(rs.getInt(5),rs.getString(7),rs.getInt(6));
            Driver Driver = new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),veh, rs.getDate(8));
            liste.add(Driver);
        }
        closeDBConnection();
        for(int i=0;i<liste.size();i++) {
            debug.printout(liste.get(i).getDriver_id()+liste.get(i).getFirstname()+liste.get(i).getLastname()+liste.get(i).getPassword()
                    +liste.get(i).getVehicle().getType());
        }
        return liste;
    }

    public static ArrayList<Driver> getOpenDrivers() throws SQLException {
        openDBConnection();
        ArrayList<Driver> list = new ArrayList<Driver>();
        String query = "SELECT employee.firstname, employee.lastname, employee.password, driver.driver_id, vehicle.vehicle_id," +
                "vehicle.space, vehicle.model FROM employee INNER JOIN driver ON employee.emp_id=driver.emp_id LEFT JOIN vehicle ON " +
                "driver.vehicle_id=vehicle.vehicle_id WHERE driver.status = 1" ;
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        while(rs.next()){
            Vehicle veh = new Vehicle(rs.getInt(5), rs.getString(7), rs.getInt(6));
            Driver d = new Driver(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), veh, rs.getDate(8));
            list.add(d);

        }
        closeDBConnection();
        for(int i = 0; i<list.size(); i++){
            debug.printout("OpenDriver Liste " + list.get(i).getDriver_id() + list.get(i).getFirstname() + list.get(i).getLastname() + list.get(i).getPassword()
                    + list.get(i).getVehicle().getType());
        }
    return list;
    }

    public static ArrayList<Assignment> getOpenAssignments(int size) throws SQLException {
        log.debug.printout("get open assignments!!");
        openDBConnection();
        ArrayList<Assignment> list = new ArrayList<Assignment>();
        String query = "SELECT * FROM assignment a INNER JOIN address ad ON a.address_delivery = ad.address_id INNER JOIN address ON a.address_pickup = address.address_id WHERE a.status = 'OPEN' AND a.size<=? ";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setString(1, String.valueOf(size));
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        log.debug.printout(rs.wasNull());
        while(rs.next()){
            Assignment a = new Assignment(rs);
            list.add(a);
        }
        closeDBConnection();
        log.debug.printout(list.size()+"offene Aufträge");
        return list;
    }



    public static void insertVehicle(String typ, int größe)  throws SQLException {
        openDBConnection();
        String query = "INSERT INTO vehicle (model,space) VALUES (?,?);";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setString(1, typ);
        ps.setString(2,String.valueOf(größe));
        ps.executeUpdate();
        closeDBConnection();
    }

    public static int getAddressId(int street, int avenue) throws SQLException {
        int add_id;
        openDBConnection();
        String query = "SELECT * FROM address Where avenue=? AND street=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setString(1, String.valueOf(avenue));
        ps.setString(2, String.valueOf(street));
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        rs.next();
        add_id=rs.getInt(1);
        closeDBConnection();
        return add_id;
    }

    public static void InsertAssignment(int mangager_id, int größe, String status, int add_get, int add_dest, Date date_created, Date date_desired) throws SQLException{
        openDBConnection();
        String query = "INSERT INTO assignment (manager_id,size,status,address_pickup,address_delivery,date_created,date_desired) VALUES (?,?,?,?,?,?,?);";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
        ps.setString(1, String.valueOf(mangager_id));
        ps.setString(2,String.valueOf(größe));
        ps.setString(3,status);

        ps.setString(4,String.valueOf(add_get));
        ps.setString(5,String.valueOf(add_dest));
        ps.setString(6, String.valueOf(date_created));
        ps.setString(7, String.valueOf(date_desired));
        ps.executeUpdate();
        closeDBConnection();
    }


    public static void insertDriver  (String prename, String surname,String vehType, String password) {
            try {
            openDBConnection();
            java.util.Date utilDate = new java.util.Date();
            Date date_created = new java.sql.Date(utilDate.getTime());
            int vehiclID;

            String getVehicleID = "SELECT vehicle_id FROM vehicle WHERE model =?;";
            PreparedStatement p1 = (PreparedStatement) conn.prepareStatement(getVehicleID);
            p1.setString(1,vehType);
            p1.executeQuery();
            ResultSet VehicleIDResult = p1.getResultSet();

            if(VehicleIDResult.next()) {vehiclID=VehicleIDResult.getInt(1);} else {
                //TODO text "FEHLER"
                return;}

            String insertEmployee = "INSERT INTO employee (firstname, lastname, password) VALUES(?,?,?);";
            debug.printout(insertEmployee);
            PreparedStatement insertEmp = (PreparedStatement) conn.prepareStatement(insertEmployee);
            insertEmp.setString(1,prename);
            insertEmp.setString(2,surname);
            insertEmp.setString(3,password);
            insertEmp.executeUpdate();
            debug.printout("EMPLOYEE INSERTED");

            String getEmpId = "SELECT emp_id FROM employee ORDER BY emp_id DESC LIMIT 1 ";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(getEmpId);
            ps.executeQuery();
            ResultSet empIDRes = ps.getResultSet();
            empIDRes.next();
            int employeeID = empIDRes.getInt(1);


            String updateEmployee = "UPDATE employee SET emp_sign=? WHERE emp_id=?;";
            PreparedStatement updateEmp = (PreparedStatement) conn.prepareStatement(updateEmployee);
            updateEmp.setString(1,Integer.toString(employeeID));
            updateEmp.setInt(2,employeeID);
            updateEmp.executeUpdate();
            debug.printout("EMPLOYEE UPDATED");

            String insertDriver = "INSERT INTO driver (emp_id,location_id, vehicle_id, super_manager,engaged, driverSince)" +
                    " VALUES(?,1,?,1,1,?);";
            PreparedStatement updateFahrer = (PreparedStatement) conn.prepareStatement(insertDriver);
            updateFahrer.setInt(1,employeeID);
            updateFahrer.setInt(2,vehiclID);
            updateFahrer.setString(3, String.valueOf(date_created));
            updateFahrer.executeUpdate();
            debug.printout("DRIVER INSERTED");
            closeDBConnection();}

            catch (SQLException e) {
                e.printStackTrace();
            }


    }

    public static void UpdateDriver(String preName, String surName,String vehicle, String password, int DriverID ) throws SQLException {
        openDBConnection();
        int empID; int vehID;
        String queryEmpId = "SELECT emp_id FROM driver WHERE driver_id=?;";
        PreparedStatement getEmpId = (PreparedStatement) conn.prepareStatement(queryEmpId);
        getEmpId.setInt(1,DriverID);
        getEmpId.executeQuery();
        ResultSet rs = getEmpId.getResultSet();
        rs.next();
        empID = rs.getInt(1);

        String findVehicleID = "SELECT vehicle_id FROM vehicle WHERE model=?";
        PreparedStatement findVehicle = (PreparedStatement) conn.prepareStatement(findVehicleID);
        findVehicle.setString(1,vehicle);
        findVehicle.executeQuery();
        ResultSet resultVehId = findVehicle.getResultSet();
        if(resultVehId.next()) {vehID=resultVehId.getInt(1);} else {//TODO fehlgeschlagen, gibt vehicl nicht
            return; }


        String updateEmployee = "UPDATE employee SET firstname=?,lastname=?,password=? WHERE emp_id=?";
        PreparedStatement updateEmp = (PreparedStatement) conn.prepareStatement(updateEmployee);
        updateEmp.setString(1,preName);
        updateEmp.setString(2,surName);
        updateEmp.setString(3,password);
        updateEmp.setInt(4,empID);
        updateEmp.executeUpdate();
        debug.printout("UPDATED EMPLOYEE");

        String updateDriver = "UPDATE driver SET vehicle_id =? WHERE driver_id=?";
        PreparedStatement updateDriv = (PreparedStatement) conn.prepareStatement(updateDriver);
        updateDriv.setInt(1,vehID);
        updateDriv.setInt(2,DriverID);
        updateDriv.executeUpdate();
        debug.printout("UPDATE DRIVER");
        closeDBConnection();
    }

    public static void UpdateAssignment(int ass_id, int manager_id, int größe, String status, int add_get, int add_dest, Date date_created, Date date_desired)throws SQLException {
        openDBConnection();
        String query = "UPDATE assignment SET manager_id=?,size=?,status=?,address_pickup=?,address_delivery=?,date_created=?,date_desired=? WHERE assignment_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, String.valueOf(manager_id));
        p.setString(2, String.valueOf(größe));
        p.setString(3, status);
        p.setString(4, String.valueOf(add_get));
        p.setString(5, String.valueOf(add_dest));
        p.setString(6, String.valueOf(date_created));
        p.setString(7, String.valueOf(date_desired));
        p.setString(8, String.valueOf(ass_id));
        p.executeUpdate();
        closeDBConnection();
    }

    public static void updateVehicle(int vehicleId, String typ, int size) throws SQLException {
        openDBConnection();
        String query = "UPDATE vehicle SET model=?, space=?WHERE vehicle_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, typ);
        p.setString(2, String.valueOf(size));
        p.setString(3, String.valueOf(vehicleId));
        p.executeUpdate();
        closeDBConnection();
    }

    public static void updateAssDriver_id(int ass_id, int driver_id) throws SQLException {
        openDBConnection();
        String query = "UPDATE assignment SET driver_id=? WHERE assignment_id=?";
        PreparedStatement p = (PreparedStatement) conn.prepareStatement(query);
        p.setString(1, String.valueOf(driver_id));
        p.setString(2, String.valueOf(ass_id));
        p.executeUpdate();
        closeDBConnection();
    }

}

