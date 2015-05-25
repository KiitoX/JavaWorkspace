package com.mcmanuellp.textad_take1.options;

import com.mcmanuellp.textad_take1.api.IOption;

public class OptionBooleanDebug implements IOption
{
	boolean value;

	public OptionBooleanDebug(boolean value)
	{
		this.value = value;
	}

	public Object getOptionValue()
	{
		return getBooleanValue();
	}

	public void setOptionValue(Object optionValue)
	{
		setBooleanValue((Boolean)optionValue);
	}

	public Object getBooleanValue()
	{
		return this.value;
	}

	public void setBooleanValue(boolean optionValue)
	{
		this.value = optionValue;
	}
}
