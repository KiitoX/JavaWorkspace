package com.mcmanuellp;

import com.mcmanuellp.lib.*;

import java.io.*;
import java.util.*;

public class Index
{
	public boolean stub;
	public File filePath = new File(System.getenv("AppData") + "/.mcmanuellp");
	public File savePath = new File(filePath, "indexing");

	ArrayList<File> folderIndex = new ArrayList<>();
	ArrayList<File> fileIndex   = new ArrayList<>();

	public final Character   dummyChar        = '_';
	public final Character[] indexFolderNames = new Character[] {dummyChar, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	public final String      ending           = ".index";
	//public final String      version          = "1.0";

	public f f = new f();

	public Index(File index)
	{
		index(index);
		registerIndexFiles();
		toWrittenIndex(folderIndex);
		toWrittenIndex(fileIndex);
	}

	void add(File file)
	{
		if(file.isDirectory()) folderIndex.add(file);
		else fileIndex.add(file);
	}

	void index(File... index)
	{
		ArrayList<File> folders = new ArrayList<>();
		if(index == null) return;
		for(File f : index)
		{
			if(f.isDirectory())
			{
				System.out.println("Current folder: " + f.getAbsolutePath());//TODO remove, debug
				File[] folderContent = f.listFiles();
				folders.addAll(Arrays.asList(folderContent != null ? folderContent : new File[0]));
			}
			add(f);
		}
		if(!folders.isEmpty())
		{
			File[] f = new File[folders.size()];
			index(folders.toArray(f));
		}
	}

	public void registerIndexFiles()
	{
		ArrayList<File> files = new ArrayList<>();
		for(char folder : indexFolderNames)
		{
			File f = new File(savePath, folder + "");
			stub = f.mkdirs();
			for(char file : indexFolderNames)
			{
				String s = file + ending;
				File fi = new File(f, s);
				try {
					stub = fi.createNewFile();
				} catch(IOException e) {
					e.printStackTrace();
				}
				files.add(fi);
			}
		}
	}

	public String write(File file)
	{
		return file.getName() + "," + file.getAbsolutePath() + "," + file.isDirectory();
	}

	public void toWrittenIndex(ArrayList<File> files)
	{
		Map<File, ArrayList<File>> index = new HashMap<>();
		for(File f : files)
		{
			File key = this.f.findIndexFile(f.getName());
			ArrayList<File> value = new ArrayList<>();

			if(index.containsKey(key))
				value = index.get(key);
			value.add(f);
			index.remove(key);
			index.put(key, value);
		}
		try
		{
			FileOutputStream is;
			OutputStreamWriter osw;
			Writer w;
			for(File key : index.keySet())
			{
				is = new FileOutputStream(key);
				osw = new OutputStreamWriter(is);
				w = new BufferedWriter(osw);
				for(File value : index.get(key))
				{
					w.write(write(value) + "\n");
				}
				w.close();
			}
		}
		catch(IOException e)
		{
			System.err.println("Problem writing to file: " + e.getMessage());
		}
	}

	public class f
	{
		f()
		{

		}

		public ArrayList<File> find(String search)
		{
			ArrayList<File> results = new ArrayList<>();
			File index = findIndexFile(search);
			List<String> indexLines = readIndexFile(index);

			for(String s : indexLines)
			{
				List<String> line = LineUtils.splitLine(s);
				if(line.get(0).contains(search)) results.add(new File(line.get(1)));
			}
			return results;
		}

		public String getMatchingChar(char c)
		{
			return ((Character)(CompareUtils.equalsAny(c, indexFolderNames) ? c : dummyChar)).toString();
		}

		public File findIndexFile(String search)
		{
			search = search.toLowerCase();
			if(search.length() >= 2)
			{
				File folder = new File(savePath, getMatchingChar(search.charAt(0)));
				return new File(folder, getMatchingChar(search.charAt(1)) + ending);
			}
			else
				return findIndexFile(search + " ");
		}

		public List<String> readIndexFile(File file)
		{
			try
			{
				return LineUtils.getLines(file);
			}
			catch(IOException e)
			{
				ExceptionUtils.handle(e, false, false);
			}
			return new ArrayList<>();
		}
	}
}
