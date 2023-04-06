package model.entity;

import java.util.ArrayList;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

public class Soldier extends Entity implements IEntity {
//	default
	private static final int def_health = 100;
	private static final int def_attack = 5;
	private static final int def_speed = 10;

//others
   
   public Camp camp;
   public Navi navi;

// constructer
	
	public Soldier(MainModel m, Cell c, Map map, int h, int a, int s) {
		super(m, c, map, h, a, s);
	}
	
	public Soldier(MainModel m, Cell c, Map map) {
		super(m, c, map, def_health, def_attack, def_speed);
	}

	@Override
	public boolean isEnemy(IEntity ent) {
		if(ent instanceof Navi){
           return ent instanceof Navi;
		} else{
			return ent instanceof Camp;
		}
		}
	
	// before solider go to find Camp, he check if there is navi or camp next to him.
	public void check(){
    //place all cases ordonnee
		int xR = super.position.getX()+1;
		int yR = super.position.getY();

		int xU = super.position.getX();
		int yU = super.position.getY()+1;

		int xL = super.position.getX()-1;
		int yL = yR;

		int xD = xU;
		int yD = super.position.getY()-1;

		Cell cellR = new Cell(model,xR,yR);
		Cell cellU = new Cell(model,xU,yU);
		Cell cellL = new Cell(model,xL,yL);
		Cell cellD = new Cell(model,xD,yD);

	//check the camp or navi is in those positions or no
        
	    if(cellR.getEntity() == navi || cellR.getEntity() == camp||cellD.getEntity() == navi ||cellD.getEntity() == camp)this.attack();
		if(cellL.getEntity() == navi || cellL.getEntity() == camp || cellU.getEntity() == navi||cellU.getEntity() == camp)this.attack();
	}

	// search for the camp



	//public void search(){
	//	super.model.campList
	//}

	public void SoilderAction(){
     //het the list of positions next to the soilder   
		ArrayList<ICell>n = super.map.neighbours(super.position);
    //check the neighbours is enemy or not
		for(int i = 0; i<n.size();i++){

			if(n.get(i).getEntity() != null){
               this.canAttack();
			   n.get(i).getEntity().sufferAttack(this.attack);
			}
		}

	//go to search the camp
    
	// this.search()

	}

	@Override
	public String toString() {
		return "s";
	}
}



