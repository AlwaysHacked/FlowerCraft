package model.terrain;

import java.awt.Point;

import model.entity.IEntity;

public interface ICell {
	/** Supprime l'entité de la case et renvoie true si il y en avait une */
    boolean deleteEntity();
    
	/** Ajoute l'entité e si la case est vide sinon renvoie false
     * e is not null */
    boolean addEntity(IEntity e);
    
    /** Renvoie true si la case est accessible à e*/
    boolean isAccessible();
    
    /** Getters */
    
//    Donne l'abcisse de la case
    int getX();

//    Donne l'ordonnée de la case
    int getY();

//    Donne la position de la case
    Point getCoord();
    
//    Donne l'entité sur la case ou null si il n'y en a pas
    IEntity getEntity();

//    Donne le terrain de la case
    Terrain getTerrain();

//    Donne la valeur d'accessibilité pour a*
    int getA();
    
//    Créer un point au coordonnée x y
    Point createCoord(int x, int y);
    
//    Affiche la cellule dans le terminal
    void affiche();
}
