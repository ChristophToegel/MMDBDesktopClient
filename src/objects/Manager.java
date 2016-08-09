package objects;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by juli on 05.08.16.
 */
public class Manager {

    private int manager_id;
    private int emp_id;
    private int emp_sign;
    private String firstname;
    private String lastname;




    public Manager (ResultSet result) throws SQLException {
        emp_id = result.getInt(1);
        emp_sign = result.getInt(2);
        firstname = result.getString(3);
        lastname = result.getString(4);
        manager_id= result.getInt(6);
    }

    public int getManager_id() {
        return manager_id;
    }
    public int getEmp_id() {
        return emp_id;
    }

    public int getEmp_sign() {
        return emp_sign;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
