package model.terrain;

import java.awt.Point;
import java.util.ArrayList;

import model.Entity;

public interface ICell {
	/** Supprime l'entité e de la case et renvoie true si e etait sur la case */
	public boolean deleteEntity(Entity e);
    
	/** Ajoute l'entité e */
    public void addEntity(Entity e);
    
    /** Renvoie true si la case est accessible à e*/
    public boolean isAccessible(Entity e);
    
    /** Getters */
    
//    Donne l'abcisse de la case
    public int getX();
    
//    Donne l'ordonnée de la case
    public int getY();
    
//    Donne la position de la case
    public Point getCoord();
    
//    Donne la liste des entités sur la case
    public ArrayList<Entity> getEntities();

    
//    Créer un point au coordonnée x y
    public Point createCoord(int x, int y);
    
//    Affiche la cellule dans le terminal
    public void affiche();
}
