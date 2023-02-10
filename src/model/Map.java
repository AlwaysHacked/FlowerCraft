package model;

import java.util.Random;

import model.terrain.Cell;

public class Map {
//	Grid's size
	public static final int sizeGrid = 10;
    private final MainModel m;
    private Cell[][] grid;
    
//  Probabilities  
//		Allies' probabilities (friend animals included)
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
    	for(int i = 0; i < sizeGrid; i++) {
    		for(int j = 0; j < sizeGrid; j++) {
//    			System.out.print(i + "," + j + " ");
    			this.grid[i][j] = this.createCell(i, j);
    		}
//    		System.out.println();
    	}
    }
    
//	we supppose there are 3 zones
//	* the allies zone :
//		navis with their animals are created
//		50 % of map
//	* the buffer zone :
//		not friendly animals are located here
//		15 % of map  
//	* the ennemy zone : 
//		ennemies appear on this 
//		35 % of map
//	at first, there's only one Entity on a Cell    	
    
    private Cell createCell(int x, int y) {
    	int az = (int) (this.sizeGrid * .5);
    	int bz = (int) (this.sizeGrid * .15);
    	int ez = (int) (this.sizeGrid * .35);
    	
    	Cell c = new Cell(m, x, y);
    	int r = rand.nextInt(100);
    	
    	if (x < az) {
			if (r <= this.probNavi) {
				System.out.println(x + " " + y);
				c.addEntity(new Navi(m, c, this, 100, 5, 10, 20));
			}
		}
    	else if (x < bz) {
    		
    	}
    	else {
    		
    	}
    	
    	return c;
    }
    
    // Getters
    public Cell getCell(int x, int y) {
    	return this.grid[x][y];
    }
    
    
    // printing in terminal
  
    public void showLineNums() {
    	System.out.print(" ");
    	for (int i = 0; i < this.sizeGrid; i++)
    		System.out.print(" " + i);
    	System.out.println();
    }
    
    public void affiche_barre() {
    	System.out.print(" @");
    	for (int j = 0; j < this.sizeGrid*2; j++) 
			System.out.print('_');
    	System.out.print('@');
    	System.out.println();
    }
    
    public void affiche() {
    	this.showLineNums();
    	this.affiche_barre();
    	for (int i = 0; i < this.sizeGrid; i++) {
    		System.out.print(i);
    		System.out.print('|');
    		for (int j = 0; j < this.sizeGrid; j++) {
    			this.grid[j][i].affiche();
    			System.out.print('|');
    		}
    		System.out.println();
    	}
    	this.affiche_barre();
    }
}
