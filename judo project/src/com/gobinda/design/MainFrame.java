package com.gobinda.design;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements KeyListener,
		MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JPanel cardLayoutController;
	private static LogInPanel logInPanel;
	public static All all;

	public static MainFrame mainFrame;
	public static AddingTeam addingTeam;
	public static TeamManager teamManager;
	public static DeleteingTeam deleteingTeam;
	public static EditTeam editTeam;

	public static CardLayout card;

	public static int I_WIDTH, I_HEIGHT;

	private static String topPanel = new String();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editTeamInitialization();
					deleteingTeamInitialization();
					teamManagerInitialization();
					mainFrameInitialization();
					addingTeamInitialization();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void editTeamInitialization() {
				// TODO Auto-generated method stub
				editTeam = new EditTeam();
				editTeam.setVisible(false);

			}

			private void deleteingTeamInitialization() {
				// TODO Auto-generated method stub
				deleteingTeam = new DeleteingTeam();
				deleteingTeam.setVisible(false);
			}

			private void addingTeamInitialization() {
				// TODO Auto-generated method stub
				addingTeam = new AddingTeam();
				addingTeam.setVisible(false);
			}

			private void mainFrameInitialization() {
				// TODO Auto-generated method stub
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				I_WIDTH = dim.width;
				I_HEIGHT = dim.height;
				mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			}
		});
	}

	protected static void teamManagerInitialization() {
		// TODO Auto-generated method stub
		teamManager = new TeamManager();
		teamManager.loadFromFile();
	}

	public MainFrame() {
		super();

		addKeyListener(this);
		addMouseWheelListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setSize(I_WIDTH, I_HEIGHT);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		card = new CardLayout();

		cardLayoutController = new JPanel();
		cardLayoutController.setBackground(Color.LIGHT_GRAY);
		cardLayoutController.setLayout(card);
		contentPane.add(cardLayoutController);

		logInPanel = new LogInPanel();
		cardLayoutController.add(logInPanel, "loginpanel");

		all = new All();
		cardLayoutController.add(all, "all");

		setTopPanel("loginpanel");

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (topPanel.equals("loginpanel")) {
			logInPanel.keyPressed(e);
		} else if (topPanel.equals("all")) {
			all.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		if (topPanel.equals("loginpanel")) {
			logInPanel.keyTyped(e);
		} else if (topPanel.equals("all")) {
			all.keyTyped(e);
		}

	}

	public static void setTopPanel(String str) {
		card.show(cardLayoutController, str);
		topPanel = str;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

		if (topPanel.equals("all")) {
			all.mouseWheelMoved(e);
		}

	}

}
