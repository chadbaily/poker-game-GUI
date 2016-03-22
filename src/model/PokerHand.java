package model;

import java.util.Vector;

/**
 * The PokerHand class is code that extends hand it takes the number of cards in
 * your hand and determines their ranking. There are a total of 10 Poker Hands
 * that are set in the PokerHandRanking enumeration. In PokerHand, each hand is
 * given one of these rankings based on the cards in the hand.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public class PokerHand extends Hand
{

	private int myNumberCards;
	private int myMaxNumberCards;
	private PokerHandRanking ranking;
	private Hand myHand;

	/**
	 * The constructor method passes in the max number of cards and set the
	 * initial ranking to null.
	 * 
	 * @param maxNum
	 */
	public PokerHand(int maxNum)
	{
		super(maxNum);
		ranking = null;

	}

	/**
	 * Method that is used to determine the ranking of the current poker hand.
	 * Calls a private method to keep code clean
	 * 
	 * @return The PokerHandRanking of the PokerHand
	 */
	public PokerHandRanking determineRanking()
	{
		return pokerRanker();
	}

	/**
	 * The compareTo method passes in a pokerHand. It compares the current
	 * ranking against another PokerHand and its ranking.
	 * 
	 * @param pokerHand
	 * @return if the ranking is less than the pokerHand ranking it returns a -1
	 *         if greater it returns a 1 and if equal to it returns a 0.
	 */
	public int compareTo(PokerHand pokerHand)
	{
		if (ranking.ordinal() < pokerHand.determineRanking().ordinal())
		{
			return -1;
		}
		if (ranking.ordinal() > pokerHand.determineRanking().ordinal())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * toString method that returns the PokerHand in the fashion of all the
	 * cards in your hand
	 * 
	 * @return returns a string in the format of "Your hand is : " + myCards,
	 *         myCards representing the cards in your PokerHand
	 */
	public String toString()
	{
		return "Your hand is : " + myHand;
	}

	/**
	 * Method to get the ranking of the current hand. This is done be getting
	 * the ranking as an integer value
	 * 
	 * @return the ranking of the current card as an Int value
	 */
	public int getRanking()
	{
		return ranking.ordinal();
	}

	/**
	 * Method to get the number of cards in the hand. Done be calling the hand,
	 * then myCards, which represents all the cards in a hand
	 * 
	 * @return the total number of cards in a hand
	 */
	public int getNumberCards()
	{
		myNumberCards = myHand.myCards.size();
		return myNumberCards;
	}

	/**
	 * Method to get the maximum number of cards that are allowed in a hand
	 * 
	 * @return the max number of cards allowed in a hand
	 */
	public int getMaxNumberCards()
	{
		return myMaxNumberCards;
	}

	/**
	 * The isHighCard ranking creates a vector of integers of the discarded
	 * cards and the final discarded cards. It runs through the hand to compare
	 * the cards of a High Card hand. If myDiscards contains one card it adds
	 * the other if it does not contain either then it adds both.
	 * 
	 * @return highcard ranking if myDiscards size is 0 and returns true that it
	 *         is a high card, if not it returns false.
	 */
	public boolean isHighCard()
	{
		Vector<Integer> myDiscards = new Vector<Integer>();
		Vector<Integer> myFinalDiscards = new Vector<Integer>();
		int count = 0;
		ranking = null;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())

			{
				if (myDiscards.contains(i))
				{
					myDiscards.add(i + 1);
				}
				else
				{
					myDiscards.add(i);
					myDiscards.add(i + 1);
				}
			}
		}
		if (myDiscards.size() == 0)
		{
			ranking = PokerHandRanking.HIGH_CARD;
			return true;
		}
		else
			return false;
	}

	/**
	 * The isPair method sets the ranking to null originally and has a count for
	 * how many pairs are in the hand, every time there is a pair it adds 1 to
	 * the count.
	 * 
	 * @return if the count is one then the pokerHandRanking is a pair and it
	 *         returns true if not it returns false.
	 */
	public boolean isPair()
	{
		ranking = null;
		int count = 0;

		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())
				count++;
		}
		if (count == 1)
		{
			ranking = PokerHandRanking.PAIR;
			return true;
		}
		else
			return false;
	}

	/**
	 * The isTwoPair method is similar to the previous method only difference is
	 * that it has a myPairs and FinalDiscards vector that contains the cards
	 * that are alike in the hand and those to be discarded at the end. First
	 * the cards are added to the myPairs vector, and a counter is used to
	 * determine the total number of pairs.
	 * 
	 * @return if the myPairs vector contains 4 cards and the count of pairs is
	 *         two then it returns true and the ranking is two pair.
	 */
	public boolean isTwoPair()
	{
		Vector<Integer> myPairs = new Vector<Integer>();
		int count = 0;
		ranking = null;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())

			{
				if (myPairs.contains(i))
				{
					myPairs.add(i + 1);
				}
				else
				{
					myPairs.add(i);
					myPairs.add(i + 1);
				}
			}
		}
		for (int i = 0; i < myPairs.size() - 1; i++)
		{
			if (myCards.get(myPairs.get(i)).getType().getType() == myCards.get(myPairs.get(i) + 1).getType().getType())
			{
				count++;
			}
		}
		if (myPairs.size() == 4 && count == 2)
		{
			ranking = PokerHandRanking.TWO_PAIR;
			return true;
		}
		else
			return false;

	}

	/**
	 * The isThreeOfKind method sets up a vectors that will contain the cards
	 * that match. It goes through the hand, if the hand contains equal cards it
	 * adds them to the myDiscards vector.
	 * 
	 * @return if the myMatches size is 3 then we know that the hand is three of
	 *         a kind and we return true if not we return false.
	 */
	public boolean isThreeOfKind()
	{
		Vector<Integer> myMatches = new Vector<Integer>();

		ranking = null;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())

			{
				if (myMatches.contains(i))
				{
					myMatches.add(i + 1);
				}
				else
				{
					myMatches.add(i);
					myMatches.add(i + 1);
				}
			}
		}
		if (myMatches.size() == 3)
		{
			ranking = PokerHandRanking.THREE_OF_KIND;
			return true;
		}
		else
			return false;

	}

	/**
	 * The isStraight method sets a boolean of less to true and a boolean of
	 * suit to false. The types are then compared, if the one to the left is one
	 * less than the one immediately to the right, then less stays true,
	 * otherwise it becomes false
	 * 
	 * @return true if the hand is a straight, false if it is not
	 */
	public boolean isStraight()
	{
		ranking = null;
		boolean less = true;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			less &= myCards.get(i).getType().getType() + 1 == myCards.get(i + 1).getType().getType();
		}

		if (less)
		{
			ranking = PokerHandRanking.STRAIGHT;
			return true;
		}
		return false;
	}

	/**
	 * The isFlush method sets the ranking to null and the boolean suit to true.
	 * The cards are then tested to see if they all contain the same CardSuit.
	 * If they do, then the PokerHand ranking of Flush is returned.
	 * 
	 * @return if suit is true then the method returns true and returns a
	 *         pokerHandRanking of Flush if not then it returns false.
	 */
	public boolean isFlush()
	{
		ranking = null;
		boolean suit = true;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			suit &= myCards.get(i).getSuit() == myCards.get(i + 1).getSuit();
		}
		if (suit)
		{
			ranking = PokerHandRanking.FLUSH;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This Method is used to determine if the PokerHand is a FullHouse. The
	 * ranking is set to null as soon as the method is called. The cards are
	 * then compared for their type, as FullHouse should contain a pair and
	 * three of a kind. The matching cards are then added to the vector
	 * myMatchess
	 * 
	 * @return if all 5 cards are in the myMatches vector then the ranking is a
	 *         Full house and the method returns true, if not the method returns
	 *         false.
	 */
	public boolean isFullHouse()
	{
		Vector<Integer> myMatches = new Vector<Integer>();
		ranking = null;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())

			{
				if (myMatches.contains(i))
				{
					myMatches.add(i + 1);
				}
				else
				{
					myMatches.add(i);
					myMatches.add(i + 1);
				}
			}
		}
		if (myMatches.size() == 5)
		{
			ranking = PokerHandRanking.FULL_HOUSE;
			return true;
		}
		else
			return false;
	}

	/**
	 * for the isFourOfKind method we have one vectors and the count is set to
	 * 0. The method goes through the hand if their are two cards that are a
	 * like it adds them to the myDiscards vector. For each card that is alike
	 * it also adds 1 to the count.
	 * 
	 * @return if myDiscards size is 4 and the count is 3 then the method
	 *         returns true and the ranking is four of kind, if not it returns
	 *         false.
	 */
	public boolean isFourOfKind()
	{
		Vector<Integer> myDiscards = new Vector<Integer>();
		int count = 0;
		ranking = null;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			if (myCards.get(i).getType().getType() == myCards.get(i + 1).getType().getType())

			{
				if (myDiscards.contains(i))
				{
					myDiscards.add(i + 1);
				}
				else
				{
					myDiscards.add(i);
					myDiscards.add(i + 1);
				}
			}
		}
		for (int i = 0; i < myDiscards.size() - 1; i++)
		{
			if (myCards.get(myDiscards.get(i)).getType().getType() == myCards.get(myDiscards.get(i) + 1).getType()
					.getType())
			{
				count++;
			}
		}
		if (myDiscards.size() == 4 && count == 3)
		{
			ranking = PokerHandRanking.FOUR_OF_KIND;
			return true;
		}
		else
			return false;

	}

	/**
	 * The isStraightFlush method sets the ranking to null and has booleans less
	 * and suit set to true. It goes through the hand and if the card is equal
	 * to the card plus one is saves it as less, if the card's suit is equal to
	 * the next it is saved as a suit.
	 * 
	 * @return if less and suit are both true then the ranking is Straight flush
	 *         and the method returns true if not it returns false.
	 */
	public boolean isStraightFlush()
	{
		ranking = null;
		boolean less = true;
		boolean suit = true;
		for (int i = 0; i < myCards.size() - 1; i++)
		{
			less &= myCards.get(i).getType().getType() + 1 == myCards.get(i + 1).getType().getType();
		}

		for (int i = 0; i < myCards.size() - 1; i++)
		{
			suit &= myCards.get(i).getSuit() == myCards.get(i + 1).getSuit();

		}

		if (less && suit)
		{
			ranking = PokerHandRanking.STRAIGHT_FLUSH;
			return true;
		}
		return false;
	}

	/**
	 * the isRoyalFlush method sets the ranking to null and has a boolean called
	 * match set to true. It goes through the hand and match is true as long as
	 * the suit of the card equals the suit of the next card.
	 * 
	 * @return if match is true and the type for the card 4 is an Ace then it
	 *         goes through the hand and if they are king, queen, jack and ace
	 *         it returns true and the ranking is a Royal flush, if not it
	 *         returns false.
	 */
	public boolean isRoyalFlush()
	{
		ranking = null;
		boolean match = true;

		for (int i = 0; i < myCards.size() - 1; i++)
		{
			match &= myCards.get(i).getSuit().equals(myCards.get(i + 1).getSuit());
		}

		if (match && myCards.get(4).getType() == CardType.ACE)
		{
			for (int i = 1; i < myCards.size() - 1; i++)
			{
				for (int j = 10; j < 14; j++)
				{
					if (myCards.get(i).getType().ordinal() == j)
					{
						ranking = PokerHandRanking.ROYAL_FLUSH;
						return true;
					}

					break;
				}
			}
		}

		return false;
	}

	/**
	 * Private method to determine the ranking of a PokerHand. Each method to
	 * determine a PokerHand is called, and the correct match is returned
	 * 
	 * @return the PokerHandRanking of a PokerHand
	 */
	private PokerHandRanking pokerRanker()
	{
		if (isRoyalFlush())
		{
			return ranking;
		}

		else if (isStraightFlush())
		{
			return ranking;
		}

		else if (isFourOfKind())
		{
			return ranking;
		}

		else if (isFullHouse())
		{
			return ranking;
		}

		else if (isFlush())
		{
			return ranking;
		}

		else if (isStraight())
		{
			return ranking;
		}

		else if (isThreeOfKind())
		{
			return ranking;
		}

		else if (isTwoPair())
		{
			return ranking;
		}

		else if (isPair())
		{
			return ranking;
		}

		else if (isHighCard())
		{
			return ranking;
		}

		return null;

	}

}
