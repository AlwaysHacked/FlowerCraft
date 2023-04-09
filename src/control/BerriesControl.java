package control;

import model.MainModel;
import model.terrain.Berries;
import model.terrain.ICell;
import view.MainView;
import view.ViewControlPanel;

import java.util.ArrayList;

public class BerriesControl extends Thread {
    private MainModel model;
    private MainView view;
    // Liste de toutes les cases Berries
    private ArrayList<Berries> berries = new ArrayList<>();

    public BerriesControl(MainModel m, MainView v) {
        this.model = m;
        this.view = v;
        for (ICell[] ct : m.getMap().getGrid()) {
            for (ICell c : ct) {
                if (c instanceof Berries) {
                    berries.add((Berries) c);
                }
            }
        }
    }

    @Override
    public void run() {
        while (model.isRunning()) {
            berries.forEach((this::update));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Actualise un Berries et le supprime de la liste si il est passé en dessous de
    // 0
    private void update(Berries b) {
        if (model.isRunning()) {
            b.newFrame();
            view.update();
            if (!b.update())
                berries.remove(b);
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
