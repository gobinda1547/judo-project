package com.gobinda.design;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class All extends JPanel implements KeyListener, MouseMotionListener,
		MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Tv tv;

	public JButton btnDoIt, btnAddTeam, btnShowAllTeam, btnRound, btnEditTeam,
			btnDeleteTeam, btnAuto, btnSave;

	public All() {
		super();
		addKeyListener(this);
		addMouseMotionListener(this);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 5));

		btnDoIt = new JButton("Arrange");
		btnDoIt.addKeyListener(this);
		btnDoIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (Tv.showAraenging) {
					return;
				}

				new Thread(new Arranging()).start();

				btnDoIt.setEnabled(false);
				btnAddTeam.setEnabled(false);
				btnRound.setEnabled(false);
				btnEditTeam.setEnabled(false);
				btnDeleteTeam.setEnabled(false);
				btnSave.setEnabled(false);
				btnShowAllTeam.setEnabled(false);
				btnAuto.setEnabled(false);

				tv.makeAllFalse();
				Tv.showAraenging = true;
				tv.repaint();

			}
		});
		btnDoIt.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnDoIt);

		btnAddTeam = new JButton("Add Team");
		btnAddTeam.addKeyListener(this);
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MainFrame.addingTeam.setVisible(true);

			}
		});
		btnAddTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnAddTeam);

		btnShowAllTeam = new JButton("All Team");
		btnShowAllTeam.addKeyListener(this);
		btnShowAllTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tv.makeZero();
				tv.makeAllFalse();
				Tv.showTeams = true;
				tv.repaint();
			}
		});

		btnDeleteTeam = new JButton("Delete Team");
		btnDeleteTeam.addKeyListener(this);
		btnDeleteTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.deleteingTeam.setVisible(true);
			}
		});
		btnDeleteTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnDeleteTeam);

		btnEditTeam = new JButton("Edit Team");
		btnEditTeam.addKeyListener(this);
		btnEditTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.editTeam.setVisible(true);
			}
		});
		btnEditTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnEditTeam);
		btnShowAllTeam.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnShowAllTeam);

		btnSave = new JButton("Save");
		btnSave.addKeyListener(this);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.teamManager.saveData();
			}
		});

		btnAuto = new JButton("Auto");
		btnAuto.addKeyListener(this);
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tv.makeZero();
				if (Tv.autoMode) {
					Tv.autoMode = false;
				} else {
					Tv.autoMode = true;
					new Thread(new AutoModeHelper()).start();
				}

			}
		});

		btnRound = new JButton("Round");
		btnRound.addKeyListener(this);
		btnRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tv.makeZero();
				tv.makeAllFalse();
				Tv.showRounds = true;
				tv.repaint();

			}
		});
		btnRound.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnRound);
		btnAuto.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnAuto);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnSave);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		tv = new Tv();
		tv.addKeyListener(this);
		panel_1.add(tv, BorderLayout.CENTER);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (Tv.showRounds || Tv.showTeams) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (Tv.end > MainFrame.I_HEIGHT - 120) {
					Tv.y -= 5;
					tv.repaint();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (Tv.start < 0) {
					Tv.y += 5;
					tv.repaint();
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

	class AutoModeHelper implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			while (Tv.autoMode) {
				if (Tv.end > MainFrame.I_HEIGHT - 120) {
					Tv.y -= 1;
				} else {
					tv.makeZero();
				}
				ghumao();
				tv.repaint();
			}

		}

		private void ghumao() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	class Arranging implements Runnable {

		@Override
		public void run() {

			new Thread(new PairTeamManager()).start();

			int x = 0;

			Tv.a_min = 0;
			Tv.a_sec = 0;
			while (x < 181) {
				ghumao();
				x++;
				tv.makeSomeChangeForArranging();
				tv.repaint();
			}

			tv.makeZero();
			tv.makeAllFalse();
			Tv.showRounds = true;

			btnDoIt.setEnabled(true);
			btnAddTeam.setEnabled(true);
			btnRound.setEnabled(true);
			btnEditTeam.setEnabled(true);
			btnDeleteTeam.setEnabled(true);
			btnSave.setEnabled(true);
			btnShowAllTeam.setEnabled(true);
			btnAuto.setEnabled(true);

			tv.repaint();
		}

		public void ghumao() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
