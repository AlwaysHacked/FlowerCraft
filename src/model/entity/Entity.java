package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.ICell;

import java.util.ArrayList;
import java.util.Stack;

import control.EntityControl;

import static model.Action.STOP;

public class Entity implements IEntity {
	private static final Action[] possibleActions = new Action[]{};

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

		this.stop();

		this.entC = new EntityControl(this.model, this);
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
	protected void attack() {
		IEntity ent = this.canAttack();
		if (ent != null)
			ent.sufferAttack(this.attack);
		else move();
	}

	/**
	 * La fonction bouge le pion sur la cellule suivante
	 * Le chemin est stocke dans l'attribut `path`
	 */
	protected void move() {
		if (destination == position)currentAction = STOP;
		else{
			if (path.empty()) generatePath();
			if (canMove(this.path.peek())) {
				this.position.deleteEntity();
				this.position = this.path.pop();
				this.position.addEntity(this);
			} else generatePath();
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

	protected void create() {}
	/** Méthode de IEntity */


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
		
		if(x < this.map.sizeGrid - 1 && y < this.map.sizeGrid - 1 && x > 0 && y > 0) {
	        this.path.add(this.map.getCell(x+1, y));
	        this.move();
		}
		else throw new IllegalArgumentException("Impossible d'aller a la case (" + x + ", " + y + ")");
	}

	// After checking if the move is possible will
	// put the path gotten from AStar into path variable.
	// If the move isn't possible will throw exception
	private void generatePath() {
		AStar a = new AStar(this.model, this.map, this.position, this.destination);
		this.path = a.getPath();
		System.out.println(this.path.size());
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

}
