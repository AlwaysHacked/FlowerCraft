package model;

public class MapFactory {
    /** Singleton et factory pattern */
    private final MainModel model;
    private static volatile MapFactory instance;

    /** Cartes pré-définies : */

    private final static String[][] deforest =
            {       {"FIELD",   "BERRIES",  "BERRIES",  "FIELD",   "BERRIES",   "BERRIES",  "FIELD",   "BERRIES",  "BERRIES",   "FIELD"},
                    {"FIELD",   "BERRIES",  "BERRIES",  "WATER",   "BERRIES",   "BERRIES",  "FIELD",   "BERRIES",  "BERRIES",   "FIELD"},
                    {"FIELD",   "BERRIES",  "BERRIES",  "WATER",   "WATER",   "WATER",  "FIELD",   "BERRIES",  "BERRIES",   "FIELD"},
                    {"FIELD",   "FIELD",    "FIELD",    "WATER",   "FIELD",     "FIELD",    "FIELD",   "FIELD",    "FIELD",     "FIELD"},
                    {"FIELD",   "FOREST",   "FIELD",    "FIELD",   "FOREST",    "FIELD",    "FIELD",   "FIELD",    "FIELD",     "FOREST"},
                    {"FOREST",  "FOREST",   "FOREST",   "FOREST",  "FOREST",    "FOREST",   "FOREST",  "FOREST",   "FOREST",    "FOREST"},
                    {"FOREST",  "WATER",    "WATER",    "FOREST",  "FOREST",    "FOREST",   "WATER",  "FOREST",   "BERRIES",   "FOREST"},
                    {"WATER",   "WATER",    "WATER",    "FOREST",  "BERRIES",   "WATER",   "FOREST",  "FOREST",   "FOREST",    "FOREST"},
                    {"WATER",   "WATER",    "BERRIES",  "FOREST",  "FOREST",    "FOREST",   "WATER",  "FOREST",   "BERRIES",   "FOREST"},
                    {"FOREST",  "FOREST",   "FOREST",   "FOREST",  "FOREST",    "FOREST",   "FOREST",  "FOREST",   "FOREST",    "FOREST"}
            };

    /** Partie singleton :
     * contrôle du constructeur
     * getInstance renvoie l'unique instance et
     * l'initalise si elle n'existe pas 
    */
    private MapFactory(MainModel model) {
        this.model = model;
    }
    public static MapFactory getInstance(MainModel model) {
        if (instance == null) {
            synchronized (MapFactory.class) {
                if (instance == null) {
                    instance = new MapFactory(model);
                }
            }
        }
        return instance;
    }

    /** Partie factory :
     * créé la Map avec le nom donnée */
    public Map createMap(String channel) {
        if (channel == null || channel.isEmpty())
            return null;
        return switch (channel) {
            case "DEFOREST" -> new Map(model, deforest);
            default -> throw new IllegalArgumentException("Unknown channel " + channel);
        };
    }
}
