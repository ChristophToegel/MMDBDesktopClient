package objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by juli on 03.08.16.
 */

public class Assignment {

    private int ass_id;
    private int driver_id;
    private int size;
    private String Status;
    private String address_pickup;
    private int street_pickup;
    private int avenue_pickup;
    private String address_delivery;
    private int street_delivery;
    private int avenue_delivery;
    private Date date_created;
    private Date date_accepted;
    private Date date_delievered;
    private Date date_desired;

    public Assignment(ResultSet r) throws SQLException {
        ass_id = r.getInt(1);
        driver_id = r.getInt(2);
        size = r.getInt(4);
        Status = r.getString(5);

        date_created =r.getDate(8);
        date_created =r.getDate(8);
        date_accepted = r.getDate(9);
        date_delievered = r.getDate(10);
        date_desired = r.getDate(11);

            street_delivery = r.getInt(13);
            avenue_delivery = r.getInt(14);
            street_pickup = r.getInt(16);
            avenue_pickup = r.getInt(17);


    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_accepted() {
        return date_accepted;
    }

    public void setDate_accepted(Date date_accepted) {
        this.date_accepted = date_accepted;
    }

    public Date getDate_delievered() {
        return date_delievered;
    }

    public void setDate_delievered(Date date_delievered) {
        this.date_delievered = date_delievered;
    }

    public Date getDate_desired() {
        return date_desired;
    }

    public void setDate_desired(Date date_desired) {
        this.date_desired = date_desired;
    }

    public String getAddress_delivery() {
        address_delivery=street_delivery+".Street "+avenue_delivery+".Avenue";
        return address_delivery;
    }

    public void setAddress_delivery(String address_delivery) {
        this.address_delivery = address_delivery;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getAss_id() {

        return ass_id;
    }

    public void setAss_id(int ass_id) {
        this.ass_id = ass_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAddress_pickup() {
        address_pickup=street_pickup+".Street "+avenue_pickup+".Avenue";
        return address_pickup;
    }

    public void setAddress_pickup(String address_pickup) {
        this.address_pickup = address_pickup;
    }


}
