package model;

import java.util.Random;

import model.terrain.Cell;

public class Map {
//	Grid's size
	public static final int sizeGrid = 10;
    private final MainModel m;
    private Cell[][] grid;
    
//  Probabilities  
//		Allies' probabilities
    	private final int probNavi = 5; 	
//		Ennemies' probabilities

//    	Neutral animals probabilities
    
    private Random rand = new Random();
    
    public Map(MainModel model) {
        this.m = model;
        this.initGrid();
    }
    
    private void initGrid(){
    	this.grid = new Cell[sizeGrid][sizeGrid];
    	for(int i = 0; i < sizeGrid; i++)
    		for(int j = 0; j < sizeGrid; j++)
    			this.grid[i][j] = createCell(i, j);
    }
    
    private void randomGeneration() {
//    	we supppose there are 3 zones
//    	* the allies zone :
//    		navis with their animals are created
//    		50 % of map
//    	* the buffer zone :
//    		not friendly animals are located here
//    		15 % of map  
//    	* the ennemy zone : 
//    		ennemies appear on this 
//    		35 % of map
//		at first, there's only one Entity on a Cell
    	
    	int az = (int) (this.sizeGrid * .5);
    	int bz = (int) (this.sizeGrid * .15);
    	int ez = (int) (this.sizeGrid * .35);
    	
    	for(int i = 0; i < az; i++)
    		for(int j = 0; j < this.sizeGrid; j++) {
    			int r = rand.nextInt(100);
    			if (r <= this.probNavi)
    				this.grid[i][j].addEntity(new Navi(m, grid[i][j], this, 100, 5, 10, 20));
    		}
    }
    
    private Cell createCell(int x, int y) {
    	return new Cell(m, x, y);
    }
    
    // Getters
    public Cell getCell(int x, int y) {
    	return this.grid[x][y];
    }
  
    public void affiche_barre() {
    	System.out.print('@');
    	for (int j = 0; j < this.sizeGrid*2; j++) 
			System.out.print('_');
    	System.out.print('@');
    	System.out.println();
    }
    
    public void affiche() {
    	this.affiche_barre();
    	for (int i = 0; i < this.sizeGrid; i++) {
    		System.out.print('|');
    		for (int j = 0; j < this.sizeGrid; j++) {
//    			System.out.print(this.grid[j][i].getX() + " " + this.grid[j][i].getY());
    			this.grid[j][i].affiche();
    			System.out.print('|');
    		}
    		System.out.println();
    	}
    	this.affiche_barre();
    }
}
