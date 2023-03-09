package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

public class MobileEntity implements IEntity {
	private MainModel model;
	private ICell position;
	private Map map;
	
	private int health;
	private int attack;
	private int speed;
	
	protected Action currentAction;

//	IDEA : special bool cases : invicibility / rage / ...
	
	public MobileEntity(MainModel m, ICell c, Map map, int h, int a, int s) {
		this.model = m;
		this.position = c;
		this.map = map;
		
		this.health = h;
		this.attack = a;
		this.speed = s;
	}
	
//	war methods
//	does it work?
	@Override
	public boolean isEnemy(MobileEntity ent) {
		if (this instanceof Navi)
			return ent instanceof Soldier;
		else if (this instanceof Soldier)
			return ent instanceof Navi;
		else 
			return false;
	}
	
//	Can attack if the Entity is next to him and is his enemy
	@Override
	public boolean canAttack(MobileEntity ent, ICell c) {
//		TODO
//		return this.nextTo(c) && c.getEntities().contains(ent);	/*&& this.isEnemy(ent)*/
		return false;
	}
	
	@Override
	public void attack(MobileEntity ent) {
		ent.sufferAttack(this.attack);
	}
	@Override
	public void sufferAttack(int impact) {
		this.health -= impact;
	}
	
	
//	position methods
	@Override
	public boolean nextTo(ICell c) {
		int x = c.getX();
		int y = c.getY();
		
		return 	this.map.getCell(x+1, y).equals(c) ||
				this.map.getCell(x-1, y).equals(c) ||
				this.map.getCell(x, y+1).equals(c) ||
				this.map.getCell(x, y-1).equals(c) ;
	}

	@Override
	public boolean atSamePlace(ICell c) {
		return c.equals(this.position);
	}
	
	private int abs(int a) {
		return a > 0 ? a : -a;
	}
	
//	private int calcG(Cell a, Cell b) {
//		int res = this.abs(a.getX() - b.getX());
//		return res + abs(a.getY() - b.getY());
//	}
	
	private int dist(int ax, int ay, int bx, int by) {
		int res = this.abs(ax - bx);
		return res + abs(ay - by);
	}
	
	private void calcS(int G[][], int H[][], int F[][], ICell a, ICell b) {
//		System.out.println(Integer.toString(ax) + ' ' + Integer.toString(ay));
		int ax = a.getX(), ay = a.getY();
		int bx = b.getX(), by = b.getY();
		
		if(ax < this.map.sizeGrid - 1) {
//			G[ax+1][ay] = this.map.getCell(ax+1, ay).getTerrain().value;
	        H[ax+1][ay] = dist(ax+1, ay, bx, by);
	        F[ax+1][ay] = G[ax+1][ay] + H[ax+1][ay];
		}
		if(ay < this.map.sizeGrid - 1) {
//		    G[ax][ay+1] = this.map.getCell(ax, ay+1).getTerrain().value;
		    H[ax][ay+1] = dist(ax, ay+1, bx, by);
		    F[ax][ay+1] = G[ax][ay+1] + H[ax][ay+1];
		}
		if(ax > 0) {
//		    G[ax-1][ay] = this.map.getCell(ax-1, ay).getTerrain().value;
		    H[ax-1][ay] = dist(ax-1, ay, bx, by);
		    F[ax-1][ay] = G[ax-1][ay] + H[ax-1][ay];
		}
		if(ay > 0) {
//		    G[ax][ay-1] = this.map.getCell(ax, ay-1).getTerrain().value;
		    H[ax][ay-1] = dist(ax, ay-1, bx, by);
		    F[ax][ay-1] = G[ax][ay-1] + H[ax][ay-1];
		}
	}
	
	private void init(int A[][]) {
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) 
				A[i][j] = -1;
	}
	
	private void show(int A[][]) {
    	this.map.showLineNums();
    	this.map.affiche_barre();
		for (int i = 0; i < this.map.sizeGrid; i++) {
    		System.out.print(i);
    		System.out.print('|');
    		for (int j = 0; j < this.map.sizeGrid; j++) {
    			System.out.print(Integer.toString(A[i][j]) + '|');
    		}
    		System.out.println();
		}
		this.map.affiche_barre();
	}
	
	private void smallest(int A[][], int[] coord) {
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < this.map.sizeGrid; i++) 
			for (int j = 0; j < this.map.sizeGrid; j++) 
				if(min > A[i][j] && A[i][j] > -1) {
					min = A[i][j];
					coord[0] = i;
					coord[1] = j;
				}
	}
	
//	as i see it, we'd like the method to return an array of `Cell`s
//	so the entity just follows the one path
//	in this case the method may not be here...
//	in the meantime, i'll start here
//	Best Regards.
//	Serge
	public boolean aStar(ICell dest) {
		int depX = this.position.getX();
		int depY = this.position.getY();
		
		int arrX = dest.getX();
		int arrY = dest.getY();
//		we need another map with three numbers in it, starting with the cells surrounding the starting point::
//		* the distance of each cell from the starting point (G cost)
//		* the distance of each cell from the arriving point (H cost)
//		* the sum G + H of each cell (F cost)
//		ArrayList<ArrayList<int[]>> values = new ArrayList<>(this.map.sizeGrid);  
		int G[][] = new int[this.map.sizeGrid][this.map.sizeGrid]; init(G);
		int H[][] = new int[this.map.sizeGrid][this.map.sizeGrid]; init(H);
		int F[][] = new int[this.map.sizeGrid][this.map.sizeGrid]; init(F);
		
//		calcS(G, H, F, depX, depY, arrX, arrY);
		
//		next we will choose the cell containing the smallest F cost and will choose as the new starting point
//		boolean notFound = true;
		while(depX != arrX || depY != arrY) {
//		for(int i = 0; i < 2; i++) {
			int[] coord = {0,0};
			smallest(F, coord); 
			depX = coord[0];
			depY = coord[1];
			calcS(G, H, F, this.position, dest);
			System.out.println("G:");
			show(G);
			System.out.println("H:");
			show(H);
			System.out.println("F:");
			show(F);
			
//			System.out.println(Integer.toString(coord[0]) + ' ' + Integer.toString(coord[1]));
			System.out.println(Integer.toString(depX) + ' ' + depY);
			System.out.println(depX == arrX && depY == arrY);
			System.out.println(Integer.toString(arrX) + ' ' + arrY);
//			what is needed to be done
//			* in case if there's not a unique minimal value in F, 
//			  a comparison is also needed with the H cases
//			
			
		}
		return false;
	}

	//	getters
	@Override
	public Action getCurrentAction() {return this.currentAction;}
	@Override
	public int getHealth() {return this.health;}
	@Override
	public int getAttack() {return this.attack;}
	@Override
	public int getSpeed() {return this.speed;}
	@Override
	public ICell getPosition() {return this.position;}
	
	// setters
	@Override
	public void setCurrentAction(Action currentAction) {this.currentAction = currentAction;}
	@Override
	public void setHealth(int health) {this.health = health;}
	@Override
	public void setAttack(int attack) {this.attack = attack;}
	@Override
	public void setSpeed(int speed) {this.speed = speed;}
	@Override
	public void setPosition(ICell position) {this.position = position;}

//	other
	public Action[] possibleActions() {
		return Action.values();
	}
	
}
