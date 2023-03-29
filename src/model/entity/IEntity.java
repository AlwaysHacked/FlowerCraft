package model.entity;

import model.Action;
import model.terrain.ICell;

public interface IEntity {
	
	void update();
	
//	war methods
	boolean isEnemy(IEntity ent);
	IEntity canAttack() ;
	void attack();
	void sufferAttack(int impact) ;
	
//	position methods
	boolean canMove(ICell c);
	
//	getters
	Action getCurrentAction() ;
	int getHealth() ;
	int getAttack() ;
	int getSpeed() ;
	ICell getPosition() ;
	
//	setters
	void setCurrentAction(Action currentAction);
	void setHealth(int health);
	void setAttack(int attack);
	void setSpeed(int speed);
	void setPosition(ICell position);
}
