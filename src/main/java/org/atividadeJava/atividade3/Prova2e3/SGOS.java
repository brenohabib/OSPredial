package org.atividadeJava.atividade3.Prova2e3;

import org.atividadeJava.atividade3.Prova2e3.Menu.Control;
import org.atividadeJava.atividade3.Prova2e3.User.Admin;
import org.atividadeJava.atividade3.Prova2e3.User.Person;

public class SGOS {
    public static void main(String[] args) {
        //new Register();
        new Control(new Person("Breno", "breno.oliveira@academico.unirv.edu.br"));
    }
}
