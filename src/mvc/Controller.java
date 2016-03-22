package mvc;
/**
 * Simple controller class used to show how Model-View-Controller
 * is implemented.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 */
public class Controller
{
    ///////////////////
    //  Properties   //
    ///////////////////
     
    public View myView;
    public Model myModel;
    
    ///////////////////
    //    Methods    //
    ///////////////////
    
    /**
     * Controller constructor; view must be passed in since 
     * controller has responsibility to notify view when 
     * some event takes place.
     */
    public Controller()
    {
        myModel = new Model();
        myView = new View(this);
    }
    
    /**
     * Modifies the number value of the model and gives it to
     * the view.
     *
     * <pre>
     * pre:  a valid view and controller have been designated
     * post: the number value of the model is increased by one,
     *       and the view is modified accordingly
     * </pre>
     */
    public void increment(Integer iRow)
    {
        int value, row;
        
        row = iRow.intValue();
        myModel.incrementValue();
        value = myModel.getNumberValue();
        myView.changeImage(value, row);
        myView.setTextField(""+value);
    }
    
    /**
     * Modifies the number value of the model and gives it to
     * the view.
     *
     * <pre>
     * pre:  a valid view and controller have been designated
     * post: the number value of the model is decreased by one,
     *       and the view is modified accordingly
     * </pre>
     */
    public void decrement()
    {
        int value;
        myModel.decrementValue();
        value = myModel.getNumberValue();
        myView.setTextField(""+value);
    }
    
    /**
     * Obtains the number value of the model.
     *
     * <pre>
     * pre:  a valid view and controller have been designated
     * post: the number value of the model obtained
     * </pre>
     *
     * @return the model's number value
     */
    public String getModelValue()
    {
        String modelValue;
        
        modelValue = ""+myModel.getNumberValue();
        return modelValue;
    }
    
}