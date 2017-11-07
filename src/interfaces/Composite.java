package interfaces;

public interface Composite extends ProjectComponent {
	void operation();
	void add(ProjectComponent c);
	void remove(ProjectComponent c);
	ProjectComponent getChild(int i);
}
