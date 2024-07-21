package resources;

import model.Player;

import java.util.Comparator;

public class Compare implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getGoles() > o2.getGoles()) {
            return -1;
        }
        if (o1.getGoles() < o2.getGoles()) {
            return 1;
        }
        if (o1.getAsistencias() > o2.getAsistencias()) {
            return -1;
        }
        if (o1.getAsistencias() < o2.getAsistencias()) {
            return 1;
        }
        return o1.getNombre().compareTo(o2.getNombre());


    }
}
