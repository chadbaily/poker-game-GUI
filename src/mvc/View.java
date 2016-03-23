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
import java.awt.Image;
import java.lang.reflect.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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
	private JTextField myTextField;
	private Controller myController;
	private Image myXImage;
	private Image myImage;
	private JLabel myLabel;
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
		myPlayerCardPanel.setLocation(100, 480);
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

		value = myController.handRanking();
		myLabel = new JLabel(value);
		myLabel.setSize(getSize());
		myLabel.setLocation(300, 100);

		this.add(myCompCardPanel);
		this.add(myPlayerCardPanel);
		this.add(myLabel);

		this.addWindowListener(new AWindowListener());
		this.associateListeners();
		this.setVisible(true);
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
		Method flipOver;
		Class<?>[] classArgs;

		controllerClass = myController.getClass();

		flipOver = null;
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
			flipOver = controllerClass.getMethod("flipCardsOver", classArgs);
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
			myCardListener[i] = new ButtonListener(myController, flipOver, args);
			myPlayerCardView[i].addMouseListener(myCardListener[i]);
		}
	}

	/**
	 * When the mouse clicks on a card, the card is then revealed
	 * 
	 * @param row
	 *            the index of the card
	 * @param image
	 *            the image passed in
	 */
	public void changeImage(int row, Image image)
	{
		myPlayerCardView[row].setImage(image);
	}

	/**
	 * Updates myTextField with the String text.
	 *
	 * @param text
	 *            the text string to use in updating the text field
	 */
	public void setTextField(String text)
	{
		myLabel.setText(text);
	}

	/**
	 * Makes a border around each Card
	 */
	public void makeBorder(int row)
	{
		for (int i = 0; i < myNumSquares; i++)
		{
			// myPlayerCardView[row].setBorder(new LineBorder(Color.BLUE, 12));
		}
	}

}