package br.com.ctis.hackathon.exception;

import br.com.ctis.hackathon.mensagem.Mensagem;
import br.com.ctis.hackathon.mensagem.MensagemErro;

/**
 * Exceção para Registro não encontrado do tipo {@link RuntimeException}.
 *
 * @author felipe.n.sousa@ctis.com.br
 */
public class RegistroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	private Mensagem mensagem;

	public RegistroNaoEncontradoException() {
		super();
	}

	public RegistroNaoEncontradoException(String message) {
		this.mensagem = new MensagemErro(message);
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

}
