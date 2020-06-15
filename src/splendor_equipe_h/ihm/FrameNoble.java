package splendor_equipe_h.ihm;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.metier.Noble;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrameNoble extends JFrame implements ActionListener
{

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
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Noble");
        this.setResizable(false);

        //Creation des composants
        this.lblTexte = new JLabel(Message.CHOIX_NOBLE.getLib());
        this.lblTexte.setFont(SplendorFont.SEGOE_SMALL.getFont());

        this.panelNoble = new JPanel();
        this.panelNoble.setLayout(new GridLayout(1, this.visiteNoble.length));

        this.lblNobles = new JLabel[this.visiteNoble.length];

        for (int i = 0; i < this.lblNobles.length; i++)
        {
            this.lblNobles[i] = new JLabel();
            this.lblNobles[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
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

        this.setLocation((int) ((Controleur.screenWidth  - this.getSize().getWidth() ) / 2),
                         (int) ((Controleur.screenHeight - this.getSize().getHeight()) / 2));

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
        public void mousePressed(MouseEvent e)
        {
            if(FrameNoble.this.lblNobleChoisi != null)
                FrameNoble.this.lblNobleChoisi.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

            FrameNoble.this.lblNobleChoisi = null;
            FrameNoble.this.btnValider.setEnabled(false);

            if (e.getSource() instanceof JLabel)
            {
                FrameNoble.this.lblNobleChoisi = (JLabel) e.getSource();
                FrameNoble.this.lblNobleChoisi.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                FrameNoble.this.btnValider.setEnabled(true);
            }
        }
    }

}
