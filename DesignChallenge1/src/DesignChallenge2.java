import java.util.Calendar;
import java.util.GregorianCalendar;

public class DesignChallenge2 {

    public static void main(String[] args) {
        // TODO code application logic here
        CalendarView cpv = new CalendarProgramView();
        CalendarModel cpm = new CalendarProgramModel();
        CalendarControl cpc = new CalendarProgramControl();
        AgendaView apv = new AgendaProgramView(cpv);
        DayView dpv = new DayProgramView(cpv);
        WeekView wpv = new WeekProgramView(cpv);
        cpv.attachController(cpc);
        cpv.attachAgendaView(apv);
        cpv.attachDayView(dpv);
        cpv.attachWeekView(wpv);
        cpm.connectDatabase("com.mysql.jdbc.Driver","jdbc:mysql://112.211.95.65:3306/","superuser","kathyemir","swdespa");
        cpm.attachView(cpv);
        cpc.attachModel(cpm);
        cpm.attachObserver((ObserverView) apv);
        cpm.attachObserver((ObserverView) dpv);
        cpm.attachObserver((ObserverView) wpv);
        cpv.initialize();
        cpv.refreshView();
        cpm.timeCheck();
    	//com.mysql.jdbc.Driver
    	//jdbc:mysql://127.0.0.1:3306/
    	//root (username)
    	//july (password)
    	//SWDESPA (database)
    }
    
}
