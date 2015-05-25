package com.mcmanuellp.twodpathfinding;

import javax.swing.*;

public class TwoDPathFinding
{
	public static void main(String[] args)
	{
		Field f = new Field(3);

		f.draw();

		f.findPath();

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}

		//Window w = new Window();
	}
}
