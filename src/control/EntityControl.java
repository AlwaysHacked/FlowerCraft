package control;

import model.MainModel;
import model.entity.Entity;
import model.entity.IEntity;

public class EntityControl extends Thread{
	private MainModel model;
	private IEntity ent;
	
	public EntityControl(MainModel m, IEntity ent) {
		this.model = m;
		this.ent = ent;
	}
    @Override
    public void run() {
        while (model.isRunning()) {
            try {
                if (ent.isDead())
                    this.interrupt();

                ent.update();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
