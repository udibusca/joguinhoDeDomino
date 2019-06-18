package jogo;

import javax.swing.JDialog;
/**
 * Classe responsavel por criar o nome jogador/modal
 * @author acastroa
 *
 */
public class Nome extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Criar o nome jogador.
	 *
	 * @param parent the parent
	 * @param modal the modal
	 */
	public Nome(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Inits the components.
     */
    private void initComponents() {

        nome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jogadorVirtual = new javax.swing.JRadioButton();
        jogadorHumano = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrar com o nome");
        setMinimumSize(new java.awt.Dimension(300, 150));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nome.setMinimumSize(new java.awt.Dimension(100, 100));
        nome.setPreferredSize(new java.awt.Dimension(90, 30));
        getContentPane().add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 50, 30));

        jogadorVirtual.setText("Jogador Virtual");
        jogadorVirtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virtualActionPerformed(evt);
            }
        });
        getContentPane().add(jogadorVirtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 120, -1));

        jogadorHumano.setText("Jogador Humano");
        jogadorHumano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humanoActionPerformed(evt);
            }
        });
        getContentPane().add(jogadorHumano, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        pack();
    }

    /**
     * J button 1 action performed.
     *
     * @param evt the evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
   dispose();
    }

    /**
     * Virtual action performed.
     *
     * @param evt the evt
     */
    private void virtualActionPerformed(java.awt.event.ActionEvent evt) {
         jogadorHumano.setSelected(false);

    }

    /**
     * Humano action performed.
     *
     * @param evt the evt
     */
    private void humanoActionPerformed(java.awt.event.ActionEvent evt) {
      jogadorVirtual.setSelected(false);

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
            java.util.logging.Logger.getLogger(Nome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Nome dialog = new Nome(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Declaração das variaveis , não mexer
     */
    public javax.swing.JRadioButton jogadorHumano;
    private javax.swing.JButton jButton1;
    public javax.swing.JTextField nome;
    public javax.swing.JRadioButton jogadorVirtual;
}
