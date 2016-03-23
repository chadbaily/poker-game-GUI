package model;

/**
 * The PokerModel class is code that requires players and a deck, the class will
 * deal players the cards it will switch turns of the players get the player's
 * turn who is up, keep track of the rounds and winner of each round.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public class PokerModel
{
	private Player[] myPlayer;
	private int myIndexPlayerup;
	private int myMaxRounds;
	private int myRound;
	private Deck myDeck;

	/**
	 * The PokerModel constructor passes in a player and creates a computer
	 * player with the name Bob and "human" player, then gives them a spot in
	 * the player array.
	 * 
	 * @param player
	 */
	public PokerModel(Player player)
	{
		ComputerPlayer myCPlayer = new ComputerPlayer("Bob");
		myPlayer = new Player[2];
		myPlayer[0] = player;
		myPlayer[1] = myCPlayer;
		myMaxRounds = 6;
		myIndexPlayerup = 1;
	}

	/**
	 * The switchTurns method gets the index of the player up and increments it
	 * every time the method is called.
	 * 
	 * @return the index of the next player up
	 */
	public int switchTurns()
	{
		myIndexPlayerup++;
		return myIndexPlayerup;
	}

	/**
	 * The dealCards method creates a new deck called myDeck and for 5
	 * iterations it deals and adds cards to the both player's hands.
	 */
	public void dealCards()
	{
		myDeck = new Deck();
		for (int i = 0; i < 5; i++)
		{
			myPlayer[0].myHand.add(myDeck.draw());
			myPlayer[1].myHand.add(myDeck.draw());
		}

	}

	/**
	 * the determineWinner method takes the number of wins of one player and
	 * determines who the winner is
	 * 
	 * @return myPlayer[0] if their number of wins is 3 if not then it returns
	 *         myPlayer[1].
	 */
	public Player determineWinner()
	{
		if (myPlayer[0].myNumberWins == 3)
		{
			return myPlayer[0];
		}
		else
			return myPlayer[1];
	}

	/**
	 * resetGame method clears the hands of the players and sets the rounds back
	 * to 0.
	 * 
	 * @return true if all is done successfully.
	 */
	public boolean resetGame()
	{
		myRound = 0;
		myPlayer[0].myHand.myCards.removeAllElements();
		myPlayer[1].myHand.myCards.removeAllElements();
		return true;
	}

	/**
	 * the getPlayerUp method chooses which player to get up
	 * 
	 * @return if the player's index is even then it returns myPlayer[0] if not
	 *         it returns myPlayer[1]
	 */
	public Player getPlayerUp()
	{
		if (myIndexPlayerup % 2 == 0)
		{
			return myPlayer[0];
		}
		else
			return myPlayer[1];
	}

	public Player getPlayer(int index)
	{
		return myPlayer[index];
	}

	/**
	 * getIndexPlayerUp method generates a newPlayer
	 * 
	 * @return if the newPlayer is myPlayer[0] it returns 0 if not then it
	 *         returns 1.
	 */
	public int getIndexPlayerUp()
	{
		Player newPlayer = getPlayerUp();
		if (myPlayer[0] == newPlayer)
		{
			return 0;
		}
		else
			return 1;
	}

}
