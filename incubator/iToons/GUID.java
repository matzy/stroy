/*
 * GUID.java
 *
 * Created on 19. September 2004, 10:10
 */
import java.util.*;

/**
 *
 * @author  Stephan
 */
public class GUID {
    
    /** Creates a new instance of GUID */
    public GUID() {
        guid = "1|" + 
               System.currentTimeMillis() + "|" +
               random.nextDouble();
    }
        
    public GUID( String str ) {
        guid = str;
    }
    
    public boolean equals( Object obj ) {
        return obj instanceof GUID &&
               ((GUID)obj).guid == guid;
    }
    
    
    public String toString() {
        return guid;
    }
    
    private String guid;
    final private static Random random = new Random(); 
}
