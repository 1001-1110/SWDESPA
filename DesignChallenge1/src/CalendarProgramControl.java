
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

	public void updateViews(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		String dateFilter = new String();
		dateFilter += currentSelectedYear+"-";
		
		if(currentSelectedMonth < 10)
			dateFilter += "0"+(currentSelectedMonth+1)+"-";
		else
			dateFilter += (currentSelectedMonth+1)+"-";
		
		if(currentSelectedDay < 10)
			dateFilter += "0"+currentSelectedDay;
		else
			dateFilter += currentSelectedDay;
		
		cm.notifyViews(dateFilter);
	}

	public void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cm.notifyDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}

	public void refreshCalendar(int monthToday, int yearToday) {
		cm.notifyCalendar(monthToday, yearToday);
	}
	
}
