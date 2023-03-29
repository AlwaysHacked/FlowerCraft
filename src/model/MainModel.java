package model;

import model.entity.AStar;
import model.entity.Navi;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

import java.util.Queue;
import java.util.ArrayList;

public class MainModel {
    private final Map map;
    private boolean running = true;

    public MainModel() {
        this.map = MapFactory.getInstance(this).createMap("DEFOREST");
    

    //      Ancien constructeur
//    public MainModel() {
//        map = new Map(this);

        ICell c = map.getCell(0,0);
        Navi n = new Navi(this, c, map, 100, 5, 10, 20);
        if (c.getEntity() == null)
        	c.addEntity(n);
        else {
        	c.deleteEntity();
        	c.addEntity(n);
        }
        
        
        
        
//        AStar star = new AStar(this, this.map, map.getCell(0, 0), map.getCell(this.map.sizeGrid - 1, this.map.sizeGrid - 8));
//        Queue<ICell> path = star.getPath();
//        while(path.peek() != null) {
//        	ICell c1 = path.poll();
//    	System.out.println(c1.getX() +" " + c1.getY());
//        }
//        ArrayList<ICell> path = star.getPath();
//        for (ICell c1 : path) {
//        	System.out.println(c1.getX() +" " + c1.getY());
//        }
        
        
        this.move(map.getCell(0, 0),  map.getCell(3, this.map.sizeGrid - 1));
        
    }
    
//    private boolean isPossibleMove(int,) {
//    	return 
//    }
    
    private void move(ICell begin, ICell end) {
    	System.out.println("begin :" + begin.getX() + " " + begin.getY());
    	System.out.println("end :" + end.getX() + " " + end.getY());
    	
    	int moveX = begin.getX() > end.getX() ? -1 : begin.getX() == end.getX() ? 0 : 1;
    	int moveY = begin.getY() > end.getY() ? -1 : begin.getY() == end.getY() ? 0 : 1;

    	int X = begin.getX();
    	int Y = begin.getY();
    	
    	while(X != end.getX() || Y != end.getY() ){
//    		this.map.affiche();
    		if (X != end.getX())// && this.isPossibleMove())
    			X += X != end.getX() ? moveX : 0;
    		else
    			Y += Y != end.getY() ? moveY : 0;
    		System.out.println(X + " " + Y);
    		ICell c = this.map.getCell(X, Y);
    		System.out.println(c.getTerrain());
			if (c.getTerrain() == Terrain.WATER || !c.isAccessible())
				return ;
		}
    	

    }
    
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }
}
