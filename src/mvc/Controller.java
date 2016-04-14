package mvc;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.*;

/**
 * Controller for the PokerModel game, has three main methods, start, discard,
 * and border. Controls the view for the PokerModel
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
	Vector<Integer> myBorderIndex;

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
		myBorderIndex = new Vector<Integer>();

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
		myModel.resetMVCGame();
		myModel.dealCards();
		myPlayer.getHand().orderCards();
		myPlayerRanking = "<HTML>" + myModel.getPlayer(0).getHand().determineRanking();
		myView.setCardRanking(myPlayerRanking);

		int value;
		ImageIcon myImage;
		myPlayer.getHand().orderCards();
		value = myModel.getPlayer(0).getHand().getNumberCardsInHand();
		/*
		 * Gets the cards in the players hand and updates the view with the new
		 * cards
		 */
		for (int i = 0; i < value; i++)
		{
			myImage = new ImageIcon(myPlayer.getHand().getCards().get(i).getImage());
			myView.changePlayerImage(i, myImage);
		}
		/*
		 * Removes the start button and also re-enables the mouse listeners for
		 * the cards
		 */
		myView.removeStart();
		if (myStarts > 1)
		{
			myView.playAgainTwo();
		}
	}

	/**
	 * Method that is able to tell which cards to be discarded by if they are
	 * selected with a border. This Method is responsible for determining when a
	 * round stops and when the game is over First the selected cards are
	 * determined, then
	 */
	public void discard()
	{
		myDiscardCount++;
		myBorderIndex.removeAllElements();
		ComputerPlayer myCompPlayer = (ComputerPlayer) myModel.getPlayer(1);
		Vector<Integer> myCompPlayerDiscards = myCompPlayer.selectCardsToDiscard();
		Vector<Integer> myDiscards = new Vector<Integer>();
		Vector<Card> myPlayerCards = new Vector<Card>();
		Vector<Card> myComputerPlayerCards = new Vector<Card>();

		/*
		 * Discards the Player and ComputerPlayer cards, also uses a vector of
		 * integers to correctly change the images
		 */
		discardCards(myPlayerCards, myDiscards, myComputerPlayerCards, myCompPlayerDiscards);

		/*
		 * Updating the Game, and player ranking text
		 */
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
			myModel.incrementRounds();
			myBorderIndex.removeAllElements();
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
			 * If the rounds are less than five, then the hands are compared. If
			 * the players hand is better than the computer players, then he
			 * wins and all info is updated to show that. Otherwise the
			 * computerPlayer wins, and all info is reflected to show that
			 */
			if (myModel.getRound() < 5)
			{
				/*
				 * Updates the winner's score and relates that to the view
				 */
				updateWinner(myPlayerCards, myComputerPlayerCards);
				/*
				 * How to know if you want to continue with the game or not
				 */
				JFrame myFrame = new JFrame("Continue?");
				int n = JOptionPane.showOptionDialog(myFrame, "Would you like to play again?", "Play Again?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				/*
				 * If the player clicks "yes" then the card images are flipped
				 * and reset. Else the game is exited.
				 */
				if (n == 0)
				{
					myView.playAgainOne();
				}
				else if (n == 1)
				{
					myView.quit();
				}
			}
			/*
			 * If five rounds are reached, then a winner is determined and the
			 * game exits
			 */
			else if (myModel.getRound() == 5)
			{
				/*
				 * Declares the winner of the game and updates their score
				 */
				declareWinner();
			}

		}

	}

	/**
	 * Method to create a border around the cards, also serves to toggleSelected
	 * on the card that was clicked
	 * 
	 * @param iRow
	 */
	public void border(Integer iRow)
	{
		int row;
		row = iRow.intValue();
		/*
		 * If the size of the index of cards in myBorderIndex and the card click
		 * is not already selected, then a border is made and it is added to
		 * myBorderIndex
		 */
		if (myBorderIndex.size() < 3 && !myPlayer.getHand().getCards().get(row).isSelected())
		{
			myBorderIndex.add(iRow);
			myPlayer.getHand().getCards().get(row).toggleSelected();
			myView.makeBorder(iRow, myPlayer.getHand().getCards().get(row).isSelected());
		}
		/*
		 * If the card has already been clicked, then no border is made, and any
		 * border there is taked away also removes the index of the card form
		 * myBorderIndex
		 */
		else if (myPlayer.getHand().getCards().get(row).isSelected())
		{
			myPlayer.getHand().getCards().get(row).toggleSelected();
			myView.makeBorder(iRow, myPlayer.getHand().getCards().get(row).isSelected());
			myBorderIndex.remove(iRow);
		}
	}

	/**
	 * Method that takes in the Player and ComputerPlayerCards, then uses an
	 * array of Integers from the cards that were discarded to flip change the
	 * correct image
	 * 
	 * @param myPlayerCards
	 * @param myDiscards
	 * @param myComputerPlayerCards
	 * @param myCompPlayerDiscards
	 */
	private void discardCards(Vector<Card> myPlayerCards, Vector<Integer> myDiscards,
			Vector<Card> myComputerPlayerCards, Vector<Integer> myCompPlayerDiscards)
	{
		int value;

		value = myPlayer.getHand().getNumberCardsInHand();
		/*
		 * Gets the selected cards from the players hand and adds them to the
		 * discards and the player cards. My discards representing the index at
		 * i and myPlayerCards representing the card discarded.
		 */
		for (int i = 0; i < value; i++)
		{
			if (myPlayer.getHand().getCards().get(i).isSelected())
			{
				myDiscards.add(i);
				myPlayerCards.add(myPlayer.getHand().getCards().get(i));
			}
		}
		/*
		 * The auto discard for computer player
		 */
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
		/*
		 * Changing the images to account for the new cards added to the hands
		 */
		for (int i = 0; i < value; i++)
		{
			myImage = new ImageIcon(myPlayer.getHand().getCards().get(i).getImage());
			myView.changePlayerImage(i, myImage);
			myView.makeBorder(i, false);
		}
	}

	private void updateWinner(Vector<Card> myPlayerCards, Vector<Card> myComputerPlayerCards)
	{
		/*
		 * If the player wins
		 */
		if (myModel.getPlayer(0).getHand().compareTo(myModel.getPlayer(1).getHand()) == 1)
		{
			myView.setGametext("<HTML> You Discarded : " + myPlayerCards + "<BR> The AI Discarded : "
					+ myComputerPlayerCards + "<BR> The Winner of this round was : " + myModel.getPlayer(0).getName()
					+ " with a " + myModel.getPlayer(0).getHand().determineRanking());
			myModel.getPlayer(0).incrementNumberWins();
			myView.setPlayerInfo(myModel.getPlayer(0).getName(), myModel.getPlayer(0).getNumberWins());
		}
		/*
		 * If the computer player wins
		 */
		else
		{
			myView.setGametext("<HTML> You Discarded : " + myPlayerCards + "<BR> The AI Discarded : "
					+ myComputerPlayerCards + "<BR> The Winner of this round was : " + myModel.getPlayer(1).getName()
					+ " with a " + myModel.getPlayer(1).getHand().determineRanking());
			myModel.getPlayer(1).incrementNumberWins();
			myView.setCPlayerInfo(myModel.getPlayer(1).getName(), myModel.getPlayer(1).getNumberWins());
		}
	}

	private void declareWinner()
	{
		if (myModel.getPlayer(0).getHand().compareTo(myModel.getPlayer(1).getHand()) == 1)
		{
			Player winner = myModel.determineWinner();
			winner.incrementNumberWins();
			JFrame myFrame = new JFrame("Continue?");
			JOptionPane.showMessageDialog(myFrame,
					winner.getName() + " won! with a score of " + winner.getNumberWins());
			myView.quit();
		}
		else
		{
			Player winner = myModel.determineWinner();
			JFrame myFrame = new JFrame("Continue?");
			JOptionPane.showMessageDialog(myFrame,
					winner.getName() + " won! with a score of " + winner.getNumberWins());
			myView.quit();
		}
	}
}