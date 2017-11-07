package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EastControlPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;
	
	
	public EastControlPanel() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		JTextField userID = new JTextField("User ID");
		JButton addUser = new JButton("Add User");
		setUpRowWithTwoThings(userID, addUser, 0);
		
		JTextField groupID = new JTextField("Group ID");
		JButton addGroup = new JButton("Add Group");
		setUpRowWithTwoThings(groupID, addGroup, 1);
		
		JButton openUserView = new JButton("Open User View");
		setUpLongComponentRow(openUserView, 3);
		
		//top padding
		c.insets = new Insets(320,0,0,0);
		
		JButton userTotal = new JButton("Show User Total"); 	
		JButton groupTotal = new JButton("Show Group Total");
		setUpRowWithTwoThings(userTotal, groupTotal, 5);
		
		//no padding
		c.insets = new Insets(0,0,0,0);
		
		JButton msgTotal = new JButton("Show Messages Total");
		JButton positivePercentage = new JButton("Show Positive Percentage");
		setUpRowWithTwoThings(msgTotal, positivePercentage, 6);
	}
	
	private void setUpRowWithTwoThings(Component c1, Component c2, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.weightx = 0.0;
		c.gridwidth= 1;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = y;
		add(c1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = y;
		add(c2, c);
	}
	
	private void setUpLongComponentRow(Component comp, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth= 3; //make it long
		c.gridx = 0;
		c.gridy = y;
		add(comp, c);
	}
}