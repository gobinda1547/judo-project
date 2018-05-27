package com.gobinda.design;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddingTeam extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField vn;
	private JTextField tn;

	private JLabel lbl;

	public AddingTeam() {

		super("Add Team");
		setSize(520, 410);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("University Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(76, 61, 307, 29);
		contentPane.add(lblNewLabel);

		vn = new JTextField();
		vn.setFont(new Font("Tahoma", Font.BOLD, 18));
		vn.setBounds(76, 92, 307, 29);
		contentPane.add(vn);
		vn.setColumns(10);

		JLabel lblTeamName = new JLabel("Team Name:");
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTeamName.setBounds(76, 132, 307, 29);
		contentPane.add(lblTeamName);

		tn = new JTextField();
		tn.setFont(new Font("Tahoma", Font.BOLD, 18));
		tn.setColumns(10);
		tn.setBounds(76, 162, 307, 29);
		contentPane.add(tn);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String s1 = vn.getText();
				String s2 = tn.getText();

				if (s1.trim().length() == 0) {
					lbl.setText("Invalid University Name");
					return;
				}
				if (s2.trim().length() == 0) {
					lbl.setText("Invalid Team Name");
					return;
				}

				MainFrame.teamManager.addTeam(s1, s2);
				lbl.setText("Successfully Added");
				vn.setText("");
				tn.setText("");
				All.tv.repaint();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(297, 214, 86, 29);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.addingTeam.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(170, 214, 117, 29);
		contentPane.add(btnCancel);

		lbl = new JLabel("");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl.setBounds(10, 11, 484, 29);
		contentPane.add(lbl);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vn.setText("");
			}
		});
		btnNewButton_1.setBounds(386, 92, 27, 29);
		contentPane.add(btnNewButton_1);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tn.setText("");
			}
		});
		button.setBounds(386, 162, 27, 29);
		contentPane.add(button);
	}
}
