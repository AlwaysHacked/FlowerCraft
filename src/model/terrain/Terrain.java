package model.terrain;

public enum Terrain {
	FIELD(1), BERRIES(1), FOREST(3), WATER(Integer.MAX_VALUE);
	
	public final int value;
	
	private Terrain(int i) {
		this.value = i;
	}
}
