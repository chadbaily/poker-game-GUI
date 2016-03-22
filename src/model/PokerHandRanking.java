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
public enum PokerHandRanking
{
	HIGH_CARD(1), PAIR(2), TWO_PAIR(3), THREE_OF_KIND(4), STRAIGHT(5), FLUSH(6), FULL_HOUSE(7), FOUR_OF_KIND(
			8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

	private int myPokerHandRanking;

	/**
	 * Default constructor for PokerHandRanking, sets the ranking of a specific
	 * PokerHand.
	 * 
	 * @param name
	 */
	private PokerHandRanking(int rank)
	{
		myPokerHandRanking = rank;
	}

	/**
	 * Getter method to return the rank of a PokerHand.
	 * 
	 * @return the rank of a PokerHand determined by myPokerHandRanking
	 */
	public int getRank()
	{
		return myPokerHandRanking;
	}
}
