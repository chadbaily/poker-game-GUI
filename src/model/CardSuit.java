package model;

/**
 * CardSuit is an enumeration class, allowing for each card to have one of four
 * suits: Spades, Hearts, Diamonds, and Clubs. Simulates the card suit of a real
 * card.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public enum CardSuit
{
	SPADES("Spades"), HEARTS("Hearts"), DIAMONDS("Diamonds"), CLUBS("Clubs");

	private final String mySuit;

	/**
	 * Default constructor for CardSuit, sets the name of a specific suit.
	 * 
	 * @param name
	 */
	private CardSuit(String name)
	{
		mySuit = name;
	}

	/**
	 * Getter method to return the suit of a card.
	 * 
	 * @return
	 */
	public String getSuit()
	{
		return mySuit;
	}

}
