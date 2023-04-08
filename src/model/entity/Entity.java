package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.ICell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import control.EntityControl;

import static model.Action.STOP;

public class Entity implements IEntity {
	private static final Action[] possibleActions = new Action[] {};

	public static final int NAVI_HEALTH = 100;
	public static final int NAVI_SPEED = 5;
	public static final int NAVI_ATTACK = 10;

	public static final int SOLDIER_HEALTH = 100;
	public static final int SOLDIER_SPEED = 5;
	public static final int SOLDIER_ATTACK = 15;

	protected MainModel model;
	protected ICell position;
	public Map map;

	protected int health;
	protected int attack;
	protected int speed;

	protected Stack<ICell> path;
	protected ICell destination;
	protected Action currentAction;

	public Entity(MainModel m, ICell c, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = model.getMap();

		this.health = h;
		this.attack = a;
		this.speed = s;

		// this.stop();

	}

	@Override
	public void update() {

		switch (this.currentAction) {
			case ATTACK -> this.attack();
			case HARVEST -> this.harvest();
			case BUILD -> this.build();
			case MOVE -> this.move();
			case STOP -> this.stop(); // vide le chemin et ne fait rien
			case CREATE -> this.create();
		}
	}

	/* Liste des méthodes associées à chaque action */

	/**
	 * Attaque l'ennemi (s'il y en a un) a cote de lui
	 * En absence d'ennemi, bouge vers sa destination
	 */
	public void attack() {
		IEntity ent = this.canAttack();
		if (ent != null)
			ent.sufferAttack(this.attack);
		else
			move();
	}

	/**
	 * La fonction bouge le pion sur la cellule suivante
	 * Le chemin est stocke dans l'attribut `path`
	 */
	public void move() {
		if (destination == position)
			this.currentAction = Action.STOP;
		else {
			if (path.empty()) {
				generatePath();
			} else {
				for(var i : path)System.out.println(i.getCoord());
				if (canMove(this.path.peek())) {
					this.path.peek().addEntity(this);
					this.position.deleteEntity();
					this.position = this.path.peek();
					this.path.pop();///////

				} // else generatePath();
			}
		}
	}

	protected void build() {
	}

	protected void harvest() {
	}

	protected void stop() {
		destination = null;
		path = new Stack<>();
	}

	protected void create() {
	}

	/** Méthode de IEntity */

	@Override
	public boolean isDead() {
		return this.health <= 0;
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
		ArrayList<ICell> c = model.getMap().neighbours(this.position);
		for (ICell cc : c) {
			IEntity ent = cc.getEntity();
			if (this.isEnemy(ent))
				return ent;
		}
		return null;
	}

	@Override
	public void sufferAttack(int impact) {
		this.health -= impact;
		if (this.health <= 0) {
			this.position.deleteEntity();
			this.position = null;
		}
	}

	@Override
	public boolean canMove(ICell c) {
		return c.isAccessible() && this.position.nextTo(c);
	}

	/** Méthodes privées */

	private void moveStraight(int x, int y) {
		this.path = new Stack<>();
		x += this.position.getX();
		y += this.position.getY();

		if (x < this.map.sizeGrid - 1 && y < this.map.sizeGrid - 1 && x > 0 && y > 0) {
			this.path.add(this.map.getCell(x + 1, y));
			this.move();
		} else
			throw new IllegalArgumentException("Impossible d'aller a la case (" + x + ", " + y + ")");
	}

	// After checking if the move is possible will
	// put the path gotten from AStar into path variable.
	// If the move isn't possible will throw exception
	public void generatePath() {
		AStar a = new AStar(this.model, model.getMap(), this.position, this.destination);
		Stack<ICell> tab = a.getPath();
		this.path = tab;
		// ArrayList<ICell> temp = new ArrayList<ICell>(tab);
		// Collections.reverse(temp);
		// Stack<ICell> newtab = new Stack<>();
		// newtab.addAll(temp);
		// this.path = newtab;
	}

	// for test of moves
	public void testMove(ICell c) {
		System.out.print("herere");
		this.destination = c;
		this.generatePath();
		this.map.affiche();

		while (!this.path.isEmpty()) {
			this.map.affiche();
		}
	}

	// getters
	@Override
	public Action getCurrentAction() {
		return this.currentAction;
	}

	@Override
	public Action[] possibleActions() {
		return possibleActions;
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

	public Stack<ICell> getPath() {
		return this.path;
	}

	public void setPath(Stack<ICell> path) {
		this.path = path;
	}

	public ICell getDestination() {
		return this.destination;
	}

	public void setDestination(ICell destination) {
		this.destination = destination;
	}

}
