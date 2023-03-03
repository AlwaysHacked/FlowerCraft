package model.terrain;

import model.IEntity;
import model.MainModel;

import java.awt.*;

public class Water extends Cell{
    public Water(MainModel model, Point p) {
        super(model, p);
    }

    public Water(MainModel model, int x, int y) {
        super(model, x, y);
    }

    @Override
    public boolean deleteEntity() { return false; }

    @Override
    public boolean addEntity(IEntity e) { return false; }

    @Override
    public Terrain getType() {
        return Terrain.WATER;
    }
}
