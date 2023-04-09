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
	private Sound sound;
	
	public MainControl(MainModel m, MainView v) {
		this.model = m;
		this.view = v;
		this.berries = new BerriesControl(m, v);
		this.berries.start();
		this.entities = new EntityControl(m, v);
		this.entities.start();
		this.sound = new Sound();
		this.sound.playMusic(0);

//		this.map = new Map(this.model);
//		this.jar = new Jardinier(this.model, this.map, this.map.getCell(0, 0));
//		this.map.getCell(5, 4).addEntity(jar);

//		this.map.affiche();
	}
}
