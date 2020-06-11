package splendor.ihm.listeners.frameplateau;

import splendor.ihm.FramePlateau;

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
            System.out.println("ACHAT");
        }
        else if(e.getSource() == framePlateau.getBtnReserve())
        {
            System.out.println("RESERVER");
        }
    }

}
