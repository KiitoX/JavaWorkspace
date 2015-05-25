package com.mcmanuellp.util;

import javax.swing.*;
import java.awt.*;

public class SwingUtils
{
	public static void setDefaultLookAndFeel()
	{
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(Exception e) { ExceptionUtils.handle(e, true, false); }
	}

	public static class label
	{
		public static void addCenterLabel(JPanel p, String s)
		{
			JLabel label = new JLabel(s, JLabel.CENTER);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			p.add(label);
		}

		public static void addLeftLabel(JPanel p, String s)
		{
			JLabel label = new JLabel(s, JLabel.LEFT);
			label.setAlignmentX(Component.LEFT_ALIGNMENT);
			p.add(label);
		}

		public static void addRightLabel(JPanel p, String s)
		{
			JLabel label = new JLabel(s, JLabel.RIGHT);
			label.setAlignmentX(Component.RIGHT_ALIGNMENT);
			p.add(label);
		}
	}
}
