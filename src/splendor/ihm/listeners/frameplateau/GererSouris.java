package splendor.ihm.listeners.frameplateau;

import splendor.ihm.FramePlateau;

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
