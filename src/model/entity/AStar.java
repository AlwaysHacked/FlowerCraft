package model.entity;

import java.util.LinkedList;
import java.util.Stack;


import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

public class AStar {
	MainModel model;
	Map map;
	
	ICell start;
	ICell end;
	
	// two dimensional arrays containing two fields
	// the first one is if it's visited / initialized
	// the second one it's value
	int G[][][];
	int H[][][];
	int F[][][];
	
	Stack<ICell> path;
	
	public AStar(MainModel model, Map map, ICell start, ICell end) {
		this.model = model;
		this.map = map;
		
		this.start = start;
		this.end = end;
		
		G = new int[this.map.sizeGrid][this.map.sizeGrid][4]; init(G);
		H = new int[this.map.sizeGrid][this.map.sizeGrid][4]; init(H);
		F = new int[this.map.sizeGrid][this.map.sizeGrid][4]; init(F);
		
		path = new Stack<ICell>();
		
//		this.aStar();
	}
	
	private void init(int A[][][]) {
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) {
				A[i][j][0] = 0; // visit
				A[i][j][1] = 0; // value
				A[i][j][2] = -1; // for f : x, parents from what node it has been initialised
				A[i][j][3] = -1; //         y
			}
	}
		
	private void calcS(ICell inter) {
		int ax = inter.getX(), ay = inter.getY();
		int bx = end.getX(), by = end.getY();
		
		if(ax < this.map.sizeGrid - 1) {
			G[ax+1][ay][0] = 1;
			G[ax+1][ay][1] = this.map.getCell(ax+1, ay).getA() ;
			H[ax+1][ay][0] = 1;
			H[ax+1][ay][1] = dist(ax+1, ay, bx, by);
			F[ax+1][ay][1] = G[ax+1][ay][1] + H[ax+1][ay][1];
			if (inter.nextTo(this.map.getCell(ax+1, ay)) && F[ax+1][ay][2] == -1) F[ax+1][ay][2] = ax;
			if (inter.nextTo(this.map.getCell(ax+1, ay)) && F[ax+1][ay][3] == -1) F[ax+1][ay][3] = ay;
		}
		if(ay < this.map.sizeGrid - 1) {
			G[ax][ay+1][0] = 1;
			G[ax][ay+1][1] = this.map.getCell(ax, ay+1).getA();
			H[ax][ay+1][0] = 1;
			H[ax][ay+1][1] = dist(ax, ay+1, bx, by);
			F[ax][ay+1][1] = G[ax][ay+1][1] + H[ax][ay+1][1];
			if (inter.nextTo(this.map.getCell(ax, ay+1)) && F[ax][ay+1][2] == -1) F[ax][ay+1][2] = ax;
			if (inter.nextTo(this.map.getCell(ax, ay+1)) && F[ax][ay+1][3] == -1) F[ax][ay+1][3] = ay;
		}
		if(ax > 0) {
			G[ax-1][ay][0] = 1;
			G[ax-1][ay][1] = this.map.getCell(ax-1, ay).getA();
			H[ax-1][ay][0] = 1;
			H[ax-1][ay][1] = dist(ax-1, ay, bx, by);
			F[ax-1][ay][1] = G[ax-1][ay][1] + H[ax-1][ay][1];
			if (inter.nextTo(this.map.getCell(ax-1, ay)) && F[ax-1][ay][2] == -1) F[ax-1][ay][2] = ax;
			if (inter.nextTo(this.map.getCell(ax-1, ay)) && F[ax-1][ay][3] == -1) F[ax-1][ay][3] = ay;
		}
		if(ay > 0) {
			G[ax][ay-1][0] = 1;
			G[ax][ay-1][1] = this.map.getCell(ax, ay-1).getA();
			H[ax][ay-1][0] = 1;
			H[ax][ay-1][1] = dist(ax, ay-1, bx, by);
			F[ax][ay-1][1] = G[ax][ay-1][1] + H[ax][ay-1][1];
			if (inter.nextTo(this.map.getCell(ax, ay-1)) && F[ax][ay-1][2] == -1) F[ax][ay-1][2] = ax;
			if (inter.nextTo(this.map.getCell(ax, ay-1)) && F[ax][ay-1][3] == -1) F[ax][ay-1][3] = ay;
		}
	}
	
	
	private ICell smallest() {
		int x = -1, y = -1; 
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) 
				if(G[i][j][0] == 1 && H[i][j][0] == 1 && 
				F[i][j][0] == 0 && min > F[i][j][1]) {
					min = F[i][j][1];
					x = i;
					y = j;
				}
		F[x][y][0] = 1;
		return this.map.getCell(x, y);
	}

	public Stack<ICell> getPath() {
		aStar(this.start);
		
//		i'm searching for f value 1
//		if there's no 1 in f, the node / cell couldn't be found
		
		int x = -1 , y = -1;
		
		for (int i = 0; i < this.map.sizeGrid; i++)
			for (int j = 0; j < this.map.sizeGrid; j++) 
				if(F[i][j][0] == 1 && F[i][j][1] == 1) {
					x = i; y = j;
				}
		
		if (x == -1) return null;
		
		while(x != this.start.getX() && x != this.start.getY()) {
			this.path.add(this.map.getCell(x, y));
			x = F[x][y][2];
			y = F[x][y][3];
		}
		return this.path;
	}

	public void aStar(ICell parent)  {
		if (parent.samePlace(this.end)) 
			return ;

		calcS(parent);
		ICell smallest_f = smallest();

		aStar(smallest_f);
	}


	private int abs(int a) {
		return a > 0 ? a : -a;
	}
	
	private int dist(int ax, int ay, int bx, int by) {
		int res = this.abs(ax - bx);
		return res + abs(ay - by);
	}
	
	private void show(int A[][][]) {
    	this.map.showLineNums();
    	this.map.affiche_barre();
		for (int i = 0; i < this.map.sizeGrid; i++) {
    		System.out.print(i);
    		System.out.print('|');
    		for (int j = 0; j < this.map.sizeGrid; j++) {
    			System.out.print(Integer.toString(A[i][j][1]) + "\t|");
    		}
    		System.out.println();
		}
		this.map.affiche_barre();
	}
	
	private void showID(int A[][][]) {
    	this.map.showLineNums();
    	this.map.affiche_barre();
		for (int i = 0; i < this.map.sizeGrid; i++) {
    		System.out.print(i);
    		System.out.print('|');
    		for (int j = 0; j < this.map.sizeGrid; j++) {
    			System.out.print((A[i][j][2]) + " " + (A[i][j][3]) + " \t|");
    		}
    		System.out.println();
		}
		this.map.affiche_barre();
	}

	
//	private Queue<ICell> smallest() {
//	int x = inter.getX(), y = inter.getY();
//	int min_val = Integer.MAX_VALUE;
//	
//	Queue<ICell> min = new Queue<>();
//	
//	for (int i = 0; i < this.map.sizeGrid; i++) 
//		for (int j = 0; j < this.map.sizeGrid; j++)
//			if (F[i][j][0] == 0 ) {
//				if(min_val > F[i][j][1] ) {
//					min_val = F[i][j][1];
//					min = new Queue<>();
//					min.add(new Cell(this.model, i, j));
//				}
//				else if(min_val == G[i][j][1]) {
//					min.add(new Cell(this.model, i, j));
//				}
//			}
//	return min;
//}

	
}
