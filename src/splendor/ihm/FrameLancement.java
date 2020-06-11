package splendor.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import splendor.Controleur;

public class FrameLancement extends JFrame implements ActionListener
{

    private JComboBox cbxLangue;
    private JComboBox cbxNombreJoueur;
    private JButton   btnValider;

    private Controleur controleur;

    public FrameLancement(Controleur controleur)
    {
        this.controleur = controleur;
        this.setLayout(new GridLayout(7, 1));
        this.setSize(300,300);

        // Création
        this.cbxLangue       = new JComboBox<>(new String[] { "FR", "ENG" });
        this.cbxNombreJoueur = new JComboBox<>(new Integer[] { 2, 3, 4 });
        this.btnValider      = new JButton("Valider votre choix");

        // Activation
        this.btnValider.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnValider)
        {
            this.controleur.lancementPartie(this.cbxLangue.getSelectedItem().toString(), Integer.parseInt(String.valueOf(this.cbxNombreJoueur.getSelectedItem())));
            this.dispose();
        }
    }

}