package Users;

import interfaces.ProjectComponent;
import interfaces.Composite;
import interfaces.Element;
import interfaces.Visitor;

import java.util.ArrayList;

/**
 * Write a description of class UserGroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserGroup implements Composite, Element, Visitor
{
	private String id;
    private ArrayList<ProjectComponent> members;

	/**
     * Constructor for objects of class UserGroup
     */
    public UserGroup()
    {
        members = new ArrayList<ProjectComponent>();
    }
    
    //useful methods
    public ProjectComponent findById(String id) {
    	ProjectComponent result = null;
    	System.out.println("Searching for " + id);
    	for(int i = 0; i < members.size(); i++) {
    		ProjectComponent c = members.get(i); 
    		//System.out.println(c.toString());
    		if(c.toString().equals(id))
    			return c;
    		else if(c instanceof UserGroup) {
    			result = ((UserGroup) c).findById(id);
    			if(result != null)
    				return result;
    		}
    	}
    	return result;
    }

    
	//Composite methods
    @Override
	public void operation() {
		for(int i = 0; i < members.size(); i++) {
			members.get(i).operation();
		}
	}

	@Override
	public void add(ProjectComponent c) {
		if(findById(c.toString()) == null) {
			members.add(c);
			System.out.println("added " + c.toString()+ " to group " + toString());
		}
		else {
			System.out.println("already in a thing");
		}
	}

	@Override
	public void remove(ProjectComponent c) {
		if(members.contains(c)) {
			members.remove(c);
		}
	}

	@Override
	public ProjectComponent getChild(int i) {
		if(i > -1 && i < members.size()) {
			return members.get(i);
		}
		return null;
	}

	
	//visitor methods
	@Override
	public void visitUserGroup(UserGroup g) {
		add(g);
	}


	@Override
	public void visitUser(User u) {
		add(u);
	}

	//element methods
	@Override
	public void accept(Visitor v) {
		v.visitUserGroup(this);
	}


	//gets and sets id
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<ProjectComponent> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<ProjectComponent> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return id;
	}
	public int getUserTotal() {
		int count = 0;
		for(int i = 0; i < members.size(); i++) {
			ProjectComponent c = members.get(i);
			if(c instanceof UserGroup) {
				count += ((UserGroup)c).getUserTotal();
			}
			else {
				count++;
			}
		}
		return count;
	}
	
	public int getUserGroupTotal() {
		int count = 0;
		for(int i = 0; i < members.size(); i++) {
			ProjectComponent c = members.get(i);
			if(c instanceof UserGroup) {
				count++;
				count += ((UserGroup)c).getUserGroupTotal();
			}
		}
		return count;
	}

	public int findMessageTotal() {
		int count = 0;
		for(int i = 0; i < members.size(); i++) {
			ProjectComponent c = members.get(i);
			if(c instanceof UserGroup) {
				count += ((UserGroup)c).getUserGroupTotal();
			}
			else {
				count += ((User)c).getNewsFeed().size();
				System.out.println(((User)c).getNewsFeed().size());
			}
		}
		return count;
	}

	public double findPositivePercentage() {
		String[] happyWords = {"happy","good","great","excellent"};
		double positiveCount = 0;
		
		for(int i = 0; i < members.size(); i++) {
			ProjectComponent c = members.get(i);
			if(c instanceof UserGroup) {
				positiveCount += ((UserGroup)c).getUserGroupTotal();
			}
			else {
				positiveCount += ((User)c).getCountOfPositiveMessages(happyWords);
			}
		}
		return positiveCount/findMessageTotal();
	}
}
