package model.entity;

import java.util.ArrayList;
import java.util.Random;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

import static model.Action.ATTACK;

public class Soldier extends Entity implements IEntity {
//	default
	private static final int def_health = 100;
	private static final int def_attack = 5;
	private static final int def_speed = 10;
	private static final Random rand = new Random();

//others
   
   public Camp camp;
   public Navi navi;

// constructeur
	
	public Soldier(MainModel m, ICell c) {
		super(m, c, def_health, def_attack, def_speed);
		currentAction = ATTACK;
	}

	
	//public void move() {
	//	if (destination == position) {
	//		destination = (ICell) model.camps.get(rand.nextInt(model.camps.size()));
	//	} else{
			
			// if (path.empty()) generatePath();
			// if (canMove(this.path.peek())) {
			// 	this.position.deleteEntity();
			// 	this.position = this.path.pop();
			// 	this.position.addEntity(this);
			// } else generatePath();
	//	}
	//}
    
	//go to aleartoire camps(n'importe lequele)
	@Override
	public void move() {
		destination = (ICell) model.camps.get(rand.nextInt(model.camps.size()));
			
			if (path.empty()) {
				generatePath();
			} else {
				for(var i : path)System.out.println(i.getCoord());
				if (canMove(this.path.peek())) {
					this.path.peek().addEntity(this);
					this.position.deleteEntity();
					this.position = this.path.peek();
					this.path.pop();///////

				} // else generatePath();
			}
		}
	}



	@Override
	public boolean isEnemy(IEntity ent) {
           return ent instanceof Navi || ent instanceof Camp;
	}

	public void SoilderAction(){
		int nb = 0;
	// before solider go to find Camp, he check if there is navi or camp next to him.
     //have the list of positions next to the soilder   
		ArrayList<ICell>n = super.map.neighbours(super.position);
    //check the neighbours is enemy or not
		for(int i = 0; i<n.size();i++){

			if(n.get(i).getEntity() != null){
               this.canAttack();
			   n.get(i).getEntity().sufferAttack(this.attack);
			   break;//because soilder only can attack once after soilder"s attack , 
			   //it"s navi's turn
			   nb = nb + 1;
			}
		}
	//and then if 4 cells around soilder is null he goes to aleatoire camp.
        if(nb == 0)this.move(); //nb == 0 so all the cells are null /vide

	}
	
	
	@Override
	public String toString() {
		return "s";
	}
}



