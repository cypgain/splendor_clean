package splendor.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import splendor.Controleur;
import splendor.utils.Message;

public class FrameLancement extends JFrame implements ActionListener {
    private JComboBox cbxLangue;
    private JComboBox cbxNombreJoueur;
    private JButton   btnValider;

    private Controleur ctrl;

    public FrameLancement(Controleur ctrl)
    {
        this.ctrl=ctrl;
        this.setLayout(new GridLayout(7,1));
        this.setSize(300,300);

        // Création
        this.cbxLangue=new JComboBox<String>(new String[] {"FR","ENG"});
        this.cbxNombreJoueur = new JComboBox<Integer>(new Integer[] {2,3,4});
        this.btnValider = new JButton("Valider votre choix");

        //activation
        this.btnValider.addActionListener(this);

        //ajout a la frame
        this.add(new JLabel("Langue"));
        this.add(this.cbxLangue);
        this.add(new JLabel(""));
        this.add(new JLabel("Nombre de joueurs"));
        this.add(this.cbxNombreJoueur);
        this.add(new JLabel(""));
        this.add(this.btnValider);


        this.setLocation(600,400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==this.btnValider)
        {
            this.ctrl.lancementPartie(this.cbxLangue.getSelectedItem().toString(),(Integer)this.cbxNombreJoueur.getSelectedItem());
            this.dispose();
        }
    }
}