package model;

import model.entity.AStar;
import model.entity.IEntity;
import model.entity.Navi;
import model.terrain.Berries;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.Terrain;
import model.entity.Camp;

import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

public class MainModel {
    private static final int COUT_CAMP = 80;

    private final Map map;
    private boolean running = true;

    //  attribute for the List of Camp so solider ca attack it 

    public ArrayList<Camp> campList = new ArrayList<>();
    public int food = 0;

//    Attributs pour gérer les ordres des joueurs
    private IEntity entity = null;
    private Action action = null;
    

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
        if (entity == null)
            entity = cell.getEntity();
        else if (action == null)
            entity = cell.getEntity();
        else {
            switch (action) {
                case MOVE, ATTACK -> {
                    entity.setCurrentAction(action);
                }
                case BUILD -> {
                    if (cell.isAccessible() && food >= COUT_CAMP) {
                        entity.setCurrentAction(action);
                    }
                }
                case HARVEST -> {
                    if (cell.isAccessible() && cell instanceof Berries){
                        entity.setCurrentAction(action);
                    }
                }
                case CREATE, STOP -> entity = cell.getEntity();
            }
            action = null;
        }
    }

    /** Recois l'action cliquée et fais lance l'action ou attends un clic */
    public void selectAction(Action action) {
        if (entity != null && Arrays.stream(entity.possibleActions()).toList().contains(action))
            switch (action) {
                case HARVEST, BUILD, MOVE, ATTACK -> this.action = action;
                case STOP, CREATE -> entity.setCurrentAction(action);
            }
    }

    public ArrayList<Camp> getCampList() {
       return campList;
    }
    
}
