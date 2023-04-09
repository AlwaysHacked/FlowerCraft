package control;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Action;
import model.MainModel;
import model.entity.*;
import model.terrain.ICell;
import view.MainView;
import model.entity.Soldier;

public class EntityControl extends Thread {
    private MainModel model;
    private MainView view;
    private ArrayList<ICell> ent;
    private int counter;
    private Soldier soldier;

    public EntityControl(MainModel m, MainView v) {
        this.model = m;
        this.view = v;      

//        checkEntity();
    }

    @Override
    public void run() {
        while (model.isRunning()) {
//            ent.forEach((this::update));
            checkEntity(); //// important to remove charater that has died
            model.entities.forEach(IEntity::update);
            view.update();

            counter ++; 
            this.createSoilder();

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSoilder(){

        if(this.counter % 5 == 0) soldier.buildSoilder();//We can change %10 to control the speed

    }

    public void checkEntity() {
        var temp = new CopyOnWriteArrayList<IEntity>();
        for (ICell[] ct : model.getMap().getGrid()) {
            for (ICell c : ct) {
                if (c.getEntity() != null) {
                    temp.add(c.getEntity());
                }
            }
        }
        this.model.entities = temp;
        System.out.println(model.entities.size());
    }

    // public void update(ICell e) {
    //     if (e.getEntity() instanceof Navi) {
    //         var temp = e.getEntity();
    //         if (((Navi) temp).getPath() != null && temp.getCurrentAction() == Action.MOVE) {
    //             temp.setPosition(((Navi) temp).getPath().get(0));
    //             ((Navi) temp).getPath().get(0).addEntity(temp);
    //             e.deleteEntity();
    //             var pathway = ((Navi) temp).getPath();
    //             pathway.remove(0);
    //             ((Navi) temp).setPath(pathway);
    //             if (((Navi) temp).getPath().size() == 0) {
    //                 ((Navi) temp).setPath(null);
    //             }
    //         } else {
    //             if (temp.getCurrentAction() == Action.HARVEST) {
    //                 ((Navi) temp).harvest();
    //                 temp.setCurrentAction(Action.STOP);
    //                 model.setStartCell(null);
    //                 model.setEndCell(null);
    //             } else if (temp.getCurrentAction() == Action.MOVE) {
    //                 temp.setCurrentAction(Action.STOP);
    //                 model.setStartCell(null);
    //                 model.setEndCell(null);
    //             } else if (temp.getCurrentAction() == Action.BUILD) {
    //                 ((Navi) temp).buildCamp(model.getEndCell());
    //                 temp.setCurrentAction(Action.STOP);
    //                 model.setStartCell(null);
    //                 model.setEndCell(null);
    //             }else if (temp.getCurrentAction() == Action.ATTACK) {
    //                 ((Navi) temp).attack();
                    
    //             }
    //         }
    //     }
    // }
}
