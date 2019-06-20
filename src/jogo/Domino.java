package jogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.RandomStringUtils;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


/**
 * Classe responsavel montar a tela e logica do jogo.
 * @author acastroa
 *
 */
public class Domino extends JFrame {

	private static final long serialVersionUID = 1L;

	Timer voltas;
	
	Random ramdon = new Random();
	
	ArrayList<Peca> pecas = new ArrayList<Peca>();
	
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	int X1, Y1, X2, Y2;
	
	Jogador j1, j2;
	
	int jogadores_pos = 0;
	
	Peca cursor = null;
	
	Peca f = null;
	
	int b1 = 0, b2 = 0;
	
	static public ArrayList<Object> todas_pecas = new ArrayList<Object>();

	/**
	 * Instantiates a new domino.
	 */
	public Domino() {
		
		initComponents();

		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	/**
	 * Inits the components.
	 */
	private void initComponents() {

		montaTabuleiro = new JPanel();
		panelJogador_1 = new JPanel();
		nomeJogador_1 = new JLabel();
		panelJogador_2 = new JPanel();
		nomeJogador_2 = new JLabel();

		jPanel2 = new JPanel();
		jButton1 = new JButton();
		jLabel1 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1350, 800));
		getContentPane().setLayout(new AbsoluteLayout());
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

		montaTabuleiro.setPreferredSize(new Dimension(1300, 800));
		montaTabuleiro.setLayout(new AbsoluteLayout());
		montaTabuleiro.setBorder(new LineBorder(new Color(0, 0, 0)));

		panelJogador_2.setPreferredSize(new Dimension(300, 80));
		panelJogador_2.setLayout(new java.awt.GridLayout(1, 7));
		montaTabuleiro.add(panelJogador_2, new AbsoluteConstraints(350, 0, 300, 80));
		panelJogador_2.getAccessibleContext().setAccessibleDescription("");

		panelJogador_1.setPreferredSize(new Dimension(300, 80));
		panelJogador_1.setLayout(new GridLayout(1, 7));
		montaTabuleiro.add(panelJogador_1, new AbsoluteConstraints(360, 650, 300, 80));

		montaTabuleiro.add(nomeJogador_1, new AbsoluteConstraints(230, 680, 100, 30));
		montaTabuleiro.add(nomeJogador_2, new AbsoluteConstraints(234, 10, 80, 30));


		getContentPane().add(montaTabuleiro, new AbsoluteConstraints(10, 0, 1190, 770));

		jPanel2.setMinimumSize(new java.awt.Dimension(400, 800));
		jPanel2.setPreferredSize(new java.awt.Dimension(400, 800));
		jPanel2.setLayout(new AbsoluteLayout());

		jButton1.setText("Iniciar Partida");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jPanel2.add(jButton1, new AbsoluteConstraints(10, 10, -1, -1));

		jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		jLabel1.setText("É a vez de....");
		jPanel2.add(jLabel1, new AbsoluteConstraints(20, 60, 300, 30));

		getContentPane().add(jPanel2, new AbsoluteConstraints(1190, 0, 400, 770));

