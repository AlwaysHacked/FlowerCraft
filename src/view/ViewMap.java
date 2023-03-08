package view;
import model.*;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MainModel;

public class ViewMap extends JPanel {
	private MainModel m;
	private ArrayList<JLabel> jl = new ArrayList<>();
	public final int SIZE = 100;
	
	public ViewMap(MainModel model) {
		this.m = model;
		Dimension dim = new Dimension(SIZE, SIZE); /////change later on
        this.setPreferredSize(dim);
        setOpaque(true);
	}
}
