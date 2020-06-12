package splendor.ihm;

import splendor.Controleur;
import splendor.metier.Noble;
import splendor.utils.Message;
import splendor.utils.SplendorFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameNoble extends JFrame implements ActionListener
{

    /**
     *
     */
    private static final long serialVersionUID = -3610097031579091119L;

    private Controleur controleur;

    private Noble[] visiteNoble;
    private Noble   nobleChoisi;

    private JLabel   lblTexte;
    private JPanel   panelNoble;
    private JLabel[] lblNobles;
    private JButton  btnValider;
    private JLabel   lblNobleChoisi;

    public FrameNoble(Controleur controleur, Noble[] visiteNoble)
    {
        this.controleur  = controleur;
        this.visiteNoble = visiteNoble;
        this.nobleChoisi = null;

        this.setLayout(new BorderLayout());

        //Creation des composants
        this.lblTexte = new JLabel(Message.CHOIX_NOBLE.getLib());
        this.lblTexte.setFont(SplendorFont.SEGOE_SMALL.getFont());

        this.panelNoble = new JPanel();
        this.panelNoble.setLayout(new GridLayout(1, this.visiteNoble.length));

        this.lblNobles = new JLabel[this.visiteNoble.length];

        for (int i = 0; i < this.lblNobles.length; i++)
        {
            this.lblNobles[i] = new JLabel();
            this.lblNobles[i].setIcon(new ImageIcon(this.visiteNoble[i].getUrl()));
            this.lblNobles[i].setName(this.visiteNoble[i].getUrl());
        }

        this.btnValider = new JButton(Message.BOUTON_VALIDER_NOBLE.getLib());
        this.btnValider.setEnabled(false);

        this.lblNobleChoisi = null;

        // Activation des composants
        this.btnValider.addActionListener(this);

        for (JLabel l : this.lblNobles)
            l.addMouseListener(new GererSouris());

        // Ajout des composants
        for (JLabel l : this.lblNobles)
            this.panelNoble.add(l);

        this.add(this.lblTexte,   BorderLayout.NORTH);
        this.add(this.panelNoble, BorderLayout.CENTER);
        this.add(this.btnValider, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public Noble getNobleChoisi()
    {
        return this.nobleChoisi;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnValider)
        {
            for (Noble n : this.visiteNoble)
                if (n.getUrl() == this.lblNobleChoisi.getName())
                    this.nobleChoisi = n;

            this.dispose();
        }
    }

    class GererSouris extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            FrameNoble.this.lblNobleChoisi = null;
            FrameNoble.this.btnValider.setEnabled(false);

            if (e.getSource() instanceof JLabel)
            {
                FrameNoble.this.lblNobleChoisi = (JLabel) e.getSource();
                FrameNoble.this.btnValider.setEnabled(true);
            }
        }
    }

}
