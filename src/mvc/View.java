package mvc;

/**
 * View for a Poker Game
 * @author chadbaily
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.*;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

@SuppressWarnings("serial")
public class View extends Frame
{
	//////////////////////
	// Properties //
	//////////////////////

	public final static int myNumSquares = 5;
	private JLabel[] myCompCardView;
	private JLabel[] myPlayerCardView;
	private JPanel myCompCardPanel;
	private JPanel myPlayerCardPanel;
	private ButtonListener[] myCardListener;
	private ButtonListener myStartButtonListener;
	private ButtonListener myDiscardButtonListener;
	private JTextField myTextField;
	private Controller myController;
	private JButton myStartButton;
	private JButton myPlayAgain;
	private JLabel myLabel;
	private JLabel myPlayerInfo;
	private JLabel myCPlayerInfo;
	private JLabel myPlayerGameInfo;
	private JLabel myComputerGameInfo;
	private JFrame myFrame;
	private ImageIcon myBlankImage;
	private JButton myDiscardButton;

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
		myFrame = new JFrame("Swing Version");
		myFrame.setSize(600, 600);
		myFrame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		myCompCardView = new JLabel[myNumSquares];
		myPlayerCardView = new JLabel[myNumSquares];

		String myButtonText = "Start Game";
		myStartButton = new JButton(myButtonText);

		myDiscardButton = new JButton("Discard");

		myCardListener = new ButtonListener[myNumSquares];
		myCompCardPanel = new JPanel(new GridLayout(1, 0));
		myPlayerCardPanel = new JPanel(new GridLayout(1, 0));
		myBlankImage = new ImageIcon("src/cards/E.GIF");
		/*
		 * Sets the cards up with 2 rows of 5 cards, there backs facing up
		 */
		for (i = 0; i < myNumSquares; i++)
		{
			c.weightx = 1;
			myCompCardView[i] = new JLabel(myBlankImage, JLabel.CENTER);
			myPlayerCardView[i] = new JLabel(myBlankImage, JLabel.CENTER);

			myPlayerCardPanel.add(myPlayerCardView[i], c);
			myCompCardPanel.add(myCompCardView[i], c);
		}

		myController = controller;

		value = myController.myPlayerRanking;
		myLabel = new JLabel(value);

		value = myController.myPlayer.getName() + "\n" + myController.myPlayer.getNumberWins();
		myPlayerInfo = new JLabel(value);

		myPlayerGameInfo = new JLabel();
		myComputerGameInfo = new JLabel();

		myLabel.setSize(getSize());
		myPlayerInfo.setSize(getSize());
		myPlayerGameInfo.setSize(getSize());
		myComputerGameInfo.setSize(getSize());

		c.gridx = 0;
		c.gridy = 4;
		myFrame.add(myStartButton, c);

		c.gridx = 0;
		c.gridy = 5;
		myFrame.add(myPlayerGameInfo, c);
		
		c.gridx = 0;
		c.gridy = 6;
		myFrame.add(myComputerGameInfo, c);

		c.gridx = 0;
		c.gridy = 1;
		myFrame.add(myCompCardPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		myFrame.add(myPlayerCardPanel, c);

		c.gridx = 2;
		c.gridy = 2;
		myFrame.add(myLabel, c);

		c.gridx = 0;
		c.gridy = 0;
		myFrame.add(myPlayerInfo, c);

		this.associateListeners(controller);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
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
	public void associateListeners(Controller controller)
	{
		Class<? extends Controller> controllerClass;
		Method startGameMethod, selectMethod, discardMethod;
		Class<?>[] classArgs;

		controllerClass = controller.getClass();

		startGameMethod = null;
		selectMethod = null;
		discardMethod = null;
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
			selectMethod = controllerClass.getMethod("border", classArgs);
			startGameMethod = controllerClass.getMethod("startGame", (Class<?>[]) null);
			discardMethod = controllerClass.getMethod("discard", (Class<?>[]) null);
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
			myCardListener[i] = new ButtonListener(myController, selectMethod, args);
			myPlayerCardView[i].addMouseListener(myCardListener[i]);
		}

		myStartButtonListener = new ButtonListener(controller, startGameMethod, null);
		myDiscardButtonListener = new ButtonListener(controller, discardMethod, null);
		myDiscardButton.addMouseListener(myDiscardButtonListener);
		myStartButton.addMouseListener(myStartButtonListener);
	}

	/**
	 * When the mouse clicks on a card, the card is then revealed
	 * 
	 * @param row
	 *            the index of the card
	 * @param image
	 *            the image passed in
	 */
	public void changeImage(int row, Icon image)
	{

		myPlayerCardView[row].setIcon(image);
	}

	/**
	 * Updates myTextField with the String text.
	 *
	 * @param text
	 *            the text string to use in updating the text field
	 */
	public void setCardRanking(String text)
	{
		myLabel.setText(text);
	}

	/**
	 * Sets the Player's Name and total number of wins
	 * 
	 * @param name
	 * @param wins
	 */
	public void setPlayerInfo(String name, int wins)
	{
		myPlayerInfo.setText(name + "\n" + wins);
	}

	public void setPlayerGametext(String text)
	{
		myPlayerGameInfo.setText(text);
	}
	
	public void setComputerGametext(String text)
	{
		myComputerGameInfo.setText(text);
	}

	/**
	 * Makes a border around each Card, somewhat
	 */
	public void makeBorder(int row, boolean toggleSelected)
	{
		if (toggleSelected)
		{
			// Image image =
			// myPlayerCardView[row].getIcon().getScaledInstance(widthX,
			// heightY, image.SCALE_SMOOTH);
			// icon.setImage(image);
			//
			// int borderWidth = 1;
			// int spaceAroundIcon = 0;
			// Color borderColor = Color.BLUE;
			//
			// BufferedImage bi = new BufferedImage(icon.getIconWidth() + (2 *
			// borderWidth + 2 * spaceAroundIcon),
			// icon.getIconHeight() + (2 * borderWidth + 2 * spaceAroundIcon),
			// BufferedImage.TYPE_INT_ARGB);
			//
			// Graphics2D g = bi.createGraphics();
			// g.setColor(borderColor);
			// g.drawImage(icon.getImage(), borderWidth + spaceAroundIcon,
			// borderWidth + spaceAroundIcon, null);
			//
			// BasicStroke stroke = new BasicStroke(5); // 5 pixels wide
			// (thickness
			// // of the border)
			// g.setStroke(stroke);
			//
			// g.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);
			// g.dispose();
			//
			// label = new JLabel(new ImageIcon(bi), JLabel.LEFT);
			// label.setVerticalAlignment(SwingConstants.TOP);
			myPlayerCardView[row].setBorder(new LineBorder(Color.RED));

		}
		else
			myPlayerCardView[row].setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public void removeStart()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		myFrame.remove(myStartButton);
		c.gridx = 0;
		c.gridy = 4;

		myFrame.add(myDiscardButton, c);
	}

	public void removeDiscard()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < myNumSquares; i++)
		{
			myPlayerCardView[i].removeMouseListener(myCardListener[i]);
		}
		c.gridx = 0;
		c.gridy = 4;
		myFrame.remove(myDiscardButton);
		myFrame.add(myStartButton, c);

	}

}