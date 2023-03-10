package view;
import model.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;

public class ViewMap extends JPanel {
	private MainModel m;
	private ArrayList<JLabel> cases;
	public final int SIZE = 100;
	public final int STEP = 10;
	
	public ViewMap(MainModel model) {
		this.m = model;
		Dimension dim = new Dimension(SIZE, SIZE); /////change later on
        this.setPreferredSize(dim);
        setOpaque(true);
        
        for (int i = 0; i < STEP; i++) {
            for (int j = 0; j < STEP; j++) {

                    ImageIcon temp = new ImageIcon();
                    JLabel object = new JLabel();
                    object.setBounds(j*STEP, i*STEP, STEP, STEP);
                    object.setIcon(temp);
                    //ZoneController ctrl = new ZoneController(this.grille, this.grille.getZone(j, i));
                    //object.addMouseListener(ctrl);
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
        /* Pour chaque cellule... */
        int count = 0;
        for (int i = 0; i < STEP; i++) {
            for (int j = 0; j < STEP; j++) {
                if (m.getMap().getCell(i, j) != null) {
                    paint(g, m.getMap().getCell(i, j), j*STEP, i*STEP, count);// to be written
                    count++;
                }
            }
        }
        //add other components later on
    }
    
    

}
