package model;

import java.util.ArrayList;
import java.util.Random;

import model.entity.Entity;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Cell;
import model.terrain.CellFactory;
import model.terrain.ICell;

public class Map {
//	Grid's size
	public final int sizeGrid;
    private final MainModel m;
    private ICell[][] grid;
    
//  Probabilities, have to be divided by 2  
//		Allies' probabilities (friend animals included)
    	private final int probNavi = 5;
//		Ennemies' probabilities
    	private final int probSoldier = this.probNavi + 4
    			;
//    	Neutral animals probabilities
    
    private Random rand = new Random();
    
    public Map(MainModel model) {
        this.m = model;
		this.sizeGrid = 10;

        this.initGrid();
    }

	public Map(MainModel model, String[][] setup) {
		this.m = model;
		this.sizeGrid = setup.length;

		/**
		 * Initialisation predeterminee de la carte
		 */
		this.grid = new ICell[sizeGrid][sizeGrid];
		CellFactory make = CellFactory.getInstance(model);
		for (int i = 0; i < sizeGrid; i++)
			for (int j = 0; j < sizeGrid; j++)
				grid[i][j] = make.createCell(i,j,setup[i][j]);
	}
    
	/**
	 * Initialisation de la grille caree de taille `sizeGrid`
	 */
    private void initGrid(){
    	this.grid = new Cell[sizeGrid][sizeGrid];
    	for(int i = 0; i < sizeGrid; i++) 
    		for(int j = 0; j < sizeGrid; j++) 
    			this.grid[i][j] = this.createCell(i, j);
    }
    
    /**
     * Calcul les voisins de la cellule c 
     * @param ICell c
     * @return les voisins dans ArrayList
     */
	public ArrayList<ICell> neighbours(ICell c) {
		ArrayList<ICell> n = new ArrayList<>();

		int ax = c.getX();
		int ay = c.getY();


		if(ax < this.sizeGrid - 1)     
		    n.add(this.getCell(ax+1, ay));
		    
		if(ay < this.sizeGrid - 1)     
		    n.add(this.getCell(ax, ay+1));
		    
		if(ax > 0)     
		    n.add(this.getCell(ax-1, ay));
		    
		if(ay > 0)     
		    n.add(this.getCell(ax, ay-1));    	    

		return n;
	}

    /**
     * Creation de Cell avec une apparition random des entites
     * L'algorithme d'apparition :
	we supppose there are 3 zones
	* the allies zone :
		navis with their friendly animals are created
		50 % of map
	* the buffer zone :
		only neutral animals are located here
		15 % of map  
	* the ennemy zone : 
		ennemies appear on this 
		35 % of map
    */
    private Cell createCell(int x, int y) {
    	int az = (int) (this.sizeGrid * .5);
    	int bz = (int) (this.sizeGrid * .15);
    	int ez = (int) (this.sizeGrid * .35);
    	
    	Cell c = new Cell(this.m, x, y);
    	int r = rand.nextInt(200);
    	
    	if (x < az) {
			if (r <= this.probNavi) {
				System.out.println(x + " " + y);
				c.addEntity(new Navi(this.m, c, this, 
						Entity.NAVI_HEALTH, Entity.NAVI_SPEED, Entity.NAVI_ATTACK));
			}
		}
    	else if (x < bz) {
    		
    	}
    	else {
    		if (r <= this.probNavi) {
				System.out.println(x + " " + y);
				c.addEntity(new Soldier(this.m, c, this, 100, 5, 10));
			}
    	}
    	
    	return c;
    }
    
    // Getters
    public ICell getCell(int x, int y) {
    	return this.grid[x][y];
    }
    
    
/**
 * Fonctions d'affichage  
 */
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
				System.out.print(this.grid[j][i].toString());
    			System.out.print('|');
    		}
    		System.out.println();
    	}
    	this.affiche_barre();
    }
}
