package exception;

/**
 * Classe de gestion d exception en cas de photo non trouve
 * @version 2.0
 */
public class PhotoNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhotoNotFoundException(String photo) {
		super("la photo " + photo + "n a pas pu etre trouver");
	}
}
