import Sound.Sound;
import control.MainControl;
import model.MainModel;
import view.MainView;


public class Main {
	public static void main(String[] args) {
		MainModel m = new MainModel();
		MainView v = new MainView(m);
		MainControl c = new MainControl(m, v);
		Sound s = new Sound(m);
		s.playMusic(0);
	}
}