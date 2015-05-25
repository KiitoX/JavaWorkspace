package com.mcmanuellp.util;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ClipboardUtils
{
	public static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	public static boolean displayedLinuxWarningMessage = false;

	public static void copy(String copy)
	{
		toClipboard(new StringSelection(copy));
		if(OSUtil.detectedOS == OSUtil.OSType.Linux && !displayedLinuxWarningMessage)
		{
			displayedLinuxWarningMessage = true;
			System.out.println("Detected Linux, due to a bug in Ubuntu(& possibly other) copying to clipboard might not work, to fix that locate the java.policy file (you should find it's path by issuing 'locate java.policy' in the terminal(you might have to issue 'sudo updatedb' first))  and add 'permission java.awt.AWTPermission \"accessClipboard\";' just before the last closing bracket.");
		}
	}

	public static String paste()
	{
		try
		{
			return (String) fromClipboard().getTransferData(DataFlavor.stringFlavor);
		} catch(UnsupportedFlavorException e)
		{
			ExceptionUtils.handle(e, true, false, "couldn't paste clipboard as String");
		} catch(IOException e)
		{
			ExceptionUtils.handle(e, true, false, "error while pasting, read/write exception");
		}
		return null;
	}

	private static void toClipboard(Transferable copy)
	{
		clipboard.setContents(copy, null);
	}

	private static Transferable fromClipboard()
	{
		return clipboard.getContents(null);
	}
}
