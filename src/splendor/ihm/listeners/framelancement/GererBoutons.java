package splendor.ihm.listeners.framelancement;

import splendor.ihm.FrameLancement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererBoutons implements ActionListener
{

    private FrameLancement frameLancement;

    public GererBoutons(FrameLancement frameLancement)
    {
        this.frameLancement = frameLancement;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.frameLancement.getBtnValider())
        {
            this.frameLancement.lancementPartie(this.frameLancement.getCbxLangue().getSelectedItem().toString(), Integer.parseInt(String.valueOf(this.frameLancement.getCbxNombreJoueur().getSelectedItem())));
            this.frameLancement.dispose();
        }
    }

}
