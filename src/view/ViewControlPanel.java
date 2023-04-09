package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import control.mouseControl.actionController;
import model.MainModel;
import model.entity.Camp;
import model.entity.Entity;
import model.entity.Navi;
import model.entity.Soldier;
import model.terrain.Berries;

public class ViewControlPanel extends JPanel {

    private MainModel m;
    private ArrayList<JButton> elements;
    private final int width = ViewMap.STEP * 3;
    private final int height = ViewMap.STEP * 10;

    public ViewControlPanel(MainModel model) {

        this.m = model;
        this.elements = new ArrayList<>();
        this.setLayout(null);
        Dimension dim = new Dimension(width, height);
        
        // GrilleController ctrl = new GrilleController(grille);
        // /** Enregistrement du contrôleur comme auditeur du bouton.**
        // object.addMouseListener(ctrl);

        String[] names = { "ATTACK", "HARVEST", "STOP", "MOVE", "BUILD", "CREATE" };

        for (int i = 0; i < 6; i++) {
            ImageIcon temp2 = new ImageIcon();
            JButton object2 = new JButton();
            object2.setLayout(null);
            object2.setBounds(i * 36, height / 4 * 3, 30, 30);
            object2.setIcon(temp2);
            object2.setName(names[i]);
            object2.setOpaque(false);
            actionController ctrl = new actionController(m, object2, null);
            object2.addMouseListener(ctrl);
            object2.setIcon(new ImageIcon());
            this.elements.add(object2);
            this.add(object2);
            //System.out.println(elements.get(i).getName());
        }
        this.setPreferredSize(dim);
        setOpaque(true);

    }

    public void update() {
        // TODO Auto-generated method stub
        repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon temp = new ImageIcon(this.getClass().getResource("Ressources/ControlPanel.png"));
        g.drawImage(temp.getImage(), 0, 0, width, height, this);

        ImageIcon temp3 = new ImageIcon(this.getClass().getResource("Ressources/berry3.png"));
        g.drawImage(temp3.getImage(), 10, height/2-50, 20, 20, this);
        g.setFont(new Font ("Courier New", Font.BOLD, 14));
        g.setColor(Color.WHITE);
        g.drawString("Ressources : " + m.food, 40, height/2-40);

        g.setColor(Color.CYAN);
        g.drawString("Navi cost : " + Camp.COUT_NAVI, 40, height/2-25);
        g.setColor(Color.CYAN);
        g.drawString("Camp cost : " + Camp.COUT_CAMP, 40, height/2-10);


        if(m.getSelectedEntity() != null){
//            if(m.getEndCell() instanceof Berries){
//                ImageIcon temp2 = new ImageIcon(this.getClass().getResource("Ressources/berry2.png"));
//                g.drawImage(temp2.getImage(), 0, height/2+100, 40, 40, this);
//                g.setFont(new Font ("Courier New", 1, 14));
//                g.setColor(Color.YELLOW);
//                g.drawString("Berries : "+((Berries)m.getEndCell()).getFood(), 40, height/2+120);
//            }
            if(m.getSelectedEntity() instanceof Soldier){
                ImageIcon temp2 = new ImageIcon(this.getClass().getResource("Ressources/Icon_Soldier.png"));
                g.drawImage(temp2.getImage(), 0, 50, width, height/3, this);
                g.setFont(new Font ("Courier New", Font.BOLD, 24));
                g.setColor(Color.YELLOW);
                g.drawString(m.getSelectedEntity().getHealth()+"", width/2-30, height/2+40);
                g.drawString(Entity.SOLDIER_HEALTH+"", width/2+30, height/2+40);
                g.setFont(new Font ("Courier New", Font.BOLD, 12));
                g.setColor(Color.YELLOW);
                g.drawString("Soldier", width-60, 40);
            }else{
                if(m.getSelectedEntity() instanceof Navi){
                    ImageIcon temp2 = new ImageIcon(this.getClass().getResource("Ressources/Icon_Navi.png"));
                    g.drawImage(temp2.getImage(), 0, 50, width, height/3, this);
                    g.setFont(new Font ("Courier New", Font.BOLD, 24));
                    g.setColor(Color.YELLOW);
                    g.drawString(m.getSelectedEntity().getHealth()+"", width/2-30, height/2+40);
                    g.drawString(Entity.NAVI_HEALTH+"", width/2+30, height/2+40);
                    g.setFont(new Font ("Courier New", Font.BOLD, 12));
                    g.setColor(Color.YELLOW);
                    g.drawString("Avatar", width-60, 40);
                }
            }
        }else if(m.getStartCell() != null){
            if(m.getStartCell().getEntity() instanceof Navi){
                ImageIcon temp2 = new ImageIcon(this.getClass().getResource("Ressources/Icon_Navi.png"));
                g.drawImage(temp2.getImage(), 0, 50, width, height/3, this);
                g.setFont(new Font ("Courier New", Font.BOLD, 24));
                g.setColor(Color.YELLOW);
                g.drawString(m.getStartCell().getEntity().getHealth()+" "+Entity.NAVI_HEALTH, width/2-30, height/2+40);
                g.setFont(new Font ("Courier New", Font.BOLD, 12));
                g.setColor(Color.YELLOW);
                g.drawString("Avatar", width-60, 40);
            }
        }
        
        String[] message = { "Attack selected Soldier", "Harvest selected Berries", "Stop selected Navi", "Move to destination", "Build a Camp", "Add a new Navi" };
        for (int i = 0; i < 6; i++) {
            JButton box = elements.get(i);
            frame(g, message[i], box, box.getName(), box.getX(), box.getY(), box.getHeight(), box.getWidth());
            
        }

        // object.setBounds(0, 0, width, height);
        // object.setIcon(new ImageIcon());

    }

    public void frame(Graphics g, String mes, JButton box, String s, int x, int y, int width, int height) {
        
        ImageIcon temp2 = new ImageIcon(this.getClass().getResource("Ressources/"+s+".png"));
        g.drawImage(temp2.getImage(), x, y, width, height, this);
        box.setBounds(x, y, width, height);
        box.setOpaque(false);
        box.setContentAreaFilled(false);
        //box.setBorderPainted(false);
        box.setToolTipText(mes);
        Image image = temp2.getImage();
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        box.setIcon(new ImageIcon(newimg));
    }
    

}
