package model.entity;

import java.util.LinkedList;
import java.util.Queue;


import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

public class AStar {
	MainModel model;
	Map map;
	
	ICell start;
	ICell inter;
	ICell end;
	
	// two dimensional arrays containing two fields
	// the first one is if it's visited / initialized
	// the second one it's value
	int G[][][];
	int H[][][];
	int F[][][];
	
	Queue<ICell> path;
	
	public AStar(MainModel model, Map map, ICell start, ICell end) {
		this.model = model;
		this.map = map;
		
		this.start = start;
		this.inter = start;
		this.end = end;
		
		G = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(G);
		H = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(H);
		F = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(F);
		
		path = new LinkedList<ICell>();
		
//		this.aStar();
	}
	
	private void init(int A[][][]) {
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) {
				A[i][j][0] = 0;
				A[i][j][1] = 0;
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
		}
		if(ay < this.map.sizeGrid - 1) {
		    G[ax][ay+1][0] = 1;
		    G[ax][ay+1][1] = this.map.getCell(ax, ay+1).getA();
		    H[ax][ay+1][0] = 1;
		    H[ax][ay+1][1] = dist(ax, ay+1, bx, by);
		    F[ax][ay+1][1] = G[ax][ay+1][1] + H[ax][ay+1][1];
		}
		if(ax > 0) {
		    G[ax-1][ay][0] = 1;
		    G[ax-1][ay][1] = this.map.getCell(ax-1, ay).getA();
		    H[ax-1][ay][0] = 1;
		    H[ax-1][ay][1] = dist(ax-1, ay, bx, by);
		    F[ax-1][ay][1] = G[ax-1][ay][1] + H[ax-1][ay][1];
		}
		if(ay > 0) {
		    G[ax][ay-1][0] = 1;
		    G[ax][ay-1][1] = this.map.getCell(ax, ay-1).getA();
		    H[ax][ay-1][0] = 1;
		    H[ax][ay-1][1] = dist(ax, ay-1, bx, by);
		    F[ax][ay-1][1] = G[ax][ay-1][1] + H[ax][ay-1][1];
		}
	}
	
//	private Queue<ICell> smallest() {
//		int x = inter.getX(), y = inter.getY();
//		int min_val = Integer.MAX_VALUE;
//		
//		Queue<ICell> min = new Queue<>();
//		
//		for (int i = 0; i < this.map.sizeGrid; i++) 
//			for (int j = 0; j < this.map.sizeGrid; j++)
//				if (F[i][j][0] == 0 ) {
//					if(min_val > F[i][j][1] ) {
//						min_val = F[i][j][1];
//						min = new Queue<>();
//						min.add(new Cell(this.model, i, j));
//					}
//					else if(min_val == G[i][j][1]) {
//						min.add(new Cell(this.model, i, j));
//					}
//				}
//		return min;
//	}
	private ICell smallest() {
		int x = inter.getX(), y = inter.getY(); 
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

	public Queue<ICell> getPath() {
		show(F);
		aStar(this.start);
		return path;
	}

	public void aStar(ICell parent)  {
		System.out.println(this.inter.getTerrain());
		if (parent.samePlace(this.end)) {
//			this.path.add(this.inter);
			return ;
		}

		calcS(parent);
		show(F);
		ICell smallest_f = smallest();
		System.out.println(smallest_f.getX());

		aStar(smallest_f);
		this.path.add(parent);
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

}
