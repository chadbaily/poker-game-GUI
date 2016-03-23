package mvc;

import java.awt.*;
import model.*;

/**
 * Simple controller class used to show how Model-View-Controller is
 * implemented.
 *
 * @author Daniel Plante
 * @version 1.0 (28 January 2002)
 */
public class Controller
{
	///////////////////
	// Properties //
	///////////////////

	public View myView;
	public PokerModel myModel;
	public Player myPlayer;

	///////////////////
	// Methods //
	///////////////////

	/**
	 * Controller constructor; view must be passed in since controller has
	 * responsibility to notify view when some event takes place.
	 */
	public Controller()
	{
		myPlayer = new Player("Chad");
		myModel = new PokerModel(myPlayer);
		myModel.dealCards();
		myView = new View(this);
	}

	/**
	 * gets the number of cards in the deck, also flips the cards over to reveal
	 * which cards are where
	 */
	public void flipCardsOver(Integer iRow)
	{
		int value, row;
		Image myImage;

		row = iRow.intValue();
		myImage = myModel.getPlayerUp().getHand().getCards().get(row).getImage();
		value = myModel.getPlayerUp().getHand().getNumberCardsInHand();
		myView.changeImage(row, myImage);
		// myView.makeBorder();
	}

	/**
	 * Gets an image from the card that is at position 0
	 * 
	 * @return
	 */
	public Image getBack()
	{
		return myModel.getPlayer(0).getHand().getCards().get(0).getImage();
	}

	public String handRanking()
	{
		myModel.getPlayerUp().getHand().orderCards();
		return "" + myModel.getPlayerUp().getHand().determineRanking();
	}

}