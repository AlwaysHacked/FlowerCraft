package model;

public class Map {
//	Taille de la grille
	public static final int sizeGrid = 20;
    private final MainModel m;
    private Cell[][] grid;

    public Map(MainModel model) {
        this.m = model;
        this.initGrid();// = new Cell[sizeGrid][sizeGrid];
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
    
    public void affiche() {
    	for (int i = 0; i < this.sizeGrid; i++) {
    		for (int j = 0; j < this.sizeGrid; j++) {
    			this.grid[i][j].affiche();
    			System.out.print(' ');
    		}
    		System.out.println();
    	}
    }
}
