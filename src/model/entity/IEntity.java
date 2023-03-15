package model.entity;

import model.Action;
import model.terrain.Cell;
import model.terrain.ICell;

public interface IEntity {
//	war methods
	boolean isEnemy(MobileEntity ent);
	boolean canAttack(MobileEntity ent, ICell c) ;
	void attack(MobileEntity ent);
	void sufferAttack(int impact) ;
	
//	position methods
	boolean nextTo(ICell c);
	
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
