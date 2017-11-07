package Users;
import java.util.ArrayList;

import interfaces.*;
import ui.AdminControlPanel;

/**
 * Write a description of class User here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class User implements Subject, Observer, Leaf, Visitor, Element
{
	private String id;
	private ArrayList<User> followers;
	private ArrayList<String> newsFeed;
	private String mostRecentTweet;
	

	public User(){
		followers = new ArrayList<User>();
		newsFeed = new ArrayList<String>();
    }
    
	public void tweet(String message) {
		mostRecentTweet = message;
		newsFeed.add(0,message);
		Notify();
	}
	
	public void follow(String userId) {
		try {
			User us = AdminControlPanel.getInstance().findUser(userId);
			if(us != null && !userId.equals(id)) {
				us.Attach(this);	
			}
			else {
				System.out.println("User ID Invalid...");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//leaf methods from composite pattern
    @Override
	public void operation() {
		
	}

    //subject methods
    //someone subscribes to us
    public void Attach(Observer o){
		for(int i = 0; i < followers.size(); i++) {
			if(followers.get(i).getId().equals(o.toString())) {
				System.out.println("Already Subscribed!");
				return;
			}
		}
		followers.add((User) o);
    }
    
    //someone un-subscribes from us
    public void Detach(Observer o){
    	followers.remove(o);
    }
    
    public void Notify(){
    	for(int i = 0; i < followers.size(); i++) {
			followers.get(i).update(mostRecentTweet);
		}
    	AdminControlPanel.getInstance().updateScreen();
    }
    
    //observer methods
    //when someone else tweets
    public void update(String subjectState){
    	newsFeed.add(0, subjectState);
    }

  //visitor methods
    //we are visiting 
  	@Override
  	public void visitUserGroup(UserGroup g) {
  		g.add(this);
  	}


  	@Override
  	public void visitUser(User u) {
  		u.follow(id);
  	}

  	//element methods
  	@Override
  	public void accept(Visitor v) {
  		v.visitUser(this);
  	}
  	
    //getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<User> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}

	public ArrayList<String> getNewsFeed() {
		return newsFeed;
	}

	public void setNewsFeed(ArrayList<String> newsFeed) {
		this.newsFeed = newsFeed;
	}
	
	@Override
	public String toString() {
		return id;
	}

	public String getMostRecentTweet() {
		return mostRecentTweet;
	}
	
	public double getCountOfPositiveMessages(String[] positiveWords) {
		int count = 0;
		for(int i = 0; i < newsFeed.size(); i++) {
			if(messageContainsPositiveString(positiveWords, newsFeed.get(i))){
				count++;
			}
		}
		return (double)count;
	}
	
	public boolean messageContainsPositiveString(String[] arr, String str) {
		
		for(int i = 0; i < arr.length; i++) {
			if(str.toLowerCase().indexOf(arr[i]) != -1){
				return true;
			}
		}
		
		return false;
	}
}
