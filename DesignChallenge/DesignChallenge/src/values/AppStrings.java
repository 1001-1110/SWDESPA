package values;

public enum AppStrings {
	NOEVENTS;

	public String toString() {
		switch (this) {
		case NOEVENTS:
			return "No calendar events yet.";
		default:
			return "";
		}
	}
}
