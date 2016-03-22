package mvc;
/**
 * Simple view class used to show how Model-View-Controller
 * is implemented.  This is also the main application.
 * Version 2.0 uses Generics.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 * @version 2.0 (1 February 2008)
 */

import java.awt.*;
import java.lang.reflect.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends Frame
{
	//////////////////////
	// Properties //
	//////////////////////

	public final static int myNumSquares = 5;
	private Can[] myCompCardView;
	private Can[] myPlayerCardView;
	private JPanel myCompCardPanel;
	private JPanel myPlayerCardPanel;
	private ButtonListener[] myCardListener;
	private TextField myTextField;
	private Controller myController;
	private Image myXImage;
	// private Image myOImage;
	private Image myBlankImage;

	///////////////////////
	// Methods //
	///////////////////////

	/**
	 * View constructor used to lay out the view
	 *
	 * <pre>
	 * pre:  none
	 * post: the view is set up and initialized
	 * </pre>
	 */
	public View(Controller controller)
	{
		String value;
		int i;

		this.setSize(600, 600);
		this.setLayout(null);
		this.setBackground(Color.gray);

		myXImage = Toolkit.getDefaultToolkit().getImage("src/cards/3C.GIF");
		myBlankImage = Toolkit.getDefaultToolkit().getImage("src/cards/E.GIF");

		myCompCardView = new Can[myNumSquares];
		myPlayerCardView = new Can[myNumSquares];
		int valW = 73;
		int valL = 97;

		myCardListener = new ButtonListener[myNumSquares];
		myCompCardPanel = new JPanel(new GridLayout(1, 0));
		myCompCardPanel.setSize(365, 97);
		myCompCardPanel.setLocation(100, 80);

		myPlayerCardPanel = new JPanel(new GridLayout(1, 0));
		myPlayerCardPanel.setSize(365, 97);
		myPlayerCardPanel.setLocation(100, 240);
		// mySquaresPanel.setBackground(Color.red);
		// use Image Panel to store cards
		for (i = 0; i < myNumSquares; i++)
		{
			myCompCardView[i] = new Can(myBlankImage);
			myPlayerCardView[i] = new Can(myBlankImage);
			myPlayerCardPanel.add(myPlayerCardView[i]);
			myCompCardPanel.add(myCompCardView[i]);
		}

		myController = controller;

		value = myController.getModelValue();
		myTextField = new TextField(value);
		myTextField.setSize(100, 50);
		myTextField.setLocation(400, 400);

		this.add(myCompCardPanel);
		this.add(myPlayerCardPanel);
		this.add(myTextField);

		this.setVisible(true);
		this.addWindowListener(new AWindowListener());
		this.associateListeners();
	}

	/**
	 * Associates each component's listener with the controller and the correct
	 * method to invoke when triggered.
	 *
	 * <pre>
	 * pre:  the controller class has be instantiated
	 * post: all listeners have been associated to the controller
	 *       and the method it must invoke
	 * </pre>
	 */
	public void associateListeners()
	{
		Class<? extends Controller> controllerClass;
		Method incrementMethod;
		Class<?>[] classArgs;

		controllerClass = myController.getClass();

		incrementMethod = null;
		classArgs = new Class[1];

		try
		{
			classArgs[0] = Class.forName("java.lang.Integer");
		}
		catch (ClassNotFoundException e)
		{
			String error;
			error = e.toString();
			System.out.println(error);
		}

		try
		{
			incrementMethod = controllerClass.getMethod("increment", classArgs);
		}
		catch (NoSuchMethodException exception)
		{
			String error;

			error = exception.toString();
			System.out.println(error);
		}
		catch (SecurityException exception)
		{
			String error;

			error = exception.toString();
			System.out.println(error);
		}

		int i;
		Integer[] args;

		for (i = 0; i < myNumSquares; i++)
		{
			args = new Integer[1];
			args[0] = new Integer(i);
			myCardListener[i] = new ButtonListener(myController, incrementMethod, args);
			myPlayerCardView[i].addMouseListener(myCardListener[i]);
		}
	}

	public void changeImage(int num, int row)
	{
		if (0 == num % 2)
		{
			myPlayerCardView[row].setImage(myXImage);
		}
		else
		{
			myPlayerCardView[row].setImage(myBlankImage);
		}
	}

	/**
	 * Updates myTextField with the String text.
	 *
	 * @param text
	 *            the text string to use in updating the text field
	 */
	public void setTextField(String text)
	{
		myTextField.setText(text);
	}

}