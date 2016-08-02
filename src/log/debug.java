package log;

/**
 * Created by Christoph on 02.08.16.
 */
public class debug {

    private static final boolean DEBUG_MODE = true;

    public static void printout(Object object){
        if(DEBUG_MODE) {
            System.out.println(object);
        }
    }
}
