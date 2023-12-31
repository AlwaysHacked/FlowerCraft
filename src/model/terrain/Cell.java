package model.terrain;
import model.entity.Navi;
import model.entity.Soldier;
import java.util.ArrayList;

import java.awt.Point;

import model.entity.IEntity;
import model.MainModel;

public class Cell implements ICell{
	private Point coord; 
    private final MainModel model;
    protected IEntity entity = null;
	protected Terrain terrain;


    public Cell(MainModel model, Point p) {
        this.model = model;
        this.coord = p;
    }
    
    public Cell(MainModel model, int x, int y) {
//        this.Cell(model, new Point(x, y));
    	this.model = model;
        this.coord = new Point(x, y);
    }

	// --------------------- ICell Methods ---------------------
	@Override
	public boolean deleteEntity() {
		if (entity == null) return false;
		entity = null;
		return true;
    }

	@Override
    public boolean addEntity(IEntity e) {
		if (entity != null) return false;
		entity = e;
		return true;
//    	System.out.println("\tcell: " + this.coord.x + " " + this.coord.y + "\t" + entities.size());
    }

	@Override
	public boolean nextTo(ICell c) {
		return  (c.getX()+1 == this.getX() && this.getY() == c.getY()) ||
				(c.getX()-1 == this.getX() && this.getY() == c.getY()) ||
				(c.getX() == this.getX() && this.getY() == c.getY()+1) ||
				(c.getX() == this.getX() && this.getY() == c.getY()-1) ||
				(c.getX()+1 == this.getX() && this.getY()+1 == c.getY()) ||
				(c.getX()-1 == this.getX() && this.getY()-1 == c.getY()) ||
				(c.getX()+1 == this.getX() && this.getY()-1 == c.getY()) ||
				(c.getX()-1 == this.getX() && this.getY()+1 == c.getY());
	}
	

	@Override
	public boolean isAccessible() { return this.entity == null; }

	public boolean samePlace(ICell c) {
		return c.getX() == this.getX() && 
				c.getY() == this.getY();
	}

    /** Getters */
	@Override
    public int getX() {
    	return this.coord.x;
    }

	@Override
    public int getY() {
    	return this.coord.y;
    }

	@Override
    public Point getCoord() {
    	return this.coord;
    }

	@Override
    public Point createCoord(int x, int y) {
    	return new Point(this.coord.x + x, this.coord.y + y);
    }

	@Override
    public IEntity getEntity(){
    	return this.entity;
    }

	@Override
	public Terrain getTerrain() {
		return terrain;
	}

	@Override
	public int getA() {
		return entity == null ? 1 : 10000;
	}

	@Override
    public void affiche() {
    	char c = ' ';
    	if (entity != null) {
    		if(entity instanceof Soldier)
    			c = 's';
    		else if(entity instanceof Navi)
    			c = 'N';
    		
    	}
//    	System.out.print(c);
    }

	@Override
	public String toString() {
		if (entity == null) return " ";
		return entity.toString();
	}
	// --------------------- Other Methods ---------------------

	@Override
	public int isBeingHarvested() {
		// TODO Auto-generated method stub
		return 0;
	}
}
