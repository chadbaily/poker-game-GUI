package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * The Hand class is a class that simulates a player holding a hand of cards in
 * a card game. The hand is comprised of a set number of cards, that is
 * determined at the start of a game. You can add, discard, and order your hand,
 * like you would in a real card game.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public class Hand
{
	protected int myMaxNumberCards;
	protected Vector<Card> myCards;

	/**
	 * Default constructor of the Hand Class, sets the maximum amount of cards
	 * to the maxNum that is passed in, then creates a vector of size maxNum.
	 * 
	 * @param maxNum
	 */
	public Hand(int maxNum)
	{
		myMaxNumberCards = maxNum;
		myCards = new Vector<Card>(maxNum);
	}

	/**
	 * Method that adds a card to the hand if the hand does not already contain
	 * the card, and if the hand does not already contain the maximum number of
	 * cards allowed. If the maximum number is already reached, the card is not
	 * added to the hand, or if the hand already contains the card then the card
	 * is not added.
	 * 
	 * @param card
	 * @return true if the card was added, false if the card was not.
	 */
	public boolean add(Card card)
	{
		if (myCards.size() < myMaxNumberCards)
		{
			if (myTestMultCards(card))
			{
				return false;
			}
			else
			{
				myCards.add(card);
				return true;
			}
		}
		else if (myCards.size() > myMaxNumberCards)
		{
			return false;
		}
		return false;
	}

	/**
	 * Method that discards a card based on the indices that are passed into it.
	 * If the size of the array passed into the method is >=0 then an empty
	 * array is returned.
	 * 
	 * @param indices
	 * @return A array of cards that were discarded
	 */
	public Vector<Card> discard(Vector<Integer> indices)
	{
		Vector<Card> myTemp = new Vector<Card>(indices.size());
		int x = 0;
		if (indices.size() > 0)
		{
			for (int i = indices.size() - 1; i >= 0; i--)
			{
				Integer ind = indices.get(i);
				Card card = myCards.get(ind);
				myTemp.add(card);
				Card tmp = myTemp.get(x);
				myCards.remove(tmp);
				x++;
			}
			return myTemp;
		}
		else
			return myTemp;

	}

	/**
	 * toString method that returns the Hand in the fashion of all the cards in
	 * your hand
	 * 
	 * @return returns a string in the format of "Your hand is : " + myCards,
	 *         myCards representing the cards in your hand
	 */
	public String toString()
	{
		return "Your hand is : " + myCards;
	}

	/**
	 * Method to order the cards using collections to sort the array by the
	 * CardSuit enumeration and the CardType enumeration by their given order in
	 * the enumeration
	 */
	public void orderCards()
	{
		Collections.sort(myCards, new Comparator<Card>()
		{
			public int compare(Card o1, Card o2)
			{
				return o1.compareTo(o2);
			}

		});
	}

	/**
	 * Getter method to get the number of cards in a hand by getting the myCard
	 * size
	 * 
	 * @return the size of myCards, which represents the hand
	 */
	public int getNumberCardsInHand()
	{
		return myCards.size();
	}

	/**
	 * Method to the the cards in the hand
	 * 
	 * @return myCards, whic represents the Hand
	 */
	public Vector<Card> getCards()
	{
		return myCards;
	}

	/**
	 * Private method to see if the hand has multiple of the same cards by
	 * checking the suits and type of each card to see if they match
	 * 
	 * @param card
	 * @return true if there was a match, false if there was no match
	 */
	private boolean myTestMultCards(Card card)
	{
		boolean match = false;
		for (int i = 0; i < myCards.size(); i++)
		{
			if (myCards.get(i).getSuit() == card.getSuit() && myCards.get(i).getType() == card.getType())
			{
				match = true;
			}
		}
		return match;
	}
}
