package com.mcmanuellp.textad_take1.api.data;

public enum Environment
{
	DESERT(Temperature.HOT, Humidity.DESERT),
	FOREST(Temperature.MILD, Humidity.NORMAL),
	BEACH(Temperature.WARM, Humidity.WET),
	PLAINS(Temperature.MILD, Humidity.NORMAL),
	MOUNTAINS(Temperature.COLD, Humidity.DRY),
	CAVE(Temperature.COLD, Humidity.NORMAL),;

	final Temperature temperature;
	final Humidity    humidity;

	Environment(Temperature t, Humidity h)
	{
		this.temperature = t;
		this.humidity = h;
	}
}