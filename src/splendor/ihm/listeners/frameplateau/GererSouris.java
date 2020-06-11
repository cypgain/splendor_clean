package splendor.ihm.listeners.frameplateau;

import splendor.ihm.FramePlateau;
import splendor.utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

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
            label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
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
            this.framePlateau.setCarteSelectionnee(this.framePlateau.getIndexOfLblCarte(lblCarte));

            lblCarte.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
        // Selection dos de carte
        else if (this.framePlateau.isDosCarte(e.getSource()))
        {
            JLabel lblDosCarte = (JLabel) e.getSource();

            this.framePlateau.resetBorderLblCartes();
            this.framePlateau.setCarteSelectionnee(-1);

            this.framePlateau.resetBorderLblDosCartes();
            this.framePlateau.setDosCarteSelectionnee(this.framePlateau.getIndexOfLblDosCarte(lblDosCarte));

            lblDosCarte.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
        // Selection de jeton
        else if (this.framePlateau.isJeton(e.getSource()))
        {
            JLabel lblJeton = (JLabel) e.getSource();
            lblJeton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

            int indexJeton = this.framePlateau.getIndexOfLblJeton(lblJeton);

            this.framePlateau.addSelectedJeton(indexJeton);
            this.framePlateau.updateGraphics();

            this.framePlateau.resetCarteSelectionnee();
            this.framePlateau.resetDosCarteSelectionnee();

            if (this.framePlateau.isJetonsSelectedFull())
            {
                if(this.framePlateau.getCurrentJoueur().getNbJetons() + this.framePlateau.getAmountJetonsSelected() <= 10)
                {
                    for (int i = 0; i < this.framePlateau.getTabJetonsChoisis().length; i++)
                    {
                        if (this.framePlateau.getTabJetonsChoisis()[i] != -1)
                        {
                            this.framePlateau.getCurrentJoueur().ajouterJeton(this.framePlateau.getTabJetonsChoisis()[i], 1);
                            this.framePlateau.getTabJetons()[this.framePlateau.getTabJetonsChoisis()[i]] -= 1;
                        }
                    }

                    // TODO - Fin du tour
                    this.framePlateau.finTourJoueur();
                }
                else
                {
                    JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_JETON_FULL.getLib());
                }

                this.framePlateau.resetJetonsChoisis();
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
            lblJeton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }
    }

}
