package jogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
/**
 * Um jogador da partida, ou seja, a IA que decide como jogar.
 * 
 * <hr>
 * <h2> Métodos básicos </h2>
 * <p>  {@code void} 
 * <b>  {@link #InicioJogo()} </b>: 
 * Em um jogo de dominó, os quatro Jogadores vão jogar.
 * Escolhendo jogar só, escolhe seu nome como jogador humano
 * e os outros tres jogadores virtuais Ganha quem termina as pecas primeiro
 * <b>  Jogo com 28 peças </b>
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

        radioButtonChamaServidor = new JRadioButton();
        radioButtonChamaCliente = new JRadioButton();
        jButton1 = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(":: Jogo Dominó ::");
        jLabel1.setText("ESCOLHA COMO QUER JOGAR");
        
        radioButtonChamaServidor.setText("Convidar para um jogo");
        radioButtonChamaServidor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        radioButtonChamaCliente.setText("Participar de um jogo");
        radioButtonChamaCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("ok");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(radioButtonChamaCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioButtonChamaServidor, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(53, Short.MAX_VALUE))
            );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(32, 32, 32)
                    .addComponent(radioButtonChamaServidor)
                    .addGap(27, 27, 27)
                    .addComponent(radioButtonChamaCliente)
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
    private void jButton1ActionPerformed(ActionEvent evt) {
            dispose();
            //TODO Para implementar usando RMI
            
            // Convidar para um jogo
            if (radioButtonChamaServidor.isSelected()) new DominoServidor().setVisible(true);
            
            // Participar de um jogo
            if (radioButtonChamaCliente.isSelected()) new DominoCliente().setVisible(true);

    }

    /**
     * J radio button 1 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton1ActionPerformed(ActionEvent evt) {
          radioButtonChamaCliente.setSelected(false);
    }

    /**
     * J radio button 2 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton2ActionPerformed(ActionEvent evt) {
        radioButtonChamaServidor.setSelected(false);
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioJogo().setVisible(true);
            }
        });
    }

    // Variáveis de declaração - não modificam
    private JButton jButton1;
    private JRadioButton radioButtonChamaServidor;
    private JRadioButton radioButtonChamaCliente;
    private JLabel jLabel1;

}
