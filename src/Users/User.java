package Users;
import java.util.ArrayList;
import java.util.Stack;

import interfaces.*;

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
	private Stack<String> newsFeed;
	
	public User(){
		followers = new ArrayList<User>();
		newsFeed = new Stack<String>();
    }
    
	//leaf methods
    @Override
	public void operation() {
		
	}

    //subject methods
    //someone subscribes to us
    public void Attach(Observer o){
    	followers.add((User) o);
    }
    
    //someone un-subscribes from us
    public void Detach(Observer o){
    	followers.remove(o);
    }
    
    public void Notify(){
    	for(int i = 0; i < followers.size(); i++) {
			followers.get(i).update();
		}
    }
    
    //observer methods
    //when someone else tweets
    public void update(){
    	
    	//newsFeed.push();
    }

  //visitor methods
  	@Override
  	public void visitUserGroup(UserGroup g) {
  		
  	}


  	@Override
  	public void visitUser(User u) {
  		
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

	public Stack<String> getNewsFeed() {
		return newsFeed;
	}

	public void setNewsFeed(Stack<String> newsFeed) {
		this.newsFeed = newsFeed;
	}
}
