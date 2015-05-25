package com.mcmanuellp.twodpathfinding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame
{
	JPanel panelButtons;
	JLabel header;
	JLabel display;
	final int buttonCount = 9 * 9;
	JButton[] buttons = new JButton[buttonCount];

	public Window()
	{
		super("PathFindingTest");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout(5, 5));

		panelButtons = new JPanel(new GridLayout(9, 9));

		for(int i = 0; i < buttonCount; i++)
		{
			buttons[i] = new JButton(i + ":0");
			panelButtons.add(buttons[i]);
			addButtonListener(buttons[i]);
		}

		header = new JLabel("LayoutTest");
		header.setHorizontalAlignment(JLabel.CENTER);

		display = new JLabel("clickButt on");
		display.setHorizontalAlignment(JLabel.CENTER);

		getContentPane().add(BorderLayout.NORTH, header);
		getContentPane().add(BorderLayout.SOUTH, display);

		getContentPane().add(BorderLayout.CENTER, panelButtons);

		pack();
		setResizable(false);
		setSize(585, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addButtonListener(JButton b)
	{
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				eingabe(ae.getActionCommand());
			}
		});
	}

	private void eingabe(String a)
	{
		System.out.println(getSize() + " : " + buttons[0].getWidth() + "-" + buttons[0].getHeight());
		System.out.println(a);
		display.setText(a);
	}
}
