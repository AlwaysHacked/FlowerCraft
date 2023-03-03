package model.terrain;

import model.MainModel;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Berries extends Cell implements Runnable{
    public static final int MAX_FOOD = 100;
    private int food = MAX_FOOD;

    public Berries(MainModel model, Point p) {
        super(model, p);
    }

    public Berries(MainModel model, int x, int y) {
        super(model, x, y);
    }

    @Override
    public Terrain getType() {
        return Terrain.BERRIES;
    }

    @Override
    public boolean isAccessible() { return entity == null; }

    @Override
    public void run() {
        while (food > 0) {
            if (food <= MAX_FOOD)
                food += 5;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
