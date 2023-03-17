package view;

import java.awt.*;

import javax.swing.JFrame;

import model.MainModel;

public class MainView {
	private JFrame frame;
	private ViewMap map;
	private ViewControlPanel control;

	public MainView(MainModel model) {

		frame = new JFrame();
		frame.setTitle("Avatar : The Game");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gcon = new GridBagConstraints();
		gcon.weightx = 2;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.CENTER;

		map = new ViewMap(model);

		gcon.gridy = 0;
		gcon.gridx = 0;
		gcon.gridwidth = 2;
		gcon.gridheight = 2;
		
		frame.add(map, gcon);
		
		control = new ViewControlPanel(model);

		gcon.gridy = 0;
		gcon.gridx = 2;
		gcon.gridwidth = 1;
		gcon.gridheight = 2;

		frame.add(control, gcon);

		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void update() {
		map.update();
	}
}
