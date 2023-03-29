package model.entity;

import model.Action;
import model.terrain.ICell;

public interface IEntity {
//	war methods
	boolean isEnemy(Entity ent);
	boolean canAttack(Entity ent) ;
	void attack(Entity ent);
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
