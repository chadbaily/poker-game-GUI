package mvc;

/**
 * View for a Poker Game, has a frame, 3 JPanels (Background, each players cards(5)), 
 * multiple buttons, and JLabels to indicate what is happening in the game
 * @author chadbaily
 */
import java.awt.*;
import java.lang.reflect.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class View extends Frame
{

	/*
	 * Properties for the view
	 */
	public final static int myNumSquares = 5;
	private JLabel[] myCompCardView;
	private JLabel[] myPlayerCardView;
	private JPanel myCompCardPanel;
	private JPanel myPlayerCardPanel;
	private ButtonListener[] myCardListener;
	private ButtonListener myStartButtonListener;
	private ButtonListener myDiscardButtonListener;
	private Controller myController;
	private JButton myStartButton;
	private JLabel myLabel;
	private JLabel myPlayerInfo;
	private JLabel myCPlayerInfo;
	private JLabel myGameInfo;
	private JLabel myBackgroundLabel;
	private JFrame myFrame;
	private ImageIcon myBlankImage;
	private ImageIcon myBackgroundImage;
	private JButton myDiscardButton;

	///////////////////////
	// Methods //
	///////////////////////

	/**
	 * View constructor used to lay out the view, sets up the initial view for
	 * what the player sees
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

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		myCompCardView = new JLabel[myNumSquares];
		myPlayerCardView = new JLabel[myNumSquares];

		String myButtonText = "Start Game";
		myStartButton = new JButton(myButtonText);

		myDiscardButton = new JButton("Discard");

		myCardListener = new ButtonListener[myNumSquares];

		myCompCardPanel = new JPanel(new GridLayout(1, 5, 8, 8));
		myPlayerCardPanel = new JPanel(new GridLayout(1, 5, 8, 8));

		myBackgroundImage = new ImageIcon("src/cards/background.jpg");
		myBackgroundLabel = new JLabel(myBackgroundImage);
		myBackgroundLabel.setLayout(new GridBagLayout());
		myBackgroundLabel.setOpaque(false);
		myFrame.add(myBackgroundLabel);

		myBlankImage = new ImageIcon("src/cards/E.GIF");
		myPlayerCardPanel.setOpaque(true);
		myCompCardPanel.setOpaque(true);

		/*
		 * Sets the cards up with 2 rows of 5 cards, there backs facing up
		 */
		for (i = 0; i < myNumSquares; i++)
		{
			myCompCardView[i] = new JLabel(myBlankImage, JLabel.CENTER);
			myPlayerCardView[i] = new JLabel(myBlankImage, JLabel.CENTER);

			myCompCardView[i].setOpaque(false);
			myPlayerCardView[i].setOpaque(false);

			myPlayerCardPanel.add(myPlayerCardView[i]);
			myCompCardPanel.add(myCompCardView[i]);
		}

		myPlayerCardPanel.setOpaque(false);
		myCompCardPanel.setOpaque(false);

		myController = controller;

		value = myController.myPlayerRanking;
		myLabel = new JLabel(value);

		value = myController.myPlayer.getName() + "\n" + myController.myPlayer.getNumberWins();
		myPlayerInfo = new JLabel(value);
		myPlayerInfo.setForeground(Color.white);

		value = myController.myModel.getPlayer(1).getName() + "\n" + myController.myModel.getPlayer(1).getNumberWins();
		myCPlayerInfo = new JLabel(value);
		myCPlayerInfo.setForeground(Color.white);

		myGameInfo = new JLabel();
		myGameInfo.setForeground(Color.white);

		myLabel.setForeground(Color.white);

		myLabel.setSize(getSize());
		myLabel.setFont(new Font("Serif", Font.BOLD, 16));

		myPlayerInfo.setSize(getSize());
		myPlayerInfo.setFont(new Font("Serif", Font.BOLD, 16));

		myCPlayerInfo.setSize(getSize());
		myCPlayerInfo.setFont(new Font("Serif", Font.BOLD, 16));

		myGameInfo.setSize(getSize());
		myGameInfo.setFont(new Font("Serif", Font.BOLD, 16));

		c.gridx = 0;
		c.gridy = 4;
		myBackgroundLabel.add(myStartButton, c);

		c.gridx = 0;
		c.gridy = 5;
		myBackgroundLabel.add(myGameInfo, c);

		c.gridx = 0;
		c.gridy = 1;
		myBackgroundLabel.add(myCompCardPanel, c);

		c.gridx = 0;
		c.gridy = 3;
		myBackgroundLabel.add(myPlayerCardPanel, c);

		c.gridx = 1;
		c.gridy = 2;
		myBackgroundLabel.add(myLabel, c);

		c.gridx = 0;
		c.gridy = 0;
		myBackgroundLabel.add(myPlayerInfo, c);

		c.gridx = 1;
		c.gridy = 0;
		myBackgroundLabel.add(myCPlayerInfo, c);

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
	public void changePlayerImage(int row, Icon image)
	{
		myPlayerCardView[row].setIcon(image);
	}

	/**
	 * Changes the card image for the computerplayer, only uses cards in the
	 * computerplayers hand
	 * 
	 * @param row
	 * @param image
	 */
	public void changeCompImage(int row, Icon image)
	{
		myCompCardView[row].setIcon(image);
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
	 * Sets the Player's Name and total number of wins in the view, allows it to
	 * be updated from controller
	 * 
	 * @param name
	 * @param wins
	 */
	public void setPlayerInfo(String name, int wins)
	{
		myPlayerInfo.setText(name + "\n" + wins);
	}

	/**
	 * Sets the ComputerPlayer's Name and total number of wins in the view,
	 * allows it to be updated from controller
	 * 
	 * @param name
	 * @param wins
	 */
	public void setCPlayerInfo(String name, int wins)
	{
		myCPlayerInfo.setText(name + "\n" + wins);
	}

	/**
	 * Sets who discarded what and who won each round, with what hand. Allows
	 * the text to be chaged from the controller
	 * 
	 * @param text
	 */
	public void setGametext(String text)
	{
		myGameInfo.setText(text);
	}

	/**
	 * Makes a border around each Card if the card has been selected in the view
	 */
	public void makeBorder(int row, boolean toggleSelected)
	{
		if (toggleSelected)
		{
			myPlayerCardView[row].setBorder(new LineBorder(Color.blue, 3));

		}
		else
		{
			myPlayerCardView[row].setBorder(new EmptyBorder(0, 0, 0, 0));
		}
	}

	/**
	 * Removes the start button and adds the discard button
	 */
	public void removeStart()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		myBackgroundLabel.remove(myStartButton);
		c.gridx = 0;
		c.gridy = 4;

		myBackgroundLabel.add(myDiscardButton, c);
	}

	/**
	 * Method to re-make the JPanels and JLabels that were originally used when
	 * setting up the view
	 */
	public void playAgainOne()
	{
		for (int i = 0; i < myNumSquares; i++)
		{
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;

			c.weightx = 1;
			myCompCardView[i].setIcon(myBlankImage);
			myPlayerCardView[i].setIcon(myBlankImage);

			myPlayerCardPanel.add(myPlayerCardView[i], c);
			myCompCardPanel.add(myCompCardView[i], c);
		}
	}

	/**
	 * Method to re-add the mouse listeners to the JPanel and JLabel that were
	 * disabled during removeDiscard()
	 */
	public void playAgainTwo()
	{
		for (int i = 0; i < myNumSquares; i++)
		{
			myPlayerCardView[i].addMouseListener(myCardListener[i]);
		}

	}

	/**
	 * Method to remove the discard button and re-add the start button. Removes
	 * the mouse listeners for the JPanel and JLabel so that cards cannot be
	 * clicked when they are not supposed to be
	 */
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
		myBackgroundLabel.remove(myDiscardButton);
		myBackgroundLabel.add(myStartButton, c);
	}

	/**
	 * Method to close the frame and quit the Java App.
	 */
	public void quit()
	{
		myFrame.setVisible(false);
		myFrame.dispose();
		System.exit(0);
	}

}