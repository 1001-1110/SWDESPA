
import java.awt.Color;
import java.util.Calendar;

public class SMS {
	private String eventName;
	private Calendar date;
	private Color color;
	
	public SMS(String eventName, Calendar date, Color color) {
		this.eventName = eventName;
		this.date = date;
		this.color = color;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return eventName + "\n" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.YEAR);
	}
}
