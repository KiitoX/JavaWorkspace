package com.mcmanuellp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils
{
	public static List<File> getMissingFiles(File... files)
	{
		ArrayList<File> file = new ArrayList<>();
		for(File f : files)
		{
			if(!f.exists()) file.add(f);
		}
		return file;
	}

	public static void createMissingTextFiles(File... files) throws IOException
	{
		for(File f : getMissingFiles(files))
		{
			writeToFile(f, "");
		}
	}

	public static void writeToFile(File file, String s)
	{
		try
		{
			FileOutputStream is = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			w.write(s);
			w.close();
		}
		catch(IOException e)
		{
			ExceptionUtils.handle(e, true, true);
		}
	}
}
