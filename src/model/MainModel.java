package model;

public class MainModel {
    private final Panel panel;
    private final Map map;

    public MainModel() {
        panel = new Panel(this);
        map = null;//new Map(this);
    }
}
