package model;

/**
 * The Player Class is code that requires a name for the player, it gives the
 * player a new hand and sets their number of wins to 0. If no name given the
 * player will acquire the default name of JohnCena. It will also decide whether
 * the player is the computer or an actual person.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 *         March 18, 2016
 */
public class Player
{
	protected static final String DEFAULT_NAME = "JohnCena";
	protected String myName;
	protected int myNumberWins;
	protected boolean myAmAI;
	protected PokerHand myHand;

	/**
	 * The constructor sets the player's name, gives it a new PokerHand of 5
	 * cards, sets the number of wins for the player to 0 and tells you it is
	 * not a computer player.
	 */
	public Player()
	{
		myName = DEFAULT_NAME;
		myHand = new PokerHand(5);
		myNumberWins = 0;
		myAmAI = false;
	}

	/**
	 * The Player method requires a string name. If the name is null or not
	 * valid it gives the player a Default name. If the name matches the correct
	 * letter characters it gives the player the name passed through, with a new
	 * PokerHand, number of wins at 0 and lets you know that it is not a
	 * computer player.
	 */
	public Player(String name)
	{
		if (name == null || validateName(name) == false)
		{
			myName = DEFAULT_NAME;
		}
		else if (name.matches("[a-zA-Z]+"))

		{
			myName = name;
		}
		myHand = new PokerHand(5);
		myNumberWins = 0;
		myAmAI = false;
	}

	/**
	 * The validateName method passes in a name of type string
	 * 
	 * @param name
	 * @return returns true if the name has valid characters and sets it to the
	 *         default name or false if it does not to let us know if the name
	 *         is valid or not.
	 */
	public boolean validateName(String name)
	{

		if (name.matches("[a-zA-Z]+"))

			return true;

		myName = DEFAULT_NAME;
		return false;
	}

	/**
	 * incrementNumberWins method adds a win when the player win to the number
	 * of wins the player already has.
	 * 
	 * @return the number of wins plus 1 of the player.
	 */
	public int incrementNumberWins()
	{
		return myNumberWins++;
	}

	public String toString()
	{
		return null;
	}

	/**
	 * the clone method makes a new object called myClone which is a clone of
	 * the player and their wins, then returns myClone.
	 */
	public Object clone()
	{
		Object myClone = new Object();
		myClone = myName + myNumberWins;
		return myClone;
	}

	public PokerHand getHand()
	{
		return myHand;
	}

	public String getName()
	{
		return myName;
	}

	public int getNumberWins()
	{
		return myNumberWins;
	}

	/**
	 * getAmAI method tells you whether the player is an AI or not
	 * 
	 * @return true if player is AI and false if player is not an AI.
	 */
	public boolean getAmAI()
	{
		if (myAmAI)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
