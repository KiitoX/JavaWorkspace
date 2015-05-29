package com.mcmanuellp;

import javax.swing.*;

public class Screen extends JFrame
{
	private JPanel    rootPanel;
	private JTextArea textArea;

	public Screen()
	{
		setVisible(true);
		setContentPane(rootPanel);
	}

	public void write(String content)
	{
		textArea.setText(content);
	}
}
