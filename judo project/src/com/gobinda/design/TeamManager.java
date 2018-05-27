package com.gobinda.design;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Vector;

public class TeamManager {

	public static Vector<String> universityName;
	public static Vector<Vector<String>> teams;

	public TeamManager() {

		universityName = new Vector<String>();
		teams = new Vector<Vector<String>>();

	}

	public void addTeam(String versityName, String teamName) {

		int pos = -1;
		for (int i = 0; i < universityName.size(); i++) {
			if (universityName.get(i).equals(versityName)) {
				pos = i;
				break;
			}
		}
		if (pos == -1) {

			universityName.add(versityName);
			teams.add(new Vector<String>());
			int x = universityName.size() - 1;
			teams.get(x).add(teamName);
		} else {
			teams.get(pos).add(teamName);
		}

	}

	public static String[] getUniversitiesName() {
		return universityName.toArray(new String[universityName.size()]);
	}

	public void loadFromFile() {

		String fileLocation = new String("C:\\Judo\\teamName.txt");
		if (!new File(fileLocation).exists()) {
			new File("C:\\Judo").mkdirs();
			try {
				Formatter ff2 = new Formatter(new File(fileLocation));
				ff2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			Scanner scanner = new Scanner(new File(fileLocation));
			while (scanner.hasNextLine()) {
				String ss = scanner.nextLine();
				String[] nowString = ss.split(";");
				MainFrame.teamManager.addTeam(nowString[0], nowString[1]);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		new PairTeamManager().loadFromFile2();
	}

	public void saveData() {

		String fileLocation = new String("C:\\Judo\\teamName.txt");
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
			Formatter formatter = new Formatter(new File(fileLocation));
			for (int i = 0; i < universityName.size(); i++) {
				String versityName = universityName.get(i).trim();
				for (int j = 0; j < teams.get(i).size(); j++) {
					formatter.format("%s;%s%n", versityName, teams.get(i).get(j));
				}
			}
			formatter.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		saveData2();

	}

	public void saveData2() {

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

		String s1, s2, s3;
		try {
			Formatter formatter = new Formatter(new File(fileLocation));
			for (int i = 0; i < PairTeamManager.pairTeams.size(); i++) {
				s1 = PairTeamManager.pairTeams.elementAt(i).aTeam.comBined().trim();
				s2 = PairTeamManager.pairTeams.elementAt(i).bTeam.comBined().trim();
				s3 = String.valueOf(PairTeamManager.pairTeams.elementAt(i).winner);
				formatter.format("%s;%s;%s%n", s1, s2, s3);
			}

			if (PairTeamManager.luckyWinerTeam != -1) {
				formatter.format(";;%s\n", PairTeamManager.luckyTeam.comBined());
			}

			formatter.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean deleteTeam(String s1, String s2) {
		// TODO Auto-generated method stub

		int pos = -1, pos2 = -1;
		for (int i = 0; i < universityName.size(); i++) {
			if (s1.equals(universityName.get(i))) {
				pos = i;
				break;
			}
		}

		if (pos == -1) {
			return false;
		}

		for (int i = 0; i < teams.get(pos).size(); i++) {
			if (s2.equals(teams.get(pos).get(i))) {
				pos2 = i;
			}
		}

		if (pos2 == -1) {
			return false;
		}

		teams.get(pos).remove(pos2);
		if (teams.get(pos).size() == 0) {
			universityName.remove(pos);
			teams.remove(pos);
		}

		return true;

	}

}
