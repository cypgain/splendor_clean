package splendor_equipe_h.ihm.listeners.framejoueur;

import splendor_equipe_h.ihm.FrameJoueur;
import splendor_equipe_h.metier.Carte;
import splendor_equipe_h.utils.Message;

import javax.swing.*;
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
        if(this.frameJoueur.getJoueur() == this.frameJoueur.getCurrentJoueur())
        {
            Carte carteReserve = this.frameJoueur.getCarteReserve();

            if(carteReserve != null)
            {
                if (this.frameJoueur.prendreCarte(this.frameJoueur.getJoueur(), carteReserve))
                {
                    this.frameJoueur.getJoueur().getTabCartesReserve().remove(carteReserve);
                }
                else
                {
                    JOptionPane.showMessageDialog(this.frameJoueur, Message.ERR_NOT_ENOUGH_JETON.getLib());
                    return;
                }

                this.frameJoueur.finTourJoueur();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this.frameJoueur, Message.NOT_YOUR_TURN.getLib());
        }

        this.frameJoueur.resetCarteReserveChoisie();
        this.frameJoueur.updateGraphics();
    }

}
