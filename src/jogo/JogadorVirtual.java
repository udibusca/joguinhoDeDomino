package jogo;

import java.util.ArrayList;
/**
 * Classe que implementa o som de movimento das peças.
 * @author acastroa
 *
 */
public class JogadorVirtual extends Jogador {

	private static final long serialVersionUID = 1L;

	public JogadorVirtual(ArrayList<?> fichas) {
		super(fichas);
	}

	public Peca pecasMovendo(Peca x) {

		if (x != null) {
			for (int i = 0; i < pecas.size(); i++) {
				Peca f = (Peca) pecas.get(i);
				if (f.lado1 == x.lado1 || f.lado1 == x.lado2) {

					f.ponta = f.lado1;
					return f;
				}
				if (f.lado2 == x.lado1 || f.lado2 == x.lado2) {

					f.ponta = f.lado2;
					return f;
				}
			}
		} else {

			Peca f = (Peca) pecas.get(0);
			f.ponta = f.lado1;
			return f;
		}
		
		return null;
	}

}
