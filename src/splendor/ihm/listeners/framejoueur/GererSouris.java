package splendor.ihm.listeners.framejoueur;

import splendor.ihm.FrameJoueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GererSouris extends MouseAdapter
{

    private FrameJoueur frameJoueur;

    public GererSouris(FrameJoueur frameJoueur)
    {
        this.frameJoueur = frameJoueur;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(this.frameJoueur.getCarteReserveChoisie() != null)
            this.frameJoueur.getCarteReserveChoisie().setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel carteChoisie = (JLabel) e.getSource();
        this.frameJoueur.setCarteReserveChoisie(carteChoisie);
        carteChoisie.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
    }

}
