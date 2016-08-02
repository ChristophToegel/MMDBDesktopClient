package Objekts;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andi on 02.08.2016.
 */
public class driver {

    private int emp_id;
    private int emp_sign;
    private String firstname;
    private String lastname;
    private String password;
    private int driver_id;
    private int location_id;
    private int vehicle_id;
    private int super_manager;
    private int engaged;


    public driver(ResultSet r) throws SQLException {
        emp_id = r.getInt(0);
        emp_sign = r.getInt(1);
        firstname = r.getString(2);
        lastname = r.getString(3);
        password = r.getString(4);
        driver_id = r.getInt(5);
        location_id = r.getInt(6);
        vehicle_id = r.getInt(7);
        super_manager = r.getInt(8);
        engaged = r.getInt(9);
    }

}
