package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Berries;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

import static model.Action.*;

public class Navi extends Entity implements IEntity {
	//	default
	private static final Action[] possibleActions = new Action[]{HARVEST, ATTACK, STOP, BUILD, MOVE };
	private static final int def_health = 100;
	private static final int def_attack = 5;
	private static final int def_speed = 10;

	private Action action = STOP;
	
	public Navi(MainModel m, ICell c) {
		super(m, c, def_health, def_attack,def_speed);
		super.currentAction = STOP;
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
		return super.position.getTerrain() == Terrain.BERRIES && ((Berries)super.position).getFood() > 9 && position == destination;
	}
	
	/**
	 * S'il est sur le bon type de terrain,
	 * il recolte les baies
	 * => 
	 * les baies presentes diminuent
	 * les ressources augmentent
	 */
	public void harvest() {
		if(this.canHarvest()){
			Camp.RESSOURCES += this.position.isBeingHarvested();
		}
		else move();
	}

	/**
	 * Verifie si la construction de Camp est possible
	 * @param c, l'endroit de construction
	 * @return vraie s'il n'y a personne sur `c` et est a cote de Navi
	 */
	public boolean canBuildCamp(ICell c){
		return c.isAccessible() && c.nextTo(this.position) && this.position.nextTo(c) && Camp.RESSOURCES/this.model.getCamps().size() >= 1;
	}

	/**
	 * Construit un Camp sur le cell demande
	 * @param cell la case de construction
	 */
	public void build(){
		
		if(destination != null && this.canBuildCamp (destination)){
			EntityFactory.getInstance(model).createEntity(destination, "CAMP");
		}
		else {
			System.out.println("Building forbidden, move closer / More berries needed");
			this.currentAction = Action.STOP;
		}
	}

//	public void builingCamp(){
//		this.camp.construCamp();
//	}
	
//	getters
	Action getAction() {
		return action;
	}
	@Override
	public Action[] possibleActions() {
		return possibleActions;
	}
	
//	setters
	void setAction(Action action) {
		this.action = action;
	}


}