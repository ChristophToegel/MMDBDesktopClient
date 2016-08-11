package objects;

/**
 * Created by Andi on 03.08.2016.
 */
public class Vehicle {
    private int vehicle_id;
    private String Type;
    private int Space;

    public Vehicle(int vehicle_id, String Type, int Space) {
        this.Type = Type;
        this.Space = Space;
        this.vehicle_id = vehicle_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public String getType() {
        return Type;
    }

    public int getSpace() {
        return Space;
    }

}
