package model;

import java.util.ArrayList;
import java.util.Random;

import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Cell;
import model.terrain.CellFactory;
import model.terrain.ICell;

public class Map {
//	Grid's size
	public final int sizeGrid;
    private final MainModel model;
    private ICell[][] grid;

    
    private Random rand = new Random();

	public Map(MainModel model, String[][] setup) {
		this.model = model;
		this.sizeGrid = setup.length;

//		Initialisation predeterminee de la carte
		this.grid = new ICell[sizeGrid][sizeGrid];
		CellFactory make = CellFactory.getInstance(model);
		for (int i = 0; i < sizeGrid; i++)
			for (int j = 0; j < sizeGrid; j++)
				grid[i][j] = make.createCell(i,j,setup[i][j]);

//		Création des entité
		EntityFactory factory = EntityFactory.getInstance(model);
		factory.createEntity(grid[6][4], "CAMP");
		factory.createEntity(grid[6][5], "NAVI");
		factory.createEntity(grid[6][3], "NAVI");
		factory.createEntity(grid[0][5], "SOLDIER");
//		factory.createEntity(grid[0][3], "SOLDIER");

	}

    
    /**
     * Calcul les voisins de la cellule c 
     * @param c la case dont on demande les voisins
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

    
    // Getters
    public ICell getCell(int x, int y) {
    	return this.grid[x][y];
    }

	public ICell[][] getGrid(){
		return this.grid;
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
