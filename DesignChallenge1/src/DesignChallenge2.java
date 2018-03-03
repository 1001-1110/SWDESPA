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
public class DesignChallenge2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarControl cpc = new CalendarProgramControl();
        CalendarView cpv = new CalendarProgramView(cpc);
        CalendarModel cpm = new CalendarProgramModel(cpc);
        cpc.attachView(cpv);
        cpc.attachModel(cpm);
         
    }
}
