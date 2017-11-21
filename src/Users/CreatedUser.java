package Users;

import interfaces.CreationTimeable;

public abstract class CreatedUser implements CreationTimeable {
	private long creationTime;
	
	public CreatedUser() {
		creationTime = System.currentTimeMillis();
	}
	
	public long getCreationTime() {
		return creationTime;
	}
}
