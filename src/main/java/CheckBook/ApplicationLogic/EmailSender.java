package CheckBook.ApplicationLogic;


import CheckBook.DataAccess.Models.MyEmail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class EmailSender {

    private final String emailUser = "checkbook.services@gmail.com";
    private final String emailPass = "check123!";

    private String[] to;
    private String body;
    private String subject;

    public EmailSender() {
        this.to = null;
        this.subject = null;
        this.body = null;
    }

    public EmailSender(String[] to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Attempts to send an email with the information this object already contains.
     * The email will send from the email address checkbook.services@gmail.com
     *
     * @return Returns a -1 if not enough info was provided, a 0 if enough info was
     * provided but an exception still occurred, or a 1 if the email was
     * sent successfully.
     */
    public int sendEmail() {

        // Checking to see if the all the necessary information to send an email has
        // been provided
        if (this.to == null || this.subject == null || this.body == null) {
            return -1;
        }

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", emailUser);
        props.put("mail.smtp.password", emailPass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(emailUser));
            InternetAddress[] toAddress = new InternetAddress[this.to.length];

            for (int i = 0; i < this.to.length; i++) {
                toAddress[i] = new InternetAddress(this.to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(this.subject);
            message.setText(this.body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, this.emailUser, this.emailPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Attempts to send an email. The email will send from the address
     * checkbook.services@gmail.com
     *
     * @param to      The recipient of the email.
     * @param subject The subject of the email.
     * @param body    The body of the email.
     * @return Returns a -1 if any info was null, a 0 if the info was not null but
     * an exception occurred, or a 1 if the email was sent successfully.
     */
    public int sendEmail(String[] to, String subject, String body) {

        // Checking to see if the all the necessary information to send an email has
        // been provided
        if (to == null || subject == null || body == null) {
            return -1;
        }

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", this.emailUser);
        props.put("mail.smtp.password", this.emailPass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(this.emailUser));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, this.emailUser, this.emailPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sendContactUsForm(MyEmail myEmail) {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", this.emailUser);
        props.put("mail.smtp.password", this.emailPass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);


        try {
            message.setFrom(new InternetAddress("checkbook.services@gmail.com"));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("cenzoreyes@gmail.com"));


            message.setSubject(myEmail.getSubject());
            message.setText(myEmail.getContent());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, this.emailUser, this.emailPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
