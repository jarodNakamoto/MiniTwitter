package Users;

import interfaces.Component;
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
    private ArrayList<Component> members;

    /**
     * Constructor for objects of class UserGroup
     */
    public UserGroup()
    {
        members = new ArrayList<Component>();
    }

    
	//Composite methods
    @Override
	public void operation() {
		for(int i = 0; i < members.size(); i++) {
			members.get(i).operation();
		}
	}

	@Override
	public void add(Component c) {
		if(!members.contains(c)) {
			members.add(c);
		}
	}

	@Override
	public void remove(Component c) {
		if(members.contains(c)) {
			members.remove(c);
		}
	}

	@Override
	public Component getChild(int i) {
		if(i > -1 && i < members.size()) {
			return members.get(i);
		}
		return null;
	}

	
	//visitor methods
	@Override
	public void visitUserGroup(UserGroup g) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void visitUser(User u) {
		// TODO Auto-generated method stub
		
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
}
