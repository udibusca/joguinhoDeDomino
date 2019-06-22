package jogo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Classe responsavel para Criar/iniciar um jogador
 * @author acastroa
 *
 */
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	ArrayList<Peca> pecas;
	String nome;

	/**
	 * Instantiates a new jogador.
	 *
	 * @param pecas the fichas
	 */
	public Jogador(ArrayList pecas) {
		this.pecas = pecas;
	}

	public boolean equals(Object x) {
		return nome.equals(((Jogador) x).nome);

	}

	/**
	 * Move.
	 *
	 * @param x the x
	 * @return true, if successful
	 */
	public boolean Move(int x) {
		boolean valida = false;
		for (int i = 0; i < pecas.size(); i++) {
			Peca f = (Peca) pecas.get(i);
			if (f.lado1 == x || f.lado2 == x)
				valida = true;
		}
		return valida;
	}
	
	/**
	 * Cada mexida de cada jogador é emitido um som.
	 *
	 * @param x the x
	 * @return the peca
	 */
	public Peca pecasMovendo(Peca x) {

		for (int i = 0; i < pecas.size(); i++) {
			Peca f = (Peca) pecas.get(i);
			if (f.seleccionada()) {
				f.selecionada(false);

				return f;
			}
		}
		return null;
	}

}
