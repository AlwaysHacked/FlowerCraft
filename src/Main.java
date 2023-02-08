import control.MainControl;
import model.MainModel;

public class Main {
	public static void main(String[] args) {
		MainModel m = new MainModel();
//		View v = new MainView();
		MainControl c = new MainControl(m);
	}
}
