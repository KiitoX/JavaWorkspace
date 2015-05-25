package com.mcmanuellp;

import com.mcmanuellp.lib.CompareUtils;
import com.mcmanuellp.lib.SwingUtils;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
	public static boolean stub;
	public static File filePath = new File(System.getenv("AppData") + "/.mcmanuellp");
	public static File savePath = new File(filePath, "indexing");

	public static ArrayList<File> folderIndex = new ArrayList<>();
	public static ArrayList<File> fileIndex   = new ArrayList<>();

	public static final Character[] indexFolderNames = new Character[] {'0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	public static final String      ending           = ".index";
	public static final String      version          = "1.3f";

	public static void main(String[] args)
	{
		SwingUtils.setDefaultLookAndFeel();

		registerIndexFiles();

		index(new File("C:\\Users\\Admin"));
		toWrittenIndex(folderIndex);
		toWrittenIndex(fileIndex);

		Scanner sc = new Scanner(System.in);
		String st;
		while(true)
		{
			st = sc.next();
			if(st.equals("@")) break;
			System.out.println(searchFor(st));
		}
		sc.close();
	}

	public static void add(File file)
	{
		if(file.isDirectory())
			folderIndex.add(file);
		else
			fileIndex.add(file);
	}

	/*
		ArrayList<String> names = new ArrayList<>();
		for(File f : files)
			names.add(f.getName());
	 */

	public static ArrayList<String> print(ArrayList<File> files)
	{
		ArrayList<String> names = new ArrayList<>();
		for(File f : files)
			names.add(f.getName());
		return names;
	}

	public static void index(File... toBeIndexed)
	{
		ArrayList<File> folders = new ArrayList<>();
		if(toBeIndexed == null)
			return;
		for(File f : toBeIndexed)
		{
			if(f.isDirectory())
			{
				System.out.println("Current folder: " + f.getAbsolutePath());
				File[] folder = f.listFiles();
				folders.addAll(Arrays.asList(folder != null ? folder : new File[]{new File(savePath, "temp.tmp")}));
			}
			add(f);
		}
		if(!folders.isEmpty())
		{
			File[] f = new File[folders.size()];
			index(folders.toArray(f));
		}
	}

	public static String write(File file)
	{
		return file.getName() + "," + file.getAbsolutePath() + "," + file.isDirectory();
	}

	public static void toWrittenIndex(ArrayList<File> files)
	{
		Map<File, ArrayList<File>> index = new HashMap<>();
		for(File f : files)
		{
			File key = searchFor(f.getName());
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

	public static void registerIndexFiles()
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

	public static File searchFor(String search)
	{
		search = search.toLowerCase();
		File folder;
		File file = new File("C:\\");
		if(search.length() >= 2)
		{
			folder = new File(savePath, ((CompareUtils.equalsAny(search.charAt(0), indexFolderNames)) ? search.charAt(0) + "" : "0"));
			file = new File(folder, ((CompareUtils.equalsAny(search.charAt(1), indexFolderNames)) ? search.charAt(1) + "" : "0") + ending);
		}
		else
			searchFor(search + " ");

		return file;//TODO return (mby searched) file content
	}
}
