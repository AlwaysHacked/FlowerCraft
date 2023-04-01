package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;

public class ViewControlPanel extends JPanel {

    private MainModel m;
    private ArrayList<JLabel> elements;
    private final int width = ViewMap.STEP * 3;
    private final int height = ViewMap.STEP * 10;

    public ViewControlPanel(MainModel model) {

        this.m = model;
        this.elements = new ArrayList<>();

        Dimension dim = new Dimension(width, height);
        this.setPreferredSize(dim);
        setOpaque(true);

        ImageIcon temp = new ImageIcon();
        JLabel object = new JLabel();
        object.setBounds(0, 0, width, height / 3);
        object.setIcon(temp);

        // GrilleController ctrl = new GrilleController(grille);
        // /** Enregistrement du contrôleur comme auditeur du bouton.**
        // object.addMouseListener(ctrl);
        this.elements.add(object);

    }

    public void update() {
        // TODO Auto-generated method stub
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon temp = new ImageIcon(this.getClass().getResource("Ressources/ControlPanel.png"));
        g.drawImage(temp.getImage(), 0, 0, width, height, this);
        // object.setBounds(0, 0, width, height);
        // object.setIcon(new ImageIcon());

    }

}
