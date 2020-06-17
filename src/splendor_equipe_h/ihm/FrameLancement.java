package splendor_equipe_h.ihm;

import javax.swing.*;
import java.awt.*;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.ihm.listeners.framelancement.GererBoutons;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

@SuppressWarnings("serial")
public class FrameLancement extends JFrame
{
    private JPanel            panelPrincipal;

    private JComboBox<String> cbxLangue;
    private JComboBox<Integer> cbxNombreJoueur;

    private JLabel    lblLangue;
    private JLabel    lblJoueurs;

    private JButton   btnRegles;

    private JButton   btnValider;

    private Controleur controleur;

    public FrameLancement(Controleur controleur)
    {
        this.controleur = controleur;

        this.setTitle(Message.LANCEMENT.getLib());
        this.setSize(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Controleur.COULEUR_FOND);

        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);

        // Création
        this.panelPrincipal  = new JPanel(new GridLayout(9, 1));
        this.panelPrincipal.setBackground(Controleur.COULEUR_FOND);
        this.cbxLangue       = new JComboBox<>(new String[] { "FR", "ENG" });
        this.cbxNombreJoueur = new JComboBox<>(new Integer[] { 2, 3, 4 });
        this.btnRegles       = new JButton(Message.LANCEMENT_RULES.getLib());
        this.btnValider      = new JButton(Message.LANCEMENT_VALIDER.getLib());
        this.lblJoueurs      = new JLabel(Message.LANCEMENT_PLAYER.getLib(), JLabel.CENTER);
        this.lblJoueurs.setForeground(Controleur.COULEUR_TEXTE);
        this.lblJoueurs.setFont(SplendorFont.SEGOE_SMALL.getFont());
        this.lblLangue       = new JLabel(Message.LANCEMENT_LANGUE.getLib(), JLabel.CENTER);
        this.lblLangue.setForeground(Controleur.COULEUR_TEXTE);
        this.lblLangue.setFont(SplendorFont.SEGOE_SMALL.getFont());

        // Activation
        this.btnRegles .addActionListener(new GererBoutons(this));
        this.btnValider.addActionListener(new GererBoutons(this));

        // Ajout à la frame
        this.panelPrincipal.add(this.lblLangue);

        this.panelPrincipal.add(this.cbxLangue);
        this.panelPrincipal.add(new JLabel(""));
        this.panelPrincipal.add(this.lblJoueurs);
        this.panelPrincipal.add(this.cbxNombreJoueur);
        this.panelPrincipal.add(new JLabel(""));
        this.panelPrincipal.add(this.btnRegles);
        this.panelPrincipal.add(new JLabel(""));
        this.panelPrincipal.add(this.btnValider);

        this.add(this.panelPrincipal);

        // Affichage
        this.setLocation((int) ((Controleur.screenWidth - this.getSize().getWidth()) / 2), (int) (( Controleur.screenHeight - this.getSize().getHeight()) / 2));
        this.setVisible(true);
    }

    public void lancementPartie(String langue, int nbJoueurs)
    {
        this.controleur.lancementPartie(langue, nbJoueurs);
    }

    public JButton            getBtnValider()      { return this.btnValider;      }
    public JComboBox<String>  getCbxLangue()       { return this.cbxLangue;       }
    public JComboBox<Integer> getCbxNombreJoueur() { return this.cbxNombreJoueur; }
    public JButton            getBtnRegles()       { return this.btnRegles;       }

}