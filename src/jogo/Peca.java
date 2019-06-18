package jogo;

import java.awt.Color;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe responsavel por montar as Pecas.
 * @author acastroa
 *
 */
public class Peca extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public int e1, e2;
	
	JButton b1;
	
	JButton b2;
	
	boolean visible = false;
	
	boolean horizontal = true;
	
	URL x;
	
	int y, fx, fy;
	
	ArrayList pecas;
	
	Random ramdon;

	boolean seleccionada;
	int ponta;
	public static int tamanho = 40;

	/**
	 * Instantiates a new peca.
	 *
	 * @param e1 the e 1
	 * @param e2 the e 2
	 * @param pecas the pecas
	 */
	public Peca(int e1, int e2, ArrayList pecas) {

		ramdon = new Random();
		visible = false;
		seleccionada = false;
		this.e1 = e1;
		this.e2 = e2;
		this.pecas = pecas;
		b1 = new JButton();
		b2 = new JButton();

		setSize(new java.awt.Dimension(tamanho * 2, tamanho));
		setLayout(new java.awt.GridLayout(1, 2));

		b1.setBackground(Color.BLACK);
		b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		b1.setFocusPainted(false);
		b1.setFocusable(false);
		b1.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
		b1.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
		b1.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
		add(b1);

		b2.setBackground(Color.BLACK);
		b2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		b2.setFocusPainted(false);
		b2.setFocusable(false);
		b2.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
		b2.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
		b2.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
		add(b2);

		fx = 1;
		fy = 1;
		b1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				ponta = e1;
				seleccionada = true;
			}

			public void mouseMoved(java.awt.event.MouseEvent evt) {
			}
		});
		b2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				ponta = e2;
				seleccionada = true;
			}

			public void mouseMoved(java.awt.event.MouseEvent evt) {
			}
		});

		pecas.add(this);

	}

	/* (non-Javadoc)
	 * @see java.awt.Component#toString()
	 */
	public String toString() {
		return e1 + "," + e2;
	}

	/**
	 * Selecionada.
	 *
	 * @param s the s
	 */
	public void selecionada(boolean s) {
		seleccionada = s;
	}

	/**
	 * Seleccionada.
	 *
	 * @return true, if successful
	 */
	public boolean seleccionada() {
		return seleccionada;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object x) {
		if (!(x instanceof Peca))
			return false;
		Peca f = (Peca) x;
		if ((e1 == f.e1 && e2 == f.e2) || (e1 == f.e2 && e2 == f.e1))
			return true;
		return false;
	}

	/**
	 * Es doble.
	 *
	 * @return true, if successful
	 */
	public boolean esDoble() {
		return e1 == e2;
	}

	/**
	 * Inverter.
	 */
	public void inverter() {
		b1.setIcon(new javax.swing.ImageIcon(getClass().getResource(e2 + ".png")));
		b2.setIcon(new javax.swing.ImageIcon(getClass().getResource(e1 + ".png")));
		int temp = e1;
		e1 = e2;
		e2 = temp;
		validate();
	}

	/**
	 * Gira.
	 */
	public void gira() {
		if (horizontal) {
			setSize(new java.awt.Dimension(tamanho, tamanho * 2));
			setLayout(new java.awt.GridLayout(2, 1));

			if (visible)
				b1.setIcon(new javax.swing.ImageIcon(getClass().getResource(e1 + ".png")));
			    b1.setBackground(Color.BLACK);

			b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			b1.setFocusPainted(false);
			b1.setFocusable(false);
			b1.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
			b1.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
			b1.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
			add(b1);

			if (visible)
				b2.setIcon(new javax.swing.ImageIcon(getClass().getResource(e2 + ".png"))); // NOI18N
			else
				b2.setBackground(Color.BLACK);
			b2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			b2.setFocusPainted(false);
			b2.setFocusable(false);
			b2.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
			b2.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
			b2.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
			add(b2);
			horizontal = false;
		} else {
			setSize(new java.awt.Dimension(tamanho, tamanho));
			setLayout(new java.awt.GridLayout(1, 2));

			if (visible)
				b1.setIcon(new javax.swing.ImageIcon(getClass().getResource(e1 + ".png"))); // NOI18N
			else
				b1.setBackground(Color.BLACK);
			b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			b1.setFocusPainted(false);
			b1.setFocusable(false);
			b1.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
			b1.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
			b1.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
			add(b1);

			if (visible)
				b2.setIcon(new javax.swing.ImageIcon(getClass().getResource(e2 + ".png"))); // NOI18N
			else
				b2.setBackground(Color.BLACK);
			b2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			b2.setFocusPainted(false);
			b2.setFocusable(false);
			b2.setMaximumSize(new java.awt.Dimension(tamanho, tamanho));
			b2.setMinimumSize(new java.awt.Dimension(tamanho, tamanho));
			b2.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
			add(b2);
			horizontal = true;
		}
		validate();

	}

	/**
	 * Volta.
	 */
	public void volta() {
		if (visible) {
			b1.setIcon(null);
			b2.setIcon(null);
			b1.setBackground(Color.BLACK);
			b2.setBackground(Color.BLACK);
			add(b1);
			add(b2);

			visible = false;
		} else {
			b1.setIcon(new javax.swing.ImageIcon(getClass().getResource(e1 + ".png")));
			b2.setIcon(new javax.swing.ImageIcon(getClass().getResource(e2 + ".png")));
			add(b1);
			add(b2);
			visible = true;
		}
		validate();
	}

}
