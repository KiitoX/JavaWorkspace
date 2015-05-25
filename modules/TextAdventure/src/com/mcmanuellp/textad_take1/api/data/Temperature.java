package com.mcmanuellp.textad_take1.api.data;

public enum Temperature
{
	HOT(10),
	WARM(5),
	MILD(0),
	COLD(-5),
	FREEZING(-10),;

	final int temperature;

	Temperature(int t)
	{
		this.temperature = t;
	}

	public int getTemperature()
	{
		return this.temperature;
	}
}