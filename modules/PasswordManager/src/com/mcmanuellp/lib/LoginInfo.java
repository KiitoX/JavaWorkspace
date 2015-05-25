package com.mcmanuellp.lib;

public class LoginInfo
{
	String   password;
	String   username;
	String   email;
	String   serviceKey;
	String[] additions;

	public static LoginInfo getEmpty()
	{
		return new LoginInfo("-", "-", "-", "-", "-");
	}

	public LoginInfo(String password, String username, String email, String serviceKey, String... additions)
	{
		this.password = password;
		this.username = username;
		this.email = email;
		this.serviceKey = serviceKey;
		this.additions = additions;
	}

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public String getEmail()
	{
		return email;
	}

	public String getServiceKey()
	{
		return serviceKey;
	}

	public String[] getAdditions()
	{
		return additions;
	}

	public String getAdditionsWritable()
	{
		String flag = "";

		for(String s : getAdditions())
		{
			flag = flag + s + ',';
		}
		return flag.substring(0, flag.getBytes().length - 1);
	}

	public String toString()
	{
		return String.format("%s,%s,%s,%s,%s", getPassword(), getUsername(), getEmail(), getServiceKey(), getAdditionsWritable());
	}
}
