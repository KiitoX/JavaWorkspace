package com.mcmanuellp;

import com.mcmanuellp.lib.*;
import com.mcmanuellp.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PasswordManager
{
	public static class file
	{
		File path;
		public File save;
		public File temp;
		public File s;
		public File p;

		file()
		{
			setPath(getPathDependantOnOS());
			initFiles();
		}

		public File getPathDependantOnOS()
		{
			OSUtil.OSType osType = OSUtil.getOperatingSystemType();
			switch(osType)
			{
				case Windows:
					return new File(System.getenv("AppData"), "/.mcmanuellp/pw");
				case MacOS:
					return new File("~/Library/Application Support/mcmanuellp/pw");
				case Linux:
					return new File(System.getenv("HOME"), "/.mcmanuellp/pw");
				default:
				{
					System.out.println("WARNING: Failed to recognize known OS, files are saved in fallback location '~/.mcmanuellp/pw', if you want to handle this, please run with '-path:<save-path>' argument, doing that doesn't disable this message");
					return new File("/.mcmanuellp/pw");
				}
			}
		}

		public void setPath(File path)
		{
			if(!path.exists()) path.mkdirs();
			if(path.isDirectory()) this.path = path;
			else setPath(path.getParentFile());
		}

		public void initFiles()
		{
			save = new File(path, "save.enc");
			temp = new File(path, "temp.txt");
			s = new File(path, "s.enc");
			p = new File(path, "p.enc");
		}

		public void createFiles()
		{
			if(!path.exists()) stub = path.mkdir();
			try
			{
				FileUtils.createMissingTextFiles(save, temp, s, p);
				if(!FileUtils.getMissingFiles(s).isEmpty()) FileUtils.writeToFile(s, AESUtils.tempSalt);
				if(!FileUtils.getMissingFiles(p).isEmpty()) FileUtils.writeToFile(p, AESUtils.tempIv);
			} catch(IOException e)
			{
				ExceptionUtils.handle(e, true, true);
			}
		}
	}

	public static class args
	{
		public String args;
		public String info;

		args()
		{
			info = "-path:<savePath>, -key:<encryptKey>, -jcefix";
		}

		public void read(String[] args)
		{
			this.args = Arrays.toString(args);

			for(String arg : args)
			{
				if(arg.startsWith("-key:"))
				{
					crypto.auto = true;
					crypto.key = arg.substring(5);
				}
				if(arg.startsWith("-path:"))
				{
					file.setPath(new File(arg.substring(6)));
					file.initFiles();
				}
				if(arg.startsWith("-jcefix"))
				{
					System.out.println("DISCLAIMER: Please remove this argument after running it once, you might have to rerun this after a java update.");
					System.out.println("DISCLAIMER: We do not take responsibility for any legal issues this might inflict.");
					AESUtils.applyJCEChanges();
				}
			}
		}
	}

	public static class crypto
	{
		public String key;
		public boolean auto;

		crypto()
		{
			auto = false;
		}

		public void inputKey()
		{
			if(!auto)
			{
				String input = JOptionPane.showInputDialog(null, "Please Input Decryption Key", "Input Decryption Key", JOptionPane.QUESTION_MESSAGE);
				if(input != null) key = input;
				else System.exit(0);
			}
			decrypt();
		}

		public void decrypt()
		{
			try
			{
				auto = false;
				AESUtils.decrypt(key, file.save, file.temp, file.s, file.p);
			} catch(InvalidKeyException | InvalidKeySpecException | BadPaddingException e)
			{
				if(e instanceof InvalidKeyException && e.getMessage().contains("Illegal key size"))
					System.out.println("This error is probably caused due to the lack of the necessary Java Cryptography Extension policy files, if you don't already have these files installed, \nyou can download them for JRE 8 here: http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html \nif needed they are also available for other versions here: http://www.oracle.com/technetwork/java/javase/downloads/index.html \ninstall instructions are shipped with the files. \nAlternatively you can run this with '-jcefix' as argument but we do not take responsibility for any legal issues that may cause.");
				ExceptionUtils.handle(e, true, false);
				inputKey();
			} catch(NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | IllegalBlockSizeException | IOException e)
			{
				ExceptionUtils.handle(e, true, true);
			}
		}

		public void encrypt()
		{
			try
			{
				AESUtils.encrypt(key, file.temp, file.save, file.s, file.p);
			} catch(Exception e)
			{
				ExceptionUtils.handle(e, true, true);
			}
		}
	}

	public static class login
	{
		public ArrayList<LoginInfo> logins;
		public int length;
		public int current;

		login()
		{
			current = 0;
		}

		public void readFromFile(File file)
		{
			List<String> lines;

			ArrayList<LoginInfo> loginInfos = new ArrayList<>();

			String[] split_lines;

			String password;
			String username;
			String email;
			String serviceKey;
			String[] additions;

			try
			{
				lines = LineUtils.getLines(file.getPath());

				for(String line : lines)
				{
					split_lines = ParseUtils.parseStringArray(line);

					if(split_lines.length > 3)
					{
						password = split_lines[0];
						username = split_lines[1];
						email = split_lines[2];
						serviceKey = split_lines[3];
						additions = new String[split_lines.length - 4];

						System.arraycopy(split_lines, 4, additions, 0, additions.length);

						loginInfos.add(new LoginInfo(password, username, email, serviceKey, additions));
					}
				}

				logins = loginInfos;
			} catch(IOException e)
			{
				ExceptionUtils.handle(e, true, true);
			}
		}

		public void writeToFile(File file)
		{
			try
			{
				FileOutputStream is = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(is);
				Writer w = new BufferedWriter(osw);

				for(LoginInfo l : logins)
				{
					w.write(l.toString() + "\n");
				}

				w.close();
			} catch(IOException e)
			{
				ExceptionUtils.handle(e, true, true, "Problem writing to the file " + file.getName());
			}
		}

		public void updateIndexLength()
		{
			length = logins.toArray().length;
		}

		public boolean indexAvailable(int index)
		{
			updateIndexLength();
			return index < length && index >= 0;
		}

		public void setCurrentIndex(int index)
		{
			if(indexAvailable(index)) { current = index; } else if(logins.isEmpty()) { current = 0; } else
			{
				System.out.println("ERR: index out of bounds (loginListCurrentIndex)\nACTION: setCurrentIndex(int:)");
			}
		}

		public void changeCurrentIndex(int dir)
		{
			int index;
			int fallback;
			updateIndexLength();
			index = (dir > 0) ? current + 1 : ((dir < 0) ? current - 1 : current);
			fallback = (dir > 0) ? 0 : ((dir < 0) ? length - 1 : current);
			if(indexAvailable(index)) { setCurrentIndex(index); } else { setCurrentIndex(fallback); }
		}

		public void removeCurrentLoginInfo()
		{
			int result = JOptionPane.showConfirmDialog(null, "Do you really want to remove this entry?\nThis action is irreversible!", "Remove entry", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if(result == JOptionPane.OK_OPTION)
			{
				removeCurrentLoginInfo(true);
			}
		}

		public void removeCurrentLoginInfo(boolean really)
		{
			if(really)
			{
				if(!logins.isEmpty())
				{
					logins.remove(current);
				}
				changeCurrentIndex(-1);
				v.displayC(0);
			}
		}

		public LoginInfo getCurrentLoginInfo()
		{
			if(!logins.isEmpty())
			{
				return logins.get(current);
			} else
			{
				return LoginInfo.getEmpty();
			}
		}
	}

	public static final String version = "1.8a";//TODO update this & changelog before every built artifact
	public static class changelog extends Changelog
	{
		changelog()
		{
			super();
		}

		@Override
		public void init()
		{
			add("1.0", "initial build");
			add("1.1", "better split up window code");
			add("1.2", "encryption of save file");
			add("1.3", "first library outsourcing");
			add("1.3+", "several failed builds due to corrupted library");
			add("1.4a", "major library fix");
			add("1.4d", "initial changelog");
			add("1.4d", "changes listed for prior versions were added later");
			add("1.4d1", "changelog compatibility w/ .jar file");
			add("1.4d2", "changelog displaying cleanup");
			add("1.5", "outsourcing of commonly used methods to external lib");
			add("1.5", "generalizing JFrame creation");
			add("1.5a", "more lib cleanup");
			add("1.6", "complete rewrite, compatible w/ 1.5 saves");
			add("1.6", "save file needs to be renamed to 'save.txt'");
			add("1.7", "switch to more secure encryption");
			add("1.7", "saves need to be manually copied");
			add("1.7", "JCE is required to run this version");
			add("1.7", "key can now be any size");
			add("1.7", "now additionally needs correct salt & pepper files (s.enc & p.enc)");
			add("1.7a", "forgot to remove hints that keys can only be 2++*16 bytes long");
			add("1.7a", "forgot to remove length checks for -key: argument");
			add("1.7b", "fixed nullPointerException");
			add("1.7c", "changed default save folder from %AppData%/.mcmanuellp to %AppData%/.mcmanuellp/pw");
			add("1.7d", "removed outsourced changelog");
			add("1.7d1", "added changelog for versions prior to 1.4d");
			add("1.8", "cross platform compatibility added");
			add("1.8", "manual permission fix added");
			add("1.8", "several dependency fixes");
			add("1.8", "changelog display gui cleanup");
			add("1.8a", "linux path fix");
		}
	}

	public static file file = new file();
	public static args args = new args();
	public static crypto crypto = new crypto();
	public static login login = new login();
	public static changelog changelog = new changelog();

	public static boolean stub;

	public static FormViewLoginInfo v;

	public static void main(String[] args)
	{
		//apply look and feel
		SwingUtils.setDefaultLookAndFeel();

		//read additional program arguments
		PasswordManager.args.read(args);

		PasswordManager.file.createFiles();

		//decryption
		crypto.inputKey();
		login.readFromFile(file.temp);
		stub = file.temp.delete();

		//setup of the gui
		v = new FormViewLoginInfo();
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				onExit();
			}
		});
		v.setVisible(true);
		v.display(0);
	}

	public static void onExit()
	{
		login.writeToFile(file.temp);
		crypto.encrypt();
		stub = file.temp.delete();
		System.exit(0);
	}
}
