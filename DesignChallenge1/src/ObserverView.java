import java.util.List;

public interface ObserverView {

	public abstract void update(List<Occasion>occasions);
	
	public abstract void deselect();
}
