package model.terrain;

import model.MainModel;

import java.awt.*;

public class Field extends Cell{
    public Field(MainModel model, Point p) {
        super(model, p);
        terrain = Terrain.FIELD;
    }

    public Field(MainModel model, int x, int y) {
        super(model, x, y);
        terrain = Terrain.FIELD;
    }

    @Override
    public boolean isAccessible() { return entity == null; }
}
