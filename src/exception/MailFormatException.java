package exception;
/**
 * Classe de gestion d exception en cas de mauvais format de mail
 * @version 2.0
 */
public class MailFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailFormatException(String mail) {
		super("le format du mail: " + mail + " est incorect");
	}
}
