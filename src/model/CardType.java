package model;

/**
 * CardType is an enumeration class, allowing for each card to have one of 13
 * Types: Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen,
 * King, and Ace. Simulates the card type of a real card.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 */
public enum CardType
{
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(
			13), ACE(14);

	private final int myType;

	/**
	 * Default constructor for CardType, sets the name of a specific Type.
	 * 
	 * @param name
	 */
	private CardType(int type)
	{
		myType = type;
	}

	/**
	 * Getter method to return the type of a card.
	 * 
	 * @return the type of the card
	 */

	public int getType()
	{
		return myType;
	}

}
