package interfaces;

public interface Composite extends Component {
	void operation();
	void add(Component c);
	void remove(Component c);
	Component getChild(int i);
}
