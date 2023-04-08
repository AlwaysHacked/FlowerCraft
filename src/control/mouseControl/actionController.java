package control.mouseControl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import model.Action;
import model.MainModel;
import model.entity.AStar;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Berries;
import model.terrain.ICell;
import model.terrain.Water;
import view.ViewControlPanel;
import view.ViewMap;

public class actionController implements MouseListener {

    public MainModel model;
    public JButton icon;
    public ICell cell;
    JPopupMenu pop;

    public actionController(MainModel m, JButton i, ICell c) {
        this.model = m;
        this.icon = i;
        this.cell = c;
        this.pop = new JPopupMenu();
        this.pop.add(new JMenuItem("Choose berries to harvest"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if (cell != null) {
            if (cell.getEntity() instanceof Navi) {
                model.setStartCell(cell);
            }
            if (model.getStartCell() != null && !(cell instanceof Water)) {
                model.setEndCell(cell);
            }
        }
        if (model.getStartCell() != null && model.getEndCell() != null) {
            var navi = (Navi) model.getStartCell().getEntity();
            if (icon.getName() == "HARVEST" && model.getEndCell() instanceof Berries) {
                
                if (model.getStartCell() == model.getEndCell()) {
                    navi.setCurrentAction(Action.HARVEST);
                }else{
                    System.out.println("Move selected Navi on berries first");
                }
                
            } else if (icon.getName() == "ATTACK") {
                var can = model.getStartCell().getEntity().canAttack();
                if(can != null){
                    navi.setCurrentAction(Action.ATTACK);
                }else{
                    System.out.println("selected Navi should be close to a Soldier");
                }
            } else if (icon.getName() == "STOP") {
                
                navi.setCurrentAction(Action.STOP);
                model.setStartCell(null);
                model.setEndCell(null);

            } else if (icon.getName() == "MOVE") {
                
                if (model.getStartCell() != model.getEndCell() && model.getEndCell().getEntity() == null) {
                    AStar path = new AStar(model, model.getMap(), model.getStartCell(), model.getEndCell());
                    var smallPath = path.getPath();
                    Collections.reverse(smallPath);
                    navi.setPath(smallPath);
                    navi.setCurrentAction(Action.MOVE);
                }
                
            } else if (icon.getName() == "BUILD") {
               
                if (navi.canBuildCamp(model.getEndCell()) && !(model.getEndCell() instanceof Berries)) {
                    navi.setCurrentAction(Action.BUILD);
                }else{
                    System.out.println("Choose an empty cell next to the selected Navi");
                }
            }


        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
