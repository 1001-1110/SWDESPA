
public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	
	public void addOccasion(String info, String date, String timeFrom, String timeTo, boolean isEvent, boolean isTask) {
		String[] splitDate = date.split("/");
		date = splitDate[2]+"-"+splitDate[0]+"-"+splitDate[1];
		
		info = info.replaceAll("<html>", "");
		info = info.replaceAll("</html>", "");
		info = info.replaceAll("<s>", "");
		info = info.replaceAll("</s>", "");
		
		if(isEvent) {
			cm.writeDatabase(new Event(info,date+" "+timeFrom,date+" "+timeTo,false));
		}else if(isTask) {
			cm.writeDatabase(new Task(info,date+" "+timeFrom+":00",date+" "+timeTo+":00",false));
		}
	}	
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}

	public void updateIsDone(String dateFrom, boolean isDone) {
		cm.updateDatabase(dateFrom, isDone);
	}
	
	public void deleteIsDone(String dateFrom) {
		cm.deleteDatabase(dateFrom);
	}
	
	public void updateViews(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay, boolean isEvent, boolean isTask) {
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
		
		if(isEvent && isTask)
			cm.notifyViews(dateFilter);
		else if(!isEvent && isTask)
			cm.notifyFilteredViews(dateFilter,"Task");
		else if(isEvent && !isTask)
			cm.notifyFilteredViews(dateFilter,"Event");
		else
			cm.notifyFilteredViews(dateFilter,"");
	}	
	
	public void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cm.notifyDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}

	public void refreshCalendar(int monthToday, int yearToday) {
		String month = new String();
		month = month + (monthToday+1);
		if(monthToday < 10)
			month = "0" + month;
		String dateFilter = yearToday + "-" + month;
		cm.notifyCalendar(monthToday, yearToday, dateFilter);
	}
	
}
