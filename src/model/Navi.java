package model;

import model.terrain.Cell;

public class Navi extends MobileEntity implements IEntity {
	private int harvest_capacity;
	private Cell harvestCell;
	
	public Navi(MainModel m, Cell c, Map map, int h, int a, int s, int hc) {
		super(m, c, map, h, a, s);
		this.harvest_capacity = hc;
		this.harvestCell = null;	
		super.currentAction = null;
	}
	
	public boolean canHarvest(Cell c){
		return false;
	}
}