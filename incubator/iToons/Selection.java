/*
 * Selection.java
 *
 * Created on 19. September 2004, 09:16
 */

/**
 *
 * @author  Stephan
 */
public interface Selection {
    public boolean belongs( Comic comic );
    public boolean isSystem();
    public String getName();
    public void update();
    public Comic get( int idx );
    public void setName(String name);
    public int size();
}
