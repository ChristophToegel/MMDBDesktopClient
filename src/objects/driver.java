package objects;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andi on 02.08.2016.
 */
public class driver {

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


    public driver(ResultSet r) throws SQLException {
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

    public String getFirstname(){
        return firstname;
    }

}
