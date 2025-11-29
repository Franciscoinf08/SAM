package sam.controller;


import sam.model.domain.Usuario;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.*;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnviarEmailController {

    public String getRandom(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean enviarEmail(String email, String codigo){
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

            mess.setSubject("Verificacao de email de usuário");
            mess.setText("Registro realizado com sucesso. Por favor verifique sua conta usando o codigo: "+codigo);

            Transport.send(mess);

            test = true;

        }catch(Exception e){
            e.printStackTrace();
        }


        return  test;
    }
}
