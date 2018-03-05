import java.util.List;

public interface Database {

	public abstract List<Occasion> getOccasions(String dateFilter);
	
	public abstract boolean addOccasion(Occasion occ);
	
}
