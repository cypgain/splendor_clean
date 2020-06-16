package splendor_equipe_h.ihm.listeners.framejoueur;

import splendor_equipe_h.ihm.FrameJoueur;

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

    private boolean isMouseWithinFrame() 
    {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        Rectangle bounds = this.frameJoueur.getBounds();
        bounds.setLocation(this.frameJoueur.getLocationOnScreen());
        return bounds.contains(mousePos);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

        JLabel carteChoisie = (JLabel) e.getSource();
        if (carteChoisie != this.frameJoueur.getCarteReserveChoisie())
        {
            this.frameJoueur.resetCarteReserveChoisie();
            this.frameJoueur.setCarteReserveChoisie(carteChoisie);
        }
        else
        {
            this.frameJoueur.resetCarteReserveChoisie();
        }
    }

}
