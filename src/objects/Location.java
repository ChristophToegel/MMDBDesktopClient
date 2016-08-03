package objects;

/**
 * Created by Andi on 03.08.2016.
 */
public class Location {
    private int location_id;
    private int avenue;
    private int street;

    public Location(int location_id, int avenue, int street) {
        this.avenue = avenue;
        this.street = street;
        this.location_id = location_id;
    }

    public String toText() {
        return ""+avenue+".Avenue/"+street+"Street";
    }
}
