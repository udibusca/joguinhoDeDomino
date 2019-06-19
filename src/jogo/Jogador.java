package jogo;

import java.awt.event.ItemEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * Classe responsavel para Criar/iniciar um jogador
 * @author acastroa
 *
 */
public class Jogador implements Serializable {

	ArrayList<Peca> fichas;

	ArrayList<Peca> pecas;
	String nome;
	Clip clip;
	AudioInputStream audio;

	/**
	 * Instantiates a new jogador.
	 *
	 * @param fichas the fichas
	 */
	public Jogador(ArrayList fichas) {
		this.fichas = fichas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	 *
	 * @param x the x
	 * @return the peca
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
