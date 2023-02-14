package model;

import model.terrain.Cell;

public interface IEntity {
//	war methods
	public boolean isEnemy(MobileEntity ent); 
	public boolean canAttack(MobileEntity ent, Cell c) ;
	public void attack(MobileEntity ent);
	public void sufferAttack(int impact) ;
	
//	position methods
	public boolean nextTo(Cell c);
	public boolean atSamePlace(Cell c);
	
//	getters
	public Action getCurrentAction() ;
	public int getHealth() ;
	public int getAttack() ;
	public int getSpeed() ;
	public Cell getPosition() ;
	
//	setters
	public void setCurrentAction(Action currentAction);
	public void setHealth(int health);
	public void setAttack(int attack);
	public void setSpeed(int speed);
	public void setPosition(Cell position);
}
