package org.atividadeJava.atividade3.Prova2e3;

public class Tecnician extends Person {
    private int id;

    public Tecnician(int id, String name, String email) {
        super(name, email);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}