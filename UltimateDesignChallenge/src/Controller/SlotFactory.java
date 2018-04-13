package Controller;
import Entry.Entry;
import Entry.Slot;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SlotFactory extends EntryFactory{

	private String doctor;
	private String slotType;
	
	public SlotFactory(String doctor, String slotType) {
		this.doctor = doctor;
		this.slotType = slotType;
	}
	
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	
	@Override
	protected Entry makeEntry(Calendar dateFrom, int id) {
		Calendar dateTo = new GregorianCalendar();
		dateTo.setTime(dateFrom.getTime());
		dateTo.add(Calendar.MINUTE, 30);
		//YYYY-MM-DD HH:MM:SS
		//String doctor, String slotType, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id
		String durationFrom = dateFrom.get(Calendar.YEAR)+"-"+(dateFrom.get(Calendar.MONTH)+1)+"-"+dateFrom.get(Calendar.DATE)+" "+dateFrom.get(Calendar.HOUR_OF_DAY)+":"+dateFrom.get(Calendar.MINUTE)+":00";
		String durationTo = dateTo.get(Calendar.YEAR)+"-"+(dateTo.get(Calendar.MONTH)+1)+"-"+dateTo.get(Calendar.DATE)+" "+dateTo.get(Calendar.HOUR_OF_DAY)+":"+dateTo.get(Calendar.MINUTE)+":00";
		return new Slot(doctor, slotType, dateFrom, dateTo,durationFrom,durationTo,id);
		
	}

}
