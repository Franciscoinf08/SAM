package sam.model.service;

import jakarta.mail.*;
import sam.model.domain.Denuncia;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Session;

import java.util.Properties;

public class EmailNotificador {

    private final String host = "smtp.seuprovedor.com"; // Ex: smtp.gmail.com
    private final String porta = "587"; // TLS
    private final String usuario = "seu_email@provedor.com"; // email que vai enviar
    private final String senha = "sua_senha"; // senha ou app password

    public void notificarDenuncia(Denuncia denuncia) throws MessagingException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", porta);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(denuncia.getDenunciante().getEmail())); // email do denunciante
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("dev@seudominio.com")); // email do desenvolvedor
        message.setSubject("Nova denúncia registrada");
        String texto = String.format(
                "Usuário: %s\nDenunciado: %s\nMotivo: %s\nDetalhes:\n%s",
                denuncia.getDenunciante().getNome(),
                denuncia.getDenunciado().getNome(),
                denuncia.getMotivo(),
                denuncia.getDetalhes()
        );
        message.setText(texto);

        Transport.send(message);
    }
}
