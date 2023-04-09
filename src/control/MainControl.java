package control;
import java.util.Map;

import model.MainModel;
import view.MainView;
import Sound.Sound;

public class MainControl {
	private MainModel model;
	private MainView view;
	private BerriesControl berries;
	private EntityControl entities;
	private ViewControl viewControl;
	
	public MainControl(MainModel m, MainView v) {
		this.model = m;
		this.view = v;
		this.berries = new BerriesControl(m, v);
		this.berries.start();
		this.entities = new EntityControl(m, v);
		this.entities.start();
		this.viewControl = new ViewControl(m, v);
		this.viewControl.start();
	}
}
