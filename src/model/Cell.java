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
//        this.Cell(model, new Point(x, y));
    	this.model = model;
        this.coord = new Point(x, y);
    }

    private void Cell(MainModel model2, Point point) {
		// TODO Auto-generated method stub
		
	}

	public boolean deleteEntity(Entity e) {
    	return this.entities.remove(e);
    }
    
    public boolean addEntity(Entity e) {
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
    
    public ArrayList<Entity> getEntities(){
    	return this.entities;
    }
    
    public void affiche() {
    	System.out.print(entities.size() == 0 ? ' ' : 'J');
    }
}
