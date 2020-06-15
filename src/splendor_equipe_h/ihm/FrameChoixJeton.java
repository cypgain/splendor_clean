package splendor_equipe_h.ihm;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.utils.Couleur;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrameChoixJeton extends JFrame implements ActionListener 
{

    private Controleur controleur;

    private JLabel   lblTexte;
    private JPanel   panelJeton;
    private JLabel[] lblJetons;
    private JButton  btnValider;

    private int[]    tabJetonReposer;

    public FrameChoixJeton(Controleur controleur, int[] tabJetonsChoisis) 
    {
        this.controleur = controleur;

        this.setLayout(new BorderLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.setResizable(false);
        this.setTitle(Message.FRAME_JETON.getLib());

        // Creation des composants
        this.lblTexte = new JLabel(Message.CHOIX_DEPOSER_JETON.getLib().replace("{NUM}", ""+ this.controleur.getNbJetonADeposer()));
        this.lblTexte.setFont(SplendorFont.SEGOE_SMALL.getFont());

        this.panelJeton = new JPanel(new GridLayout(1, tabJetonsChoisis.length, 20, 20));

        this.lblJetons = new JLabel[tabJetonsChoisis.length];

        this.tabJetonReposer=null;

        
        for (int i = 0; i < this.lblJetons.length && tabJetonsChoisis[i] != -1; i++) 
        {
            
            this.lblJetons[i] = new JLabel();
            this.lblJetons[i].setIcon(new ImageIcon("ressources/jeton_" + Couleur.values()[tabJetonsChoisis[i]].toString().toLowerCase() + ".png"));
            this.lblJetons[i].setName(""+tabJetonsChoisis[i]);
            this.lblJetons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }
        

        this.btnValider = new JButton(Message.BOUTON_DEPOSER_JETON.getLib());
        this.btnValider.setEnabled(false);

        // Activation des composants
        this.btnValider.addActionListener(this);

        
        for (JLabel l : this.lblJetons)
        {
            if (l!=null) l.addMouseListener(new GererSouris());
        }
        

        // Ajout des composants
        for (JLabel l : this.lblJetons)
        {
            if (l!=null) this.panelJeton.add(l);
        }
        
        this.add(this.lblTexte,   BorderLayout.NORTH);
        this.add(this.panelJeton, BorderLayout.CENTER);
        this.add(this.btnValider, BorderLayout.SOUTH);

        this.setLocation((int) ((Controleur.screenWidth  - this.getSize().getWidth ()) / 2),
                         (int) ((Controleur.screenHeight - this.getSize().getHeight()) / 2));

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == this.btnValider) 
        {
            this.tabJetonReposer = this.setJetonReposerChoisis();
        }
    }

    public int nbJetonsChoisis()
    {
        int cpt = 0;

        for (JLabel lbl : this.lblJetons) 
        {
            if (lbl == null)
                continue;

            if (((LineBorder) lbl.getBorder()).getLineColor() == Color.RED)
            {
                cpt++;
            }
        }

        return cpt;
    }

    public boolean enoughJetonChoisis()
    {
        return this.nbJetonsChoisis() >= this.controleur.getNbJetonADeposer();
    }

    public int[] setJetonReposerChoisis()
    {
        int[] tabJetonReposer = new int[this.controleur.getTabJetonsChoisis().length];
        int   cpt             = 0;

        for (JLabel lbl : this.lblJetons) 
        {
            if (lbl == null)
            {
                tabJetonReposer[cpt] = -1;
                cpt++;
                continue;
            }
            
            if (((LineBorder) lbl.getBorder()).getLineColor() == Color.RED)
            {
                tabJetonReposer[cpt] = Integer.parseInt(lbl.getName());
                cpt++;
            }
        }

        return tabJetonReposer;
    }

    public int[] getJetonReposerChoisis()
    {
        return this.tabJetonReposer;
    }

    class GererSouris extends MouseAdapter 
    {
        public void mousePressed(MouseEvent e) 
        {

            if (e.getSource() instanceof JLabel) 
            {
                if (((LineBorder) ((JLabel) e.getSource()).getBorder()).getLineColor() == Color.WHITE)
                {
                    ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                }
                else
                {
                    ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                }
            }
            if (FrameChoixJeton.this.enoughJetonChoisis())
            {
                FrameChoixJeton.this.btnValider.setEnabled(true);
            }
            else
            {
                FrameChoixJeton.this.btnValider.setEnabled(false);
            }
        }
    }

    public static void main(String[] args)
    {
        new FrameChoixJeton(new Controleur(), new int[] {0,0,4});
    }

}
