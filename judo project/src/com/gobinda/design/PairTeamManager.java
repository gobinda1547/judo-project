package com.gobinda.design;

import java.io.File;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class PairTeamManager implements Runnable {

	public static Vector<PairTeam> pairTeams;
	public static int luckyWinerTeam;
	public static Team luckyTeam = null;

	public PairTeamManager() {
		pairTeams = new Vector<PairTeam>();
		luckyWinerTeam = -1;
	}

	public void addThisPair(PairTeam pairTeam) {
		pairTeams.add(pairTeam);
	}

	public void loadFromFile2() {

		String fileLocation = new String("C:\\Judo\\roundName.txt");
		if (!new File(fileLocation).exists()) {
			new File("C:\\Judo").mkdirs();
			try {
				Formatter ff2 = new Formatter(new File(fileLocation));
				ff2.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		try {
			Scanner scanner = new Scanner(new File(fileLocation));
			while (scanner.hasNextLine()) {
				String ss = scanner.nextLine();
				if (ss.charAt(0) == ';' && ss.charAt(1) == ';') {
					String[] nowStrings = ss.split(";;");
					luckyTeam = new Team(nowStrings[1]);
					luckyWinerTeam = 0;
				} else {
					String[] nowString = ss.split(";");
					Team aTeam = new Team(nowString[0]);
					Team bTeam = new Team(nowString[1]);
					boolean bb = Boolean.parseBoolean(nowString[2]);
					PairTeamManager.pairTeams
							.add(new PairTeam(aTeam, bTeam, bb));
				}

			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		pairTeams.removeAllElements();
		luckyTeam = null;
		luckyWinerTeam = -1;
		Random r = new Random();
		Vector<Team> teams = new Vector<Team>();
		int l1 = TeamManager.universityName.size();
		for (int i = 0; i < l1; i++) {
			int l2 = TeamManager.teams.get(i).size();
			for (int j = 0; j < l2; j++) {
				teams.add(new Team(TeamManager.teams.get(i).get(j),
						TeamManager.universityName.get(i)));
			}
		}
		int len = teams.size();

		boolean[] vis = new boolean[len];

		if (len % 2 == 1) {
			luckyWinerTeam = r.nextInt(len);
			luckyTeam = teams.elementAt(luckyWinerTeam);
			vis[luckyWinerTeam] = true;
		} else {
			luckyWinerTeam = -1;
		}

		int a1, a2;
		while (optionHave(vis)) {

			a1 = firstTeamSelection(vis);
			vis[a1] = true;

			if (otherTeamsOptions(teams, a1, vis)) {
				a2 = secondTeamSelection(teams, a1, vis);
			} else {
				a2 = secondTeamSelectionAny(teams, a1, vis);
			}
			vis[a2] = true;
			pairTeams.add(new PairTeam(teams.elementAt(a1),
					teams.elementAt(a2), r.nextBoolean()));

		}

		All.tv.repaint();

	}

	private int secondTeamSelectionAny(Vector<Team> teams, int a1, boolean[] vis) {
		// TODO Auto-generated method stub

		int len = teams.size(), xx = -1;
		Random rr = new Random();

		boolean ans = false;

		while (!ans) {
			xx = rr.nextInt(len);
			if (xx != a1 && !vis[xx]) {
				ans = true;
				return xx;
			}

		}

		return xx;
	}

	private int secondTeamSelection(Vector<Team> teams, int a1, boolean[] vis) {
		// TODO Auto-generated method stub

		int len = teams.size(), xx = -1;
		Random rr = new Random();
		Team firstTeam = teams.elementAt(a1);

		boolean ans = false;

		while (!ans) {
			xx = rr.nextInt(len);
			if (xx != a1
					&& !vis[xx]
					&& !firstTeam.university
							.equals(teams.elementAt(xx).university)) {
				ans = true;
				return xx;
			}

		}

		return xx;
	}

	private boolean otherTeamsOptions(Vector<Team> teams, int already,
			boolean[] vis) {
		// TODO Auto-generated method stub

		int len = teams.size();
		Team aa = teams.elementAt(already);

		for (int i = 0; i < len; i++) {
			if (already != i && !vis[i]
					&& !aa.university.equals(teams.elementAt(i).university)) {
				return true;
			}

		}

		return false;
	}

	private int firstTeamSelection(boolean[] vis) {
		// TODO Auto-generated method stub
		Random r = new Random();
		boolean ans = true;
		int x = 0, len = vis.length;
		while (ans) {
			x = r.nextInt(len);
			ans = vis[x];
		}

		return x;
	}

	private boolean optionHave(boolean[] vis) {
		// TODO Auto-generated method stub

		int len = vis.length;

		for (int i = 0; i < len; i++) {
			if (!vis[i]) {
				return true;
			}
		}

		return false;
	}

	class Team {

		String name, university;

		public Team(String teamName, String universityName) {
			name = teamName;
			university = universityName;
		}

		public Team(String combindString) {

			name = new String();
			university = new String();
			boolean tf = true;
			for (int i = 0; i < combindString.length(); i++) {
				char ch = combindString.charAt(i);
				if (tf) {
					if (ch == '(') {
						tf = false;
					} else {
						name += ch;
					}
				} else {
					if (ch == ')') {
						break;
					} else {
						university += ch;
					}
				}
			}
			name = name.trim();
			university = university.trim();

		}

		public String comBined() {
			// TODO Auto-generated method stub
			return String.format("%s ( %s )", name, university);
		}

	}

	class PairTeam {

		Team aTeam, bTeam;
		boolean winner;

		public PairTeam(Team aTeam, Team bTeam, boolean winner) {
			this.aTeam = aTeam;
			this.bTeam = bTeam;
			this.winner = winner;
		}

		public String comBined() {
			return String.format(" %s ( %s ) Vs %s ( %s )", aTeam.name,
					aTeam.university, bTeam.name, bTeam.university);
		}

	}

}
