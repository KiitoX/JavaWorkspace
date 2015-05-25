package com.mcmanuellp.textad_take1.api.data;

public enum Humidity
{
	SWAMP(-10),
	WET(-5),
	NORMAL(0),
	DRY(5),
	DESERT(10),
	;

	final int humidity;

	Humidity(int h)
	{
		this.humidity = h;
	}

	public int getHumidity()
	{
		return this.humidity;
	}
}