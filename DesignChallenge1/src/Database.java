import java.util.List;

public interface Database {

	public abstract List<Occasion> getOccasions();
	
	public abstract void addOccasion(Occasion occ);
	
}
