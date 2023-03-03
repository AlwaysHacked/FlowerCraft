package model.terrain;

import model.MainModel;

import java.awt.*;

public class Field extends Cell{
    public Field(MainModel model, Point p) {
        super(model, p);
    }

    public Field(MainModel model, int x, int y) {
        super(model, x, y);
    }

    @Override
    public Terrain getType() {
        return Terrain.FIELD;
    }

    @Override
    public boolean isAccessible() { return entity == null; }
}
