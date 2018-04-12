
import Controller.CalendarControl;
import Controller.CalendarProgramControl;
import Model.CalendarModel;
import Model.CalendarProgramModel;
import View.AgendaProgramView;
import View.AgendaView;
import View.CalendarProgramView;
import View.CalendarView;
import View.DayProgramView;
import View.DayView;
import View.ObserverView;
import View.WeekProgramView;
import View.WeekView;

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
