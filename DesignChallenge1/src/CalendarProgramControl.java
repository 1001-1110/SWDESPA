import java.util.GregorianCalendar;

public class CalendarProgramControl implements CalendarControl{

	CalendarModel cm;
	
	public boolean addOccasion(String info, String dateFrom, String dateTo, String timeFrom, String timeTo, boolean isEvent, boolean isTask) {
		String[] splitFromDate = dateFrom.split("/");
		dateFrom = splitFromDate[2]+"-"+splitFromDate[0]+"-"+splitFromDate[1];

		String[] splitToDate = dateTo.split("/");
		dateTo = splitToDate[2]+"-"+splitToDate[0]+"-"+splitToDate[1];		
		
		String[] splitFromTime = timeFrom.split(":");
		String[] splitToTime = timeTo.split(":");
		
		if(new GregorianCalendar(Integer.parseInt(splitToDate[2]),Integer.parseInt(splitToDate[0]),Integer.parseInt(splitToDate[1]),Integer.parseInt(splitToTime[0]),Integer.parseInt(splitToTime[1])).compareTo(new GregorianCalendar(Integer.parseInt(splitFromDate[2]),Integer.parseInt(splitFromDate[0]),Integer.parseInt(splitFromDate[1]),Integer.parseInt(splitFromTime[0]),Integer.parseInt(splitFromTime[1]))) < 0)
			return false;
		
		info = info.replaceAll("<html>", "");
		info = info.replaceAll("</html>", "");
		info = info.replaceAll("<s>", "");
		info = info.replaceAll("</s>", "");
		
		dateFrom = dateFrom.trim();
		timeFrom = timeFrom.trim();
		timeTo = timeTo.trim();		
		
		if(isEvent) {
			if(info.equals("") || timeFrom.equals("") || timeTo.equals("") || dateFrom.equals("") || dateTo.equals("") || timeFrom.equals(timeTo) && dateFrom.equals(dateTo)) 
				return false;	
			String[] splitTime = timeFrom.split(":");
			if(Integer.parseInt(splitTime[0]) >= 24 || (!splitTime[1].equals("00") && !splitTime[1].equals("30")))
				return false;

			splitTime = timeTo.split(":");
			if(Integer.parseInt(splitTime[0]) >= 24 || (!splitTime[1].equals("00") && !splitTime[1].equals("30")))
				return false;		
			cm.writeDatabase(new Event(0,info,dateFrom+" "+timeFrom,dateTo+" "+timeTo,false));
		}else if(isTask) {
			if(info.equals("") || timeFrom.equals("") || dateFrom.equals("")) 
				return false;	
			String[] splitTime = timeFrom.split(":");
			if(Integer.parseInt(splitTime[0]) >= 24 || (!splitTime[1].equals("00") && !splitTime[1].equals("30")))
				return false;
			String newTime = new String();
			if(splitTime[1].equals("30"))
				newTime = (Integer.parseInt(splitTime[0])+1) +":"+ "00";
			else
				newTime = splitTime[0] +":"+ "30";
			cm.writeDatabase(new Task(0,info,dateFrom+" "+timeFrom+":00",dateFrom+" "+newTime+":00",false));
		}
		
		return true;			
		

	}	
	
	public void attachModel(CalendarModel cm) {
		this.cm = cm;
	}

	public void updateIsDone(int occasionID, boolean isDone) {
		cm.updateDatabase(occasionID, isDone);
	}
	
	public void deleteIsDone(int occasionID) {
		cm.deleteDatabase(occasionID);
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
			cm.notifyObservers(dateFilter);
		else if(!isEvent && isTask)
			cm.notifyObservers(dateFilter,"Task");
		else if(isEvent && !isTask)
			cm.notifyObservers(dateFilter,"Event");
		else
			cm.notifyObservers(dateFilter,"");
		
		cm.notifyDeselect();
	}	
	
	public void updateDateTitle(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
		cm.notifyDateTitle(currentSelectedYear, currentSelectedMonth, currentSelectedDay);
	}

	public void refreshCalendar(int monthToday, int yearToday) {
		String month = new String();
		month = month + (monthToday+1);
		if(monthToday < 10)
			month = "0" + month;
		String monthFilter = yearToday + "-" + month;
		cm.notifyCalendar(monthToday, yearToday, monthFilter);
	}
	
	public void refreshCalendarDays() {
		cm.refreshDays();
	}
	
}
