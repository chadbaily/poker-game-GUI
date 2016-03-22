package mvc;

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
		myView = new View(this);
		myModel.dealCards();
	}

	/**
	 * Modifies the number value of the model and gives it to the view.
	 *
	 * <pre>
	 * pre:  a valid view and controller have been designated
	 * post: the number value of the model is increased by one,
	 *       and the view is modified accordingly
	 * </pre>
	 */
	public void getNumCards(Integer iRow)
	{
		int value, row;

		row = iRow.intValue();
		value = myModel.getPlayerUp().getHand().getNumberCardsInHand();
		myView.changeImage(value, row);
		myView.setTextField("" + value);
	}

	// /**
	// * Modifies the number value of the model and gives it to the view.
	// *
	// * <pre>
	// * pre: a valid view and controller have been designated
	// * post: the number value of the model is decreased by one,
	// * and the view is modified accordingly
	// * </pre>
	// */
	// public void decrement()
	// {
	// int value;
	// myModel.decrementValue();
	// value = myModel.getNumberValue();
	// myView.setTextField("" + value);
	// }

	/**
	 * Obtains the number value of the model.
	 *
	 * <pre>
	 * pre:  a valid view and controller have been designated
	 * post: the number value of the model obtained
	 * </pre>
	 *
	 * @return the model's number value
	 */
	public String getModelValue()
	{
		String modelValue;

		modelValue = "" + myModel.getPlayerUp().getHand().getNumberCardsInHand();
		return modelValue;
	}

}