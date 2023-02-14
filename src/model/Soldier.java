package model;

import model.terrain.Cell;

public class Soldier extends MobileEntity implements IEntity {
	public Soldier(MainModel m, Cell c, Map map, int h, int a, int s) {
		super(m, c, map, h, a, s);
	}

	@Override
	public boolean isEnemy(MobileEntity ent) {
		return ent instanceof Navi/*or their animals*/;
	}
}
