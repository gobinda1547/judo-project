package com.gobinda.design;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

import com.gobinda.design.PairTeamManager.PairTeam;

public class Tv extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	public static int x = 0, y = 0, start = 0, end = 0;
	public static boolean showTeams = false, showRounds = false,
			autoMode = false, showAraenging = false;

	public static int a_min, a_sec;

	public void makeAllFalse() {
		showTeams = false;
		showRounds = false;
		showAraenging = false;
	}

	public Tv() {
		makeAllFalse();
		addKeyListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		start = y;

		if (showAraenging) {
			displayArranging(g);
		} else if (showTeams) {
			displayTeams(g);
		} else if (showRounds) {
			displayRounds(g);
		}

	}

	private void displayArranging(Graphics g) {
		// TODO Auto-generated method stub

		String str = String.valueOf(a_min) + ":" + String.valueOf(a_sec);
		FontMetrics ff = g.getFontMetrics();
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial",Font.BOLD+Font.PLAIN,60));
		g.drawString(str, MainFrame.I_WIDTH/2 - ff.stringWidth(str)/2 , MainFrame.I_HEIGHT/3);
		
		

	}

	private void displayRounds(Graphics g) {
		// TODO Auto-generated method stub

		Vector<PairTeam> vv = PairTeamManager.pairTeams;

		if (vv.size() == 0) {
			autoMode = false;
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 50));
			FontMetrics ff = g.getFontMetrics();
			String str = new String("No Previous Data Found!");
			g.drawString(str, MainFrame.I_WIDTH / 2 - ff.stringWidth(str) / 2,
					MainFrame.I_HEIGHT / 2 - ff.getAscent());

			return;
		}

		g.setColor(Color.BLACK);

		g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 30));

		FontMetrics ff = g.getFontMetrics();

		int bad = 50;
		int c = x + bad - 10;
		int d = y + 50;
		int ghorHeight = 150;
		int forNum = 100;

		int weNeed = MainFrame.I_WIDTH - (bad * 2) - c;

		int j = 0;
		for (int i = 0; i < vv.size(); i++, j++) {

			g.setColor(Color.BLACK);
			g.drawRect(c, d + i * ghorHeight, c + weNeed, ghorHeight);

			String num = String.valueOf(i + 1);

			g.drawString(num, c + forNum / 2 - ff.stringWidth(num) / 2, d
					+ (i + 1) * ghorHeight - ghorHeight / 2);

			int pp = d + (i + 1) * ghorHeight;
			g.drawLine(c + forNum, d + i * ghorHeight, c + forNum, pp);

			num = vv.elementAt(i).aTeam.comBined();

			if (vv.elementAt(i).winner) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GREEN);
			}

			int ddd = ff.getHeight() + 5;
			pp = d + ddd;
			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(i * ghorHeight) + pp);

			g.setColor(Color.BLACK);
			num = new String("Vs");
			pp += ddd;

			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(i * ghorHeight) + pp);

			if (!vv.elementAt(i).winner) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GREEN);
			}
			num = vv.elementAt(i).bTeam.comBined();
			pp += ddd;
			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(i * ghorHeight) + pp);

		}

		if (PairTeamManager.luckyWinerTeam != -1) {

			g.setColor(Color.BLACK);
			g.drawRect(c, d + j * ghorHeight, c + weNeed, ghorHeight);

			String num = String.valueOf(j + 1);

			g.drawString(num, c + forNum / 2 - ff.stringWidth(num) / 2, d
					+ (j + 1) * ghorHeight - ghorHeight / 2);

			int pp = d + (j + 1) * ghorHeight;
			g.drawLine(c + forNum, d + j * ghorHeight, c + forNum, pp);

			num = new String("The Lucky Winner");

			int ddd = ff.getHeight() + 5;
			pp = d + ddd;
			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(j * ghorHeight) + pp);

			num = new String("is");
			pp += ddd;

			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(j * ghorHeight) + pp);

			g.setColor(Color.RED);
			num = PairTeamManager.luckyTeam.comBined();
			pp += ddd;
			g.drawString(num,
					c + forNum + weNeed / 2 - ff.stringWidth(num) / 2,
					(j * ghorHeight) + pp);

			j++;
		}

		end = y + j * ghorHeight + 50;
	}

	private void displayTeams(Graphics g) {
		// TODO Auto-generated method stub

		Vector<String> vector = TeamManager.universityName;
		Vector<Vector<String>> vector2 = TeamManager.teams;

		int ans = 0;
		for (int i = 0; i < vector.size(); i++) {
			if (vector2.get(i).size() > 0) {
				ans += vector2.get(i).size();
			}
		}

		if (ans == 0) {
			g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 50));
			g.setColor(Color.red);
			String info = new String("No Team Name Found!");
			FontMetrics ff = g.getFontMetrics();
			int a = MainFrame.I_WIDTH / 2 - ff.stringWidth(info) / 2;
			int b = MainFrame.I_HEIGHT / 2 - ff.getAscent() / 2;
			g.drawString(info, a, b);
			return;
		}

		String[] strings = new String[ans];
		for (int i = 0, k = 0; i < vector.size(); i++) {
			for (int j = 0; j < vector2.get(i).size(); j++) {
				strings[k++] = vector2.get(i).get(j) + " ( " + vector.get(i)
						+ " )";
			}
		}
		int boro = 0, check = 0;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD + Font.PLAIN, 25));
		FontMetrics ff = g.getFontMetrics();
		for (int i = 0; i < strings.length; i++) {
			check = ff.stringWidth(strings[i]);
			if (boro < check) {
				boro = check;
			}
		}

		boro += 200;
		int c = x + MainFrame.I_WIDTH / 2 - boro / 2;
		int d = y + 50;

		int numberBoxLength = 150, allBoxHeight = 50;

		int j = 0;
		for (int i = 0, p; i < strings.length; i++, j++) {
			p = d + i * allBoxHeight;
			g.drawRect(c, p, boro, allBoxHeight);
			g.drawLine(c + numberBoxLength, p, c + numberBoxLength, p
					+ allBoxHeight);

			g.drawString(
					String.valueOf(i + 1),
					c + (numberBoxLength) / 2
							- ff.stringWidth(String.valueOf(i + 1)) / 2, p
							+ allBoxHeight - allBoxHeight / 2 + ff.getAscent()
							/ 2);

			g.drawString(strings[i], c + numberBoxLength
					+ (boro - numberBoxLength) / 2 - ff.stringWidth(strings[i])
					/ 2, p + allBoxHeight - allBoxHeight / 2 + ff.getAscent()
					/ 2);

		}
		end = y + j * allBoxHeight + 50;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void makeZero() {
		// TODO Auto-generated method stub
		x = 0;
		y = 0;
	}

	public void makeSomeChangeForArranging() {
		// TODO Auto-generated method stub

		a_sec++;
		if (a_sec == 60) {
			a_min++;
			a_sec = 0;
		}
	}
}
