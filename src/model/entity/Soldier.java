package model.entity;

import model.MainModel;
import model.Map;
import model.terrain.Cell;

public class Soldier extends Entity implements IEntity {
//	default
	private static final int def_health = 100;
	private static final int def_attack = 5;
	private static final int def_speed = 10;
	
	public Soldier(MainModel m, Cell c, Map map, int h, int a, int s) {
		super(m, c, map, h, a, s);
	}
	
	public Soldier(MainModel m, Cell c, Map map) {
		super(m, c, map, def_health, def_attack, def_speed);
	}

//	@Override
//	public boolean isEnemy(MobileEntity ent) {
//		return ent instanceof Navi/*or their animals*/;
//	}

	@Override
	public String toString() {
		return "s";
	}
}
