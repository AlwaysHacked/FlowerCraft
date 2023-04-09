package model;

import model.entity.IEntity;
import model.entity.Navi;
import model.terrain.Berries;
import model.terrain.ICell;
import model.entity.Camp;

import java.util.Arrays;
import java.util.ArrayList;

public class MainModel {
    private static final int COUT_CAMP = 80;

    private final Map map;
    private boolean running = true;
    private ICell startCell = null;
    private ICell endCell = null;

    //  attribute for the List of Camp so solider ca attack it 

    public ArrayList<Camp> camps = new ArrayList<>();
    public ArrayList<IEntity> entities = new ArrayList<>();
    public int food = 0;

//    Attributs pour gérer les ordres des joueurs
    private IEntity selectedEntity = null;
    private Action selectedAction = null;
    

    public MainModel() {
        this.map = MapFactory.getInstance(this).createMap("DEFOREST");
    
        // ICell c = map.getCell(0, 0);
        // Navi n = new Navi(this, c);
        // if (c.getEntity() == null)
        // 	c.addEntity(n);
        // else {
        // 	c.deleteEntity();
        // 	c.addEntity(n);
        // }
        
    }
        
    public Map getMap() {
    	return this.map;
    }

    public boolean isRunning() {
        return running;
    }


    public boolean isGameOver() {return camps.isEmpty();}

    /* Méthode d'action du controleur sur le modèle */

    /** Recois la cellule cliquée et fais lance l'action */
    public void clic(ICell cell) {
        if (selectedEntity == null)
            selectedEntity = cell.getEntity();
        else if (selectedAction == null)
            selectedEntity = cell.getEntity();
        else {
            switch (selectedAction) {
                case MOVE, ATTACK -> {
                    selectedEntity.setCurrentAction(selectedAction);
                    selectedEntity.setDestination(cell);
                }
                case BUILD -> {
                    if (cell.isAccessible()) {
                        System.out.println("running rn");
                        selectedEntity.setCurrentAction(selectedAction);
                        selectedEntity.setDestination(cell);
                    }
                }
                case HARVEST -> {
                    if (cell.isAccessible() && cell instanceof Berries){
                        selectedEntity.setCurrentAction(selectedAction);
                        selectedEntity.setDestination(cell);
                    }
                }
                case CREATE, STOP -> selectedEntity = cell.getEntity();
            }
            selectedAction = null;
        }
    }

    /** Recois l'action cliquée et fais lance l'action ou attends un clic */
    public void selectAction(Action action) {
        
        if (selectedEntity != null && Arrays.stream(selectedEntity.possibleActions()).toList().contains(action))
            switch (action) {
                case HARVEST, BUILD, MOVE, ATTACK -> this.selectedAction = action;
                case STOP, CREATE -> selectedEntity.setCurrentAction(action);
            }
    }

    public ArrayList<Camp> getCamps() {
       return camps;
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

    public IEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @param selectedEntity the selectedEntity to set
     */
    public void setSelectedEntity(IEntity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    /**
     * @return Action return the selectedAction
     */
    public Action getSelectedAction() {
        return selectedAction;
    }

    /**
     * @param selectedAction the selectedAction to set
     */
    public void setSelectedAction(Action selectedAction) {
        this.selectedAction = selectedAction;
    }

}
