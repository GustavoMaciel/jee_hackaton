package br.com.ctis.hackathon.exception;

/**
 * Exceção padrão que será propagada para a camada de serviços
 * 
 * @author alysson.cordeiro@ctis.com.br
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = -653060566410176358L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}
}
