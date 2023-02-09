package model;

import model.terrain.Cell;

public class MobileEntity implements IEntity {
	private MainModel model;
	private Cell position;
	private Map map;
	
	private int health;
	private int attack;
	private int speed;
	
	protected Action currentAction;

//	IDEA : special bool cases : invicibility / rage / ...
	
	public MobileEntity(MainModel m, Cell c, Map map, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = map;
		
		this.health = h;
		this.attack = a;
		this.speed = s;
	}
	
	public void suffer_attack(int impact) {
		this.health -= impact;
	}
	
	public Action[] possibleActions() {
		return Action.values();
	}

	//	getters
	public Action getCurrentAction() {return this.currentAction;}
	public int getHealth() {return this.health;}
	public int getAttack() {return this.attack;}
	public int getSpeed() {return this.speed;}
	public Cell getPosition() {return this.position;}
	
	// setters
	public void setCurrentAction(Action currentAction) {this.currentAction = currentAction;}

	public void setHealth(int health) {this.health = health;}

	public void setAttack(int attack) {this.attack = attack;}

	public void setSpeed(int speed) {this.speed = speed;}

	public void setPosition(Cell position) {this.position = position;}
}
