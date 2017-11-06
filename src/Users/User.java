package Users;
import interfaces.*;

/**
 * Write a description of class User here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class User implements Subject, Observer, Leaf
{
	

	public User(){
    
    }
    
	//leaf methods
    @Override
	public void operation() {
		
	}

    //subject methods
    public void Attach(Observer o){
    
    }
    public void Detach(Observer o){
    
    }
    public void Notify(){
    
    }
    
    //observer methods
    public void update(){
    
    }
}
