package model.entity;

import control.EntityControl;
import model.MainModel;
import model.terrain.*;

public class EntityFactory {
    private final MainModel model;
    private static volatile EntityFactory instance;

    private EntityFactory(MainModel model) {
        this.model = model;
    }
    public static EntityFactory getInstance(MainModel model) {
        if (instance == null) {
            synchronized (EntityFactory.class) {
                if (instance == null) {
                    instance = new EntityFactory(model);
                }
            }
        }
        return instance;
    }

    /** Partie factory :
     * créé des Cell aux coordonnées x y
     * et du type donné dans channel
     * mais avec le type apparent channel */
    public void createEntity(ICell cell, String channel) {
        if (channel == null || channel.isEmpty())
            return;
        IEntity entity;
        EntityControl thread;
        switch (channel) {
            case "NAVI" -> entity = new Navi(model, cell);
            case "SOLDIER" -> entity = new Soldier(model, cell);
            case "CAMP" -> {
                entity = new Camp(model, cell);
                model.camps.add((Camp) entity);
            }
            default -> throw new IllegalArgumentException("Unknown channel " + channel);
        }
        cell.addEntity(entity);
        model.entities.add(entity);
        //thread = new EntityControl(this.model, entity);
        //thread.run();
    }
}