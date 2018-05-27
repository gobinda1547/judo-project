package com.gobinda.design;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditTeam extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JLabel lbl;

	public EditTeam() {

		super("Edit Team");

		setBounds(100, 100, 523, 456);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Change University Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 77, 487, 25);
		contentPane.add(lblNewLabel);

		JLabel lblChangeTeamName = new JLabel("Change Team Name:");
		lblChangeTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeTeamName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChangeTeamName.setBounds(10, 194, 487, 25);
		contentPane.add(lblChangeTeamName);

		t1 = new JTextField();
		t1.setFont(new Font("Tahoma", Font.BOLD, 15));
		t1.setBounds(129, 109, 224, 25);
		contentPane.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setFont(new Font("Tahoma", Font.BOLD, 15));
		t2.setColumns(10);
		t2.setBounds(129, 145, 224, 25);
		contentPane.add(t2);

		t3 = new JTextField();
		t3.setFont(new Font("Tahoma", Font.BOLD, 15));
		t3.setColumns(10);
		t3.setBounds(129, 230, 224, 25);
		contentPane.add(t3);

		t4 = new JTextField();
		t4.setFont(new Font("Tahoma", Font.BOLD, 15));
		t4.setColumns(10);
		t4.setBounds(129, 266, 224, 25);
		contentPane.add(t4);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t1.setText("");
			}
		});
		btnNewButton.setBounds(356, 109, 30, 25);
		contentPane.add(btnNewButton);

		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t2.setText("");
			}
		});
		button.setBounds(356, 145, 30, 25);
		contentPane.add(button);

		JButton button_1 = new JButton("New button");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t3.setText("");
			}
		});
		button_1.setBounds(356, 230, 30, 25);
		contentPane.add(button_1);

		JButton button_2 = new JButton("New button");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t4.setText("");
			}
		});
		button_2.setBounds(356, 266, 30, 25);
		contentPane.add(button_2);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String s1 = t1.getText();
				String s2 = t2.getText();
				String s3 = t3.getText();
				String s4 = t4.getText();

				if (s1.trim().length() == 0) {
					lbl.setText("Invalid University Name 1!");
					return;
				}
				if (s2.trim().length() == 0) {
					lbl.setText("Invalid University Name 2!");
					return;
				}
				if (s3.trim().length() == 0) {
					lbl.setText("Invalid Team Name 1!");
					return;
				}
				if (s4.trim().length() == 0) {
					lbl.setText("Invalid Team Name 2!");
					return;
				}

				boolean ans = MainFrame.teamManager.deleteTeam(s1, s3);

				if (!ans) {
					lbl.setText("Team Not Found!");
					return;
				}

				MainFrame.teamManager.addTeam(s2, s4);
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				All.tv.repaint();

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(318, 317, 68, 31);
		contentPane.add(btnNewButton_1);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.editTeam.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(218, 317, 90, 31);
		contentPane.add(btnCancel);

		lbl = new JLabel("");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl.setBounds(10, 25, 487, 25);
		contentPane.add(lbl);
	}
}
