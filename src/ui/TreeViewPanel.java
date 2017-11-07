package ui;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Users.*;
import interfaces.ProjectComponent;

public class TreeViewPanel extends  JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserGroup root;
	
	public TreeViewPanel(UserGroup root) {
		this.root = root;
		update();

        setVisible(true);
	}
	
	public void update() {
		String[] elements = traverseTree(); 
		JList<String> myList = new JList<String>(elements);
		JScrollPane scrollPane = new JScrollPane(myList);
		add(scrollPane);
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
			str = indent + c.toString();
			treeTraversed.add(str);
			if(c instanceof UserGroup) {
				addLevelToTree(((UserGroup)c).getMembers(), treeTraversed, " " + indent);
			}
		}
	}
}
