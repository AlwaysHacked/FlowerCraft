package model.entity;

import model.Action;
import model.terrain.ICell;

public interface IEntity {
	
/**	
 * Cette fonction est utile pour `EntityControl`
 * `EC` a chaque unite de temps lui demandera d'update (faire son action).
 * vu le contenu de l'attribut `currentAction` 
 * la fonction correspondante sera executee 
*/
	void update();
	
//	war methods
/** prend une entite en argument
 * renvoie vraie si c'est un ennemi
 * faux sinon
 */
boolean isEnemy(IEntity ent);
	
/**
 * Examine le voisinage de l'entite
 * renvoie vraie un ennemi se trouve dans l'une des cases
 * faux sinon
 */
	IEntity canAttack() ;

	
/** Baisse les points de vie */ 
	void sufferAttack(int impact) ;
	
//	position methods
/** Selon la valeur, indique la possibilite de bouger a l'endroit demande*/
	boolean canMove(ICell c);
	
//	getters
	boolean isDead();
	Action getCurrentAction() ;
	Action[] possibleActions() ;
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
