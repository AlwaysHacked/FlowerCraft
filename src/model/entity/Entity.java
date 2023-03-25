package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
//import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

//import java.awt.Point;
//import java.lang.Thread;
import java.util.Queue;

public class Entity implements IEntity {
	protected MainModel model;
	protected ICell position;
	protected Map map;
	
	protected int health;
	protected int attack;
	protected int speed;

	protected Queue<ICell> path;
	protected ICell destination;
	protected Action currentAction;

//	IDEA : special bool cases : invicibility / rage / ...
	
	public Entity(MainModel m, ICell c, Map map, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = map;
		
		this.health = h;
		this.attack = a;
		this.speed = s;

		this.path = null;
	}
	
//	war methods
//	does it work?
	@Override
	public boolean isEnemy(Entity ent) {
		if (this instanceof Navi)
			return ent instanceof Soldier;
		else if (this instanceof Soldier)
			return ent instanceof Navi;
		else 
			return false;
	}
	
//	Can attack if the Entity is next to him and is his enemy
	@Override
	public boolean canAttack(Entity ent) {
//		TODO
		return this.nextTo(ent.getPosition()) && this.isEnemy(ent);
//		return false;
	}
	
	@Override
	public void attack(Entity ent) {
		if (this.canAttack(ent))
			ent.sufferAttack(this.attack);
	}
	
//	Baisse les points de vie 
	@Override
	public void sufferAttack(int impact) {
		this.health -= impact;
	}
	
	// Selon la valeur, indique la possibilite de bouger a l'endroit demande
	@Override
	public boolean canMove(ICell c) {
		if(c.getTerrain() == Terrain.FOREST)
			return false;

		return true;
	}

	public boolean moveToNext(){
		if(this.nextTo(this.path.peek())) {
			this.position = this.path.poll();
			return true;
		}
		return false;
	}
	
//	After checking if the move is possible will
//	put the path gotten from AStar into path variable.
//	If the move isn't possible will throw exception
	public void move(ICell c) {
		if (!canMove(c))
			throw new IllegalArgumentException("Cannot move to " + c.getX() + " " + c.getY() + "\nTerrain type is " + c.getTerrain());

//		get aStar from this.position to c
		AStar star = new AStar(this.model, this.map, this.position, c);
		this.path = star.getPath();
	}
	
//	Indique si l'entite mobile se trouve a proximite de ICell passe 
//	en argument.
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
