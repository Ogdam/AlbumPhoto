package exception;

/**
 * Classe de gestion d exception en cas de mauvaise extension de photo
 * @version 2.0
 */
public class WrongExtensionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongExtensionException(String ext, String file) {
		super("le format: " + ext + "n est pas reconnu par PicView le fichier "+ file + "n a donc pas pu etre charger");
	}
}
