
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
    	//com.mysql.jdbc.Driver
    	//jdbc:mysql://127.0.0.1:3306/
    	//root
    	//july
    	//SWDESPA
        cpm.connectDatabase("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/","root","july","SWDESPA");
        cpm.readDatabase();
        cpm.writeDatabase(new Task("Due today", 1, 10, 1998, "9999-12-31 23:59:59", "9999-12-31 23:59:59", true));
    }
    
}
