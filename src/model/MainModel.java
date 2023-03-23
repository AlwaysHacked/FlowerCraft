package model;

import model.entity.AStar;
import model.entity.Navi;
import model.terrain.Cell;
import model.terrain.ICell;
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
        
        AStar star = new AStar(this, this.map, map.getCell(0, 0), map.getCell(this.map.sizeGrid - 1, this.map.sizeGrid - 8));
        ArrayList<ICell> path = star.getPath();
        for (int i = 0; i < path.size(); i++) {
        	ICell c1 = path.get(i);
        	System.out.println(c1.getX() +" " + c1.getY());
        }
    }
    
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }
}
