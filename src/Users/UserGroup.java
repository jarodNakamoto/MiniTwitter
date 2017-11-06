package Users;

import interfaces.Component;
import interfaces.Composite;
import java.util.ArrayList;

/**
 * Write a description of class UserGroup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserGroup implements Composite
{
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
		
	}

	@Override
	public void remove(Component c) {
		
	}

	@Override
	public Component getChild(int i) {
		return null;
	}

}
