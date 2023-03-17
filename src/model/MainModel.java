package model;

import model.entity.AStar;
import model.entity.Navi;
import model.terrain.Cell;
import model.terrain.ICell;

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
        
//        AStar star = new AStar(this, this.map, map.getCell(0, 0), map.getCell(this.map.sizeGrid - 1, this.map.sizeGrid - 1));
		for (int i = 0; i < this.map.sizeGrid; i++) {
    		System.out.print(i);
    		System.out.print('|');
    		for (int j = 0; j < this.map.sizeGrid; j++) {
    			System.out.print(this.map.getCell(i, j) );
    		}
    		System.out.println();
		}
//        Cell c;
//        for (int i = 0; i < map.sizeGrid; i++)
//        	for(int j = 0; j < map.sizeGrid; j++) {
//        		e = .getEntity();
//        		if(e != null) 
//        			break;
//        	}
//        System.out.println("Choosen ent is : " + e);
//        e.
    }
    
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }
}
