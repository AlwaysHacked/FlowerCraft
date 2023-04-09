package control;

import model.MainModel;
import model.terrain.Berries;
import model.terrain.ICell;
import view.MainView;

import java.util.ArrayList;

public class ViewControl extends Thread {
    private MainModel model;
    private MainView view;

    public ViewControl(MainModel m, MainView v) {
        this.model = m;
        this.view = v;
    }

    @Override
    public void run() {
        while (model.isRunning()) {
            view.update();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
