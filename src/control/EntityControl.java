package control;

import model.MainModel;
import model.entity.Entity;

public class EntityControl extends Thread{
	private MainModel model;
	private Entity ent;
	
	public EntityControl(MainModel m, Entity ent) {
		this.model = m;
		this.ent = ent;
	}
    @Override
    public void run() {
        while (model.isRunning()) {
            ent.update();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
