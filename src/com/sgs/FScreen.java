package com.sgs;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

//FScreen - FScreen class for the first screen for the UI, In this screen UI asks user for number of users and previously existing scores
public class FScreen {

	private JFrame frame;
	
	//main - this is the starting point of the execution of first screen
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FScreen window = new FScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * FScreen - This method initializes the first screen by calling initialize method
	 */
	public FScreen() {
		initialize();
	}

	/**
	 * initialize - This method initialize the contents of the frame.
	 * It asks for number of team members and previously entered score
	 * As soon as submit button is pressed a transition is done to second screen.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Integer[] members = new Integer[] { 2, 3, 4, 5, 6, 7 };
		JLabel lblNewLabel = new JLabel();
		JComboBox<Integer> comboBox = new JComboBox(members);
		comboBox.setBounds(269, 43, 45, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblEnterTeamMembers = new JLabel("Number of Team Members");
		lblEnterTeamMembers.setBounds(77, 46, 180, 16);
		frame.getContentPane().add(lblEnterTeamMembers);
		
		JCheckBox chckbxPreviouslyEntered = new JCheckBox("Previously Entered ?");
		chckbxPreviouslyEntered.setBounds(144, 93, 144, 25);
		frame.getContentPane().add(chckbxPreviouslyEntered);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
				SScreen.showSecondScreen((Integer) comboBox.getSelectedItem(),chckbxPreviouslyEntered.isSelected());
				
			}
		});
		btnSubmit.setBounds(160, 146, 97, 25);
		frame.getContentPane().add(btnSubmit);
		
		
		lblNewLabel.setBounds(106, 203, 232, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
