package model;

import model.entity.AStar;
import model.entity.Navi;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;

import java.util.Stack;
import java.util.ArrayList;

public class MainModel {
    private final Map map;
    private boolean running = true;

    public MainModel() {
        this.map = MapFactory.getInstance(this).createMap("DEFOREST");
    
        ICell c = map.getCell(0, 0);
        Navi n = new Navi(this, c, map, 100, 5, 10 );
        if (c.getEntity() == null)
        	c.addEntity(n);
        else {
        	c.deleteEntity();
        	c.addEntity(n);
        }
        
        map.affiche();
        
//        AStar a = new AStar(this, map, c, map.getCell(6, 0));
        n.testMove(this.map.getCell(9, 1));
        
    }
        
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }
}
