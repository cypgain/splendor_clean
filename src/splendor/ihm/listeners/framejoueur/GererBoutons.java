package splendor.ihm.listeners.framejoueur;

import splendor.ihm.FrameJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererBoutons implements ActionListener
{

    private FrameJoueur frameJoueur;

    public GererBoutons(FrameJoueur frameJoueur)
    {
        this.frameJoueur = frameJoueur;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("WOW");
    }

}
