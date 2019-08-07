package datas;

import exception.*;
/**
 * Classe de gestion de personne
 * @version 2.0
 */
public class Personne {
	
	/**
	 * nom de la personne
	 */
	private String nom;
	
	/**
	 * mail de la personne
	 */
	private String mail;

	/**
	 * cree une personne a partir de son nom et son mail
	 * @param nom nom de la personne cree
	 * @param mail mail de la personne cree
	 */
	public Personne(String nom, String mail) throws MailFormatException{
		this.nom = nom;
		testMail(mail);
		this.mail = mail;
	}
	
	/**
	 * Teste la validiter du mail en tant que mail Personne@Boite-Mail.domaine
	 * @param mail mail a verifier
	 * @return True si le mail est correct 
	 * @throws MailFormatException Si le mail est incorect
	 */
	private void testMail(String mail) throws MailFormatException{
		String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$" ;
		if (!mail.matches(pattern))
			throw new MailFormatException(mail);
	}
	
	/**
	 * permet de recuperer le nom de la personne
	 * @return nom de la personne
	 */
	public String getNom()  {return this.nom;}

	/**
	 * permet de recuperer le mail de la personne
	 * @return mail de la personne
	 */
	public String getMail() {return this.mail;}
	
	public String toString() {
		return this.nom + " " + this.mail;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Personne))
			return false;
		Personne obj = (Personne)o;
		return (obj.mail.equals(this.mail) && obj.nom.equals(this.nom));
	}
}