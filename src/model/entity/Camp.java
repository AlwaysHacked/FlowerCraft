
package model.entity;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;
import model.terrain.*;


public class Camp extends Entity implements IEntity{
            private final Cell campcell;
        
            public Camp(MainModel m, ICell c, Map map,int h,int a,int s){
                
                super(m, c, map, h, a, s);
                this.campcell = null;
                super.currentAction = null;
                super.setSpeed(0);
                super.setHealth(12);
                super.setAttack(0);
        
            }
            
            public boolean canCreatNavi(ICell c){
                return c.getTerrain() != Terrain.WATER 
                && c.isAccessible()
                && this.position.nextTo(c);
            }

            public Navi creatNavi(ICell c){
                if(this.canCreatNavi(c)){
                Navi navi = new Navi(super.model,c,super.getMap(), getHealth(), getHealth(), getSpeed(), getAttack());
                return navi;
                } else{
                    return null;
                }
            }
            @Override
            void sufferAttack(int impact) 
            public void deleteCamp(){
                this.sufferAttack(attack);
                if(this.health<=0){
                    campcell.deleteEntity();
                }
            }
        
}
        
