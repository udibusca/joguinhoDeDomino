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
	public Peca somPecasMovendo(Peca x) {
		if (x != null)

		{
			try {
				audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			ArrayList move_ = new ArrayList();

			for (int i = 0; i < fichas.size(); i++) {
				Peca f = (Peca) fichas.get(i);

				if (f.e1 == x.e1 || f.e1 == x.e2) {
					f.ponta = f.e1;
					move_.add(f);
				}

				if (f.e2 == x.e1 || f.e2 == x.e2) {
					f.ponta = f.e2;
					move_.add(f);

				}
			}

			int maior = -1;
			Peca MAIOR = null;
			for (int i = 0; i < move_.size(); i++) {
				Peca f = (Peca) move_.get(i);
				int aux = f.e1 + f.e2;
				if (maior < aux) {
					maior = aux;
					MAIOR = f;
				}

			}
			if (MAIOR != null)
				return MAIOR;

		} else {
			try {
				audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			int mayor = 0;
			Peca MAYOR = null;
			for (int i = 0; i < fichas.size(); i++) {
				Peca f = (Peca) fichas.get(i);
				int aux = f.e1 + f.e2;
				if (mayor < aux) {
					mayor = aux;
					MAYOR = f;
				}
			}
			MAYOR.ponta = MAYOR.e1;
			return MAYOR;
		}

		return null;
	}

}
