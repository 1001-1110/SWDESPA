import java.util.List;

public interface Database {

	public abstract List<Occasion> getOccasions(String dateFilter);
	
	public abstract List<Occasion> getOccasions(String dateFilter, String typeFilter);
	
	public abstract boolean addOccasion(Occasion occ);
	
}
