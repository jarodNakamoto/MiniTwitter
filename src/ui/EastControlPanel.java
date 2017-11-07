package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Users.User;
import Users.UserGroup;
import interfaces.ProjectComponent;
import interfaces.ProjectPanel;

public class EastControlPanel extends JPanel implements ProjectPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c;
	private JButton addUser;
	private JTextField userID;
	private JTextField groupID;
	private JButton addGroup;
	private JButton openUserView;
	private JButton userTotal;
	private JButton groupTotal;
	private JButton msgTotal;
	private JButton positivePercentage;
	
	//AdminControlPanel.getInstance()
	
	public EastControlPanel() {
		
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		userID = new JTextField("User ID");
		addUser = new JButton("Add User");
		addUser.addActionListener(new AddUserButtonListener());
		setUpRowWithTwoThings(userID, addUser, 0);
		
		groupID = new JTextField("Group ID");
		addGroup = new JButton("Add Group");
		addGroup.addActionListener(new AddGroupButtonListener());
		setUpRowWithTwoThings(groupID, addGroup, 1);
		
		openUserView = new JButton("Open User View");
		openUserView.addActionListener(new OpenUserViewButtonListener());
		setUpLongComponentRow(openUserView, 3);
		
		//top padding
		c.insets = new Insets(320,0,0,0);
		
		userTotal = new JButton("Show User Total");
		userTotal.addActionListener(new UserTotalButtonListener());
		groupTotal = new JButton("Show Group Total");
		groupTotal.addActionListener(new GroupTotalButtonListener());
		setUpRowWithTwoThings(userTotal, groupTotal, 5);
		
		//no padding
		c.insets = new Insets(0,0,0,0);
		
		msgTotal = new JButton("Show Messages Total");
		msgTotal.addActionListener(new MessageTotalButtonListener());
		positivePercentage = new JButton("Show Positive Percentage");
		positivePercentage.addActionListener(new PositivePercentageButtonListener());
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
	
	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		
	}

	//button listeners
	private class AddUserButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String userField = userID.getText();
    		User us = new User();
    		us.setId(userField);
    		AdminControlPanel.getInstance().add(us);
        }
    }
	
	private class AddGroupButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String groupField = groupID.getText();
    		UserGroup grp = new UserGroup();
    		grp.setId(groupField);
    		AdminControlPanel.getInstance().add(grp);
        }
    }
	private class OpenUserViewButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String userField = userID.getText();
        	
    		ProjectComponent c = AdminControlPanel.getInstance().findUser(userField);
    		if(c != null) {
    			new UserViewPanel((User)c);	
    		}
    		else {
    			User us = new User();
        		us.setId(userField);
        		AdminControlPanel.getInstance().add(us);
        		new UserViewPanel(us);
    		}
        }
    }
	private class UserTotalButtonListener implements ActionListener
    {
		private boolean isDisplaying = false;
		
        public void actionPerformed(ActionEvent e)
        {
        	if(isDisplaying) {
        		userTotal.setText("Show User Total");
        	}
        	else {
        		userTotal.setText("User Total: " + AdminControlPanel.getInstance().getUserTotal());
        	}
        	isDisplaying = !isDisplaying;
        }
    }
	private class GroupTotalButtonListener implements ActionListener
    {
		private boolean isDisplaying = false;
		
        public void actionPerformed(ActionEvent e)
        {
        	if(isDisplaying) {
        		groupTotal.setText("Show Group Total");
        	}
        	else {
        		groupTotal.setText("Group Total: " + AdminControlPanel.getInstance().getGroupTotal());
        	}
        	isDisplaying = !isDisplaying;
        }
    }
	private class MessageTotalButtonListener implements ActionListener
    {
		private boolean isDisplaying = false;
		
        public void actionPerformed(ActionEvent e)
        {
        	if(isDisplaying) {
        		msgTotal.setText("Show Message Total");
        	}
        	else {
        		msgTotal.setText("Message Total: " + AdminControlPanel.getInstance().getMessageTotal());
        	}
        	isDisplaying = !isDisplaying;
        }
    }
	private class PositivePercentageButtonListener implements ActionListener
    {
		private boolean isDisplaying = false;
		
        public void actionPerformed(ActionEvent e)
        {
        	if(isDisplaying) {
        		positivePercentage.setText("Show Positive Percentage");
        	}
        	else {
        		positivePercentage.setText("Positive Percentage: " + AdminControlPanel.getInstance().getPositivePercentage());
        	}
        	isDisplaying = !isDisplaying;
        }
    }
}