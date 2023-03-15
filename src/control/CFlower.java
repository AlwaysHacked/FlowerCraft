package control;

import model.MainModel;
import model.entity.Flower;

public class CFlower extends Thread{
    private final MainModel model;
	private Flower flower;
	
	public CFlower(MainModel model, Flower flower) {
    	this.model = model;
		this.flower = flower;
	}
	
//	run : Appelle repaint toutes les 50 millisecondes
	@Override
	public void run() {
		while(flower.getRunning()) {
	    	flower.grow();
			try { Thread.sleep(50); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
