
public class DesignChallenge2 {

    public static void main(String[] args) {
        // TODO code application logic here
        CalendarView cpv = new CalendarProgramView();
        CalendarModel cpm = new CalendarProgramModel();
        CalendarControl cpc = new CalendarProgramControl();
        cpv.attachController(cpc);
        cpm.attachView(cpv);
        cpc.attachModel(cpm);
        cpv.initialize();
        cpv.refreshView();
        cpm.connectDatabase("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/","root","july","SWDESPA");
    	//com.mysql.jdbc.Driver
    	//jdbc:mysql://127.0.0.1:3306/
    	//root (username)
    	//july (password)
    	//SWDESPA (database)
    }
    
}
