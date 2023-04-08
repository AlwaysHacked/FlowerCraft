package model.entity;

import java.util.ArrayList;
import java.util.Random;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;

import static model.Action.ATTACK;

public class Soldier extends Entity implements IEntity {
	// default
	private static final int def_health = 100;
	private static final int def_attack = 5;
	private static final int def_speed = 10;
	private static final Random rand = new Random();

	// others

	public Camp camp;
	public Navi navi;

	// constructeur

	public Soldier(MainModel m, ICell c) {
		super(m, c, def_health, def_attack, def_speed);
		currentAction = ATTACK;
	}

	public void move() {
		if (destination == null) {
			//look at free cells around camp
			Camp camp =  model.camps.get(rand.nextInt(model.camps.size()));
			ArrayList<ICell> tab = new ArrayList<>();
			for(var i : this.model.getMap().getGrid()){
				for(var j : i){
					if(j.nextTo(camp.getPosition()) && j.getEntity() == null){
						tab.add(j);
					}
				}
			}
			destination = tab.get(0);
			
		} else{
			
			if (path.empty()) {
				generatePath();
			} else {
				System.out.println(position.getCoord());
				for(var i : path)System.out.println(i.getCoord());
				if (canMove(this.path.peek())) {
					this.path.peek().addEntity(this);
					this.position.deleteEntity();
					this.position = this.path.peek();
					this.path.pop();///////

				}
			}
		}
	}

	@Override
	public boolean isEnemy(IEntity ent) {
		return ent instanceof Navi || ent instanceof Camp;
	}

	// before solider go to find Camp, he check if there is navi or camp next to
	// him.
	public void check() {
		// place all cases ordonnee
		int xR = super.position.getX() + 1;
		int yR = super.position.getY();

		int xU = super.position.getX();
		int yU = super.position.getY() + 1;

		int xL = super.position.getX() - 1;
		int yL = yR;

		int xD = xU;
		int yD = super.position.getY() - 1;

		Cell cellR = new Cell(model, xR, yR);
		Cell cellU = new Cell(model, xU, yU);
		Cell cellL = new Cell(model, xL, yL);
		Cell cellD = new Cell(model, xD, yD);

		// check the camp or navi is in those positions or no

		// if(cellR.getEntity() == navi || cellR.getEntity() == camp||cellD.getEntity()
		// == navi ||cellD.getEntity() == camp)this.attack();
		// if(cellL.getEntity() == navi || cellL.getEntity() == camp ||
		// cellU.getEntity() == navi||cellU.getEntity() == camp)this.attack();
	}

	// search for the camp

	public ICell search() {

		ArrayList<Camp> camps = super.model.getCamps();

		int distance = 700;

		for (Camp camp : camps) {

			ICell campP = camp.position;// get each Camp Position
			// utiliser AStar pour calculer la plus proche camp avec soldier,
			// this.position;//position of soldier.
			// stock le temps-distance with a temps number(il va changer avec le for)
			int n = 0;

			if (n < distance)
				distance = n;// distance gets the best closed between the solider and camp
		}

		// how we know which camp has the best distance with int distance?
		ICell campPosition = null;

		return campPosition;

	}

	public void SoilderAction() {
		// het the list of positions next to the soilder
		ArrayList<ICell> n = super.map.neighbours(super.position);
		// check the neighbours is enemy or not
		for (int i = 0; i < n.size(); i++) {

			if (n.get(i).getEntity() != null) {
				this.canAttack();
				n.get(i).getEntity().sufferAttack(this.attack);
			}
		}

		// go to search the camp and get the camp position

		// ICell p = this.search();

		// move to the campS

	}

	@Override
	public String toString() {
		return "s";
	}
}
