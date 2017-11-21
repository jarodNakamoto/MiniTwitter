package ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
    private JButton updateTime;
    private JTextField creationTime;
    
    
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		c = new GridBagConstraints();
		
		userID = new JTextField("User ID");
		followUser = new JButton("Follow User");
		followUser.addActionListener(new FollowButtonListener());
		setUpRowWithTwoThings(userID, followUser, 0);
		
		tweetMessage = new JTextField("Tweet Message");	
		postTweet = new JButton("Post Tweet");
		postTweet.addActionListener(new TweetButtonListener());
		setUpRowWithTwoThings(tweetMessage, postTweet, 5);
		
		long timeMade = user.getCreationTime();
		Date formattedTime = new Date(timeMade);
		creationTime = new JTextField("Creation Time: " + formattedTime.toString());
		
		updateTime = new JButton("Display Update Time");
		updateTime.addActionListener(new DisplayUpdateTimeButtonListener());;
		
		setUpRowWithTwoThings(creationTime, updateTime, 6);
		
		updateScreen();
    }
    
    private void updateFollowing() {
    	//list of followers
		ArrayList<User> followers = (ArrayList<User>)(user.getFollowers().clone());
		User temp = new User();
		temp.setId("Current Following:");
		followers.add(0, temp);
		String[] f = Util.convertToArray(followers);
		JList<String> jList = new JList<String>(f);
		if(currentFollowing != null) {
			remove(currentFollowing);
		}
		currentFollowing = new JScrollPane(jList);
		setUpLongComponentRow(currentFollowing, 3);
    }
    
    private void updateTweets() {
    	//list of tweets
		ArrayList<String> feed = (ArrayList<String>)(user.getNewsFeed().clone());
		feed.add(0, "News Feed: ");
		String[] f2 = Util.convertToArray(feed);
		JList<String> jList2 = new JList<String>(f2);
		if(tweets != null) {
			remove(tweets);
		}
		tweets = new JScrollPane(jList2);
		setUpLongComponentRow(tweets, 7);
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
		pack();
		setVisible(true);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//button listeners
	private class TweetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	String message = tweetMessage.getText();
        	user.tweet("- " + user.getId() + ": " + message);
        	updateScreen();
        	AdminControlPanel.getInstance().updateScreen();
        }
    }
	private class FollowButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	String id = userID.getText();
        	user.follow(id);
        	updateScreen();
        	AdminControlPanel.getInstance().updateScreen();
        }
    }
	
	private class DisplayUpdateTimeButtonListener implements ActionListener{
		private boolean isDisplaying = false;
		
        public void actionPerformed(ActionEvent e){
        	if(isDisplaying) {
        		updateTime.setText("Show Updatetime");
        	}
        	else {
        		Date formattedTime = new Date(user.getLastUpdateTime());
        		updateTime.setText("UpdateTime: " + formattedTime.toString());
        	}
        	isDisplaying = !isDisplaying;
        }
    }
}