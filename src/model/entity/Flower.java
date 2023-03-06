package model.entity;

import control.CFlower;
import model.MainModel;
import model.entity.IEntity;
import model.terrain.Cell;

public abstract class Flower implements IEntity {
    private final MainModel model;
    private final Cell cell;
    private final CFlower control;
    private boolean running = true;
    
    public static final int growingTime = 20;
    private int age = 0;
    
    Flower(MainModel model, Cell cell){
    	this.model = model;
    	this.cell = cell;
    	this.control = new CFlower(model, this);
    }
    
    public boolean getRunning() {
    	return running;
    }
    
    public boolean harvest() {
    	if(age < growingTime)
    		return false;
    	else {
    		running = false;
    		
    		return true;
    	}
    }
    
    public void grow() {
    	age++;
    }
}