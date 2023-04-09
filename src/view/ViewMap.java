package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.mouseControl.actionController;
import model.MainModel;
import model.entity.*;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Berries;
import model.terrain.Field;
import model.terrain.Forest;
import model.terrain.ICell;

public class ViewMap extends JPanel {
    private MainModel model;
    private ArrayList<JButton> cases;
    public final static int SIZE = 10;
    public final static int STEP = 70;

    public ViewMap(MainModel model) {
        this.model = model;
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
                actionController ctrl = new actionController(this.model, object, this.model.getMap().getCell(j, i));
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
        if(this.model.isGameOver()){
            this.youLooseScreen();
        } else repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Pour chaque cellule...
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (model.getMap().getCell(j, i) != null) {
                    type(model.getMap().getCell(j, i), g, j, i);
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
            frame(g, "Ressources/"+ ((Berries)c).getFrame() +".png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
        } else
            frame(g, "Ressources/Submerge_1.png", x * STEP, y * STEP, STEP, STEP);
        if (c.getEntity() instanceof Navi) {
            frame(g, "Ressources/Navi.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
            g.setColor(Color.RED);
            g.fillRect(x * STEP+10, y * STEP+2, ((STEP-10)*c.getEntity().getHealth())/Entity.SOLDIER_HEALTH, 6);
            frame(g, "Ressources/healthbar.png", x * STEP, y * STEP, STEP, 10);
        }

        if (c.getEntity() instanceof Camp){
            frame(g, "Ressources/"+(c.getEntity().getHealth() > 40 ? "Camp" : "Camp_Construction")+".png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 25);
            g.setColor(Color.RED);
            g.fillRect(x * STEP+10, y * STEP+2, ((STEP-10)*c.getEntity().getHealth())/Entity.CAMP_HEALTH, 6);
            frame(g, "Ressources/healthbar.png", x * STEP, y * STEP, STEP, 10);
        }

        if (c.getEntity() instanceof Soldier){
            frame(g, "Ressources/Soldier.png", x * STEP + 10, y * STEP - 4, STEP - 20, STEP - 10);
            g.setColor(Color.RED);
            g.fillRect(x * STEP+10, y * STEP+2, ((STEP-10)*c.getEntity().getHealth())/Entity.SOLDIER_HEALTH, 6);
            frame(g, "Ressources/healthbar.png", x * STEP, y * STEP, STEP, 10);
        }

        if (model.getSelectedEntity() != null && c == model.getSelectedEntity().getPosition()){
            frame(g, "Ressources/move_1.png", x * STEP, y * STEP, STEP, STEP);
        }
//        if (m.getEndCell() != null && c == m.getEndCell()){
//            frame(g, "Ressources/move_1.png", x * STEP, y * STEP, STEP, STEP);
//        }
    }

    public void frame(Graphics g, String s, int x, int y, int width, int height) {

        ImageIcon temp2 = new ImageIcon(this.getClass().getResource(s));
        g.drawImage(temp2.getImage(), x, y, width, height, this);
    }

    public void youLooseScreen() {
        ImageIcon temp = new ImageIcon(this.getClass().getResource("Ressources/youLoose.jpg"));
        JOptionPane.showMessageDialog(this, null, "Game Over", JOptionPane.PLAIN_MESSAGE, temp);
        System.exit(0);
    }

}
