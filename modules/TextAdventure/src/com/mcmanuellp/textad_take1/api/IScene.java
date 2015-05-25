package com.mcmanuellp.textad_take1.api;

import com.mcmanuellp.textad_take1.api.data.Environment;

public interface IScene
{
	void apply();

	Environment getEnvironment();

	IAction getAction();
}