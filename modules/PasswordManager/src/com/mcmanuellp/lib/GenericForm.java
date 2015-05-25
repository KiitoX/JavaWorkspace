package com.mcmanuellp.lib;

import javax.swing.*;

public class GenericForm extends JFrame
{
	JComponent defaultFocus;

	public GenericForm(String title)
	{
		super(title);
		setDefaultFocus(getRootPane());
	}

	@Override
	public void pack()
	{
		super.pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		defaultFocus.requestFocusInWindow();
	}

	public void setDefaultFocus(JComponent component)
	{
		defaultFocus = component;
	}
}
