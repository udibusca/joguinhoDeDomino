package jogo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Jogador implements Serializable {

	ArrayList fichas;
	String nome;
	Clip clip;
	AudioInputStream audio;

	public Jogador(ArrayList fichas) {

		this.fichas = fichas;

	}

	public boolean equals(Object x) {

		return nome.equals(((Jogador) x).nome);

	}

	public boolean Move(int x) {
		boolean lleva = false;
		for (int i = 0; i < fichas.size(); i++) {
			Peca f = (Peca) fichas.get(i);
			if (f.e1 == x || f.e2 == x)
				lleva = true;
		}
		return lleva;
	}
	/**
	 * Cada mexida de cada jogador é emitido um som.
	 * @param x
	 * @return
	 */
	public Peca somPecasMovendo(Peca x) {

		for (int i = 0; i < fichas.size(); i++) {
			Peca f = (Peca) fichas.get(i);
			if (f.seleccionada()) {
				f.selecionada(false);

				try {
					audio = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("ficha.wav"));
					clip = AudioSystem.getClip();
					clip.open(audio);
					clip.start();

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return f;
			}
		}
		return null;
	}

}
