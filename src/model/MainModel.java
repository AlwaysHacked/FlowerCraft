package model;

import model.entity.AStar;
import model.entity.Navi;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;
import model.entity.Camp;

import java.util.Stack;
import java.util.ArrayList;

public class MainModel {
    private final Map map;
    private boolean running = true;
    private ICell startCell = null;
    private ICell endCell = null;

    //  attribute for the List of Camp so solider ca attack it 

    public ArrayList<Camp> campList = new ArrayList<>();
    public int food = 0;


    

    public MainModel() {
        this.map = MapFactory.getInstance(this).createMap("DEFOREST");
    
        ICell c = map.getCell(0, 0);
        Navi n = new Navi(this, c, map);
        if (c.getEntity() == null)
        	c.addEntity(n);
        else {
        	c.deleteEntity();
        	c.addEntity(n);
        }
        
    }
        
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }


    public boolean isGameOver() {return campList.isEmpty();}

    /* Méthode d'action du controleur sur le modèle */

    /** Recois la cellule cliquée et fais lance l'action */
    public void clic(ICell cell) {
    }

    /** Recois l'action cliquée et fais lance l'action ou attends un clic */
    public void selectAction(Action action) {}

    public ArrayList<Camp> getCampList() {
       return campList;
    }
    
    /**
     * @return ICell return the startCell
     */
    public ICell getStartCell() {
        return startCell;
    }

    /**
     * @param startCell the startCell to set
     */
    public void setStartCell(ICell startCell) {
        this.startCell = startCell;
    }

    /**
     * @return ICell return the endCell
     */
    public ICell getEndCell() {
        return endCell;
    }

    /**
     * @param endCell the endCell to set
     */
    public void setEndCell(ICell endCell) {
        this.endCell = endCell;
    }

}
