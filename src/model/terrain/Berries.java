package model.terrain;

import model.MainModel;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Berries extends Cell{
    public static final int MAX_FOOD = 100;
    private int food = MAX_FOOD;

    public Berries(MainModel model, Point p) {
        super(model, p);
        terrain = Terrain.BERRIES;
    }

    public Berries(MainModel model, int x, int y) {
        super(model, x, y);
        terrain = Terrain.BERRIES;
    }

    @Override
    public boolean isAccessible() { return entity == null; }

    @Override
    public String toString() {
        return "B" + super.toString();
    }

    public boolean update() {
        if (food > 0) {
            food = Integer.max(MAX_FOOD, food + 5);
            return true;
        } else {
            terrain = Terrain.FIELD;
            return false;
        }
    }
}
