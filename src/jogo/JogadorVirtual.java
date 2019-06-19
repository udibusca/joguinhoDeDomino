package jogo;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
/**
 * Classe que implementa o som de movimento das peças.
 * @author acastroa
 *
 */
public class JogadorVirtual extends Jogador {

	private static final long serialVersionUID = 1L;

	public JogadorVirtual(@SuppressWarnings("rawtypes") ArrayList fichas) {
		super(fichas);
	}

	public Peca pecasMovendo(Peca x) {

		if (x != null) {
			for (int i = 0; i < fichas.size(); i++) {
				Peca f = (Peca) fichas.get(i);
				if (f.e1 == x.e1 || f.e1 == x.e2) {

					f.ponta = f.e1;
					return f;
				}
				if (f.e2 == x.e1 || f.e2 == x.e2) {

					f.ponta = f.e2;
					return f;
				}
			}
		} else {

			Peca f = (Peca) fichas.get(0);
			f.ponta = f.e1;
			return f;
		}

		return null;
	}

}
