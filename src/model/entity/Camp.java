
package model.entity;

import model.Action;
import model.MainModel;
import model.Map;
import model.terrain.ICell;

import static model.Action.*;


public class Camp extends Entity implements IEntity{
	//	default
	private static final Action[] possibleActions = new Action[]{ CREATE };
	private static final int def_health = 100;
	private static final int def_attack = 0;
	private static final int def_speed = 0;
	private static final int COUT_NAVI = 40;

    public static int RESSOURCES = 0;

    public Camp(MainModel m, ICell c){
        super(m, c, def_health, def_attack, def_speed);
        super.currentAction = STOP;

    }


	@Override
	public String toString() {
		return "c";
	}

	@Override
	protected void create() {
		if (Camp.RESSOURCES >= COUT_NAVI)
			if (createNavi()) model.food -= COUT_NAVI;
	}

    /**
	 * Cree un navi sur l'une des cases voisines
	 * @return false si le navi n'a pas pu être créé
	 */
	public boolean createNavi(){
		ICell navC = null;
		for(ICell c : this.model.getMap().neighbours(this.position))
			if(c.isAccessible()) {
				navC = c;
				break;
			}
		if(navC != null) {
			EntityFactory.getInstance(model).createEntity(navC, "NAVI");
		}
		this.currentAction = Action.STOP;
		return navC != null;
	}

	@Override
	public Action[] possibleActions() {
		return possibleActions;
	}
    
	/**
	 * Enleve des points de vie a l'ennemi
	 * Et verifie si le camp est detruit
	 */
    @Override
    public void sufferAttack(int impact) {
    	super.sufferAttack(impact);
    	if (health <= 0)
			model.camps.remove(this);
    }
}