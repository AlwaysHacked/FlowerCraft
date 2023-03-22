package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

import java.awt.Point;
import java.lang.Thread;

public class MobileEntity implements IEntity {
	private MainModel model;
	private ICell position;
	private Map map;
	
	private int health;
	private int attack;
	private int speed;
	
	protected Action currentAction;

//	IDEA : special bool cases : invicibility / rage / ...
	
	public MobileEntity(MainModel m, ICell c, Map map, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = map;
		
		this.health = h;
		this.attack = a;
		this.speed = s;
	}
	
//	war methods
//	does it work?
	@Override
	public boolean isEnemy(MobileEntity ent) {
		if (this instanceof Navi)
			return ent instanceof Soldier;
		else if (this instanceof Soldier)
			return ent instanceof Navi;
		else 
			return false;
	}
	
//	Can attack if the Entity is next to him and is his enemy
	@Override
	public boolean canAttack(MobileEntity ent, ICell c) {
//		TODO
//		return this.nextTo(c) && c.getEntities().contains(ent);	/*&& this.isEnemy(ent)*/
		return false;
	}
	
	@Override
	public void attack(MobileEntity ent) {
		ent.sufferAttack(this.attack);
	}
	@Override
	public void sufferAttack(int impact) {
		this.health -= impact;
	}
	
	@Override
	public boolean nextTo(ICell c) {
		int x = c.getX();
		int y = c.getY();
		
		return 	this.map.getCell(x+1, y).equals(c) ||
				this.map.getCell(x-1, y).equals(c) ||
				this.map.getCell(x, y+1).equals(c) ||
				this.map.getCell(x, y-1).equals(c) ;
	}

	//	getters
	@Override
	public Action getCurrentAction() {return this.currentAction;}
	@Override
	public int getHealth() {return this.health;}
	@Override
	public int getAttack() {return this.attack;}
	@Override
	public int getSpeed() {return this.speed;}
	@Override
	public ICell getPosition() {return this.position;}
	public Map getMap() { return this.map; }
	
	// setters
	@Override
	public void setCurrentAction(Action currentAction) {this.currentAction = currentAction;}
	@Override
	public void setHealth(int health) {this.health = health;}
	@Override
	public void setAttack(int attack) {this.attack = attack;}
	@Override
	public void setSpeed(int speed) {this.speed = speed;}
	@Override
	public void setPosition(ICell position) {this.position = position;}

//	other
	public Action[] possibleActions() {
		return Action.values();
	}
	
}
