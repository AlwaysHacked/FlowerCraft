package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

public class Navi extends MobileEntity implements IEntity {
	private int harvest_capacity;
	private Cell harvestCell;
	private Action action = Action.STOP;
	
	public Navi(MainModel m, ICell c, Map map, int h, int a, int s, int hc) {
		super(m, c, map, h, a, s);
		this.harvest_capacity = hc;
		this.harvestCell = null;	
		super.currentAction = null;
	}
	
	@Override
	public boolean isEnemy(MobileEntity ent) {
		return ent instanceof Soldier /*or their technology*/;
	}
	
	public boolean canHarvest(Cell c){
		return false;
	}
	Action getAction() {
		return action;
	}
	void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "n";
	}
}