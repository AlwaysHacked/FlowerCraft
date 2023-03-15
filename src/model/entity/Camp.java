package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;


public class Camp extends MobileEntity implements IEntity{
    private final MainModel model;
    private final Cell campcell;

    public Camp(MainModel m, ICell c, Map map,int h,int a,int s){
        super(m, c, map, h, a, s);
        this.campcell = null;
        super.currentAction = null;
        //super.speed = 0;//
        //super.health = 10;//
        //super.attack = 0;//

    }
    
}
