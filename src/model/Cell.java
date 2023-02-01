package model;

import java.util.ArrayList;

public class Cell {
    private final MainModel model;
    private final ArrayList<Entity> entities = new ArrayList<>();


    public Cell(MainModel model) {
        this.model = model;
    }
}
