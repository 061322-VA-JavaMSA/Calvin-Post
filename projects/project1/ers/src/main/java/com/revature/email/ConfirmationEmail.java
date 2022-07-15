package com.revature.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;

public class ConfirmationEmail {

	private Reimbursement r;
	private String from = "message@ers.org";
	private String host = "localhost";
	private Properties props;
	private Session session;
	private MimeMessage message;
	private static Logger log = LogManager.getLogger(ConfirmationEmail.class);

	public ConfirmationEmail(Reimbursement r) throws IOException {

		this.r = r;
		System.out.println(r.getAuthor().getEmail());
		props = System.getProperties();
		props.setProperty("mail.smtp.host", host);
		session = Session.getDefaultInstance(props);
		session = getSession();
		createMessage();
		send();
	}

	public void createMessage() {
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(r.getAuthor().getEmail()));
			message.setSubject("Request submitted: " + r.getSubmitted().toString());
			message.setContent(
					"<h1>Your request has been received</h1><br><table><tr><td>Id:</td><td>" + r.getId()
							+ "</td></tr><tr><td>Type:</td><td>" + r.getReimbType().getType()
							+ "</td></tr><tr><td>Status:</td><td>" + r.getReimbStatus().getStatus()
							+ "</td></tr><tr><td>Amount:</td><td>" + r.getAmount()
							+ "</td></tr><tr><td>Description:</td><td>" + r.getDescription() + "</td></tr></table>",
					"text/html");
		} catch (MessagingException e) {
			log.error(e.getMessage());
		}
	}

	public Session getSession() throws IOException {
		Session s;
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("email.properties"));

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		s = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return s;
	}

	public void send() {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			log.error(e.getMessage());
		}
		log.info("Confirmation email sent to " + r.getAuthor().getEmail());

	}
}
