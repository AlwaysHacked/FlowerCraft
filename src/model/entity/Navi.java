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
	 * S'il est sur le bon terrain,
	 * il recolte les baies
	 * => 
	 * les baies presentes diminuent
	 * les ressources augmentent
	 */
	public void harvest() {
		if(this.canHarvest())
			RESSOURCES += this.map.getCell(super.position.getX(), super.position.getX()).isBeingHarvested();
	}
	
	public Camp startBuild(){
		Camp c = new Camp(super.model,this.position,this.map,3,0,0);
		return c;
	}

	public void builingCamp(){
		this.camp.construCamp();
	}
	
//	getters
	Action getAction() {
		return action;
	}
	
//	setters
	void setAction(Action action) {
		this.action = action;
	}


}