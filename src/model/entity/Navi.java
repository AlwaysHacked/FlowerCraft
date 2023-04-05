package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

public class Navi extends Entity implements IEntity {
	private Action action = Action.STOP;
	
	public Navi(MainModel m, ICell c, Map map, int h, int a, int s) {
		super(m, c, map, h, a, s);
		super.currentAction = null;
	}
	
	@Override
	public boolean isEnemy(IEntity ent) {
		return ent instanceof Soldier;
	}
	
	@Override
	public String toString() {
		return "n";
	}

	/**
	 * Renvoie true si le pion se trouve sur un terrain Berries
	 */
	public boolean canHarvest(){
		return super.position.getTerrain() == Terrain.BERRIES;
	}
	
	/**
	 * S'il est sur le bon type de terrain,
	 * il recolte les baies
	 * => 
	 * les baies presentes diminuent
	 * les ressources augmentent
	 */
	public void harvest() {
		int x = super.position.getX();
		int y = super.position.getX();
		
		if(this.canHarvest())
			Camp.RESSOURCES += this.map.getCell(x, y).isBeingHarvested();
		else throw new IllegalArgumentException("Impossible de recolter a la case (" + x + ", " + y + ")");
	}

	/**
	 * Verifie si la construction de Camp est possible
	 * @param ICell c, l'endroit de construction 
	 * @return vraie s'il n'y a personne sur `c` et est a cote de Navi
	 */
	public boolean canBuildCamp(ICell c){
		return c.isAccessible() && this.position.nextTo(c);
	}

	/**
	 * Construit un Camp sur le cell demande
	 * @param ICell cell 
	 */
	public void buildCamp(ICell cell){
		int x = cell.getX();
		int y = cell.getY();
		
		if(this.canBuildCamp (cell)){
			Camp c = new Camp(super.model,cell,this.map,12,0,0);
			this.map.getCell(x, y).addEntity(c);
		}
		else throw new IllegalArgumentException("Impossible de construire a la case (" + x + ", " + y + ")");
	}

//	public void builingCamp(){
//		this.camp.construCamp();
//	}
	
//	getters
	Action getAction() {
		return action;
	}
	
//	setters
	void setAction(Action action) {
		this.action = action;
	}


}