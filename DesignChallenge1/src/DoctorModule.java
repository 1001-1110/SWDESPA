
public class DoctorModule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoctorMenuView dmv = new DoctorMenuView();
		DoctorLoginView dlv = new DoctorLoginView(dmv);
		DoctorSlotAdderView dsav = new DoctorSlotAdderView(dmv);
		dmv.attachDoctorLoginView(dlv);
		dmv.attachDoctorSlotAdderView(dsav);
	}

}
