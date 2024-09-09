package org.atividadeJava.atividade3.Aula5ex1;

import java.time.LocalDate;
import org.atividadeJava.atividade2.Menu;

public class Main {
    public static void main(String[] args) {
        LocalDate initDate = LocalDate.of(2022, 1, 1);
        LocalDate finalDate = LocalDate.of(9999, 11, 29);
        LocalDate dataPagamento = LocalDate.of(2022, 1, 5);

        Aluno breno = new Aluno(
                "Breno Habib Dhaher Muhammad Oliveira",
                19,
                "Sem experiencia",
                "Nilson Veloso 1",
                0,
                "Vitalicio",
                initDate,
                finalDate
        );
        Aula aulaDoGustavo = new Aula(
                "Gustavo",
                40,
                "Feminino",
                "Pauzanes",
                1,
                "Dar aula"
        );
        Aula aulaDoWilliam = new Aula(
                "William",
                16,
                "MACHO",
                "UniRV",
                0,
                "Dar cambalhotas"
        );
        Pagamento pagamento = new Pagamento(
                0,
                0,
                567.99,
                dataPagamento,
                "Vencido"
        );
        aulaDoGustavo.inscreverAluno(breno);
        aulaDoWilliam.inscreverAluno(breno);

        Menu mainMenu = new Menu("TREINOOOOOOOO");
        mainMenu.text("Quantidade aulas: " + breno.getQuantidadeAulas(), 2).left();
        mainMenu.text("Status Pagamento: " + pagamento.getStatus(), 3).left();
        mainMenu.text("ID professor: " + breno.getAulas(), 4).left();
        mainMenu.printMenu();
    }
}
