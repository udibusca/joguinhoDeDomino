package jogo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Dados.
 */
public class Dados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int ponta;

	Peca f;

	ArrayList<Jogador> jogadores;

	/**
	 * Contrutor de dados.
	 *
	 * @param f the f
	 * @param ponta the ponta
	 * @param jogadores the jogadores
	 */
	public Dados(Peca f, int ponta, ArrayList<Jogador> jogadores) {
		this.ponta = ponta;
		this.f = f;
		this.jogadores = jogadores;
	}
}
