
package model.entity;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.*;


public class Camp extends Entity implements IEntity{
    public static int RESSOURCES = 0;
	private final Cell campcell;

    public Camp(MainModel m, ICell c, Map map,int h,int a,int s){
        super(m, c, map, h, a, s);
        this.campcell = null;
        super.currentAction = null;
        super.setSpeed(0);
        super.setHealth(12);
        super.setAttack(0);

    }
    
    /**
     * Indique la possibilite de creation d'un navi sur `c`
     * @param ICell c
     * @return vraie si c est accessible et est a cote
     */
    public boolean canCreateNavi(ICell c){
        return c.isAccessible() && this.position.nextTo(c);
    }
    
    /**
     * Apres avoir verifie cree un navi si possible
     * @param ICell c
     * @return navi cree ou null si la creation est impossible
     */
    public IEntity createNaviAt(ICell c){
        return this.canCreateNavi(c) ? this.addNavi(c) : null;
    }
    
    /**
	 * Cree un navi sur l'une des cases voisines
	 * @return navi ou null si aucun voisin accessible
	 */
	public IEntity createNavi(){
		ICell navC = null;
		for(ICell c : this.map.neighbours(this.campcell))
			if(c.isAccessible()) {
				navC = c;
				break;
			}
		
		return navC == null ? null : this.addNavi(navC);
	}
	
	/**
	 * Cree un navi et le place sur le cell demande
	 * @param ICell c
	 * @return navi cree
	 */
	private IEntity addNavi(ICell c) {
		IEntity navi = new Navi(super.model, c, super.getMap(), NAVI_HEALTH, NAVI_ATTACK, NAVI_SPEED);
	    c.addEntity(navi);
	    return navi;
	}
    
	/**
	 * Enleve des points de vie a l'ennemi
	 * Et verifie si le camp est detruit
	 */
    @Override
    public void sufferAttack(int impact) {
    	super.sufferAttack(impact);
    	deleteCamp();
    }
    
    /**
	 * Si le camp n'a plus de vie, l'enleve de map
	 */
	public void deleteCamp(){
	    if(super.getHealth() <= 0)
	        campcell.deleteEntity();
	}
}
        
