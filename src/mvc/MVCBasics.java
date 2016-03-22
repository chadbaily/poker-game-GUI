package mvc;
public class MVCBasics
{
    // Properties
    private Controller myController;
    
    // Methods
    public static void main(String[] args)
    {
        new MVCBasics();
    }
    
    public MVCBasics()
    {
        setController(new Controller());
    }

	public void setController(Controller controller) 
	{
		myController = controller;
	}

	public Controller getController() {
		return myController;
	}
}