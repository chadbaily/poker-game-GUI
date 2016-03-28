package model;

import java.awt.Toolkit;
import java.awt.Image;
import java.util.Vector;

public class Main
{

	public static void main(String[] args)
	{
		Vector<Integer> myBorderIndex = new Vector<Integer>();
		myBorderIndex.add(2);
		myBorderIndex.add(3);
		myBorderIndex.add(4);
		myBorderIndex.removeElementAt(0);
		myBorderIndex.add(4);
		myBorderIndex.add(4);
		myBorderIndex.removeElementAt(0);
		myBorderIndex.removeElementAt(0);
		myBorderIndex.removeElementAt(0);
		myBorderIndex.removeElementAt(0);
		

		System.out.println(myBorderIndex);

	}

}
