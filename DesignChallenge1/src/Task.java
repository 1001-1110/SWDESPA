import java.awt.Color;

public class Task extends Occasion{
	
	String durationFrom;
	String durationTo;
	boolean isDone;
	
	public Task(String info, String durationFrom, String durationTo, boolean isDone) {
		super(info, Color.BLUE);
		this.durationFrom = durationFrom;
		this.durationTo = durationTo;
		this.isDone = isDone;
	}

	public String getDurationFrom() {
		return durationFrom;
	}

	public String getDurationTo() {
		return durationTo;
	}

	public boolean getIsDone() {
		return isDone;
	}
}