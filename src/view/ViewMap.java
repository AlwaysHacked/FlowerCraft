package view;

import model.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.mouseControl.actionController;
import model.MainModel;
import model.entity.Camp;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Berries;
import model.terrain.Field;
import model.terrain.Forest;
import model.terrain.ICell;

public class ViewMap extends JPanel {
    private MainModel m;
    private ArrayList<JButton> cases;
    public final static int SIZE = 10;
    public final static int STEP = 70;

    public ViewMap(MainModel model) {
        this.m = model;
        this.cases = new ArrayList<>();
        this.setLayout(null);
        Dimension dim = new Dimension(SIZE * 70, SIZE * 70); ///// change later on
        this.setPreferredSize(dim);
        setOpaque(true);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                ImageIcon temp2 = new ImageIcon();
                JButton object = new JButton();
                object.setLayout(null);
                object.setBounds(j * STEP, i * STEP - 5, STEP, STEP);
                object.setIcon(temp2);
                object.setOpaque(false);
                object.setContentAreaFilled(false);
                object.setBorderPainted(false);
                actionController ctrl = new actionController(m, object, this.m.getMap().getCell(j, i));
                object.addMouseListener(ctrl);
                object.setIcon(new ImageIcon());

                // ZoneController ctrl = new ZoneController(this.grille, this.grille.getZone(j,
                // i));
                // object.addMouseListener(ctrl);
                this.add(object);
                cases.add(object);

            }
        }

    }

    public void update() {
        // maybe replace with a thread later once there is one in Cell ?
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Pour chaque cellule...
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (m.getMap().getCell(j, i) != null) {
                    type(m.getMap().getCell(j, i), g, j, i);
                }
            }
        }
        // add other components later on
    }

    public void type(ICell c, Graphics g, int x, int y) {
        if (c instanceof Field)
            frame(g, "Ressources/Normal_1.png", x * STEP, y * STEP, STEP, STEP);
        else if (c instanceof Forest)
            frame(g, "Ressources/FOREST.png", x * STEP, y * STEP, STEP, STEP);
        else if (c instanceof Berries) {
            frame(g, "Ressources/Normal_1.png", x * STEP, y * STEP, STEP, STEP);
            if(((Berries)c).getFood() == 0){
                frame(g, "Ressources/tile039.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
            }else{
            frame(g, "Ressources/BERRIES.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
            }
        } else
            frame(g, "Ressources/Submerge_1.png", x * STEP, y * STEP, STEP, STEP);
        if (c.getEntity() instanceof Navi) {
            frame(g, "Ressources/Navi.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
        }

        if (c.getEntity() instanceof Camp){
            frame(g, "Ressources/Camp.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 25);
        }

        if (c.getEntity() instanceof Soldier){
            frame(g, "Ressources/Soldier.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
        }

        if (m.getStartCell() != null && c == m.getStartCell()){
            frame(g, "Ressources/move_1.png", x * STEP, y * STEP, STEP, STEP);
        }
        if (m.getEndCell() != null && c == m.getEndCell()){
            frame(g, "Ressources/move_1.png", x * STEP, y * STEP, STEP, STEP);
        }
    }

    public void frame(Graphics g, String s, int x, int y, int width, int height) {

        ImageIcon temp2 = new ImageIcon(this.getClass().getResource(s));
        g.drawImage(temp2.getImage(), x, y, width, height, this);
    }

}
