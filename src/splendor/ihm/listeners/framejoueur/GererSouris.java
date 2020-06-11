package splendor.ihm.listeners.framejoueur;

import splendor.ihm.FrameJoueur;

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
        System.out.println("TEST");
    }

}
