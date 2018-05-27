package com.gobinda.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class LogInPanel extends JPanel implements KeyListener{

	
	private static final long serialVersionUID = 1L;
	private static String password;

	public LogInPanel() {
		super();
		addKeyListener(this);
		password = new String();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawOrganizationName(g);
		drawPasswordAll(g);

	}

	private void drawPasswordAll(Graphics g) {
		// TODO Auto-generated method stub
		String name = new String();
		for (int i = 0; i < password.length(); i++) {
			name += "*";
			// name += password.charAt(i);
		}
		int x, y;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 40));
		FontMetrics ff = g.getFontMetrics();

		x = MainFrame.I_WIDTH / 2 - ff.stringWidth(name) / 2;
		y = MainFrame.I_HEIGHT / 2 - ff.getAscent() / 2 + 20;

		g.drawString(name, x, y);
	}

	private void drawOrganizationName(Graphics g) {
		// TODO Auto-generated method stub

		String name = new String("JUDO");
		int x, y;

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 150));
		FontMetrics ff = g.getFontMetrics();

		x = MainFrame.I_WIDTH / 2 - ff.stringWidth(name) / 2;
		y = MainFrame.I_HEIGHT / 2 - ff.getAscent() / 2;

		g.drawString(name, x, y);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

			int len = password.length();
			if (len > 0) {
				password = password.substring(0, len - 1);
				repaint();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (matchPassword()) {
				MainFrame.setTopPanel("all");
			}

		}

	}

	private boolean matchPassword() {
		// TODO Auto-generated method stub
		if (password.equals("judojudo")) {
			return true;
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		char ch = e.getKeyChar();
		if (!perfect(ch)) {
			return;
		}
		password += ch;

		repaint();

	}

	private boolean perfect(char ch) {
		// TODO Auto-generated method stub

		if (ch == ' ') {
			return true;
		}
		if (ch >= 'a' && ch <= 'z')
			return true;
		if (ch >= 'A' && ch <= 'Z')
			return true;
		return false;
	}


}
