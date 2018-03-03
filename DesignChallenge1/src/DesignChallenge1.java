import java.awt.Color;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Arturo III
 */
public class DesignChallenge1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarControl cpc = new CalendarProgramControl();
        CalendarView cpv = new CalendarProgramView(cpc);
        CalendarModel cpm = new CalendarProgramModel(cpc);
        EventParser epp = new EventProgramParser(cpc); 
        EventAdder epa = new EventProgramAdder(epp);
        FBView fv = new FBView();
        SMSView sv = new SMSView();
        Observer fa = new FBAdapter(fv);
        Observer sa = new SMSAdapter(sv);
        cpc.attachView(cpv);
        cpc.attachModel(cpm);
        cpc.attachObserver(fa);
        cpc.attachObserver(sa);
        cpc.attachParser(epp);
        cpc.startInstructions();
         
    }
}
