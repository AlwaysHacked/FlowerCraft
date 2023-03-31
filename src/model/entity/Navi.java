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
	public Map map ;
	public MainModel m;
	public Cell campCell;
	private Camp camp;
	
	public Navi(MainModel m, ICell c, Map map, int h, int a, int s, int hc) {
		super(m, c, map, h, a, s);
		this.harvest_capacity = hc;
		this.harvestCell = null;	
		super.currentAction = null;
        this.map = map;
		this.m = m;
        this.campCell = null;
		
	}             
	
	public boolean isEnemy(Entity ent) {
		return ent instanceof Soldier /*or their technology*/;
	}
	
	public boolean canHarvest(){
		return this.position.getTerrain() == Terrain.BERRIES;
	}
	
	public void harvest() {
		if(this.canHarvest())
			this.map.getCell(super.position.getX(), super.position.getX()).isBeingHarvested();
	}
<<<<<<< HEAD
    

    public boolean canBuildCamp(ICell c){
		return c.getTerrain() != Terrain.WATER 
		&& c.isAccessible()
		&& this.position.nextTo(c);
	}

	public Camp buildCamp(ICell cell){
		if(this.canBuildCamp (cell)){
		Camp c = new Camp(super.model,cell,this.map,12,0,0);
		return c;
		} else {
			return null;
		}
	}


=======
	
>>>>>>> 252ee45d4e05bbd8297641eda4109935c18f24e6
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