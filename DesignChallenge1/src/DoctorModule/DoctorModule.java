package DoctorModule;
import DoctorView.DoctorLoginProgramView;
import DoctorView.DoctorMenuProgramView;
import DoctorView.DoctorSlotAdderProgramView;

public class DoctorModule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoctorMenuProgramView dmv = new DoctorMenuProgramView();
		DoctorLoginProgramView dlv = new DoctorLoginProgramView(dmv);
		DoctorSlotAdderProgramView dsav = new DoctorSlotAdderProgramView(dmv);
		dmv.attachDoctorLoginView(dlv);
		dmv.attachDoctorSlotAdderView(dsav);
	}

}
