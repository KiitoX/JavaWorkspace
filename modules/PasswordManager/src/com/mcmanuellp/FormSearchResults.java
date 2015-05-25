package com.mcmanuellp;

import com.mcmanuellp.lib.GenericForm;
import com.mcmanuellp.util.ParseUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormSearchResults extends GenericForm
{
	private JPanel     mainPanel;
	private JTextField searchTextField;
	private JTextField key1;
	private JTextField key2;
	private JTextField key3;
	private JTextField key4;
	private JTextField key5;
	private JTextField index1;
	private JTextField index2;
	private JTextField index3;
	private JTextField index4;
	private JTextField index5;
	private JButton    viewButton1;
	private JButton    viewButton2;
	private JButton    viewButton3;
	private JButton    viewButton4;
	private JButton    viewButton5;
	private JButton    closeButton;
	private JTextField[] key   = new JTextField[] {key1, key2, key3, key4, key5};
	private JTextField[] index = new JTextField[] {index1, index2, index3, index4, index5};

	private ArrayList<Integer> results = new ArrayList<>();

	public FormSearchResults(String search)
	{
		super("Search Results");
		setContentPane(mainPanel);
		setDefaultFocus(closeButton);
		pack();

		search(search);

		searchTextField.setHorizontalAlignment(JTextField.CENTER);

		closeButton.addActionListener(ae -> setVisible(false));
		viewButton1.addActionListener(viewAction(index1));
		viewButton2.addActionListener(viewAction(index2));
		viewButton3.addActionListener(viewAction(index3));
		viewButton4.addActionListener(viewAction(index4));
		viewButton5.addActionListener(viewAction(index5));
	}

	public ActionListener viewAction(JTextField i)
	{
		final JTextField index = i;
		return ae -> {
			if(!index.getText().isEmpty())
			{
				PasswordManager.v.display(ParseUtils.safeParseDecInt(index.getText()));
				setVisible(false);
			}
		};
	}

	public void search(String search)
	{
		searchTextField.setText(search);

		getResults(search);
		display();
	}

	public void getResults(String search)
	{
		results.clear();

		for(int i = 0; i < PasswordManager.login.length; i++)
		{
			if(PasswordManager.login.logins.get(i).getServiceKey().toLowerCase().contains(search.toLowerCase()))
			{
				results.add(i);
			}
		}
	}

	public void display()
	{
		for(int i = 0; i < 5; i++)
		{
			key[i].setText("");
			index[i].setText("");
		}

		for(int i = 0; i < 5 && i < results.toArray().length; i++)
		{
			int k = results.get(i);

			key[i].setText(PasswordManager.login.logins.get(k).getServiceKey().toLowerCase());
			index[i].setText(k + "");
		}
	}
}
