package model.terrain;

import model.entity.IEntity;
import model.MainModel;

import java.awt.*;

public class Water extends Cell {
    public Water(MainModel model, Point p) {
        super(model, p);
        terrain = Terrain.WATER;
    }

    public Water(MainModel model, int x, int y) {
        super(model, x, y);
        terrain = Terrain.WATER;
    }

    @Override
    public boolean deleteEntity() {
        return false;
    }

    @Override
    public boolean addEntity(IEntity e) {
        return false;
    }

    @Override
    public int getA(){
        return 20000;
    }

    @Override
    public String toString() {
        return "W" + super.toString();
    }
    
    @Override
    public boolean isAccessible() { return false; }
}
