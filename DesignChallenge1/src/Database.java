import java.util.List;

public interface Database {

	public abstract List<Occasion> getOccasions(String dateFilter);

	public abstract List<Occasion> getMonthOccasion(String monthFilter);	

	public abstract List<Occasion> getWeekOccasion(String firstFilter, String secondFilter);		
	
	public abstract List<Occasion> getWeekOccasion(String firstFilter, String secondFilter, String typeFilter);
	
	public abstract List<Occasion> getOccasions(String dateFilter, String typeFilter);
	
	public abstract boolean addOccasion(Occasion occ);

	public abstract void updateIsDone(int occasionID, boolean isDone);
	
	public abstract boolean updateEventIsDone(String dateFilter);
	
	public abstract void deleteOccasion(int occasionID);
	
}
