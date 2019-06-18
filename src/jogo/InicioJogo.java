package jogo;

import javax.swing.JFrame;
/**
 * Um jogador da partida, ou seja, a IA que decide como jogar.
 * 
 * <hr>
 * <h2>Métodos básicos</h2>
 * <p>{@code void} 
 * <b>{@link #InicioJogo()}</b>: 
 * Em um jogo de dominó, os quatro Jogadores vão jogar.
 * Escolhendo jogar só, escolhe seu nome como jogador humano
 * e os outros tres jogadores virtuais
 * Ganha quem termina as pecas primeiro
 * </p>
 * 
 * @author acastroa
 *
 */
public class InicioJogo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new inicio jogo.
	 */
	public InicioJogo() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Inits the components.
     */
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton3.setText("Jogar Só [Contra a Maquina]");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        
        jRadioButton1.setText("Convidar para um jogo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Participar de um jogo");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton3)
                .addGap(32, 32, 32)
                .addComponent(jRadioButton1)
                .addGap(27, 27, 27)
                .addComponent(jRadioButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * J button 1 action performed.
     *
     * @param evt the evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            dispose();
            //TODO Para implementar usando RMI
            //if (jRadioButton1.isSelected()) new DominoServidor().setVisible(true);
            //if (jRadioButton2.isSelected()) new DominoCliente().setVisible(true);
            if (jRadioButton3.isSelected()) new Domino().setVisible(true);
    }

    /**
     * J radio button 1 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
          jRadioButton2.setSelected(false);
          jRadioButton3.setSelected(false);
    }

    /**
     * J radio button 2 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton1.setSelected(false);
        jRadioButton3.setSelected(false);
    }

    /**
     * J radio button 3 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
       jRadioButton1.setSelected(false);
       jRadioButton2.setSelected(false);
    }

    /**
     * The main method.
     *
     * @param args the arguments
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
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioJogo().setVisible(true);
            }
        });
    }

    // Variáveis de declaração - não modificam
    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;

}
