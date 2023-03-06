package model.terrain;

import model.MainModel;

import java.awt.*;

public class Forest extends Cell{
    public Forest(MainModel model, Point p) {
        super(model, p);
    }

    public Forest(MainModel model, int x, int y) {
        super(model, x, y);
        terrain = Terrain.FOREST;
    }

    @Override
    public boolean isAccessible() { return entity == null; }
}
