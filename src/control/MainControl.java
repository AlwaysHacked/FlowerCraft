package control;

import model.*;

public class MainControl {
	private MainModel model;
	private Map map;
//	private Entity
	private Jardinier jar;
	
	public MainControl(MainModel m) {
		this.model = m;
		
		this.map = new Map(this.model);
//		this.jar = new Jardinier(this.model, this.map, this.map.getCell(0, 0));
//		this.map.getCell(0, 0).addEntity(jar);
		
//		System.out.println(this.map.getCell(0, 0));
//		System.out.println((this.map.getCell(0, 0)).getEntities());
		
		this.map.affiche();
//		jardinier a besoin d'un point de depart
//		mauvaise idee de declarer le point ici
//		faudra faire de l'aleatoire, je teste pour l'instant
		
	}
}
