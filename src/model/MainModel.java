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

    //  attribute for the List of Camp so solider ca attack it 

    public ArrayList<Camp> campList = new ArrayList<>();
    public Camp camp;
    

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

// calcule all camp in the map
   
   public void addCamps(){

         for(ICell[] cl : map.getGrid()){
            for(ICell cc : cl){

                if(cc.getEntity()==camp){
                   campList.add((Camp)cc.getEntity());
                }
            }
         }

   }

   public ArrayList<Camp> getCampList() {
    ArrayList<Camp> temp = new ArrayList<>();
    for(ICell[] cl : map.getGrid()){
        for(ICell cc : cl){

            if(cc.getEntity()==camp){
               temp.add((Camp)cc.getEntity());
            }
        }
     }

     return temp;
   }
    
}
