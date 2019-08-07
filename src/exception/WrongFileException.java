package exception;

/**
 * Classe de gestion d exception en cas de mauvais format de sauvegarde
 * @version 2.0
 */
public class WrongFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongFileException(String nomFichier, String nomElement) {
		super("le fichier: " + nomFichier + " n est pas une sauvegarde de " + nomElement);
	}
}
