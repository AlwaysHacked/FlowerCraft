package control;
import model.MainModel;
import model.entity.Camp;


public class CCamp extends Thread{
    private final MainModel model;
	private Camp camp;
	
	public CCamp(MainModel modele,Camp camp) {
    	this.model = model;
		this.camp = camp;
	}
	
//	run : Appelle repaint toutes les 50 millisecondes
	@Override
	public void run() {
            camp.deleteCamp();
			try { Thread.sleep(50); }
			catch (Exception e) { e.printStackTrace(); }
		}
}