package model;

import java.awt.Toolkit;
import java.awt.Image;
import java.util.Vector;

public class Main
{

	public static void main(String[] args)
	{
		Hand myHand;
		Player myPlayer = new Player("Chad");
		PokerModel myModel = new PokerModel(myPlayer);
		Deck myDeck = new Deck();
		myHand = new Hand(5);
		myModel.dealCards();
		Vector<Card> myCards;
		myCards = new Vector<Card>();

		System.out.println(myDeck);

	}

}
