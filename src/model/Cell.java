package model;

import java.awt.Point;
import java.util.ArrayList;

public class Cell {
	private Point coord; 
    private final MainModel model;
    private final ArrayList<Entity> entities = new ArrayList<>();


    public Cell(MainModel model, Point p) {
        this.model = model;
        this.coord = p;
    }
    
    public Cell(MainModel model, int x, int y) {
        this.model = model;
        this.coord = new Point(x, y);
    }

    public boolean delete(Entity e) {
    	return this.entities.remove(e);
    }
    
    public boolean add(Entity e) {
    	return this.entities.add(e);
    }
    
    // Getters
    public int getX() {
    	return this.coord.x;
    }
    
    public int getY() {
    	return this.coord.y;
    }
    
    public Point getCoord() {
    	return this.coord;
    }
    
    public Point getCoord(int x, int y) {
    	return new Point(this.coord.x + x, this.coord.y + y);
    }
}

// getters, setters, second constructor with point, delete and add entity
