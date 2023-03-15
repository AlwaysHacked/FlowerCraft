package view;
import model.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;
import model.terrain.Field;
import model.terrain.ICell;

public class ViewMap extends JPanel {
	private MainModel m;
	private ArrayList<JLabel> cases;
	public final static int SIZE = 10;
	public final static int STEP = 20;
	
	public ViewMap(MainModel model) {
		this.m = model;
		this.cases = new ArrayList<>();
		Dimension dim = new Dimension(SIZE*50, SIZE*50); /////change later on
        this.setPreferredSize(dim);
        setOpaque(true);
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

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
        // Pour chaque cellule... 
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (m.getMap().getCell(i, j) != null) {
                    frame(g, type(m.getMap().getCell(i, j)), j*STEP, i*STEP);// to be written
                }
            }
        }
        //add other components later on
    }
    
    public String type(ICell c) {
    	if (c instanceof Field) return "Ressources/Field.jpg";
    	else return "Ressources/Water.jpg";
    }
    
    public void frame(Graphics g, String s, int x, int y) {
    	
    	ImageIcon temp2 = new ImageIcon(this.getClass().getResource(s));
        g.drawImage(temp2.getImage(), x, y, STEP, STEP, this);
    }
    

}
