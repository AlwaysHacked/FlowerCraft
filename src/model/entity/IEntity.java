package model.entity;

import model.Action;
import model.terrain.Cell;

public interface IEntity {
//	war methods
	boolean isEnemy(MobileEntity ent);
	boolean canAttack(MobileEntity ent, Cell c) ;
	void attack(MobileEntity ent);
	void sufferAttack(int impact) ;
	
//	position methods
	boolean nextTo(Cell c);
	boolean atSamePlace(Cell c);
	
//	getters
	Action getCurrentAction() ;
	int getHealth() ;
	int getAttack() ;
	int getSpeed() ;
	Cell getPosition() ;
	
//	setters
	void setCurrentAction(Action currentAction);
	void setHealth(int health);
	void setAttack(int attack);
	void setSpeed(int speed);
	void setPosition(Cell position);
}