		pack();
	}

	/**
	 * J button 1 action performed.
	 *
	 * @param evt the evt
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		montaTabuleiro.removeAll();
		pecas = new ArrayList<Peca>();
		jogadores = new ArrayList<Jogador>();

		cursor = null;
		f = null;
		b1 = 0;
		b2 = 0;
		j = null;
		todas_pecas = new ArrayList<Object>();

		panelJogador_1 = new JPanel();
		panelJogador_2 = new JPanel();
		nomeJogador_1 = new JLabel();
		nomeJogador_2 = new JLabel();

		panelJogador_2.setPreferredSize(new java.awt.Dimension(280, 80));
		panelJogador_2.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador_2, new AbsoluteConstraints(350, 0, 280, 80));
		panelJogador_2.getAccessibleContext().setAccessibleDescription("");

		panelJogador_1.setPreferredSize(new java.awt.Dimension(280, 80));
		panelJogador_1.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador_1, new AbsoluteConstraints(360, 650, 280, 80));

		montaTabuleiro.add(nomeJogador_1, new AbsoluteConstraints(230, 680, 100, 30));
		montaTabuleiro.add(nomeJogador_2, new AbsoluteConstraints(234, 10, 80, 30));

		/**
		 * Criar os tipos das pecas disponiveis
		 */
	    new Peca(0, 0, pecas);
	    new Peca(0, 1, pecas);
	    
	    new Peca(0, 2, pecas);
	    new Peca(0, 3, pecas);
	    new Peca(0, 4, pecas);
	    new Peca(0, 5, pecas);
	    new Peca(0, 6, pecas);
	    
	    new Peca(1, 1, pecas);
	    new Peca(1, 2, pecas);
	    new Peca(1, 3, pecas);
	    new Peca(1, 4, pecas);
	    new Peca(1, 5, pecas);
	    new Peca(1, 6, pecas);
	    
	    new Peca(2, 2, pecas);
	    new Peca(2, 3, pecas);
	    new Peca(2, 4, pecas);
	    new Peca(2, 5, pecas);
	    new Peca(2, 6, pecas);
	    
	    new Peca(3, 3, pecas);
	    new Peca(3, 4, pecas);
	    new Peca(3, 5, pecas);
	    new Peca(3, 6, pecas);
	    
	    new Peca(4, 4, pecas);
	    new Peca(4, 5, pecas);
	    new Peca(4, 6, pecas);
	    
	    new Peca(5, 5, pecas);
	    new Peca(5, 6, pecas);
	    
	    new Peca(6, 6, pecas);
		
		new Peca(6, 6, pecas);

		panelJogador_1.removeAll();
		panelJogador_2.removeAll();

		voltas = new Timer();

		ArrayList<Peca> pecasJogador_1 = new ArrayList<Peca>();
		ArrayList<Peca> pecasJogador_2 = new ArrayList<Peca>();

		/**
		 * Sorteia as pecas para o jogador 1
		 */
		while (pecasJogador_1.size() < 14) {
			Peca f = (Peca) pecas.get((int) (ramdon.nextFloat() * pecas.size()));
			if (!pecasJogador_1.contains(f)) {
				pecasJogador_1.add(f);
				pecas.remove(f);
				montaTabuleiro.remove(f);
				montaTabuleiro.validate();
			}
		}

		pecasJogador_1.stream().forEach(p -> {
			if(!p.visible) {
				p.volta();
			}
			if(p.horizontal) {
				p.gira();
			}
			panelJogador_1.add(p);
			System.out.println("Peca do jogador 1 : " + p);
		});

		j1 = new Jogador(pecasJogador_1);
		Nome n = new Nome(null, true);
		n.setLocationRelativeTo(null);
		n.setVisible(true);
		// Evitar nomes iguais ::
		j1.nome = n.nome.getText() +" ID "+ RandomStringUtils.random(2,false,true);
		nomeJogador_1.setText(j1.nome);

		/**
		 * Sorteia as pecas para o jogador 2
		 */		
		while (pecasJogador_2.size() < 14) {
			Peca f = (Peca) pecas.get((int) (ramdon.nextFloat() * pecas.size()));
			if (!pecasJogador_2.contains(f)) {
				pecasJogador_2.add(f);
				pecas.remove(f);
				montaTabuleiro.remove(f);
				montaTabuleiro.validate();
			}
		}

		pecasJogador_2.stream().forEach(p -> {
			if(p.horizontal) {
				p.gira();
			}
			panelJogador_2.add(p);
			System.out.println("Peca do jogador 2 : " + p);
		});
		
		j2 = new JogadorVirtualDemaisJogadores(pecasJogador_2);
		n = new Nome(null, true);
		n.setLocationRelativeTo(null);
		n.setVisible(true);
		// Evitar nomes iguais ::
		j2.nome = n.nome.getText() +" ID "+ RandomStringUtils.random(2,false,true);

		nomeJogador_2.setText(j2.nome);

		jogadores.add(j1);
		jogadores.add(j2);

		/**
		 * Verificar quem tem a peça 6|6 para iniciar o jogo.
		 */
		jogadores.stream().forEach(j -> j.getFichas().forEach(f-> {
			System.err.println("Peca > " + f + " Jogador > " + j.getNome());
		}));
		
		jogadores_pos = (int) (jogadores.size() * ramdon.nextFloat());

		TimerTask timerTask = new TimerTask() {
			public void run() {
				try {
					voltas();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};

		X2 = X1 = montaTabuleiro.getWidth() / 2;
		Y2 = Y1 = montaTabuleiro.getHeight() / 2;

		montaTabuleiro.repaint();
		montaTabuleiro.validate();
		
		// Schedule para ficar verificando as jogadas
		voltas.scheduleAtFixedRate(timerTask, 0, 2000);

}

	Jogador j;
	Random random = new Random();

	/**
	 * Voltas das jogadas.
	 */
	void voltas() {

		j = jogadores.get(jogadores_pos);

		if (cursor != null && (j.Move(cursor.e1) || j.Move(cursor.e2))) {
			f = j.pecasMovendo(cursor);
		}else if (cursor != null) {
			JOptionPane.showMessageDialog(null, "Não tenho a peça",
					jogadores.get(jogadores_pos).nome + " PASSAR", JOptionPane.OK_OPTION);

			jogadores_pos++;
			if (jogadores_pos == jogadores.size())
				jogadores_pos = 0;
			return;
		} else if (cursor == null) {
			f = j.pecasMovendo(cursor);
			System.out.println("Movimento -> Peca : "+ f +" Jogador : "+ j.nome);			
		}
		String s1 = (cursor != null) ? cursor.e1 + "" : "";
		String s2 = (cursor != null) ? cursor.e2 + "" : "";
		jLabel1.setForeground(Color.red);

		jLabel1.setText("Aguad. o jogador " + j.nome + " Peça: " + s1 + "|" + s2);
		jLabel1.validate();

		if (f != null) {

			if (cursor == null) {
				cursor = new Peca(f.e1, f.e2, pecas);
				cursor.setVisible(false);

				if (f.saoIguais() && f.horizontal)
					f.gira();
				if (!f.saoIguais() && !f.horizontal)
					f.gira();
				if (f.saoIguais())
					montaTabuleiro.add(f,
							new AbsoluteConstraints(X1, Y1 - Peca.tamanho / 2, -1, -1));
				else
					montaTabuleiro.add(f, new AbsoluteConstraints(X1, Y1, -1, -1));
				if (!f.visible)
					f.volta();
				if (!f.saoIguais())
					X2 += Peca.tamanho * 2;
				else
					X2 += Peca.tamanho;

				montaTabuleiro.validate();
				j.fichas.remove(f);

			} else {
				int c1, c2;

				if (cursor.e1 == f.ponta) {

					if (X1 > 220 && b1 == 0) {
						if (!f.saoIguais())
							X1 -= Peca.tamanho * 2;
						else
							X1 -= Peca.tamanho;
						if (f.e1 == f.ponta) {
							c1 = f.e2;
						} else {
							c1 = f.e1;
						}
						c2 = cursor.e2;
						if (f.ponta == f.e1)
							f.inverter();

						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new AbsoluteConstraints(X1, Y1 - Peca.tamanho / 2,
									-1, -1));
						else
							montaTabuleiro.add(f, new AbsoluteConstraints(X1, Y1, -1, -1));

						if (!f.visible)
							f.volta();

						montaTabuleiro.validate();
						j.fichas.remove(f);
						cursor.e1 = c1;
						cursor.e2 = c2;
					} else {
						b1 = 1;
						if (X1 <= 220) {
							Y1 = Y1 - 100;
							// X1-=Ficha.tamanho;
						}

						if (f.e1 == f.ponta) {
							c1 = f.e2;
						} else {
							c1 = f.e1;
						}
						c2 = cursor.e2;
						if (f.ponta == f.e2)
							f.inverter();
						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new AbsoluteConstraints(X1, Y1 - Peca.tamanho / 2,
									-1, -1));
						else
							montaTabuleiro.add(f, new AbsoluteConstraints(X1, Y1, -1, -1));

						if (!f.saoIguais())
							X1 += Peca.tamanho * 2;
						else
							X1 += Peca.tamanho;

						if (!f.visible)
							f.volta();

						if (X1 >= montaTabuleiro.getWidth() - 300) {
							Y1 = Y1 - 100;
							b1 = 0;
							// X1+=Ficha.tamanho*2;
						}

						montaTabuleiro.validate();
						j.fichas.remove(f);
						cursor.e1 = c1;
						cursor.e2 = c2;

					}

				}

				else if (cursor.e2 == f.ponta) {
					if (X2 < montaTabuleiro.getWidth() - 280 && b2 == 0) {

						if (f.e1 == f.ponta) {
							c2 = f.e2;
						} else {
							c2 = f.e1;
						}
						c1 = cursor.e1;
						if (f.ponta == f.e2)
							f.inverter();

						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new AbsoluteConstraints(X2, Y2 - Peca.tamanho / 2,
									-1, -1));
						else
							montaTabuleiro.add(f, new AbsoluteConstraints(X2, Y2, -1, -1));

						if (!f.saoIguais())
							X2 += Peca.tamanho * 2;
						else
							X2 += Peca.tamanho;

						if (!f.visible)
							f.volta();

						montaTabuleiro.validate();
						j.fichas.remove(f);
						cursor.e1 = c1;
						cursor.e2 = c2;

					} else {
						b2 = 1;
						if (X2 >= montaTabuleiro.getWidth() - 300) {
							Y2 = Y2 + 100;
							// X2=X2+Ficha.tamanho;
						}

						if (!f.saoIguais())
							X2 -= Peca.tamanho * 2;
						else
							X2 -= Peca.tamanho;

						if (f.e1 == f.ponta) {
							c2 = f.e2;
						} else {
							c2 = f.e1;
						}
						c1 = cursor.e1;
						if (f.ponta == f.e1)
							f.inverter();
						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new AbsoluteConstraints(X2, Y2 - Peca.tamanho / 2,
									-1, -1));
						else
							montaTabuleiro.add(f, new AbsoluteConstraints(X2, Y2, -1, -1));

						if (!f.visible)
							f.volta();
						if (X2 < 220) {
							Y2 = Y2 + 100;
							b2 = 0;
							// X2-=Ficha.tamanho*2;
						}

						montaTabuleiro.validate();
						j.fichas.remove(f);
						cursor.e1 = c1;
						cursor.e2 = c2;
					}

				} else {
					JOptionPane.showMessageDialog(null, "Selecão incorreta", "INFO", JOptionPane.OK_OPTION);
					f.seleccionada = false;
					return;
				}
			}

			panelJogador_1.repaint();
			panelJogador_1.setLayout(new java.awt.GridLayout(1, j1.fichas.size(), 0, 0));
			montaTabuleiro.add(panelJogador_1, new AbsoluteConstraints(360, 650,
					Peca.tamanho * j1.fichas.size(), Peca.tamanho * 2));

			panelJogador_2.repaint();
			panelJogador_2.setLayout(new java.awt.GridLayout(1, j2.fichas.size(), 0, 0));
			montaTabuleiro.add(panelJogador_2, new AbsoluteConstraints(350, 0,
					Peca.tamanho * j2.fichas.size(), Peca.tamanho * 2));


			boolean pagarAlguemGanhador = false;

			for (int i = 0; i < jogadores.size(); i++) {
				Jogador x = (Jogador) jogadores.get(i);
				if (cursor != null && (x.Move(cursor.e1) || x.Move(cursor.e2)))
					pagarAlguemGanhador = true;

			}

			if (j.fichas.isEmpty())			{
				for (int i = 0; i < jogadores.size(); i++)
					for (int y = 0; y < ((Jogador) jogadores.get(i)).fichas.size(); y++) {
						Peca fi = (Peca) ((Jogador) jogadores.get(i)).fichas.get(y);

						if (!fi.visible)
							fi.volta();

					}

				javax.swing.JOptionPane.showMessageDialog(null, "Ganhou " + j.nome, "Fim",
						javax.swing.JOptionPane.OK_OPTION);

				voltas.cancel();
				montaTabuleiro.removeAll();
				
			} else if (!pagarAlguemGanhador) {
				Hashtable<Jogador, Integer> placar = new Hashtable<Jogador, Integer>();

				for (int i = 0; i < jogadores.size(); i++) {
					Jogador x = (Jogador) jogadores.get(i);
					int pontos = 0;
					for (int p = 0; p < x.fichas.size(); p++) {
						Peca fx = (Peca) x.fichas.get(p);
						pontos += fx.e1 + fx.e2;
					}

					placar.put(x, pontos);

				}

				int menor = 100000;
				Jogador ganhador = null;

				Enumeration<Jogador> e = placar.keys();
				while (e.hasMoreElements()) {
					Jogador r = (Jogador) e.nextElement();
					if (menor > (int) placar.get(r)) {
						menor = (int) placar.get(r);
						ganhador = r;
					}
				}

				e = placar.keys();

				for (int i = 0; i < jogadores.size(); i++)
					for (int y = 0; y < ((Jogador) jogadores.get(i)).fichas.size(); y++) {
						Peca fi = (Peca) ((Jogador) jogadores.get(i)).fichas.get(y);

						if (!fi.visible)
							fi.volta();

					}

				while (e.hasMoreElements()) {
					Jogador z = (Jogador) e.nextElement();
					int r = (int) placar.get(z);
					if (r == menor) {

						javax.swing.JOptionPane.showMessageDialog(null,
								"Ganhou " + z.nome + "\n" + "con " + r + " pontos.", "FIM JOGO",
								javax.swing.JOptionPane.OK_OPTION);
					}
				}

				voltas.cancel();
				montaTabuleiro.removeAll();

			}

			jogadores_pos++;
			if (jogadores_pos == jogadores.size())
				jogadores_pos = 0;

		}

	}

	/**
	 * The main method.
	 *
	 * @param args the command line arguments
	 */

	public static void main(String args[]) {
		
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Domino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Domino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Domino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Domino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/**
		 * Chamada que vem do inicioJogo()
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Domino().setVisible(true);
			}
		});
	}

	private javax.swing.JPanel montaTabuleiro;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JLabel nomeJogador_1;
	private javax.swing.JLabel nomeJogador_2;
	private javax.swing.JPanel panelJogador_1;
	private javax.swing.JPanel panelJogador_2;

}
