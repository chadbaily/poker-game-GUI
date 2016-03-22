package model;

import java.util.Vector;

/**
 * 
 * The ComputerPlayer class is code that extends player and passes through a
 * name setting myAmAI to be true, letting us know that it is indeed a computer
 * player. It discards cards from its had based on a very simple AI that
 * determines the ranking of each card. If the hand is better than a Straight,
 * then no cards are discarded. Otherwise, the appropriate cards are discarded,
 * or the lowest three.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 */
public class ComputerPlayer extends Player
{
	/**
	 * The constructor passes in a name of type String, this becomes the
	 * ComputerPlayer's name. Then myAmAI is set to true, because it is a
	 * ComputerPlayer.
	 * 
	 * 
	 * @param name
	 */
	public ComputerPlayer(String name)
	{
		super(name);
		myAmAI = true;
	}

	/**
	 * Method to automatically select the cards that a ComputerPlayer should
	 * discard. If a ComputerPlayer has either a Royal Flush, Straight Flush,
	 * Four of a Kind, Full House, Flush, or a Straight; no cards are discarded.
	 * If it is Two Pair, the card that is not part of the two pairs is
	 * discarded, if One Pair, then the other three cards are discarded. If you
	 * have three of a kind, then the cards of different types are discarded.
	 * Otherwise, the lowest three cards are discarded.
	 * 
	 * @return A vector of integers that show the indices for what card will be
	 *         discarded.
	 */
	public Vector<Integer> selectCardsToDiscard()
	{
		Vector<Integer> myDiscards = new Vector<Integer>();
		if (myHand.isRoyalFlush() || myHand.isStraightFlush() || myHand.isFourOfKind() || myHand.isFullHouse()
				|| myHand.isFlush() || myHand.isStraight())
		{
			return myDiscards;
		}

		else if (myHand.isTwoPair())
		{
			return myHandIsAnyOther();
		}

		else if (myHand.isPair())
		{
			return myHandIsAnyOther();
		}

		else if (myHand.isThreeOfKind())
		{
			return myHandIsAnyOther();
		}
		else

		{
			myDiscards.add(0);
			myDiscards.add(1);
			myDiscards.add(2);
			return myDiscards;
		}
	}

	/**
	 * Private method to clean up code. Determines which cards should be
	 * discarded for Two Pair, One Pair, and Three of a Kind by seeing if they
	 * have the matching type.
	 * 
	 * @return A vector of Integers, which indicate the cards that need to be
	 *         discarded
	 */
	private Vector<Integer> myHandIsAnyOther()
	{
		Vector<Integer> myDiscards = new Vector<Integer>();
		Vector<Integer> myFinalDiscards = new Vector<Integer>();

		for (int i = 0; i < myHand.getCards().size() - 1; i++)
		{
			if (myHand.getCards().get(i).getType().getType() == myHand.getCards().get(i + 1).getType().getType())

			{
				myDiscards.add(i);
				myDiscards.add(i + 1);
			}
		}
		for (int j = 0; j < 5; j++)
		{
			if (!myDiscards.contains(j))
			{
				myFinalDiscards.add(j);
			}
		}
		return myFinalDiscards;
	}

}
