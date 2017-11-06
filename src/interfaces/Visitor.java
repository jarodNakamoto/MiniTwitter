package interfaces;

import Users.User;
import Users.UserGroup;

public interface Visitor {
	void visitUserGroup(UserGroup g);
	void visitUser(User u);
}
