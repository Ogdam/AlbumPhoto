package datas;

import java.io.File;
import java.util.Date;
import java.util.StringTokenizer;
import exception.*;
import controlers.PicView;

/**
 * Classe de gestion de Photo
 * @version 2.0 
 */
public class Photo {
	
	/**
	 * Nom du fichier dans le r�pertoire
	 */
	private String nom;
	/**
	 *  Date de creation de la photo
	 */
	private Date date;
	/**
	 * Chemin d'acces physique � la photo
	 */
	private String path = null;
	
	/**
	 * Tente de creer une photo a l aide du chemin specifie
	 * @param chemin le chemin vers la photo
	 * @throws PhotoNotFoundException le chemin specifie est incorect
	 * @throws WrongExtensionException la photo ne possede pas une extension reconnue
	 */
	public Photo(String chemin) throws PhotoNotFoundException, WrongExtensionException{
		File photo = new File(chemin);
		if (!photo.exists())
			throw new PhotoNotFoundException(chemin);
		this.recuperationChemin(chemin);
		this.recuperationNom();
		this.recuperationDateFichier();
	}
	
	/**
	 * Renvoie le chemin de la photo a partir du dossier "images"
	 * @param chemin
	 */
	private void recuperationChemin(String chemin) throws PhotoNotFoundException{
		int s =  chemin.indexOf("images");
		if (s < 0)
			throw new PhotoNotFoundException(chemin);
		this.path =  chemin.substring(s);
	}
	
	/**
	 * attribut le nom de la photo grace a son chemin d'acquisition
	 * @throws WrongExtensionException
	 */
	private void recuperationNom() throws WrongExtensionException{
		StringTokenizer sT = new StringTokenizer(this.path, "\\/.");
		String nom = new String();
		int nbToken = sT.countTokens();
		if(nbToken == 1)
			throw new WrongExtensionException("pas d extension", path);
		for(int i = 2; i < nbToken; i++) {
			sT.nextToken();
		}
		nom = sT.nextToken();
		int n = PicView.extension.length;
		int i = 0;
		String ext = sT.nextToken();
		while(i < n && PicView.extension[i].equals(ext)) {
			i++;
		}
		if(i == n)
			throw new WrongExtensionException(ext, this.path);
		this.nom = nom;
	}
	
	/**
	 * attribut la date de derniere modification de la photo
	 */
	private void recuperationDateFichier(){
		File fichier = new File(this.nom);
		this.date= new Date(fichier.lastModified());
	}
	
	/**
	 * retourne le nom de la photo
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * retourne le chemin d'acces de la photo
	 * @return
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * retourne la date de la photo
	 * @return
	 */
	public Date getDate() {
		return this.date;
	}
	
	public String toString(){
		return this.path;
	}
	
}
