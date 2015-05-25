package com.mcmanuellp.numericalhandler;

import com.mcmanuellp.util.ParseUtils;

public class XInteger
{
	int value;

	public XInteger(String value)
	{
		setValue(value);
	}

	public void setValue(String value)
	{
		if(value.startsWith("0") && value.contains("x"))
		{
			int base = ParseUtils.safeParseDecInt(value.substring(1, value.indexOf("x")));
			String strippedValue = value.substring(value.indexOf("x") + 1);
			this.value = processValue(base, strippedValue);
		} else System.out.println("value not properly entered");
	}

	public int getValue()
	{
		return value;
	}

	public int processValue(int base, String strippedValue)
	{
		int value = 0;
		int position = strippedValue.length();

		for(char c : strippedValue.toCharArray())
		{
			value += ParseUtils.safeParseDecInt(c + "") * Math.pow(base, (--position));
		}
		//return Integer.parseUnsignedInt(strippedValue, base);
		return value;
	}

	public String processValue(int base)
	{
		return Integer.toUnsignedString(value, base);
		//TODO mby find a not as easy way? (never lulz)
	}

	public String getByBase(int base)
	{
		return "0" + base + "x" + processValue(base);
	}

	public static XInteger getFromInt(int value)
	{
		return new XInteger("010x" + value);
	}
}
