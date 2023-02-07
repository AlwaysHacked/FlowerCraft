package model;

public class Map {
//	Taille de la grille
	public static final int sizeGrid = 20;
    private final MainModel m;
    private Cell[][] grid;

    public Map(MainModel model) {
        this.m = model;
        grid = new Cell[sizeGrid][sizeGrid];
    }
    
    private void initGrid(){
    	for(int i = 0; i < sizeGrid; i++)
    		for(int j = 0; j < sizeGrid; j++)
    			grid[i][j] = createCell();
    }
    
    private Cell createCell() {
    	return new Cell(m);
    }
    
    
    
    // Getters
    public Cell getCell(int x, int y) {
    	return this.grid[x][y];
    }
}
