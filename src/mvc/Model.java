package mvc;
/**
 * Simple model class used to show how Model-View-Controller
 * is implemented.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 */
public class Model
{
    //////////////////////
    //    Properties    //
    //////////////////////
    
    /**
     * A number that will be incremented or
     * decremented one number at a time
     */
    private int myNumberValue;
    
    //////////////////////
    //     Methods      //
    //////////////////////
    
    /**
     * Default model constructor 
     */
    public Model()
    {
        myNumberValue = 100;
    }
    
    /**
     * Method to increment myNumberValue by one
     *
     * <pre>
     * pre:  myNumberValue is set to a valid integer in the
     *       range Integer.MIN_VALUE and Integer.MAX_VALUE-1
     * post: myNumberValue is incremented by one
     * </pre>
     */
    public void incrementValue()
    {
        myNumberValue++;
    }
    
    /**
     * Method to decrement myNumberValue by one
     *
     * <pre>
     * pre:  myNumberValue is set to a valid integer in the
     *       range Integer.MIN_VALUE+1 and Integer.MAX_VALUE
     * post: myNumberValue is incremented by one
     * </pre>
     */
    public void decrementValue()
    {
        myNumberValue--;
    }
    
    ////////////////////////////
    //   Accessor Methods     //
    ////////////////////////////
    public void setNumberValue(int value)
    {
        myNumberValue = value;
    }
    
    public int getNumberValue()
    {
        return myNumberValue;
    }
}