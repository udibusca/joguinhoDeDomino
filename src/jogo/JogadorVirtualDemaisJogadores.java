package jogo;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
/**
 *  Instantiates a new jogador virtual demais jogadores.
 * @author acastroa
 *
 */
public class JogadorVirtualDemaisJogadores extends JogadorVirtual {

	/**
	 * Instantiates a new jogador virtual demais jogadores.
	 *
	 * @param pecas the pecas
	 */
	public JogadorVirtualDemaisJogadores(ArrayList pecas) {
		super(pecas);

	}

	/**
	 * Metodo, para mover as pecas e realizar o som.
	 *
	 * @param x the x
	 * @return the peca
	 */
	public Peca pecasMovendo(Peca x) {
		if (x != null){
			ArrayList move_ = new ArrayList();

			for (int i = 0; i < pecas.size(); i++) {
				Peca f = (Peca) pecas.get(i);

				if (f.lado1 == x.lado1 || f.lado1 == x.lado2) {
					f.ponta = f.lado1;
					move_.add(f);
				}

				if (f.lado2 == x.lado1 || f.lado2 == x.lado2) {
					f.ponta = f.lado2;
					move_.add(f);

				}
			}

			int maior = -1;
			Peca MAIOR = null;
			for (int i = 0; i < move_.size(); i++) {
				Peca f = (Peca) move_.get(i);
				int aux = f.lado1 + f.lado2;
				if (maior < aux) {
					maior = aux;
					MAIOR = f;
				}

			}
			if (MAIOR != null)
				return MAIOR;

		} else {

			int mayor = 0;
			Peca MAIOR = null;
			for (int i = 0; i < pecas.size(); i++) {
				Peca f = (Peca) pecas.get(i);
				int aux = f.lado1 + f.lado2;
				if (mayor < aux) {
					mayor = aux;
					MAIOR = f;
				}
			}
			MAIOR.ponta = MAIOR.lado1;
			return MAIOR;
		}

		return null;
	}

}
