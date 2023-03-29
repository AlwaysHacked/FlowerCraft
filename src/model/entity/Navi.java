package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

public class Navi extends Entity implements IEntity {
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
	public boolean isEnemy(Entity ent) {
		return ent instanceof Soldier /*or their technology*/;
	}
	
	public boolean canHarvest(ICell c){
		return c.getTerrain() == Terrain.BERRIES 
				&& this.position.nextTo(c);
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

	public Camp startBuild(){
		Camp c = new Camp(super.model,this.position,this.map,3,0,0);
		return c;
	}

	public void builingCamp(){
		this.camp.construCamp();
	}
}