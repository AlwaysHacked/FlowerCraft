package model.entity;

import java.util.ArrayList;
import java.util.Random;

import model.MainModel;
import model.terrain.ICell;

import static model.Action.ATTACK;

public class Soldier extends Entity implements IEntity {
	// default
	private static final int def_health = 100;
	private static final int def_attack = 3;
	private static final int def_speed = 10;
	private static final Random rand = new Random();

	// others

	public Camp camp;
	public Navi navi;

	// constructeur

	public Soldier(MainModel m, ICell c) {
		super(m, c, def_health, def_attack, def_speed);
		currentAction = ATTACK;
	}

	public void move() {
		if (destination == null) {
			//look at free cells around camp
			Camp camp =  model.camps.get(rand.nextInt(model.camps.size()));
			ArrayList<ICell> tab = new ArrayList<>();
			for(var i : this.model.getMap().getGrid()){
				for(var j : i){
					if(j.nextTo(camp.getPosition()) && j.getEntity() == null){
						tab.add(j);
					}
				}
			}
			destination = tab.get(0);
			
		} else if(position==destination) {
//			System.out.println("renew destination");
			destination = null;
		} else{
			
			if (path.empty()) {
				generatePath();
			} else {
				
				//for(var i : path)System.out.println(i.getCoord());
				if (canMove(this.path.peek())) {
					this.path.peek().addEntity(this);
					this.position.deleteEntity();
					this.position = this.path.peek();
					this.path.pop();///////

				} else generatePath();
			}
		}
	}



	@Override
	public boolean isEnemy(IEntity ent) {
		return ent instanceof Navi || ent instanceof Camp;
	}
	
	@Override
	public String toString() {
		return "s";
	}
}
