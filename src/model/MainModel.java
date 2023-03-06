package model;

import model.terrain.Cell;

public class MainModel {
    private final Panel panel;
    private final Map map;
    private boolean running = true;

    public MainModel() {
        panel = new Panel(this);
        map = new Map(this);
        
        Cell c = map.getCell(0,0);
        Navi n = new Navi(this, c, map, 100, 5, 10, 20);
        if (c.getEntity() == null)
        	c.addEntity(n);
        else { 
        	c.deleteEntity();
        	c.addEntity(n);
        }
        
        n.aStar(map.getCell(this.map.sizeGrid - 1, this.map.sizeGrid - 1));
        
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

    public boolean isRunning() {
        return running;
    }
}
