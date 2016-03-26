package mvc;

import java.awt.*;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.*;

/**
 * Simple controller class used to show how Model-View-Controller is
 * implemented.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 */
public class Controller
{
	///////////////////
	// Properties //
	///////////////////

	public View myView;
	public PokerModel myModel;
	public Player myPlayer;
	private String myPlayerName;
	public String myPlayerRanking;

	///////////////////
	// Methods //
	///////////////////

	/**
	 * Controller constructor; view must be passed in since controller has
	 * responsibility to notify view when some event takes place.
	 */
	public Controller()
	{
		myPlayer = new Player(myPlayerName);
		myModel = new PokerModel(myPlayer);

		myView = new View(this);
	}

	/**
	 * Sets the Player's name, resets the game completely, creates a new
	 * shuffled deck and deals the cards to the players.
	 */
	public void startGame()
	{
		myModel.resetGame();

		JFrame frame = new JFrame("Name");
		myPlayerName = (String) JOptionPane.showInputDialog(frame, "Please enter the Player's Name", "Name Select",
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		myModel.dealCards();
		myPlayer.getHand().orderCards();
		myPlayerRanking = "" + myModel.getPlayer(0).getHand().determineRanking();
		myView.setCardRanking(myPlayerRanking);
		boolean test = myPlayer.validateName(myPlayerName);

		if (test)
		{
			myView.setPlayerInfo(myPlayerName, myPlayer.getNumberWins());
		}
		else
		{
			myPlayerName = "JohnCena";
			myView.setPlayerInfo(myPlayerName, myPlayer.getNumberWins());
		}

		int value;
		ImageIcon myImage;
		myPlayer.getHand().orderCards();
		value = myModel.getPlayer(0).getHand().getNumberCardsInHand();
		for (int i = 0; i < value; i++)
		{
			myImage = new ImageIcon(myPlayer.getHand().getCards().get(i).getImage());
			myView.changeImage(i, myImage);
		}

		myView.removeStart();
	}

	public void discard()
	{
		int value;
		ComputerPlayer myCompPlayer = (ComputerPlayer) myModel.getPlayer(1);
		Vector<Integer> myCompPlayerDiscards = myCompPlayer.selectCardsToDiscard();

		Vector<Integer> myDiscards = new Vector<Integer>();

		Vector<Card> myPlayerCards = new Vector<Card>();
		Vector<Card> myComputerPlayerCards = new Vector<Card>();
		value = myPlayer.getHand().getNumberCardsInHand();
		for (int i = 0; i < value; i++)
		{
			if (myPlayer.getHand().getCards().get(i).isSelected())
			{
				myDiscards.add(i);
				myPlayerCards.add(myPlayer.getHand().getCards().get(i));
			}
		}

		for (int i = 0; i < myCompPlayerDiscards.size(); i++)
		{
			myComputerPlayerCards.add(myModel.getPlayer(1).getHand().getCards().get(myCompPlayerDiscards.get(i)));
		}
		ImageIcon myImage;
		myPlayer.getHand().discard(myDiscards);
		myModel.getPlayer(1).getHand().discard(myCompPlayerDiscards);
		myModel.dealCards();
		value = myPlayer.getHand().getNumberCardsInHand();
		myPlayer.getHand().orderCards();
		for (int i = 0; i < value; i++)
		{
			myImage = new ImageIcon(myPlayer.getHand().getCards().get(i).getImage());
			myView.changeImage(i, myImage);
			myView.makeBorder(i, false);
		}

		myPlayer.getHand().orderCards();
		myPlayerRanking = "" + myPlayer.getHand().determineRanking();
		myView.setCardRanking(myPlayerRanking);
		myView.setPlayerGametext("You Discarded : " + myPlayerCards);
		myView.setComputerGametext("The AI Discarded : " + myComputerPlayerCards);
		myView.removeDiscard();

	}

	public void border(Integer iRow)
	{
		int row;
		row = iRow.intValue();
		myPlayer.getHand().getCards().get(row).toggleSelected();
		myView.makeBorder(iRow, myPlayer.getHand().getCards().get(row).isSelected());

	}
}