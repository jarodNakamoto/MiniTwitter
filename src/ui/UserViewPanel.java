package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Users.User;
import util.Util;

/**
 * Write a description of class UserViewPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserViewPanel extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//gui stuff
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    
	// references the user 
    private User user;
	private GridBagConstraints c;

    /**
     * Constructor for objects of class UserViewPanel
     */
    public UserViewPanel(User user)
    {
        this.user = user;
        setTitle(user.getId());
        setLayout(new GridBagLayout());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		c = new GridBagConstraints();
		
		JTextField userID = new JTextField("User ID");
		JButton followUser = new JButton("Follow User");
		setUpRowWithTwoThings(userID, followUser, 0);
		
		//list of followers
		ArrayList<User> followers = user.getFollowers();
		String[] f = Util.convertToArray(followers);
		JList jList = new JList(f);
		JScrollPane scrollPane = new JScrollPane(jList);
		setUpLongComponentRow(scrollPane, 3);
		
		JTextField tweetMessage = new JTextField("Tweet Message");	
		JButton postTweet = new JButton("Post Tweet");
		setUpRowWithTwoThings(tweetMessage, postTweet, 5);
		
		//list of followers
		ArrayList<String> feed = user.getNewsFeed();
		String[] f2 = Util.convertToArray(feed);
		JList jList2 = new JList(f2);
		JScrollPane scrollPane2 = new JScrollPane(jList2);
		setUpLongComponentRow(scrollPane2, 6);
		
		pack();
		setVisible(true);
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
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
