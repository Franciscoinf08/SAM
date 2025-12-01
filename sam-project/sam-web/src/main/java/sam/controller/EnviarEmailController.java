package sam.controller;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.*;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;

public class EnviarEmailController {

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean enviarEmail(String email, String codigo, String mensagem, String assunto) {
        boolean test = false;

        String toEmail = email;
        String fromEmail = "equipesam.cefetmg@gmail.com";
        String password = "lrkfxgarmwqktkmu";

        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }

                ;
            });

            Message mess = new MimeMessage(session);

            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            mess.setSubject(assunto);
            mess.setText(mensagem + codigo);

            Transport.send(mess);

            test = true;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public boolean enviarEmailSolicitacaoGestor(String nome, String email, String formaPagamento, String arquivoAnexado){
        boolean test = false;

        String toEmail = email;
        String fromEmail = "equipesam.cefetmg@gmail.com";
        String password = "lrkfxgarmwqktkmu";

        try{
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                };
            });

            Message mess = new MimeMessage(session);

            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            mess.setSubject("Validação do seu pedido para conta gestor");

            MimeBodyPart texto = new MimeBodyPart();
            texto.setText(nome + ", sua solicitação foi realizada com sucesso! Por favor realize o pagamento via " + formaPagamento + " conforme anexo.");

            MimeBodyPart anexo = new MimeBodyPart();
            anexo.attachFile(new File(arquivoAnexado));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            multipart.addBodyPart(anexo);

            mess.setContent(multipart);

            Transport.send(mess);

            test = true;

        }catch(Exception e){
            e.printStackTrace();
        }


        return  test;
    }
}