
public class DesignChallenge2 {

    public static void main(String[] args) {
        // TODO code application logic here
        CalendarView cpv = new CalendarProgramView();
        CalendarModel cpm = new CalendarProgramModel();
        CalendarControl cpc = new CalendarProgramControl();
        cpv.attachController(cpc);
        cpm.attachView(cpv);
        cpc.attachModel(cpm);
        cpv.refreshView();
    }
    
}
