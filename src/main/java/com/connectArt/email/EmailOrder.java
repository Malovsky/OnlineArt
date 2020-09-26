package com.connectArt.email;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

import com.connectArt.config.Constants;
import com.connectArt.model.ArtWork;
import com.connectArt.model.User;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailOrder {
	
	@Autowired
	static ArtWorkRepository artRepo;
	
	@Autowired
	static UserRepository userRepo;
	
	public static void orderRecap(User user, List<ArtWork> artworks) {
		
		log.info("Enter orderRecap()");
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
			email.setSubject("Récapitulatif de votre commande du : " + LocalDate.now() + " - OnlineArt");
			
			// Email message
			strgB.append("Bonjour " + user.getUsername() + " ce mail valide votre commande.\n"
					+ "Vous trouverez ci joint le récapitulatif de votre commande :\n");
			
			for(ArtWork art : artworks) {
				strgB.append(" - " + art.getName() + " : 1 exemplaire\n");
			}
			
			strgB.append("\nL'équipe Online Art.");
			
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


/*
 * TODO : TROUVER UNE METHODE QUI VERIFIE SI L'ADRESSE EMAIL EST VALIDE ET INSERER DANS LES METHODES DE VALIDATORS
 * 
*/