package model.terrain;
import model.Navi;
import java.awt.Point;
import java.util.ArrayList;

import model.IEntity;
import model.Jardinier;
import model.MainModel;
import model.Navi;

public class Cell implements ICell{
	private Point coord; 
    private final MainModel model;
    private final ArrayList<IEntity> entities = new ArrayList<>();


    public Cell(MainModel model, Point p) {
        this.model = model;
        this.coord = p;
    }
    
    public Cell(MainModel model, int x, int y) {
//        this.Cell(model, new Point(x, y));
    	this.model = model;
        this.coord = new Point(x, y);
    }

	public boolean deleteEntity(IEntity e) {
    	return this.entities.remove(e);
    }
    
    public void addEntity(IEntity e) {
    	this.entities.add(e);
//    	System.out.println("\tcell: " + this.coord.x + " " + this.coord.y + "\t" + entities.size());
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
    
    public Point createCoord(int x, int y) {
    	return new Point(this.coord.x + x, this.coord.y + y);
    }

	@Override
    public ArrayList<IEntity> getEntities(){
    	return this.entities;
    }
    
	@Override
    public void affiche() {
    	char c = ' ';
    	if (entities.size() == 1) {
    		if(entities.get(0) instanceof Navi) {
    			c = 'N';
    		}
    		else if(entities.get(0) instanceof Jardinier)
    			c = 'J';
    	}
    	System.out.print(c);
    }

	@Override
	public boolean isAccessible(IEntity e) {
		return false;
	}
}
