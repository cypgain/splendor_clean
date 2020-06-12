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

                    this.framePlateau.finTourJoueur();
                }
            }
        }
        else if(e.getSource() == framePlateau.getBtnReserve())
        {
            this.framePlateau.resetJetonsChoisis();

            int dosCarteChoisi = this.framePlateau.getDosCarteSelectionnee();
            int carteChoisie   = this.framePlateau.getCarteSelectionnee();

            if (dosCarteChoisi == -1 && carteChoisie == -1)
                return;

            if (dosCarteChoisi != -1)
            {
                int deck = 1;

                if      (this.framePlateau.getTabLblDosCartes()[dosCarteChoisi] == this.framePlateau.getTabLblDosCartes()[0]) deck = 3;
                else if (this.framePlateau.getTabLblDosCartes()[dosCarteChoisi] == this.framePlateau.getTabLblDosCartes()[1]) deck = 2;

                if (!(this.framePlateau.reserverCarte(this.framePlateau.getCurrentJoueur(), deck)))
                {
                    JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_RESERVATION.getLib());
                    return;
                }

            }
            else
            {
                if (!(this.framePlateau.reserverCarte(this.framePlateau.getCurrentJoueur(), this.framePlateau.getTabCartes()[carteChoisie]))) {
                    JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_RESERVATION.getLib());
                    return;
                }
            }

            this.framePlateau.resetDosCarteSelectionnee();
            this.framePlateau.resetCarteSelectionnee();
            this.framePlateau.updateGraphics();
            this.framePlateau.finTourJoueur();
        }
    }

}
