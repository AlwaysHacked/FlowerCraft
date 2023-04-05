package view;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.entity.Camp;
import java.awt.Graphics;

public class viewCamp extends JPanel  {
       public Camp camp;
       
       public viewCamp(Camp camp){
        this.camp = camp;
       }

       public void frame(Graphics g, String s, int x, int y, int width, int height) {

        ImageIcon temp2 = new ImageIcon(this.getClass().getResource(s));
        g.drawImage(temp2.getImage(), x, y, width, height, this);
    }
       public void drawCamp(Graphics g, int x, int y){

        frame(g,"Ressources/Camp.png", x* ViewMap.STEP,y*ViewMap.STEP,ViewMap.STEP,ViewMap.STEP);

       }  
       
       public void update() {
        repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // methode dans Camp
            

}
