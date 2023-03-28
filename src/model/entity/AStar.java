package model.entity;

import java.util.LinkedList;
import java.util.ArrayList;
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
	
	boolean succes = false;
	
	// two dimensional arrays containing two fields
	// the first one is if it's visited / initialized
	// the second one it's value
	int G[][][];
	int H[][][];
	int F[][][];
	
	ArrayList<ICell> path;
	ArrayList<ICell> p = new ArrayList<>();
	
	public AStar(MainModel model, Map map, ICell start, ICell end) {
		this.model = model;
		this.map = map;
		
		this.start = start;
		this.inter = start;
		this.end = end;
		
		G = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(G);
		H = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(H);
		F = new int[this.map.sizeGrid][this.map.sizeGrid][2]; init(F);
		
		path = new ArrayList<ICell>();
//				new LinkedList<ICell>();
		
//		this.aStar();
	}
	
	private void init(int A[][][]) {
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) {
				A[i][j][0] = 0;
				A[i][j][1] = 0;
			}
	}
		
	private void calcS() {
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
	
//	private void getP(int x, int y){
//		ICell prev = this.path.get(0);
//		for(int i = 1; i < this.path.size(); i++) {
////			System.out.println(prev.getX() + " " + prev.getY());
//			ICell p = this.path.get(i);
//			if (!prev.nextTo(p)) {
//				System.out.println(prev.getX() + " " + prev.getY() + ") && (" + p.getX() + " " + p.getY());
//				System.out.println(this.path.get(i+1).getX() + " " + this.path.get(i+1).getY());
//				this.path.remove(i);
//				F[p.getX()][p.getY()][0] = 0;
//			}
//			else {
////				System.out.println();
//				prev = p;
//			}
//		}
//	}
	
	private void getP(int ax, int ay) {
		int up, down, right, left,
			minx = Integer.MAX_VALUE, miny = minx;
		int min = F[ax][ay][1];
//		show(F);
//		System.out.println(F[ax][ay][1]);
		
		if (end.getX() == ax && end.getY() == ay)
			return;
		
		for(int i = -1; i <= 1; i+=2)
			try{
//				System.out.println(ax+i + " " + ay);
				if (F[ax+i][ay][1] < min && F[ax+i][ay][0] == 1) {
					min = F[ax+i][ay][1];
					minx = ax+i; miny = ay;
				}
			}
			catch(Exception e){}
	
		for(int i = -1; i <= 1; i+=2)
			try{
//				System.out.println(ax + " " + (ay+i));
				if (F[ax][ay+i][1] < min && F[ax][ay+i][0] == 1) {
					min = F[ax][ay+i][1];
					minx = ax; miny = ay+i;
				}
			}
			catch(Exception e){}
		try{System.out.println(minx + " " + miny + " " + F[minx][miny][1]);}
		catch(Exception e) {}
//		System.out.println();
		if(minx < Integer.MAX_VALUE) {
			this.getP(minx, miny);
			return;
		}
		
	}
			
			
//		if(ax < this.map.sizeGrid - 1) 
//		    F[ax+1][ay][1] = G[ax+1][ay][1] + H[ax+1][ay][1];
//		else
//		    right = 1000;
//	
//		if(ay < this.map.sizeGrid - 1) 
//		    F[ax][ay+1][1] = G[ax][ay+1][1] + H[ax][ay+1][1];
//		else
//		    down = 1000;
//	
//		if(ax > 0) 
//		    F[ax-1][ay][1] = G[ax-1][ay][1] + H[ax-1][ay][1];
//		else
//		    left = 1000;
//	
//		if(ay > 0) 
//		    F[ax][ay-1][1] = G[ax][ay-1][1] + H[ax][ay-1][1];
//		else
//		    up = 10000;
//	}

	public ArrayList<ICell> getPath() {
		aStar();
		show(F);
		this.getP(start.getX(), start.getY());
		return path;
	}

	public boolean aStar()  {
		System.out.println(this.inter.getTerrain());
		if (this.inter.samePlace(this.end)) {
//			this.path.add(this.inter);
			return true;
		}
//		map is not included ?
		// code here
		calcS();
		ICell coord = smallest();
		this.inter = coord;
//		System.out.println("G:");
//		show(G);
//		System.out.println("H:");
//		show(H);
//		System.out.println("F:");
//		show(F);
		if(aStar()){
			this.path.add(coord);
			return true;
		}
		else return false;
//		Pair p = new Pair();
//		while(!this.inter.samePlace(this.end)) {
//			System.out.println(Integer.toString(inter.getX()) + " " + Integer.toString(inter.getY()));
//
//			inter = smallest();
//
//
////			try {
////				Thread.sleep(5000);
////			} catch (InterruptedException e) {
////			}
////			what is needed to be done
////			* in case if there's not a unique minimal value in F,
////			  a comparison is also needed with the H cases
//		}
//		return false;
	}

//	private bool
	
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
