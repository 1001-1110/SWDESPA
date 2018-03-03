import java.util.GregorianCalendar;

public class SMSAdapter implements Observer{

	private SMSView smsview;
	
	public SMSAdapter(SMSView smsview) {
		this.smsview = smsview;
	}
	
	public void update(Event event) {
		GregorianCalendar date = new GregorianCalendar(event.getYear(), event.getMonth()-1, event.getDay());
		smsview.sendSMS(new SMS(event.getInfo(), date, event.getColor()));
	}	
	
}
