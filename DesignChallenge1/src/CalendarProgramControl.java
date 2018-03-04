
public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	
	public void addOccasion(String info, String date, String timeFrom, String timeTo, boolean isEvent, boolean isTask) {
		String[] splitDate = date.split("/");
		date = splitDate[2]+"-"+splitDate[0]+"-"+splitDate[1];
		
		if(isEvent) {
			cm.writeDatabase(new Event(info,date+" "+timeFrom,date+" "+timeTo,false));
		}else if(isTask) {
			cm.writeDatabase(new Task(info,date+" "+timeFrom+":00",date+" "+timeTo+":00",false));
		}
	}	
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}
	
}
