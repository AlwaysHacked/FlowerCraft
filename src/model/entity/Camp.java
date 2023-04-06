
package model.entity;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.*;


public class Camp extends Entity implements IEntity{
    public static int RESSOURCES = 0;

    public Camp(MainModel m, ICell c, Map map){
        super(m, c, map, 0, 12, 0);
        super.currentAction = null;

    }
    
    /**
	 * Cree un navi sur l'une des cases voisines
	 * @return false si le navi n'a pas pu être créé
	 */
	public boolean createNavi(){
		ICell navC = null;
		for(ICell c : this.map.neighbours(this.position))
			if(c.isAccessible()) {
				navC = c;
				break;
			}
		EntityFactory.getInstance(model).createEntity(navC, "NAVI");
		return navC != null;
	}
    
	/**
	 * Enleve des points de vie a l'ennemi
	 * Et verifie si le camp est detruit
	 */
   // @Override
    //public void sufferAttack(int impact) {
    //	super.sufferAttack(impact);
    //	deleteCamp();
    //}
    
    /**
	 * Si le camp n'a plus de vie, l'enleve de map
	 */
	//public void deleteCamp(){
	//    if(super.getHealth() <= 0)
	 //       campcell.deleteEntity();
	//}
}