package mvc;
/**
 * Handle window events; use the WindowAdapter convenience class
 * to only require overriding window events of interest.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 */
import java.awt.event.*;

public class AWindowListener extends WindowAdapter
{
    /**
     * pre:     a window-closing event has occurred
     * post:    the application associated with the window exits
     *
     * @param e the window event that closes the window
     */
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}
    