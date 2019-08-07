package datas;

import java.util.StringTokenizer;
import exception.*;
/**
 * Classe de gestion de Photo
 * @version 2.0 
 */
public class PhotoEvent extends Photo{
	
	/**
	 * 	Evenement auquel la photo est lie
	 */
	private Event evenement;
	
	/**
	 * construit une instance de la classe PhotoEvent
	 * @param path chemin d'acces a la photo
	 * @param evenement lier a la photo
	 * @throws PhotoNotFoundException si photo non trouve
	 * @throws WrongExtensionException si mauvaise extention
	 * @throws WrongEventException si mauvais event
	 */
	public PhotoEvent(String path, Event evenement) throws PhotoNotFoundException, WrongExtensionException, WrongEventException{
		super(path);
		this.evenement = evenement;
		this.goodEvent();
	}

	/**
	 * Si le repertoire de la photo et l evenement de la photo correspondent ne fais rien sinon return une WrongEventException
	 * @throws WrongEventException l evenement et le dossier de la photo ne correspondent pas
	 */
	private void goodEvent() throws WrongEventException{
		StringTokenizer sT= new StringTokenizer(this.getPath(),"/");
		int nbToken = sT.countTokens();
		for (int j=0; j < nbToken; j++){
			if(this.evenement.getNom().equals(sT.nextToken())) return;
		}
		throw new WrongEventException();
	}
	
	/**
	 * retourne l'evenement lier à la photo
	 * @return
	 */
	public Event getEvenement() {return  this.evenement;}
	
}