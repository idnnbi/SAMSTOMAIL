package com.example.jinwoo.myapplication;

import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailAsync extends AsyncTask<String, Integer, Integer> {

    protected Integer doInBackground(String... param) {
         return sendEmail(param[0], param[1], param[2]);
    }

    public Integer sendEmail(String email, String subject, String body) {

        final String username = ""// 
        final String password = ""//

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); //465

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));
            message.setSubject(subject);
            message.setText(body);

            //MimeBodyPart messageBodyPart = new MimeBodyPart();

            //Multipart multipart = new MimeMultipart();

//            messageBodyPart = new MimeBodyPart();
//            String file = "path of file to be attached";
//            String fileName = "attachmentName"
//            DataSource source = new FileDataSource(file);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(fileName);
//            multipart.addBodyPart(messageBodyPart);

            //message.setContent(multipart);

            Transport.send(message);

            System.out.println("Done");

            return new Integer(1);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
