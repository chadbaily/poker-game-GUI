package model;

/**
 * @purpose
 * 
 * Class that deals with the major components of a poker game,
 * dealing cards, switching turns, entering the winner, handling
 * the deck, etc.
 * 
 * @author Luisa Molina
 * @author Erica Kok
 * 
 *@dueDate 3/21/16
 */

public class PokerModel
{
	private Player[] myPlayer;
	private int myIndexPlayerUp;
	private int myMaxRounds;
	private int myRound;
	private Deck myDeck;
	
	/**
	 * Constructor, creates a new PokerModel with Players.
	 * 
	 * @param player
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public PokerModel(Player player)
	{
		myPlayer = new Player[2];
		ComputerPlayer myComputerPlayer = new ComputerPlayer("John");
		myDeck = new Deck();
		myDeck.shuffle();
		myIndexPlayerUp = 0;
		myPlayer[0] = player;
		myPlayer[1] = myComputerPlayer;
		myMaxRounds = 5;
	}
	
	/**
	 * Method to switch the turns of the Players.
	 * 
	 * @return myIndexPlayer++ if myIndexPlayerUp is less than 2
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public int switchTurns()
	{
		if(myIndexPlayerUp < myPlayer.length)
		{
			return myIndexPlayerUp++;
		}
		else
		{
			return myIndexPlayerUp = 0;
		}
	}
	
	/**
	 * Method to deal the cards to each Player.
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public void dealCards()
	{
		for (int i = 0; i < 5; i++)
		{	
			myPlayer[0].getHand().add(myDeck.draw());
			myPlayer[1].getHand().add(myDeck.draw());
		}
	}
	
	/**
	 * Method to determine the winner of the game.
	 * 
	 * @return the winning Player
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public Player determineWinner()
	{

		if ((myPlayer[0].getNumberWins() >= myPlayer[1].getNumberWins()) && myRound <= myMaxRounds)
		{
			return myPlayer[0];
		}
		else
		{
			return myPlayer[1];
		}
	}
	
	/**
	 * Method to reset the game.
	 * 
	 * @return true if the game was reset, otherwise return false.
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public boolean resetGame()
	{		
		myRound = 1;
		myPlayer[0].getHand().getCards().clear();
		myPlayer[0].getHand().getCards().setSize(0);
		myPlayer[1].getHand().getCards().clear();
		myPlayer[1].getHand().getCards().setSize(0);
		myDeck = new Deck();
		myDeck.shuffle();
		if(myPlayer[0].getHand().getNumberCardsInHand() == 0 && myPlayer[1].getHand().getNumberCardsInHand() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Method to determine the Player who's up.
	 * 
	 * @return the Player currently up
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public Player getPlayerUp()
	{
		return myPlayer[myIndexPlayerUp];
	}
	
	/**
	 * Method to get the Player at an index.
	 * 
	 * @param index
	 * 
	 * @return the Player at that index
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public Player getPlayer(int index)
	{
		return myPlayer[index];
	}

	/**
	 * Method to get the index of the Player who's up.
	 * 
	 * @return the index of the Player who's up
	 * 
	 * @author Luisa Molina
	 * @author Erica Kok
	 */

	public int getIndexPlayerUp()
	{
		return myIndexPlayerUp;
	}
}