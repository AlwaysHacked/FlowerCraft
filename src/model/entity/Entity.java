package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Berries;
import model.terrain.ICell;
import model.terrain.Terrain;

import java.util.ArrayList;
import java.util.Stack;

import control.EntityControl;

import java.util.LinkedList;

public class Entity implements IEntity {
	public static final int NAVI_HEALTH = 100;
	public static final int NAVI_SPEED = 5;
	public static final int NAVI_ATTACK = 10;

	public static final int SOLDIER_HEALTH = 100;
	public static final int SOLDIER_SPEED = 5;
	public static final int SOLDIER_ATTACK = 15;

	protected MainModel model;
	protected ICell position;
	protected Map map;

	protected int health;
	protected int attack;
	protected int speed;

	protected Stack<ICell> path;
	protected ICell destination;
	protected Action currentAction;

	protected EntityControl entC;

	public Entity(MainModel m, ICell c, Map map, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = map;

		this.health = h;
		this.attack = a;
		this.speed = s;

		this.videPath();

		this.entC = new EntityControl(this.model, this);
	}

	private void videPath() {
		this.path = new Stack<ICell>();
	}
	
	@Override
	public void update() {
		if (this.path.isEmpty())
			this.currentAction = Action.STOP;

		switch (this.currentAction) {
			case ATTACK -> this.attack();
			case HARVEST -> this.harvest();
			case BUILD -> this.build();
			case MOVE -> this.moveToNext();
			/*
			 * case UP -> this.moveStraight(0, -1);
			 * case DOWN -> this.moveStraight(0, 1);
			 * case LEFT -> this.moveStraight(-1, 0);
			 * case RIGHT -> this.moveStraight(1,0);
			 */
			case STOP -> this.videPath(); // vide le chemin et ne fait rien
		}
	}

	private void moveStraight(int x, int y) {
		this.videPath();
		x += this.position.getX();
		y += this.position.getY();
		
		if(x < this.map.sizeGrid - 1 && y < this.map.sizeGrid - 1 && x > 0 && y > 0) {
	        this.path.add(this.map.getCell(x+1, y));
	        this.moveToNext();
		}
		else throw new IllegalArgumentException("Impossible d'aller a la case (" + x + ", " + y + ")");
	}

	@Override
	public boolean isEnemy(IEntity ent) {
		if (this instanceof Navi)
			return ent instanceof Soldier;
		else if (this instanceof Soldier)
			return ent instanceof Navi;
		else
			return false;
	}

	@Override
	public IEntity canAttack() {
		ArrayList<ICell> c = this.map.neighbours(this.position);
		for (ICell cc : c) {
			IEntity ent = cc.getEntity();
			if (this.isEnemy(ent))
				return ent;
		}
		return null;
	}

	@Override
	public void attack() {
		IEntity ent = this.canAttack();
		if (ent != null)
			ent.sufferAttack(this.attack);
	}

	@Override
	public void sufferAttack(int impact) {
		this.health -= impact;
		if (this.health <= 0) {
			this.position.deleteEntity();
		}
	}

	@Override
	public boolean canMove(ICell c) {
		return c.isAccessible() && this.position.nextTo(c);
	}

	/**
	 * La fonction bouge le pion sur la cellule suivante
	 * Le chemin est sauvgarde dans l'attribut `path`
	 */
	public boolean moveToNext() {
		if (canMove(this.path.peek())) {
			this.position.deleteEntity();
			this.position = this.path.pop();
			this.position.addEntity(this);
			return true;
		}
		return false;
//		else throw new IllegalArgumentException("Impossible d a la case (" + x + ", " + y ")");
//		il va pas s'arreter apres un test s'il faut y aller ou pas, il est possible que la case soit occupee par qq'un d'autre
//		et peut se liberer
//		il vaut mieux avoir un retour en bool 
	}

	// for test of moves
	public void move(ICell c) {
		System.out.print("herere");
		this.destination = c;
		this.generatePath();
		this.map.affiche();

		while (!this.path.isEmpty()) {
//			System.out.println(this.moveToNext());
			this.map.affiche();
		}
	}

	// After checking if the move is possible will
	// put the path gotten from AStar into path variable.
	// If the move isn't possible will throw exception
	/**
	 * 
	 */
	private void generatePath() {
		AStar a = new AStar(this.model, this.map, this.position, this.destination);
		this.path = a.getPath();
		System.out.println(this.path.size());
	}

	// getters
	@Override
	public Action getCurrentAction() {
		return this.currentAction;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public int getAttack() {
		return this.attack;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public ICell getPosition() {
		return this.position;
	}

	public Map getMap() {
		return this.map;
	}

	// setters
	@Override
	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void setPosition(ICell position) {
		this.position = position;
	}

	// other
	public Action[] possibleActions() {
		return Action.values();
	}

	private void build() {
	}

	private void harvest() {
	}
}
