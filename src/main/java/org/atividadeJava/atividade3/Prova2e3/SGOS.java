package org.atividadeJava.atividade3.Prova2e3;

import org.atividadeJava.atividade3.Prova2e3.Menu.Control;
import org.atividadeJava.atividade3.Prova2e3.Menu.Register;
import org.atividadeJava.atividade3.Prova2e3.User.Admin;
import org.atividadeJava.atividade3.Prova2e3.User.Resident;

public class SGOS {
    public static void main(String[] args) {
        //new Register();
        new Control(new Resident("Breno", "Breno"));
    }
}
