package control;

import model.MainModel;
import model.terrain.Berries;

import java.util.ArrayList;

public class BerriesControl extends Thread{
    private MainModel model;
//    Liste de toutes les cases Berries
    private ArrayList<Berries> berries = new ArrayList<>();

    @Override
    public void run() {
        while (model.isRunning()) {
            berries.forEach((this::update));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    Pour ajouter les cases à l'initialisation
    public void addBerries(Berries b){
        berries.add(b);
    }

//    Actualise un Berries et le supprime de la liste si il est passé en dessous de 0
    private void update(Berries b) {
        if (!b.update())
            berries.remove(b);
    }
}
