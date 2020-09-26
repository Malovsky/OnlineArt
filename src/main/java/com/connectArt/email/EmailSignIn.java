package com.connectArt.email;

import java.time.LocalDate;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

import com.connectArt.config.Constants;
import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailSignIn {
	
	@Autowired
	static UserRepository userRepo;
	
	public static void valideSignIn(User user) {
		log.info("Enter valideSignIn()");
		StringBuilder strgB = new StringBuilder();
		
		try {
			Email email = new SimpleEmail();

			// Configuration
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(Constants.MY_EMAIL, Constants.MY_PASSWORD));

			// Required for gmail
			email.setSSLOnConnect(true);

			// Sender
			email.setFrom(Constants.MY_EMAIL);

			// Email title
			email.setSubject("OnlineArt vous souhaite la bienveue ! " + LocalDate.now());
			
			// Email message
			strgB.append("Bonjour " + user.getUsername() + " ce mail valide la création de votre compte.\n"
					+ "Vous pouvez dès à présent vous connecter via cette url : http://localhost:4200/login\n"
					+ "L'équipe Online Art.");
			email.setMsg(strgB.toString());
			
			// Receiver
			email.addTo(user.getEmail());
			email.send();
			
		} catch (Exception e) {
			log.error("Error : Envoie de mail échoué : {}");
			e.printStackTrace();
		}
	}

}
