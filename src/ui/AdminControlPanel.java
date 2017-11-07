package ui;

import java.awt.*;
import javax.swing.*;

import Users.UserGroup;

public class AdminControlPanel extends JFrame
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
    private JPanel westPanel;
    private JPanel eastPanel;
    
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
    	eastPanel = new EastControlPanel(root);
    	
    	//add panels to content page
    	add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
    }
    
    public static int getUserTotal() {
    	return 0;
    }
    
    public static int getGroupTotal() {
    	return 0;
    }
    
    public static int getMessageTotal() {
    	return 0;
    }
    
    public static double getPositivePercentage() {
    	return 0;
    }
  
}
