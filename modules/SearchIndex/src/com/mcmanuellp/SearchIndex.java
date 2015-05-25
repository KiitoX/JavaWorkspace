package com.mcmanuellp;

import com.mcmanuellp.lib.SwingUtils;

import java.io.File;
import java.util.Arrays;

public class SearchIndex
{
	public static void main(String[] args)
	{
		SwingUtils.setDefaultLookAndFeel();
		Index i = new Index(new File("C:\\Users\\Admin\\Desktop\\DartLang"));
		System.out.println(Arrays.toString(i.f.find("dart").toArray()));
	}
}
