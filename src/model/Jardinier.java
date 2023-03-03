package model;

import java.awt.Point;

import model.terrain.Cell;

public class Jardinier implements IEntity{
	private MainModel model;
	private Cell position;
	private Map map;
	
	public Jardinier(MainModel model, Map map, Cell pos) {
		this.model = model;
		this.position = pos;
		this.map = map;
	}
	
	public boolean up() {
		if (this.position.getY() -1 < 0)
			return false;
		
		return this.move(this.position.createCoord(0, -1));
	}
	
	public boolean down() {
		if (this.position.getY() + 1 > this.map.sizeGrid)
			return false;
		
		return this.move(this.position.createCoord(0, 1));
	}
	 
	public boolean left() {
		if (this.position.getX() - 1 < 0)
			return false;
		
		return this.move(this.position.createCoord(-1, 0));
	}
	
	public boolean right() {
		if (this.position.getX() + 1 > this.map.sizeGrid)
			return false;
		
		return this.move(this.position.createCoord(1, 0));
	}
	
	private boolean move(Point p) {
		this.position.deleteEntity();
		this.position = this.map.getCell(p.x, p.y);
		this.position.addEntity(this);
		
		return true;
	}
	// tore
	
	//	Getters
	public Cell getPosistion() {
		return this.position;
	}


	@Override
	public boolean isEnemy(MobileEntity ent) {
		return false;
	}

	@Override
	public boolean canAttack(MobileEntity ent, Cell c) {
		return false;
	}

	@Override
	public void attack(MobileEntity ent) {

	}

	@Override
	public void sufferAttack(int impact) {

	}

	@Override
	public boolean nextTo(Cell c) {
		return false;
	}

	@Override
	public boolean atSamePlace(Cell c) {
		return false;
	}

	@Override
	public Action getCurrentAction() {
		return null;
	}

	@Override
	public int getHealth() {
		return 0;
	}

	@Override
	public int getAttack() {
		return 0;
	}

	@Override
	public int getSpeed() {
		return 0;
	}

	@Override
	public Cell getPosition() {
		return null;
	}

	@Override
	public void setCurrentAction(Action currentAction) {

	}

	@Override
	public void setHealth(int health) {

	}

	@Override
	public void setAttack(int attack) {

	}

	@Override
	public void setSpeed(int speed) {

	}

	//	Setters
	public void setPosition(Cell pos) {
		this.position = pos;
	}
}
