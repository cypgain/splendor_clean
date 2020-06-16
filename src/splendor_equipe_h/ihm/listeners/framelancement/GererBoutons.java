package splendor_equipe_h.ihm.listeners.framelancement;

import splendor_equipe_h.ihm.FrameLancement;
import splendor_equipe_h.utils.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;


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
        else if (e.getSource() == this.frameLancement.getBtnRegles() ) 
        {
            if (Desktop.isDesktopSupported()) 
            {
                try 
                {
                    File myFile = new File("../regles.pdf");
                    Desktop.getDesktop().open(myFile);
                } 
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(this.frameLancement, Message.ERR_PDF.getLib());

                }
            }
            
        }
    }

}
