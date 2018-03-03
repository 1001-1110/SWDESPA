public class FBAdapter implements Observer{

	private FBView fbview;
	
	public FBAdapter(FBView fbview) {
		this.fbview = fbview;
	}
	
	public void update(Event event) {
		fbview.showNewEvent(event.getInfo(), event.getMonth(), event.getDay(), event.getYear(), event.getColor());
	}
	
}
