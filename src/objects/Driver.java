package objects;


import controller.DBM;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by Andi on 02.08.2016.
 */
public class Driver {

    private int emp_id;
    private String emp_sign;
    private String firstname;
    private String lastname;
    private String password;
    private int driver_id;
    private int location_id;
    private int vehicle_id;
    private int super_manager;
    private int engaged;
    private Date driverSince;
    private Vehicle vehicle;
    private Location location;


    public Driver(String firstname, String lastname, String password, int driver_id, Vehicle vehicle, Date driverSince) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.driver_id = driver_id;
        this.vehicle = vehicle;
        this.driverSince=driverSince;
    }


    public Driver(ResultSet r) throws SQLException {
        emp_id = r.getInt(1);
        emp_sign = r.getString(2);
        firstname = r.getString(3);
        lastname = r.getString(4);
        password = r.getString(5);
        driver_id = r.getInt(6);
        location_id = r.getInt(8);
        vehicle_id = r.getInt(9);
        super_manager = r.getInt(10);
        engaged = r.getInt(11);
    }
    public Date getDriverSince() {
        return driverSince;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getSuper_manager() {
        return super_manager;
    }

    public void setSuper_manager(int super_manager) {
        this.super_manager = super_manager;
    }

    public int getEngaged() {
        return engaged;
    }

    public void setEngaged(int engaged) {
        this.engaged = engaged;
    }

    public String getEmp_sign() {

        return emp_sign;
    }

    public void setEmp_sign(String emp_sign) {
        this.emp_sign = emp_sign;
    }

    public int getEmp_id() {

        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
}
