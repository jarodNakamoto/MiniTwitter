package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Users.User;
import interfaces.ProjectPanel;
import util.Util;

/**
 * Write a description of class UserViewPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserViewPanel extends JFrame implements ProjectPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//gui stuff
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    private JTextField userID;
    private JButton followUser;
    private JTextField tweetMessage;
    private JButton postTweet;
    private JScrollPane currentFollowing;
    private JScrollPane tweets;
    
    
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
		
		userID = new JTextField("User ID");
		followUser = new JButton("Follow User");
		followUser.addActionListener(new FollowButtonListener());
		setUpRowWithTwoThings(userID, followUser, 0);
		
		updateFollowing();
		
		tweetMessage = new JTextField("Tweet Message");	
		postTweet = new JButton("Post Tweet");
		postTweet.addActionListener(new TweetButtonListener());
		setUpRowWithTwoThings(tweetMessage, postTweet, 5);
		
		updateTweets();
		
		pack();
		setVisible(true);
    }
    
    private void updateFollowing() {
    	//list of followers
		ArrayList<User> followers = (ArrayList<User>)(user.getFollowers().clone());
		User temp = new User();
		temp.setId("Current Following:");
		followers.add(0, temp);
		String[] f = Util.convertToArray(followers);
		JList<String> jList = new JList<String>(f);
		currentFollowing = new JScrollPane(jList);
		setUpLongComponentRow(currentFollowing, 3);
		pack();
		setVisible(true);
    }
    
    private void updateTweets() {
    	//list of tweets
		ArrayList<String> feed = (ArrayList<String>)(user.getNewsFeed().clone());
		feed.add(0, "News Feed: ");
		String[] f2 = Util.convertToArray(feed);
		JList<String> jList2 = new JList<String>(f2);
		tweets = new JScrollPane(jList2);
		setUpLongComponentRow(tweets, 6);
		pack();
		setVisible(true);
    }

    private void setUpRowWithTwoThings(Component c1, Component c2, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
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
		updateTweets();
		updateFollowing();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//button listeners
	private class TweetButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String message = tweetMessage.getText();
        	user.tweet("- " + user.getId() + ": " + message);
        	updateScreen();
        }
    }
	private class FollowButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	String id = userID.getText();
        	user.follow(id);
        	updateScreen();
        }
    }
}