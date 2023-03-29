
package model.entity;

import model.MainModel;
import model.Map;
import model.terrain.Cell;
import model.terrain.ICell;


public class Camp extends Entity implements IEntity{
            private final Cell campcell;
            private boolean enConstruction;
        
            private boolean creat;
            private int building;
        
            public Camp(MainModel m, ICell c, Map map,int h,int a,int s){
                
                super(m, c, map, h, a, s);
                this.campcell = null;
                super.currentAction = null;
                super.setSpeed(0);
                super.setHealth(12);
                super.setAttack(0);
                this.creat = true;
                this.building = 3 ;
                this.enConstruction = false;
        
            }
        
            public boolean creat(){
                return creat;
            }
        
            public Navi creatingNavi(){
                Navi navi = new Navi(super.model, campcell,super.getMap(), getHealth(), getHealth(), getSpeed(), getAttack());
                return navi;
            }
        
            public void deleteCamp(){
                if(super.getHealth()<=0){
                    campcell.deleteEntity();
                }
            }
        
            public boolean enConstruction(){
                if(getHealth()<12){
                    return true;
                }else{
                    return false;
                }
            }
            
            public void construCamp(){
                if(this.enConstruction()){
                   super.setHealth(+3);
                }
            }
}
        
