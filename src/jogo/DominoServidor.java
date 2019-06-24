package jogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.RandomStringUtils;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class DominoServidor extends JFrame {

	private static final long serialVersionUID = 1L;

	Timer voltas = new Timer();
	
	Random ramdon = new Random();
	ArrayList<Peca> pecas = new ArrayList<Peca>();
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	int X1, Y1, X2, Y2;
	Jogador j1, j2;
	int jogador_pos = 0;
	Peca cursor = null;
	Peca f = null;
	int b1 = 0, b2 = 0;

	Jogador j;
	ServerSocket s = null;
	Socket s1, s2, s3;
	ObjectOutputStream w1, w2;
	ObjectInputStream r1, r2, r3;

	static public ArrayList<Object> todas_pecas = new ArrayList<Object>();

	public DominoServidor() {
		initComponents();

		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void initComponents() {

		montaTabuleiro = new JPanel();
		panelJogador2 = new JPanel();
		panelJogador1 = new JPanel();
		nomeJogador_2 = new JLabel();
		nomeJogador_1 = new JLabel();
		Jpainel_2 = new JPanel();
		jButton1 = new JButton();
		Jpainel_1 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1350, 800));
		getContentPane().setLayout(new AbsoluteLayout());
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		montaTabuleiro.setPreferredSize(new Dimension(1300, 800));
		montaTabuleiro.setLayout(new AbsoluteLayout());
		montaTabuleiro.setBorder(new LineBorder(new Color(0, 0, 0)));

		panelJogador2.setPreferredSize(new java.awt.Dimension(300, 80));
		panelJogador2.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1, 300, 80));
		panelJogador2.getAccessibleContext().setAccessibleDescription("");

		panelJogador1.setPreferredSize(new java.awt.Dimension(300, 80));
		panelJogador1.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 650, 300, 80));

		montaTabuleiro.add(nomeJogador_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 10, 80, 30));
		montaTabuleiro.add(nomeJogador_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 680, 100, 30));

		getContentPane().add(montaTabuleiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1190, 770));

		Jpainel_2.setMinimumSize(new java.awt.Dimension(400, 800));
		Jpainel_2.setPreferredSize(new java.awt.Dimension(400, 800));
		Jpainel_2.setLayout(new AbsoluteLayout());
		Jpainel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		jButton1.setText("Iniciar Partida");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		Jpainel_2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

		Jpainel_1.setFont(new java.awt.Font("Times New Roman", 1, 14));
		Jpainel_1.setText("É a vez de....");
		Jpainel_2.add(Jpainel_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 30));

		getContentPane().add(Jpainel_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 0, 400, 770));

		pack();
	}
	/**
	 * Clique do botão Iniciar Partida
	 * @param evt
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		montaTabuleiro.removeAll();
		pecas = new ArrayList();
		jogadores = new ArrayList();
		voltas.cancel();

		cursor = null;
		f = null;
		b1 = 0;
		b2 = 0;
		j = null;
		todas_pecas = new ArrayList();

		panelJogador2 = new javax.swing.JPanel();
		panelJogador1 = new javax.swing.JPanel();
		nomeJogador_2 = new JLabel();
		nomeJogador_1 = new JLabel();

		panelJogador2.setPreferredSize(new java.awt.Dimension(300, 80));
		panelJogador2.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 280, 80));
		panelJogador2.getAccessibleContext().setAccessibleDescription("");

		panelJogador1.setPreferredSize(new java.awt.Dimension(300, 80));
		panelJogador1.setLayout(new java.awt.GridLayout(1, 14));
		montaTabuleiro.add(panelJogador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 650, 280, 80));

		montaTabuleiro.add(nomeJogador_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 10, 80, 30));
		montaTabuleiro.add(nomeJogador_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 680, 100, 30));

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

		panelJogador1.removeAll();
		panelJogador2.removeAll();

		voltas = new java.util.Timer();

		ArrayList<Peca> pecasDoJogador_1 = new ArrayList<Peca>();
		ArrayList<Peca> pecasDoJogador_3 = new ArrayList<Peca>();

		while (pecasDoJogador_1.size() < 14) {
			Peca f = pecas.get((int) (ramdon.nextFloat() * pecas.size()));
			if (!pecasDoJogador_1.contains(f)) {
				pecasDoJogador_1.add(f);
				pecas.remove(f);
				montaTabuleiro.remove(f);
				montaTabuleiro.validate();
			}
		}
		for (int i = 0; i < pecasDoJogador_1.size(); i++) {
			Peca f = (Peca) pecasDoJogador_1.get(i);
			if (!f.visible)
				f.volta();
			if (f.horizontal)
				f.gira();
			panelJogador1.add(f);
		}


		while (pecasDoJogador_3.size() < 14) {
			Peca f = pecas.get((int) (ramdon.nextFloat() * pecas.size()));
			if (!pecasDoJogador_3.contains(f)) {
				pecasDoJogador_3.add(f);
				pecas.remove(f);
				montaTabuleiro.remove(f);
				montaTabuleiro.validate();
			}
		}

		for (int i = 0; i < pecasDoJogador_3.size(); i++) {
			Peca f = (Peca) pecasDoJogador_3.get(i);
			// if (!f.visible) f.vuelta();
			if (f.horizontal)
				f.gira();
			panelJogador2.add(f);
		}

		Jpainel_1.setText("Esperando jogador...");
		montaTabuleiro.validate();

		Nome n = new Nome(null, true);
		n.setLocationRelativeTo(null);
		n.setVisible(true);
		
		// Verifica que tipo de jogador é::
		j1 = n.jogadorHumano.isSelected() ? new Jogador(pecasDoJogador_1) : new JogadorVirtualDemaisJogadores(pecasDoJogador_1);

		j1.nome = n.nome.getText() +" ID "+ RandomStringUtils.random(2,false,true);
		setTitle(j1.nome + "  " + this.getClass().getName());

		nomeJogador_1.setText(j1.nome);

		jogador_pos = 0;

		while (s == null) {
			try {
				s = new ServerSocket(100);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro de conexão 00 (Servidor)",
						"Não é possível definir o servidor....", JOptionPane.OK_OPTION);
			}
		}

		try {
			/**
			 * O método accept() escuta uma conexão e aceita se alguma for encontrada. 
			 * O accept() bloqueia todo o restante até que uma conexão seja feita, ele fica em espera aguardando que alguém conecte. 
			 * Quando alguma conexão é aceita ele retorna um objeto Socket, que veremos mais à frente.
			 */
			s2 = s.accept();
			/**
			 * getInputStream()
			 *  É através deste método que o Servidor (ServerSocket) consegue capturar o que o cliente está enviado. 
			 */
			w2 = new ObjectOutputStream(s2.getOutputStream());
			w2.writeObject(pecasDoJogador_3);
			r2 = new ObjectInputStream(s2.getInputStream());

			j2 = (Jogador) r2.readObject();
			nomeJogador_2.setText(j2.nome);


			jogadores.add(j1);
			jogadores.add(j2);

			w2.writeObject(jogadores);


		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, " Erro de conexão (Servidor)", " COD 1 - Exit",
					javax.swing.JOptionPane.OK_OPTION);

			return;
		}

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

		voltas.scheduleAtFixedRate(timerTask, 0, 2000);
	}

	void voltas() {

		f = null;
		j = (Jogador) jogadores.get(jogador_pos);

		String s1 = (cursor != null) ? cursor.lado1 + "" : "";
		String s2 = (cursor != null) ? cursor.lado2 + "" : "";
		Jpainel_1.setForeground(Color.red);

		Jpainel_1.setText("Aguad. o jogador " + j.nome + " " + s1 + "|" + s2);
		Jpainel_1.validate();

		if (cursor != null && (j.Move(cursor.lado1) || j.Move(cursor.lado2))) {

			try {

				if (j.equals(j1)) {
					f = j1.pecasMovendo(cursor);

					if (f != null) {

						j1.pecas.remove(f);
						
						w2.writeObject(new Dados(f, f.ponta, jogadores));

					}
				}
				else if (j.equals(j2)) {
					Dados d = (Dados) r2.readObject();
					f = d.f;
					if (f != null) {
						f.ponta = d.ponta;
						j2.pecas.remove(f);
					}
				}

			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, " Erro de conexão (Servidor)", " COD 2 - Exit",
						javax.swing.JOptionPane.OK_OPTION);

				voltas.cancel();
				return;
			}

		}

		else if (cursor != null) {
			if (!(j instanceof JogadorVirtual))

				javax.swing.JOptionPane.showMessageDialog(null, "Não tenho",
						((Jogador) jogadores.get(jogador_pos)).nome + " Passo", javax.swing.JOptionPane.OK_OPTION);

			jogador_pos++;
			if (jogador_pos == jogadores.size())
				jogador_pos = 0;

			return;
		} else if (cursor == null) {
			try {
				if (j.equals(j1)) {
					f = j1.pecasMovendo(cursor);

					if (f != null) {
						j1.pecas.remove(f);

						w2.writeObject(new Dados(f, f.ponta, jogadores));

					}
				}

				else if (j.equals(j2)) {
					Dados d = (Dados) r2.readObject();
					f = d.f;
					if (f != null) {
						f.ponta = d.ponta;
						j2.pecas.remove(f);
						
						w2.writeObject(new Dados(f, f.ponta, jogadores));
					}
				}

			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, " Erro de conexão (Servidor)", " COD 3 - Exit",
						javax.swing.JOptionPane.OK_OPTION);

				voltas.cancel();
				return;
			}

		}

		if (f != null) {

			if (cursor == null) {
				cursor = new Peca(f.lado1, f.lado2, pecas);
				cursor.setVisible(false);

				if (f.saoIguais() && f.horizontal)
					f.gira();
				if (!f.saoIguais() && !f.horizontal)
					f.gira();
				if (f.saoIguais())
					montaTabuleiro.add(f,
							new org.netbeans.lib.awtextra.AbsoluteConstraints(X1, Y1 - Peca.tamanho / 2, -1, -1));
				else
					montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X1, Y1, -1, -1));
				if (!f.visible)
					f.volta();

				if (!f.saoIguais())
					X2 += Peca.tamanho * 2;
				else
					X2 += Peca.tamanho;

				j.pecas.remove(f);
				f = null;
				montaTabuleiro.validate();

			} else {
				int c1, c2;

				if (cursor.lado1 == f.ponta) {

					if (X1 > 220 && b1 == 0) {
						if (!f.saoIguais())
							X1 -= Peca.tamanho * 2;
						else
							X1 -= Peca.tamanho;

						if (f.lado1 == f.ponta) {
							c1 = f.lado2;
						} else {
							c1 = f.lado1;
						}
						c2 = cursor.lado2;
						if (f.ponta == f.lado1)
							f.inverter();

						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X1,
									Y1 - Peca.tamanho / 2, -1, -1));
						else
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X1, Y1, -1, -1));

						if (!f.visible)
							f.volta();

						j.pecas.remove(f);
						f = null;
						cursor.lado1 = c1;
						cursor.lado2 = c2;
						montaTabuleiro.validate();

					} else {
						b1 = 1;
						if (X1 <= 220) {
							Y1 = Y1 - 100;
							// X1-=Ficha.tamanho;
						}

						// X1+=Ficha.tamanho*2;
						if (f.lado1 == f.ponta) {
							c1 = f.lado2;
						} else {
							c1 = f.lado1;
						}
						c2 = cursor.lado2;
						if (f.ponta == f.lado2)
							f.inverter();
						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X1,
									Y1 - Peca.tamanho / 2, -1, -1));
						else
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X1, Y1, -1, -1));

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

						j.pecas.remove(f);
						f = null;

						cursor.lado1 = c1;
						cursor.lado2 = c2;
						montaTabuleiro.validate();

					}

				}

				else if (cursor.lado2 == f.ponta) {
					if (X2 < montaTabuleiro.getWidth() - 280 && b2 == 0) {
						// X2+=Ficha.tamanho*2;
						if (f.lado1 == f.ponta) {
							c2 = f.lado2;
						} else {
							c2 = f.lado1;
						}
						c1 = cursor.lado1;
						if (f.ponta == f.lado2)
							f.inverter();

						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X2,
									Y2 - Peca.tamanho / 2, -1, -1));
						else
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X2, Y2, -1, -1));

						if (!f.saoIguais())
							X2 += Peca.tamanho * 2;
						else
							X2 += Peca.tamanho;

						if (!f.visible)
							f.volta();

						j.pecas.remove(f);
						f = null;
						cursor.lado1 = c1;
						cursor.lado2 = c2;
						montaTabuleiro.validate();

					} else {
						b2 = 1;
						if (X2 > montaTabuleiro.getWidth() - 300) {
							Y2 = Y2 + 100;
							// X2=X2+Ficha.tamanho;
						}

						if (!f.saoIguais())
							X2 -= Peca.tamanho * 2;
						else
							X2 -= Peca.tamanho;

						// X2-=Ficha.tamanho*2;
						if (f.lado1 == f.ponta) {
							c2 = f.lado2;
						} else {
							c2 = f.lado1;
						}
						c1 = cursor.lado1;
						if (f.ponta == f.lado1)
							f.inverter();
						if (f.saoIguais() && f.horizontal)
							f.gira();
						if (!f.saoIguais() && !f.horizontal)
							f.gira();
						if (f.saoIguais())
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X2,
									Y2 - Peca.tamanho / 2, -1, -1));
						else
							montaTabuleiro.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(X2, Y2, -1, -1));

						if (!f.visible)
							f.volta();
						if (X2 < 220) {
							Y2 = Y2 + 100;
							b2 = 0;
							// X2-=Ficha.tamanho*2;
						}

						j.pecas.remove(f);
						f = null;
						cursor.lado1 = c1;
						cursor.lado2 = c2;
						montaTabuleiro.validate();

					}

				} else {
					if (!(j instanceof JogadorVirtual))
						JOptionPane.showMessageDialog(null, "Selecão incorreta " + f.toString(),
								"INFO, " + j.nome + " voce perdeu sua vez", JOptionPane.OK_OPTION);
					f.seleccionada = false;
					j.pecas.add(f);

				}
			}

			boolean algumGanha = false;

			for (int i = 0; i < jogadores.size(); i++) {
				Jogador x = (Jogador) jogadores.get(i);
				if (cursor != null && (x.Move(cursor.lado1) || x.Move(cursor.lado2)))
					algumGanha = true;

			}

			if (j.pecas.isEmpty())

			{
				for (int i = 0; i < jogadores.size(); i++)
					for (int y = 0; y < ((Jogador) jogadores.get(i)).pecas.size(); y++) {
						Peca fi = (Peca) ((Jogador) jogadores.get(i)).pecas.get(y);

						if (!fi.visible)
							fi.volta();

					}

				JOptionPane.showMessageDialog(null, "Ganhou " + j.nome, "Fim",JOptionPane.OK_OPTION);

				voltas.cancel();
				montaTabuleiro.removeAll();
			}

			else if (!algumGanha) {
				Hashtable tabla = new Hashtable();

				for (int i = 0; i < jogadores.size(); i++) {
					Jogador x = (Jogador) jogadores.get(i);
					int puntos = 0;
					for (int p = 0; p < x.pecas.size(); p++) {
						Peca fx = (Peca) x.pecas.get(p);
						puntos += fx.lado1 + fx.lado2;
					}

					tabla.put(x, puntos);

				}

				int menor = 100000;
				Jogador ganador = null;

				Enumeration e = tabla.keys();
				while (e.hasMoreElements()) {
					Jogador r = (Jogador) e.nextElement();
					if (menor > (int) tabla.get(r)) {
						menor = (int) tabla.get(r);
						ganador = r;
					}
				}

				e = tabla.keys();

				for (int i = 0; i < jogadores.size(); i++)
					for (int y = 0; y < ((Jogador) jogadores.get(i)).pecas.size(); y++) {
						Peca fi = (Peca) ((Jogador) jogadores.get(i)).pecas.get(y);

						if (!fi.visible)
							fi.volta();

					}

				while (e.hasMoreElements()) {
					Jogador z = (Jogador) e.nextElement();
					int r = (int) tabla.get(z);
					if (r == menor) {
						JOptionPane.showMessageDialog(null,	"Ganhou " + z.nome + "\n" + "con " + r + " pontos.", "FIM JOGO",
								JOptionPane.OK_OPTION);
					}
				}

				voltas.cancel();
				montaTabuleiro.removeAll();

			}

			for (int i = 0; i < jogadores.size(); i++) {
				if (jogadores.get(i).equals(j1))
					j1.pecas = ((Jogador) jogadores.get(i)).pecas;
				if (jogadores.get(i).equals(j2))
					j2.pecas = ((Jogador) jogadores.get(i)).pecas;
			}
			panelJogador1.removeAll();
			panelJogador2.removeAll();

			for (int i = 0; i < j1.pecas.size(); i++)
				panelJogador1.add((Peca) j1.pecas.get(i));
			for (int i = 0; i < j2.pecas.size(); i++)
				panelJogador2.add((Peca) j2.pecas.get(i));

			for (int i = 0; i < j1.pecas.size(); i++) {
				Peca f = (Peca) j1.pecas.get(i);
				if (f.horizontal)
					f.gira();
				if (!f.visible)
					f.volta();
				f.b1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
					public void mouseDragged(java.awt.event.MouseEvent evt) {
						f.ponta = f.lado1;
						f.seleccionada = true;
					}

					public void mouseMoved(java.awt.event.MouseEvent evt) {
					}
				});
				f.b2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
					public void mouseDragged(java.awt.event.MouseEvent evt) {
						f.ponta = f.lado2;
						f.seleccionada = true;
					}

					public void mouseMoved(java.awt.event.MouseEvent evt) {
					}
				});
			}

			for (int i = 0; i < j2.pecas.size(); i++) {
				Peca f = (Peca) j2.pecas.get(i);
				if (!f.horizontal) // TODO verificar a negação
					f.gira();
				if (f.visible)
					f.volta();
			}

			panelJogador1.repaint();
			panelJogador1.setLayout(new java.awt.GridLayout(1, j1.pecas.size(), 0, 0));
			montaTabuleiro.add(panelJogador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 650,
					Peca.tamanho * j1.pecas.size(), Peca.tamanho * 2));

			panelJogador2.repaint();
			panelJogador2.setLayout(new java.awt.GridLayout(1, j2.pecas.size(), 0, 0));
			montaTabuleiro.add(panelJogador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0,
					Peca.tamanho * j2.pecas.size(), Peca.tamanho * 2));

			montaTabuleiro.validate();

			jogador_pos++;
			if (jogador_pos == jogadores.size())
				jogador_pos = 0;

		}

	}

	/**
	 * @param args the command line arguments
	 */

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
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

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DominoServidor().setVisible(true);
			}
		});
	}

	private JPanel montaTabuleiro;
	private JButton jButton1;
	private JLabel Jpainel_1;
	private JPanel Jpainel_2;
	private JLabel nomeJogador_1;
	private JLabel nomeJogador_2;
	private JPanel panelJogador1;
	private JPanel panelJogador2;

}
