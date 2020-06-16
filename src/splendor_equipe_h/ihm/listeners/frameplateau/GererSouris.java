package splendor_equipe_h.ihm.listeners.frameplateau;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.ihm.FramePlateau;
import splendor_equipe_h.utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GererSouris extends MouseAdapter
{

    private FramePlateau framePlateau;

    public GererSouris(FramePlateau framePlateau)
    {
        this.framePlateau = framePlateau;
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        for(JLabel label : this.framePlateau.getTabLblJetons())
        {
            label.setBorder(BorderFactory.createLineBorder(Controleur.COULEUR_FOND, 2));
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        // Selection de carte
        if (this.framePlateau.isCarte(e.getSource()))
        {
            JLabel lblCarte = (JLabel) e.getSource();

            this.framePlateau.resetBorderLblDosCartes();
            this.framePlateau.setDosCarteSelectionnee(-1);

            this.framePlateau.resetBorderLblCartes();
            
            if (lblCarte.getBorder() != null)
            {
                this.framePlateau.setCarteSelectionnee(this.framePlateau.getIndexOfLblCarte(lblCarte));
                lblCarte.setBorder(BorderFactory.createLineBorder(Controleur.COULEUR_CHOIX, 2));
            }
        }
        // Selection dos de carte
        else if (this.framePlateau.isDosCarte(e.getSource()))
        {
            JLabel lblDosCarte = (JLabel) e.getSource();

            this.framePlateau.resetBorderLblCartes();
            this.framePlateau.setCarteSelectionnee(-1);

            this.framePlateau.resetBorderLblDosCartes();

            if (lblDosCarte.getBorder() != null)
            {
                this.framePlateau.setDosCarteSelectionnee(this.framePlateau.getIndexOfLblDosCarte(lblDosCarte));
                lblDosCarte.setBorder(BorderFactory.createLineBorder(Controleur.COULEUR_CHOIX, 2));
            }
        }
        // Selection de jeton
        else if (this.framePlateau.isJeton(e.getSource()))
        {
            JLabel lblJeton = (JLabel) e.getSource();
            lblJeton.setBorder(BorderFactory.createLineBorder(Controleur.COULEUR_CHOIX, 2));

            int indexJeton = this.framePlateau.getIndexOfLblJeton(lblJeton);

            this.framePlateau.addSelectedJeton(indexJeton);
            this.framePlateau.updateGraphics();

            this.framePlateau.resetCarteSelectionnee();
            this.framePlateau.resetDosCarteSelectionnee();

            if (this.framePlateau.isJetonsSelectedFull())
            {
                if(this.framePlateau.getCurrentJoueur().getNbJetons()>=10)
                {
                    JOptionPane.showMessageDialog(this.framePlateau,Message.ERR_JETON_FULL.getLib());
                    this.framePlateau.resetJetonsChoisis();

                }
                else if(this.framePlateau.getCurrentJoueur().getNbJetons() + this.framePlateau.getAmountJetonsSelected() <= 10)
                {
                    this.framePlateau.ajouterJetonJoueur();

                    this.framePlateau.finTourJoueur();

                    this.framePlateau.resetJetonsChoisis();
                }
                else
                {
                    new Thread(() ->
                    {
                        this.framePlateau.updateGraphics();
                        this.framePlateau.reposerJetonChoisis();
                    }).start();

                }

            }
            else if (this.framePlateau.getAmountJetonsSelected() == 3)
            {
                this.framePlateau.resetJetonsChoisis();
            }

            this.framePlateau.updateGraphics();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (this.framePlateau.isJeton(e.getSource()))
        {
            JLabel lblJeton = (JLabel) e.getSource();
            lblJeton.setBorder(BorderFactory.createLineBorder(Controleur.COULEUR_FOND, 2));
        }
    }

}
