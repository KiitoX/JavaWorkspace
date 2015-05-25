package com.mcmanuellp.lib;

import com.mcmanuellp.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public abstract class Changelog implements IChangelog
{
	HashMap<String, ArrayList<String>> changelog;
	ArrayList<String> keys;

	public Changelog()
	{
		this.changelog = new HashMap<>();
		this.keys = new ArrayList<>();
		init();
	}

	public Changelog(HashMap<String, ArrayList<String>> changelog, ArrayList<String> keys)
	{
		this.changelog = changelog;
		this.keys = keys;
		init();
	}

	public HashMap<String, ArrayList<String>> get()
	{
		return changelog;
	}

	public ArrayList<String> get(String key)
	{
		return changelog.get(key);
	}

	public void add(String version, String... changes)
	{
		add(version, new ArrayList<>(Arrays.asList(changes)));
	}

	public void add(String version, ArrayList<String> changes)
	{
		if(changelog.containsKey(version)) changelog.get(version).addAll(changes);
		else
		{
			changelog.put(version, changes);
			keys.add(version);
		}
	}

	public void viewChangelogPanel()
	{
		ArrayList<String> cLog = new ArrayList<>();
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		for(String key : keys)
		{
			cLog.add("v " + key + ":");
			cLog.addAll(changelog.get(key).stream().map(change -> "  " + change).collect(Collectors.toList()));
		}

		for(String line : cLog)
		{
			SwingUtils.label.addLeftLabel(panel, line);
		}
		JOptionPane.showMessageDialog(null, scrollPane, "Changelog", JOptionPane.PLAIN_MESSAGE);
	}
}
