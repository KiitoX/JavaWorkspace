package com.mcmanuellp;

import com.mcmanuellp.lib.GenericForm;
import com.mcmanuellp.lib.LoginInfo;
import com.mcmanuellp.util.ParseUtils;

import javax.swing.*;

public class FormAddLoginInfo extends GenericForm
{
	
	private JPanel     mainPanel;
	private JTextField passwordTextField;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField serviceKeyTextField;
	private JTextField additionsTextField;
	private JButton    cancelButton;
	private JButton    resetButton;
	private JButton    confirmButton;

	private boolean editing = false;

	public FormAddLoginInfo()
	{
		super("Add Login Info");
		setContentPane(mainPanel);
		setDefaultFocus(passwordTextField);
		pack();

		passwordTextField.addActionListener(ae -> usernameTextField.requestFocusInWindow());
		usernameTextField.addActionListener(ae -> emailTextField.requestFocusInWindow());
		emailTextField.addActionListener(ae -> serviceKeyTextField.requestFocusInWindow());
		serviceKeyTextField.addActionListener(ae -> additionsTextField.requestFocusInWindow());
		additionsTextField.addActionListener(ae -> onConfirm());

		cancelButton.addActionListener(ae -> onCancel());
		resetButton.addActionListener(ae -> onReset());
		confirmButton.addActionListener(ae -> onConfirm());
	}

	public void setEditing(boolean b)
	{
		editing = b;
	}

	public void onCancel()
	{
		if(editing)
		{
			editing = false;
		}
		onReset();
		setVisible(false);
	}

	public void onReset()
	{
		passwordTextField.setText("");
		usernameTextField.setText("");
		emailTextField.setText("");
		serviceKeyTextField.setText("");
		additionsTextField.setText("");
	}

	public void onConfirm()
	{
		if(editing)
		{
			PasswordManager.login.removeCurrentLoginInfo(true);
			editing = false;
		}
		PasswordManager.login.logins.add(new LoginInfo(passwordTextField.getText(), usernameTextField.getText(), emailTextField.getText(), serviceKeyTextField.getText(), ParseUtils.parseStringArray(additionsTextField.getText())));
		PasswordManager.login.updateIndexLength();
		PasswordManager.login.setCurrentIndex(0);
		PasswordManager.login.changeCurrentIndex(-1);
		PasswordManager.v.displayC(0);
		onCancel();
	}

	public void setFields(LoginInfo info)
	{
		passwordTextField.setText(info.getPassword());
		usernameTextField.setText(info.getUsername());
		emailTextField.setText(info.getEmail());
		serviceKeyTextField.setText(info.getServiceKey());
		additionsTextField.setText(info.getAdditionsWritable());
	}
}
