package datas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import exception.*;
/**
 * Classe de gestion d evenement
 * @version 2.0
 */
public class Event {
	
	/**
	 * Nom de l evenement
	 */
	private String nom;
	
	
	/**
	 * Liste des personnes presentes a l evenement
	 */
	private Map<String, Personne> listPersonnes;
	
	/**
	 * Cree un event vide
	 * @param nom nom de l evenement
	 */
	public Event(String nom) {
		this.nom = nom;
		this.listPersonnes = new HashMap<String, Personne>();
	}
	
	public Event(String nom, HashMap<String, Personne> listPersonnes) {
		this.nom = nom;
		this.listPersonnes = listPersonnes;
	}
	
	/**
	 * Cree un event deja rempli a l aide d un fichier
	 * @param nom nom de l event
	 * @param nomFichier nom du fichier servant a la creation de l event
	 */
	public Event(String nom, String nomFichier) throws IOException, WrongFileException{
		this.nom = nom;
		this.load(nomFichier);
	}
	
	/**
	 * cree et ajoute la personne a la liste de l event
	 * @param nom nom de la personne
	 * @param mail mail de la personne
	 */
	public void ajouterPersonne(String nom, String mail) {
		try {
			this.ajouterPersonne(new Personne(nom, mail));
		}catch(MailFormatException e) {
			System.out.print("La personne "+ nom + " n a pas pu etre ajouter pour la raison suivante:\n    ");
			System.out.println(e);
		}
	}
	
	/**
	 * Ajoute la personne a la liste de l'event
	 * @param mrX personne a ajouter a la liste
	 */
	public void ajouterPersonne(Personne mrX) {
		if(!listPersonnes.containsKey(mrX.getMail()))
			this.listPersonnes.put(mrX.getMail(), mrX);
		else {
			System.out.println("La personne "+ mrX.getNom() + " n a pas pu etre ajouter pour la raison suivante: ");
			System.out.println("    le mail" + mrX.getMail() + " est deja utilise");
		}
	}
	
	/**
	 * Enleve la personne qui possede l addresse mail passe en parametre
	 * @param mail l addresse de la personne a retirer de l event
	 */
	public void retirerPersonne(String mail) {
		this.listPersonnes.remove(mail);
	}
	
	/**
	 * mais a jour un event grace a un fichier
	 * @param nomFichier nom du fichier de sauvegarde
	 * @throws WrongFileException
	 */
	public void load(String nomFichier) throws IOException, WrongFileException {
		BufferedReader bIn = null;
		try {
			bIn = new BufferedReader(new FileReader(new File(nomFichier)));
			String ligne = bIn.readLine();
			if(!this.nom.equals(ligne))
				throw new WrongFileException(nomFichier, this.nom);
			this.listPersonnes = new HashMap<String, Personne>();
			while((ligne = bIn.readLine()) != null) {
				StringTokenizer sT = new  StringTokenizer(ligne, ",");
				String nom = sT.nextToken();
				String mail = sT.nextToken();
				ajouterPersonne(nom, mail);
			}
		}finally {
			try {
				if (bIn != null)
					bIn.close();
			}catch(IOException e) {System.out.println(e);
			}
		}
	}
	
	/**
	 * sauvegarde de l event sur un fichier
	 */
	public void save() {
		BufferedWriter bOut = null;
		try {
			bOut = new BufferedWriter(new FileWriter("Sauvegarde/Event/Event_"+this.getNom()+".txt"));
			bOut.write(this.toString());
		}catch(FileNotFoundException e){ System.out.println(e);
		}catch(IOException e) { System.out.println(e);
		}finally {
			try {
				bOut.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * retourne le nom de l'evenement
	 * @return
	 */
	public String getNom() {
		return nom;
	}
	
	public String toString(){
		String s = new String(this.nom+"\n");
		for(Personne p : this.listPersonnes.values()){
			s += p.toString()+"\n";
		}
		return s;
	}
}
