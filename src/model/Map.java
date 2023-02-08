package model;

public class Map {
//	Taille de la grille
	public static final int sizeGrid = 10;
    private final MainModel m;
    private Cell[][] grid;

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
