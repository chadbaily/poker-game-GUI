package mvc;

import java.awt.*;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.*;

/**
 * Controller for the pokermodel game, has three main methods, start, discard,
 * and border
 * 
 * @author chadbaily
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
	private int myDiscardCount = 0;
	private int myStarts = 0;

	///////////////////
	// Methods //
	///////////////////

	/**
	 * Controller constructor; view must be passed in since controller has
	 * responsibility to notify view when some event takes place.
	 */
	public Controller()
	{
		JFrame frame = new JFrame("Name");
		myPlayerName = (String) JOptionPane.showInputDialog(frame, "Please enter the Player's Name", "Name Select",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

		myPlayer = new Player(myPlayerName);
		myModel = new PokerModel(myPlayer);
		myView = new View(this);

		myView.setCPlayerInfo(myModel.getPlayer(1).getName(), myModel.getPlayer(1).getNumberWins());
		/*
		 * Testing to see if the player entered a valid name for the player in
		 * the game
		 */
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

	}

	/**
	 * Sets the Player's name, resets the game completely, creates a new
	 * shuffled deck and deals the cards to the players.
	 */
	public void startGame()
	{
		myStarts++;
		myModel.resetGame();
		myModel.dealCards();
		myPlayer.getHand().orderCards();
		myPlayerRanking = "" + myModel.getPlayer(0).getHand().determineRanking();
		myView.setCardRanking(myPlayerRanking);

		int value;
		ImageIcon myImage;
		myPlayer.getHand().orderCards();
		value = myModel.getPlayer(0).getHand().getNumberCardsInHand();
		for (int i = 0; i < value; i++)
		{
			myImage = new ImageIcon(myPlayer.getHand().getCards().get(i).getImage());
			myView.changePlayerImage(i, myImage);
		}

		myView.removeStart();
		if (myStarts > 1)
		{
			myView.playAgainTwo();
		}
	}

	public void discard()
	{
		myDiscardCount++;
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
			myView.changePlayerImage(i, myImage);
			myView.makeBorder(i, false);
		}

		myPlayer.getHand().orderCards();
		myModel.getPlayer(1).getHand().orderCards();
		myPlayerRanking = "" + myPlayer.getHand().determineRanking();
		myView.setCardRanking(myPlayerRanking);
		myView.setGametext(
				"<HTML> You Discarded : " + myPlayerCards + "<BR> The AI Discarded : " + myComputerPlayerCards);
		/*
		 * Every second time the cards are discarded marks the end of a game,
		 * therefore a winner is determined and the score is reflected to show
		 * that
		 */
		if (myDiscardCount % 2 == 0)
		{
			myView.removeDiscard();
			myModel.determineWinner();

			int myCardsInHand;
			ImageIcon myFlippedImage;
			myPlayer.getHand().orderCards();
			myCardsInHand = myModel.getPlayer(1).getHand().getNumberCardsInHand();
			for (int i = 0; i < myCardsInHand; i++)
			{
				myFlippedImage = new ImageIcon(myModel.getPlayer(1).getHand().getCards().get(i).getImage());
				myView.changeCompImage(i, myFlippedImage);
			}

			/*
			 * If the players hand is better than the computer players, then he
			 * wins and all info is updated to show that. Otherwise the
			 * computerPlayer wins, and all info is reflected to show that
			 */
			if (myModel.getPlayer(0).getHand().compareTo(myModel.getPlayer(1).getHand()) == 1)
			{
				myView.setGametext(
						"<HTML> You Discarded : " + myPlayerCards + "<BR> The AI Discarded : " + myComputerPlayerCards
								+ "<BR> The Winner of this round was : " + myModel.getPlayer(0).getName() + " with a "
								+ myModel.getPlayer(0).getHand().determineRanking());
				myModel.getPlayer(0).incrementNumberWins();
				myView.setPlayerInfo(myModel.getPlayer(0).getName(), myModel.getPlayer(0).getNumberWins());
			}
			else
			{
				myView.setGametext(
						"<HTML> You Discarded : " + myPlayerCards + "<BR> The AI Discarded : " + myComputerPlayerCards
								+ "<BR> The Winner of this round was : " + myModel.getPlayer(1).getName() + " with a "
								+ myModel.getPlayer(1).getHand().determineRanking());
				myModel.getPlayer(1).incrementNumberWins();
				myView.setCPlayerInfo(myModel.getPlayer(1).getName(), myModel.getPlayer(1).getNumberWins());
			}
			JFrame myFrame = new JFrame("Continue?");
			int n = JOptionPane.showOptionDialog(myFrame, "Would you like to play again?",
					"Play Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			System.out.println(n);
			if (n == 0)
			{
				myView.playAgainOne();
			}
			else if (n == 1)
			{
				myView.quit();
			}

		}

	}

	public void border(Integer iRow)
	{
		int row;
		row = iRow.intValue();
		myPlayer.getHand().getCards().get(row).toggleSelected();
		myView.makeBorder(iRow, myPlayer.getHand().getCards().get(row).isSelected());

	}
}