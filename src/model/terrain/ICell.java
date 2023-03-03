package model.terrain;

import java.awt.Point;
import java.util.ArrayList;

import model.IEntity;

public interface ICell {
	/** Supprime l'entité de la case et renvoie true si il y en avait une */
	public boolean deleteEntity();
    
	/** Ajoute l'entité e si la case est vide sinon renvoie false
     * e is not null */
    public boolean addEntity(IEntity e);
    
    /** Renvoie true si la case est accessible à e*/
    public boolean isAccessible();
    
    /** Getters */
    
//    Donne l'abcisse de la case
    public int getX();

//    Donne l'ordonnée de la case
    public int getY();

//    Donne la position de la case
    public Point getCoord();
    
//    Donne l'entité sur la case ou null si il n'y en a pas
    public IEntity getEntity();

//    Donne le type de la case
    public Terrain getType();
    
//    Créer un point au coordonnée x y
    public Point createCoord(int x, int y);
    
//    Affiche la cellule dans le terminal
    public void affiche();
}
