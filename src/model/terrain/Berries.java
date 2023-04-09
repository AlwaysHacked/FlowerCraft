package model.terrain;

import model.MainModel;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Berries extends Cell{
	private static final int HARVEST = 10;
	private static final int REGEN = 2;
    public static final int MAX_FOOD = 100;
    private int food = MAX_FOOD;
    private String frame = "BERRIES";
    private int iconFrame = 1;

    public Berries(MainModel model, Point p) {
        super(model, p);
        super.terrain = Terrain.BERRIES;
    }

    public Berries(MainModel model, int x, int y) {
        super(model, x, y);
        super.terrain = Terrain.BERRIES;
    }

    @Override
    public boolean isAccessible() { return entity == null; }

    @Override
    public String toString() {
        return "B" + super.toString();
    }

    /**
     * Baisse et renvoie le nombre de ressources
     * cueillables definie dans HARVEST
     */
    public int isBeingHarvested() {
//        System.out.println("amount of food" + this.food);
    	if(this.food > 9){
            this.food -= HARVEST;
            return HARVEST;
        }
    	return 0;
    }
    
    /**
     * Fonction de mise a jour demandee par le controleur
     * Si berries le nombre de baies arrive a 0, 
     * le terrain devient un field
     * Sinon il se regenere
     */
    public boolean update() {
        if (food > 0) {
            food = Integer.min(MAX_FOOD, food + REGEN);
            return true;
        } else {
            super.terrain = Terrain.FIELD;
            return false;
        }
    }

    /**
     * @return int return the food
     */
    public int getFood() {
        return food;
    }

    public void newFrame(){
            if(food == 100) this.frame = "BERRIES";
            else if (food == 0) this.frame = "tile039";
            else if (food > 70) this.frame = "tile015";
            else if (food > 40) this.frame = "tile005";
            else if (food > 10) this.frame = "tile004";
            else if (food > 0) this.frame = "tile003";
    }

    

    /**
     * @param food the food to set
     */
    public void setFood(int food) {
        this.food = food;
    }


    /**
     * @return String return the frame
     */
    public String getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(String frame) {
        this.frame = frame;
    }
    

}
