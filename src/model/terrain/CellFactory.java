package model.terrain;

import model.MainModel;

public class CellFactory {
    /** Singleton et factory pattern */
    private final MainModel model;
    private static volatile CellFactory instance;

    /** Partie singleton :
     * contrôle du constructeur
     * getInstance renvoie l'unique instance et
     * l'initalise si elle n'existe pas */
    private CellFactory(MainModel model) {
        this.model = model;
    }
    public static CellFactory getInstance(MainModel model) {
        if (instance == null) {
            synchronized (CellFactory.class) {
                if (instance == null) {
                    instance = new CellFactory(model);
                }
            }
        }
        return instance;
    }

    /** Partie factory :
     * créé des Cell aux coordonnées x y
     * et du type donné dans channel
     * mais avec le type apparent ICell */
    public ICell createCell(int x, int y, String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        return switch (channel) {
            case "BERRIES" -> new Berries(model, x, y);
            case "FIELD" -> new Field(model, x, y);
            case "FOREST" -> new Forest(model, x, y);
            case "WATER" -> new Water(model, x, y);
            default -> throw new IllegalArgumentException("Unknown channel " + channel);
        };
    }
}
