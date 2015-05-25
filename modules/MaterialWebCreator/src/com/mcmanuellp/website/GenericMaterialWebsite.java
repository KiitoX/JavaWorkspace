package com.mcmanuellp.website;

import com.mcmanuellp.object.HTMLObject;

import java.util.ArrayList;
import java.util.List;

public class GenericMaterialWebsite implements MaterialWebsite
{
	GenericMaterialWebsite()
	{

	}

	public List<HTMLObject> StatusBar()
	{
		return new ArrayList<>();
	}

	public List<HTMLObject> Content()
	{
		return new ArrayList<>();
	}

	public List<HTMLObject> Footer()
	{
		return new ArrayList<>();
	}
}
