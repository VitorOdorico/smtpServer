package org.example;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailSender {
    private String smtpHost;
    private String smtpPort;
    private final ExecutorService executorService;
    private static final String LOG_FILE_PATH = "C:\\dev\\smtpServerEmail\\src\\main\\java\\org\\example\\logs\\email_log.txt";
    // destino de envio para o log do smtp
    public EmailSender(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    private Session criarSessao() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        String username = "email sender";
        String password = "key email sender";

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void enviarEmailComAnexo(Session session, String destinatario, String assunto, String corpo, List<File> anexos) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("smtppvitor@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);

            // Definindo o corpo como HTML
            MimeBodyPart corpoParte = new MimeBodyPart();
            corpoParte.setContent(corpo, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpoParte);


            if (anexos != null && !anexos.isEmpty()) {
                for (File anexo : anexos) {
                    if (anexo.exists() && anexo.length() > 0) {
                        MimeBodyPart anexoParte = new MimeBodyPart();
                        anexoParte.attachFile(anexo);
                        multipart.addBodyPart(anexoParte);
                    } else {
                        System.out.println("Arquivo de anexo inválido ou vazio, ignorando: " + anexo.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("Nenhum anexo será enviado.");
            }

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("E-mail enviado para: " + destinatario);
            logEmail(destinatario, assunto, "SUCESSO");
        } catch (MessagingException | IOException e) {
            System.err.println("Erro ao enviar e-mail para: " + destinatario);
            logEmail(destinatario, assunto, "FALHA: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void enviarEmailsEmLote(List<String> destinatarios, String assunto, String corpo, List<File> anexos) {
        Session session = criarSessao();

        for (String destinatario : destinatarios) {
            executorService.submit(() -> {
                enviarEmailComAnexo(session, destinatario, assunto, corpo, anexos);
            });
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private synchronized void logEmail(String destinatario, String assunto, String status) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(String.format("[%s] Destinatário: %s | Assunto: %s | Status: %s%n", timestamp, destinatario, assunto, status));
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo de log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender(10);
        emailSender.setSmtpHost("smtp.gmail.com");
        emailSender.setSmtpPort("587");

        List<String> destinatarios = List.of(
                "emailDestino@destino.com"
        );

        // Exemplo com anexo (pode ser um arquivo vazio ou caminho inválido)
        File arquivo1 = new File("");  // Caminho inválido
        List<File> anexos = List.of(arquivo1);  // Exemplo com anexo

        // Caso queira enviar sem anexos, pode passar uma lista vazia ou null:
        // List<File> anexos = new ArrayList<>();  // Nenhum anexo

        emailSender.enviarEmailsEmLote(destinatarios, "urgente smtp server",
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Promoção Solução Network</title>\n" +
                        "    <style>\n" +
                        "        *{\n" +
                        "            margin: 0;\n" +
                        "            box-sizing: border-box;\n" +
                        "            padding: 0;\n" +
                        "        }\n" +
                        "        body {\n" +
                        "            font-family: Arial, sans-serif;\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            flex-direction: column;\n" +
                        "            width: 50%;\n" +
                        "            margin: 0 auto;\n" +
                        "            padding: 20px;\n" +
                        "            background-color: #fff;\n" +
                        "        }\n" +
                        "        .header {\n" +
                        "            font-size: 34pt;\n" +
                        "            text-align: center;\n" +
                        "            margin-bottom: 20px;\n" +
                        "        }\n" +
                        "        .offer-img {\n" +
                        "            width: 100%;\n" +
                        "            height: auto;\n" +
                        "            display: block;\n" +
                        "            margin: 0 auto;\n" +
                        "        }\n" +
                        "        .cta {\n" +
                        "            text-align: center;\n" +
                        "            font-size: 24pt;\n" +
                        "            margin-top: 20px;\n" +
                        "            color: #000;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <span class=\"header\">urgente!!!</span>\n" +

                        "        <img src=\"https://www.emailvendorselection.com/wp-content/uploads/Simple-Mail-Transfer-Protocol.png\"  class=\"offer-img\" alt=\"video de inicio\">\n" +

                        "    <span class=\"cta\">segue o envio do email usando o protocolo smtp</span>\n" +
                        "</body>\n" +
                        "</html>", anexos);

        emailSender.shutdown();
    }
}
