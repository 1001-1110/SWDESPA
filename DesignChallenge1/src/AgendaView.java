import java.util.List;

public interface AgendaView {
	
	public abstract String getSelectedOccasion();
	
	public void refreshInfoTable(List<Occasion>occasions);
	
}
