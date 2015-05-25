package com.mcmanuellp.graphictesting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.*;

public class Draw extends Frame
{
	public static void main(String[] args)
	{
		Draw d = new Draw();
	}

	public Draw()
	{
		super("Draw");
		JButton button = new JButton();
		button.setMaximumSize(new Dimension(40, 20));
		button.setSize(40, 20);
		button.setBounds(0, 250, 40, 20);
		add(button);
		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e)
			{
				update(getGraphics());
			}
		});
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				update(getGraphics());
			}
		});
		setSize(600, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new Color(250, 20, 0));
		g2d.fill3DRect(310, 50, 210, 210, false);
		g2d.setPaint(new Color(80, 250, 20));
		for(int y = 0; y < 200; y += 40)
		{
			for(int x = 0; x < 200; x += 40)
			{
				g2d.fill3DRect(x + 320, y + 60, 30, 30, false);
			}
		}
		g2d.setPaint(new Color(0, 0, 0));
		g2d.drawString("hello world", 30, 60);
		g2d.draw3DRect(20, 65, 10, 90, false);
		g2d.fill3DRect(40, 70, 20, 20, false);
		g2d.draw(new Line2D.Double(50, 100, 250, 100));
		GeneralPath gp = new GeneralPath();
		gp.moveTo(80, 200);
		gp.lineTo(180, 200);
		gp.lineTo(180, 160);
		gp.closePath();
		g2d.draw(gp);
		g2d.fill(new Ellipse2D.Float(250, 90, 20, 20));
		Point p = getMousePosition();
		while(p != null)
		{
			g2d.fill(new Ellipse2D.Float(p.x - 3, p.y - 3, 6, 6));
			p = getMousePosition();
		}
	}
}