package splendor.ihm.listeners.frameplateau;

import splendor.ihm.FramePlateau;
import splendor.metier.Carte;
import splendor.utils.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererBoutons implements ActionListener
{

    private FramePlateau framePlateau;

    public GererBoutons(FramePlateau framePlateau)
    {
        this.framePlateau = framePlateau;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == framePlateau.getBtnAcheter())
        {
            this.framePlateau.resetJetonsChoisis();

            if(this.framePlateau.getCarteSelectionnee() != -1)
            {
                Carte carteSelectionnee = this.framePlateau.getTabCartes()[this.framePlateau.getCarteSelectionnee()];

                if(carteSelectionnee != null)
                {
                    if (!(this.framePlateau.acheterCarte(this.framePlateau.getCurrentJoueur(), carteSelectionnee)))
                    {
                        JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_NOT_ENOUGH_JETON.getLib() );
                        return;
                    }

                    this.framePlateau.resetDosCarteSelectionnee();
                    this.framePlateau.resetCarteSelectionnee();
                    this.framePlateau.updateGraphics();

                    // TODO - Fin du tour joueur
                }
            }
        }
        else if(e.getSource() == framePlateau.getBtnReserve())
        {
            System.out.println("RESERVER");
        }
    }

}
