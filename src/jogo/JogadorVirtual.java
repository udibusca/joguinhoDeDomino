package jogo;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;

public class JogadorVirtual extends Jogador {

	private static final long serialVersionUID = 1L;

	public JogadorVirtual(@SuppressWarnings("rawtypes") ArrayList fichas) {
		super(fichas);
	}

	public Peca somPecasMovendo(Peca x) {

		if (x != null) {
			for (int i = 0; i < fichas.size(); i++) {
				Peca f = (Peca) fichas.get(i);
				if (f.e1 == x.e1 || f.e1 == x.e2) {
					try {
						audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
						clip = AudioSystem.getClip();
						clip.open(audio);
						clip.start();

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					f.ponta = f.e1;
					return f;
				}
				if (f.e2 == x.e1 || f.e2 == x.e2) {
					try {
						audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
						clip = AudioSystem.getClip();
						clip.open(audio);
						clip.start();

					} catch (Exception ex) {
						ex.printStackTrace();
					}

					f.ponta = f.e2;
					return f;
				}
			}
		} else {
			try {
				audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			Peca f = (Peca) fichas.get(0);
			f.ponta = f.e1;
			return f;
		}

		return null;
	}

}