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
	
	public void sufferAttack(int impact) {
		this.health -= impact;
	}
	
	public Action[] possibleActions() {
		return Action.values();
	}
	
	public boolean nextTo(Cell c) {
		int x = c.getX();
		int y = c.getY();
		
		return 	this.map.getCell(x+1, y).equals(c) ||
				this.map.getCell(x-1, y).equals(c) ||
				this.map.getCell(x, y+1).equals(c) ||
				this.map.getCell(x, y-1).equals(c) ;
	}
	
	public boolean atSamePlace(Cell c) {
		return c.equals(this.position);
	}
	
//	Can attack if the Entity is next to him
	public boolean canAttack(MobileEntity ent, Cell c) {
		return this.nextTo(c) && c.getEntities().contains(ent);			
	}
	
	public void attack(MobileEntity ent) {
		ent.sufferAttack(this.attack);
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
