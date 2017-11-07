package ui;

import java.awt.*;
import javax.swing.*;

import Users.User;
import Users.UserGroup;
import interfaces.ProjectComponent;
import interfaces.ProjectPanel;
import interfaces.Visitor;

public class AdminControlPanel extends JFrame implements ProjectPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//singleton stuff
    private static AdminControlPanel instance;
    
    //gui stuff
    private final int WINDOW_WIDTH = 1280;
    private final int WINDOW_HEIGHT = 720;
    
    //panels
    private ProjectPanel westPanel;
    private ProjectPanel eastPanel;
    
    //user things
    private UserGroup root;
    
    private AdminControlPanel() {
    	// set the title
        setTitle("MiniTwitter-Jarod Nakamoto");
        
        //specify what happens when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create BorderLayout manager
        setLayout(new GridLayout());
        
        // set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //
        root = new UserGroup();
        root.setId("Root");
        
        //setup window
        buildPanels();
        
        //display window
        setVisible(true);
    }
    
    public static AdminControlPanel getInstance() {
    	if(instance == null) {
    		instance = new AdminControlPanel();
    	}
    	return instance;
    }
    
    private void buildPanels() {
    	//build panels
    	westPanel = new TreeViewPanel(root);
    	eastPanel = new EastControlPanel();
    	
    	//add panels to content page
    	add((JPanel)westPanel, BorderLayout.WEST);
        add((JPanel)eastPanel, BorderLayout.EAST);
    }
    
    public void updateScreen() {
    	westPanel.updateScreen();
    	eastPanel.updateScreen();
    	pack();
    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	setVisible(true);
    }
    
    public int getUserTotal() {
    	return root.getUserTotal();
    }
    
    public int getGroupTotal() {
    	return root.getUserGroupTotal();
    }
    
    public int getMessageTotal() {
    	return root.findMessageTotal();
    }
    
    public double getPositivePercentage() {
    	return root.findPositivePercentage();
    }
    
    public User findUser(String userId) {
    	ProjectComponent result = root.findById(userId);
    	System.out.println(result);
    	if(result != null && result instanceof User) {
    		return (User)result;
    	}
    	return null;
    }
    
    public void add(ProjectComponent p) {
    	root.add(p);
    	updateScreen();
    }

	public void addToGroup(String groupId, String id) {
		boolean updated = false;
		ProjectComponent group = root.findById(groupId);
		ProjectComponent toAdd = root.findById(id);
		if(group instanceof UserGroup || group != null) {
			if(toAdd != null) {
				//we have valid thing and group, so add thing to group
				updated = true;
				root.remove(toAdd);
				((UserGroup)group).accept((Visitor)toAdd);
			}
			else {
				System.out.println("Invalid ID to Add");
			}
		}
		else {
			System.out.println("Invalid Group ID");
		}
		//display changes if they are made
		if(updated)
			updateScreen();
		else
			System.out.println("Not updated");
	}
}
