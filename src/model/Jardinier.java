package model;

import java.awt.Point;

public class Jardinier implements Entity{
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
		
		return this.move(this.position.getCoord(0, -1));
	}
	
	public boolean down() {
		if (this.position.getY() + 1 > this.map.sizeGrid)
			return false;
		
		return this.move(this.position.getCoord(0, 1));
	}
	
	public boolean left() {
		if (this.position.getX() - 1 < 0)
			return false;
		
		return this.move(this.position.getCoord(-1, 0));
	}
	
	public boolean right() {
		if (this.position.getX() + 1 > this.map.sizeGrid)
			return false;
		
		return this.move(this.position.getCoord(1, 0));
	}
	
	private boolean move(Point p) {
		this.position.delete(this);
		this.position = this.map.getCell(p.x, p.y);
		this.position.add(this);
		
		return true;
	}
	// tore
	
	//	Getters
	public Cell getPosistion() {
		return this.position;
	}


	//	Setters
	public void setPosition(Cell pos) {
		this.position = pos;
	}
}
