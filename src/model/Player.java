package model;

public class Player {

    private String nombre;
    private int goles;
    private int asistencias;

    public Player (String nombre, int goles, int asistencias) {
        this.nombre = nombre;
        this.goles = goles;
        this.asistencias = asistencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nombre='" + nombre + '\'' +
                ", goles=" + goles +
                ", asistencias=" + asistencias +
                '}';
    }
}
