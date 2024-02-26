package tn.esprit.e_learning.models;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public void sendRecoveryCode(String userEmail, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cinephoria.cinema@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Verification Code for Your Account");
            message.setText("Votre code de vérification est : " + verificationCode);

            Transport.send(message);
            System.out.println("Email notification sent to " + userEmail);
        } catch (Exception e) {
            System.out.println("Error sending email notification: " + e.getMessage());
        }
    }
}
