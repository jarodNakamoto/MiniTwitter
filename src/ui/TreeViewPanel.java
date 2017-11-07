package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Users.*;
import interfaces.ProjectComponent;
import interfaces.ProjectPanel;

public class TreeViewPanel extends  JPanel implements ProjectPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserGroup root;
	private JScrollPane myScrollPane;
	
	public TreeViewPanel(UserGroup root) {
		this.root = root;
		setLayout(new BorderLayout());
		updateScreen();
        setVisible(true);
	}
	
	public void updateScreen() {
		String[] elements = traverseTree(); 
		JList<String> myList = new JList<String>(elements);
		if(myScrollPane != null)
			remove(myScrollPane);
		myScrollPane = new JScrollPane(myList);
		add(myScrollPane, BorderLayout.CENTER);
	}
	
	
	
	
	private String[] traverseTree(){
		ArrayList<String> treeTraversed = new ArrayList<String>();
		treeTraversed.add(root.getId());
		
		addLevelToTree(root.getMembers(), treeTraversed, "-");
		
		String[] treeCompleted = new String[treeTraversed.size()];
		for(int i = 0; i < treeTraversed.size(); i++) {
			treeCompleted[i] = treeTraversed.get(i);
		}
		return treeCompleted;
	}
	
	private void addLevelToTree(ArrayList<ProjectComponent> arr, ArrayList<String> treeTraversed, String indent) {
		String str;
		for(int i = 0; i < arr.size(); i++) {
			ProjectComponent c = arr.get(i);
			str = indent;
			if(c instanceof UserGroup) {
				str += "(GROUP) " + c.toString();
				treeTraversed.add(str);
				addLevelToTree(((UserGroup)c).getMembers(), treeTraversed, " " + indent);
			}
			else {
				str += c.toString();
				treeTraversed.add(str);
			}
		}
	}
}
