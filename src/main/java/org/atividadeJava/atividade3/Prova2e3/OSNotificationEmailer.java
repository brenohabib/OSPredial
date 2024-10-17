package org.atividadeJava.atividade3.Prova2e3;

import org.atividadeJava.atividade3.Prova2e3.User.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class OSNotificationEmailer implements Runnable {
    private static final String CSV_FILE_PATH = "src/main/java/org/atividadeJava/atividade3/Prova2e3/CSV/os.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Person user;

    public OSNotificationEmailer(Person user) {
        this.user = user;
    }

    @Override
    public void run() {
        checkAndSendNotifications(user);
    }

    public void checkAndSendNotifications(Person user) {
        List<String[]> outdated = readAndFilterOutdated();
        if (!outdated.isEmpty()) {
            String emailContent = createEmailContent(outdated);
            sendEmail(emailContent, user);
        } else {
            System.out.println("Não há OS atrasadas para notificar.");
        }
    }

    private List<String[]> readAndFilterOutdated() {
        List<String[]> notificacoesAtrasadas = new ArrayList<>();
        LocalDate dataLimite = LocalDate.now().minusDays(3);

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] dados = line.split(",");
                LocalDate dataCriacao = LocalDate.parse(dados[8], DATE_FORMATTER);
                if (dataCriacao.isBefore(dataLimite) && "Pendente".equals(dados[7])) {
                    notificacoesAtrasadas.add(dados);
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler CSV: " + e.getMessage());
        }

        return notificacoesAtrasadas;
    }

    private String createEmailContent(List<String[]> atrasadas) {
        StringBuilder content = new StringBuilder();
        content.append("Notificações de OS atrasadas:\n\n");

        for (String[] os : atrasadas) {
            content.append(String.format("OS: %s, Cliente: %s, Data de Criação: %s, Status: %s\n",
                    os[0], os[1], os[8], os[7]));
        }

        return content.toString();
    }

    private void sendEmail(String content, Person user) {
        String host = "smtp.gmail.com";
        String username = "brenohabib2013@gmail.com";
        String password = "hkvf ummn aiop nwed";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Notificações de OS Atrasadas");
            message.setText(content);

            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
