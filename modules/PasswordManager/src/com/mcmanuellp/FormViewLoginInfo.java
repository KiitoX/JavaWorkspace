package com.mcmanuellp;

import com.mcmanuellp.lib.*;
import com.mcmanuellp.util.ClipboardUtils;
import com.mcmanuellp.util.LineUtils;
import com.mcmanuellp.util.ParseUtils;
import com.mcmanuellp.util.SwingUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FormViewLoginInfo extends GenericForm
{
	private JPanel     mainPanel;
	private JButton    copyEntryButton;
	private JButton    showPwButton;
	private JButton    copyPwButton;
	private JButton    copyUsrButton;
	private JButton    copyMailButton;
	private JButton    previousButton;
	private JButton    removeButton;
	private JButton    editButton;
	private JButton    addButton;
	private JButton    nextButton;
	private JButton    arrayIndexSearchButton;
	private JButton    serviceKeySearchButton;
	private JButton    exitButton;
	private JButton    infoButton;
	private JButton    cLogButton;
	private JButton    keyButton;
	private JTextField passwordTextField;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField serviceKeyTextField;
	private JTextField additionsTextField;
	private JTextField currentIndexTextField;
	public  JTextField serviceKeySearchTextField;
	public  JTextField arrayIndexSearchTextField;

	public FormAddLoginInfo  a;
	public FormSearchResults s;

	public FormViewLoginInfo()
	{
		super("View Login Info");
		setContentPane(mainPanel);
		setDefaultFocus(exitButton);
		pack();

		displayC(0);

		a = new FormAddLoginInfo();
		s = new FormSearchResults("");

		copyEntryButton.addActionListener(ae -> copy());
		showPwButton.addActionListener(ae -> displayPW());
		copyPwButton.addActionListener(ae -> ClipboardUtils.copy(passwordTextField.getText()));
		copyUsrButton.addActionListener(ae -> ClipboardUtils.copy(usernameTextField.getText()));
		copyMailButton.addActionListener(ae -> ClipboardUtils.copy(emailTextField.getText()));
		previousButton.addActionListener(ae -> displayC(-1));
		nextButton.addActionListener(ae -> displayC(1));
		removeButton.addActionListener(ae -> PasswordManager.login.removeCurrentLoginInfo());
		editButton.addActionListener(ae -> edit());
		addButton.addActionListener(ae -> a.setVisible(true));
		arrayIndexSearchButton.addActionListener(ae -> findIndex());
		arrayIndexSearchTextField.addActionListener(ae -> findIndex());
		serviceKeySearchButton.addActionListener(ae -> findText());
		serviceKeySearchTextField.addActionListener(ae -> findText());
		exitButton.addActionListener(ae -> PasswordManager.onExit());
		infoButton.addActionListener(ae -> viewInfoPanel());
		cLogButton.addActionListener(ae -> PasswordManager.changelog.viewChangelogPanel());//TODO migrate, viewChangelogPanel());
		keyButton.addActionListener(ae -> viewChangeKeyPanel());

		setSize(620, getHeight());
	}

	public void displayCurrentIndex()
	{
		currentIndexTextField.setText(PasswordManager.login.current + " (indexRange: 0 - " + (PasswordManager.login.length - 1) + "; totalCount: " + PasswordManager.login.length + ")");
	}

	public void display(int index)
	{
		PasswordManager.login.setCurrentIndex(index);
		displayC(0);
	}

	public void displayC(int dir)
	{
		PasswordManager.login.changeCurrentIndex(dir);
		PasswordManager.login.updateIndexLength();

		LoginInfo info = PasswordManager.login.getCurrentLoginInfo();

		displayCurrentIndex();
		passwordTextField.setText(info.getPassword());
		displayPW();
		usernameTextField.setText(info.getUsername());
		emailTextField.setText(info.getEmail());
		serviceKeyTextField.setText(info.getServiceKey());
		additionsTextField.setText(info.getAdditionsWritable());
	}

	public void displayPW()
	{
		LoginInfo info = PasswordManager.login.getCurrentLoginInfo();
		String s = "";
		boolean b = false;
		for(byte p : passwordTextField.getText().getBytes())
		{
			b = (p == '*');
			if(!b) { break; }
		}
		for(int i = 0; i < info.getPassword().length(); i++)
		{
			s += "*";
		}
		passwordTextField.setText(b ? info.getPassword() : s);
		showPwButton.setText(b ? "Hide" : "Show");
		copyPwButton.setVisible(b);
	}

	public static void copy()
	{
		if(!PasswordManager.login.logins.isEmpty())
		{
			PasswordManager.v.a.setFields(PasswordManager.login.getCurrentLoginInfo());
			PasswordManager.v.a.setVisible(true);
		}
	}

	public static void edit()
	{
		if(!PasswordManager.login.logins.isEmpty())
		{
			copy();
			PasswordManager.v.a.setEditing(true);
		}
	}

	public static void findIndex()
	{
		if(PasswordManager.login.indexAvailable(ParseUtils.safeParseDecInt(PasswordManager.v.arrayIndexSearchTextField.getText())))
		{ PasswordManager.v.display(ParseUtils.safeParseDecInt(PasswordManager.v.arrayIndexSearchTextField.getText())); }
		PasswordManager.v.arrayIndexSearchTextField.setText("");
	}

	public static void findText()
	{
		PasswordManager.v.s.search(PasswordManager.v.serviceKeySearchTextField.getText());
		PasswordManager.v.s.setVisible(true);
		PasswordManager.v.serviceKeySearchTextField.setText("");
	}

	public static void viewInfoPanel()
	{
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		SwingUtils.label.addCenterLabel(panel, "Program Args:");
		SwingUtils.label.addCenterLabel(panel, PasswordManager.args.info);
		SwingUtils.label.addCenterLabel(panel, PasswordManager.args.args.substring(1, PasswordManager.args.args.length() - 1));
		SwingUtils.label.addCenterLabel(panel, "-");
		SwingUtils.label.addCenterLabel(panel, "PasswordManager Version: " + PasswordManager.version);
		SwingUtils.label.addCenterLabel(panel, "\u00A9 2015 MCManuelLP");
		JOptionPane.showMessageDialog(null, panel, "Info", JOptionPane.PLAIN_MESSAGE);
	}

	public static void viewChangeKeyPanel()
	{
		int i = JOptionPane.YES_OPTION;
		String input;
		while(i == JOptionPane.YES_OPTION)
		{
			input = JOptionPane.showInputDialog(null, "Please Input new Key", "Change Encryption Key", JOptionPane.QUESTION_MESSAGE);
			if(input != null)
			{
				PasswordManager.crypto.key = input;
				i = JOptionPane.CANCEL_OPTION;
			}
			else
			{
				i = JOptionPane.CANCEL_OPTION;
			}
		}
	}
}
