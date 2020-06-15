package splendor_equipe_h.ihm;

import javax.swing.*;
import java.awt.*;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.ihm.listeners.framelancement.GererBoutons;

@SuppressWarnings("serial")
public class FrameLancement extends JFrame
{

    private JComboBox<String> cbxLangue;
    private JComboBox<Integer> cbxNombreJoueur;
    private JButton   btnValider;

    private Controleur controleur;

    public FrameLancement(Controleur controleur)
    {
        this.controleur = controleur;

        this.setTitle("Lancement");
        this.setLayout(new GridLayout(7, 1));
        this.setSize(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);

        // Création
        this.cbxLangue       = new JComboBox<>(new String[] { "FR", "ENG" });
        this.cbxNombreJoueur = new JComboBox<>(new Integer[] { 2, 3, 4 });
        this.btnValider      = new JButton("Valider votre choix");

        // Activation
        this.btnValider.addActionListener(new GererBoutons(this));

        // Ajout à la frame
        this.add(new JLabel("Langue"));
        this.add(this.cbxLangue);
        this.add(new JLabel(""));
        this.add(new JLabel("Nombre de joueurs"));
        this.add(this.cbxNombreJoueur);
        this.add(new JLabel(""));
        this.add(this.btnValider);

        // Affichage
        this.setLocation(600, 400);
        this.setVisible(true);
    }

    public void lancementPartie(String langue, int nbJoueurs)
    {
        this.controleur.lancementPartie(langue, nbJoueurs);
    }

    public JButton            getBtnValider()      { return this.btnValider;      }
    public JComboBox<String>  getCbxLangue()       { return this.cbxLangue;       }
    public JComboBox<Integer> getCbxNombreJoueur() { return this.cbxNombreJoueur; }

}