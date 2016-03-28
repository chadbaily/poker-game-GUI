package model;

import java.awt.Image;

/**
 * Card is a class that simulates properties that a real card would have from a
 * deck. The card contains a Suit, Type and Image. The card is able to be
 * flipped to reveal the card, or stay hidden.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public class Card
{
	private CardSuit mySuit;
	private CardType myType;
	private Image myImage;
	private boolean myIsSelected;
	private boolean myIsFaceUp;

	/**
	 * Default Constructor for the card class. Sets the suit, type and image of
	 * the card. Automatically sets myFaceUp to false and myIsSelected to false;
	 * 
	 * @param suit
	 * @param type
	 * @param image
	 */

	public Card(CardSuit suit, CardType type, Image image)
	{
		mySuit = suit;
		myType = type;
		myImage = image;
		myIsFaceUp = false;
		myIsSelected = false;
	}

	/**
	 * Method that is used to flip the card based on what it currently is. If
	 * myIsFaceUp is true, then it becomes false and so forth.
	 */
	public void flip()
	{
		if (myIsFaceUp)
		{
			myIsFaceUp = false;
		}

		else
		{
			myIsFaceUp = true;
		}
	}

	/**
	 * Method to test if the card is face up or not. Calls on myIsFaceUp and
	 * return the value
	 * 
	 * @return whether or not myIsFaceUp is true or false
	 */
	public boolean isFaceUp()
	{
		return myIsFaceUp;
	}

	/**
	 * Method to test if the card is selected or not. Calls on myIsSelected and
	 * return the value
	 * 
	 * @return whether or not myIsSeclected is true or false
	 */
	public boolean isSelected()
	{
		return myIsSelected;
	}

	/**
	 * Method that is used to change if the card is selected or not based on
	 * what it currently is. If myIsSelected is true, then it becomes false and
	 * so forth.
	 */
	public void toggleSelected()
	{
		if (myIsSelected)
		{
			myIsSelected = false;
		}
		else
			myIsSelected = true;
	}

	/**
	 * Method to compare if two cards, based on their type, are equal or if
	 * another is greater or less than another.
	 * 
	 * @param card
	 * @return returns 1 if the card is greater than the one it's being compared
	 *         to. 0 if the two card are equal, and -1 if the original card is
	 *         less than the card that is passed i.
	 */
	public int compareTo(Card card)
	{
		if (myType.getType() > card.getType().getType())
		{
			return 1;
		}
		else if (myType.getType() == card.getType().getType())
			return 0;
		else
			return -1;

	}

	/**
	 * Method to clone the current card. Creates a new card based on the suit,
	 * type and image of the original card.
	 * 
	 * @return returns a object that is a card, the card returned is an exact
	 *         copy of the original one.
	 */
	public Object clone()
	{
		Card card = new Card(mySuit, myType, myImage);
		return card;
	}

	/**
	 * toString method that returns the card in the fashion of "type of suit"
	 * 
	 * @return returns a string in the format of "CardType of CardSuit"
	 */
	public String toString()
	{
		if (myType.getType() == 14)
		{
			return "Ace of " + mySuit.getSuit();
		}

		if (myType.getType() == 11)
		{
			return "Jack of " + mySuit.getSuit();
		}

		if (myType.getType() == 12)
		{
			return "Queen of " + mySuit.getSuit();
		}

		if (myType.getType() == 13)
		{
			return "King of " + mySuit.getSuit();
		}
		else
			return myType.getType() + " of " + mySuit.getSuit();
	}

	/**
	 * Getter method to get the suit from mySuit for the current card.
	 * 
	 * @return mySuit for the current card
	 */
	public CardSuit getSuit()
	{
		return mySuit;
	}

	/**
	 * Getter method to get the type from myType for the current card.
	 * 
	 * @return myType for the current card
	 */
	public CardType getType()
	{
		return myType;
	}

	/**
	 * Getter method to get the image of the card from myImage for the current
	 * card.
	 * 
	 * @return myImage for the current card
	 */
	public Image getImage()
	{
		return myImage;
	}
}