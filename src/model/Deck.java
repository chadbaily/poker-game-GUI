package model;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * The Deck class is a class that simulates the function of a full deck of cards
 * in poker. Each deck has the ability to draw, shuffle and get the cards in the
 * deck, along with the total amount of cards in the deck. Each deck has 4
 * suits, from the CardSuit class and 13 cardTypes from the CardType class. Each
 * deck is constructed with 52 total cards, so there are no Jokers.
 * 
 * @outline Dr. Daniel Plante
 * @author Chad Baily
 * @author Delvin Riley
 * 
 * @dueDate March 21, 2016
 *
 */
public class Deck
{
	private Vector<Card> myCards;
	private int myFullDeckSize;

	/**
	 * Default constructor that sets myFullDeckSize to 52 and calls construct
	 * deck to fill the deck with cards.
	 */
	public Deck()
	{
		constructDeck();
		myFullDeckSize = 52;

	}

	/**
	 * Method to put cards into a deck. Loops through both enums and creates a
	 * card for each property. Then the card is added to the deck.
	 * 
	 * @return whether the deck was constructed properly or not, and contains 52
	 *         cards.
	 */
	public boolean constructDeck()
	{
		myCards = new Vector<Card>();
		for (CardSuit p : CardSuit.values())
		{
			for (CardType t : CardType.values())
			{
				Card myCard = new Card(p, t, null);
				myCards.add(myCard);

			}
		}
		if (myCards.size() == myFullDeckSize)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Method that randomly picks a card from the deck and returns the card, if
	 * the deck size if greater than 0, otherwise it returns null.
	 * 
	 * @return the card that was drawn from the deck, if the size of the deck is
	 *         0, then null is returned.
	 */
	public Card draw()
	{

		if (myCards.size() > 0)
		{
			Random rand = new Random();
			int x = new Random().nextInt(myCards.size());
			Card myTemp = myCards.get(x);
			myCards.remove(myCards.get(x));
			return myTemp;
		}
		else
		{
			return null;
		}

	}

	/**
	 * Method that uses Collections to randomly shuffle all of the cards inside
	 * myCards, therefore shuffling the deck.
	 * 
	 * @return true if the deck has been successfully shuffled, false if it has
	 *         not been.
	 */
	public boolean shuffle()
	{
		Object myTest = myCards;
		Collections.shuffle(myCards);
		if (myTest.equals(myCards))
		{
			return false;
		}
		else
			return true;
	}

	/**
	 * Getter method to get the full deck size
	 * 
	 * @return the value of myFullDeckSize
	 */
	public int getFullDeckSize()
	{

		return myFullDeckSize;
	}

	/**
	 * Method that gets all the cards from myCards, which represents the cards
	 * that are currently in the deck.
	 * 
	 * @return myCards, which are the cards in the deck
	 */
	public Vector<Card> getCards()
	{
		return myCards;
	}

	/**
	 * Method that returns the cards in the deck in the format of
	 * "Your Deck is : the cards in the deck"
	 * 
	 * @return A printable string in the format of "Your Deck is : myCards"
	 */
	public String toString()
	{
		return "Your deck is : " + myCards;
	}

	/**
	 * Method that makes an exact copy of the original deck by creating a new
	 * deck, emptying the deck, then filling the new deck with the contents of
	 * the original deck.
	 * 
	 * @return A exact copy of the original deck.
	 */
	public Object clone()
	{
		Deck deck = new Deck();
		deck.getCards().removeAllElements();
		boolean worked = true;
		for (int i = 0; i < myCards.size(); i++)
		{
			Card card = myCards.get(i);
			worked = worked && deck.addToDeck((Card) card.clone());
		}
		if (worked)
		{
			return deck;
		}
		else
			return null;
	}

	/**
	 * Private method that allows for cards to be added to the deck if the deck
	 * is not full and the deck does not contain the card that is trying to be
	 * added.
	 * 
	 * @param card
	 * @return whether the card was successfully added or not.
	 */
	private boolean addToDeck(Card card)
	{
		if (myCards.size() < myFullDeckSize && !myCards.contains(card))
		{
			myCards.add(card);
			return true;
		}
		else
			return false;
	}
}
