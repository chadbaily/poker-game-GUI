package mvc;

import javax.swing.SwingUtilities;

public class MVCBasics
{
	// Properties
	private Controller myController;

	// Methods
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MVCBasics();
			}
		});
	}

	public MVCBasics()
	{
		setController(new Controller());
	}

	public void setController(Controller controller)
	{
		myController = controller;
	}

	public Controller getController()
	{
		return myController;
	}
}