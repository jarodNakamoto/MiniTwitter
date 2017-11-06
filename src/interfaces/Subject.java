package interfaces;


/**
 * Write a description of interface Subject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Subject
{
    void Attach(Observer o);
    void Detach(Observer o);
    void Notify();
}
