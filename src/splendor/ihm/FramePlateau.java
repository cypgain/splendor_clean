package splendor.ihm;

import splendor.Controleur;
import splendor.ihm.listeners.frameplateau.GererSouris;
import splendor.metier.Carte;
import splendor.metier.Noble;
import splendor.utils.ImageUtils;
import splendor.utils.Message;
import splendor.utils.SplendorFont;

import javax.swing.*;
import java.awt.*;

public class FramePlateau extends JFrame
{

    /*----------------------
           Constantes
     -----------------------*/

    private static final int TAILLE_IMAGE_CARTE_X = 130;
    private static final int TAILLE_IMAGE_CARTE_Y = 200;

    private static final int TAILLE_IMAGE_NOBLE_X = 145;
    private static final int TAILLE_IMAGE_NOBLE_Y = 145;

    /*----------------------
            Attributs
     -----------------------*/

    private Controleur controleur;

    private JLabel lblTitre;

    // Nobles
    private JPanel   panelNobles;
    private JLabel[] tabLblNobles;

    // Cartes
    private JPanel   panelCartes;
    private JLabel[] tabLblCartes;
    private int      carteSelectionnee;

    // Dos de cartes
    private JPanel   panelDosCartes;
    private JLabel[] tabLblDosCartes;
    private int      dosCarteSelectionnee;

    public FramePlateau(Controleur controleur)
    {
        this.controleur = controleur;

        this.setTitle(Message.TITRE_FRAME_PLATEAU.getLib());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);

        this.loadRegionNobles();
        this.loadRegionCartes();
        this.loadRegionDosCartes();

        this.add(this.panelNobles,    BorderLayout.NORTH);
        this.add(this.panelCartes,    BorderLayout.CENTER);
        this.add(this.panelDosCartes, BorderLayout.WEST);

        this.pack();
        this.setVisible(true);
    }

    /*----------------------
       Gestion des Nobles
     -----------------------*/

    private void loadRegionNobles()
    {
        JPanel panelNoblesIn = new JPanel(new GridLayout(1, this.controleur.getTabNobles().size()));

        this.panelNobles  = new JPanel(new BorderLayout());
        this.tabLblNobles = new JLabel[this.controleur.getTabNobles().size()];
        this.lblTitre     = new JLabel("Au tour du joueur 1", JLabel.CENTER);

        this.lblTitre.setFont(SplendorFont.FRAME_PLATEAU_TITRE.getFont());

        int i = 0;
        for(Noble noble : this.controleur.getTabNobles())
        {
            this.tabLblNobles[i] = new JLabel();
            this.tabLblNobles[i].setIcon(ImageUtils.resizeImage(noble.getUrl(), FramePlateau.TAILLE_IMAGE_NOBLE_X, FramePlateau.TAILLE_IMAGE_NOBLE_Y));
            panelNoblesIn.add(this.tabLblNobles[i]);
            i++;
        }

        this.panelNobles.add(this.lblTitre, BorderLayout.NORTH);
        this.panelNobles.add(panelNoblesIn, BorderLayout.SOUTH);
    }

    /*----------------------
    Gestion des Dos de Cartes
     -----------------------*/

    private void loadRegionDosCartes()
    {
        this.panelDosCartes       = new JPanel(new GridLayout(3, 1, 10, 10));
        this.tabLblDosCartes      = new JLabel[3];
        this.dosCarteSelectionnee = -1;

        for(int i = 0; i < this.tabLblDosCartes.length; i++)
        {
            this.tabLblDosCartes[i] = new JLabel();
            this.tabLblDosCartes[i].addMouseListener(new GererSouris(this));
            this.panelDosCartes.add(this.tabLblDosCartes[i]);
        }

        this.tabLblDosCartes[0].setIcon(ImageUtils.resizeImage("ressources/dev_III_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[0].setName("ressources/dev_III_dos.png");

        this.tabLblDosCartes[1].setIcon(ImageUtils.resizeImage("ressources/dev_II_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[1].setName("ressources/dev_II_dos.png");

        this.tabLblDosCartes[2].setIcon(ImageUtils.resizeImage("ressources/dev_I_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[2].setName("ressources/dev_I_dos.png");
    }

    public boolean isDosCarte(Object object)
    {
        if(object instanceof JLabel)
        {
            for(JLabel l : this.tabLblDosCartes)
            {
                if(l == object)
                    return true;
            }
        }

        return false;
    }

    public void resetBorderLblDosCartes()
    {
        for(JLabel lbl : this.tabLblDosCartes)
        {
            lbl.setBorder(null);
        }
    }

    public int getIndexOfLblDosCarte(JLabel lblDosCarte)
    {
        for(int i = 0; i < this.tabLblDosCartes.length; i++)
        {
            if(this.tabLblDosCartes[i] == lblDosCarte)
                return i;
        }

        return 0;
    }

    public void setDosCarteSelectionnee(int dosCarteSelectionnee) { this.dosCarteSelectionnee = dosCarteSelectionnee; }

    /*----------------------
        Gestion des Cartes
     -----------------------*/

    private void loadRegionCartes()
    {
        this.panelCartes       = new JPanel(new GridLayout(3, 4, 10, 10));
        this.tabLblCartes      = new JLabel[12];
        this.carteSelectionnee = -1;

        Carte[] tabCartes = this.controleur.getTabCartes();

        for (int i = 0; i < tabCartes.length; i++)
        {
            this.tabLblCartes[i] = new JLabel();
            this.panelCartes.add(this.tabLblCartes[i]);
        }

        this.updateCartes();
    }

    private void updateCartes()
    {
        Carte[] tabCartes = this.controleur.getTabCartes();

        for (int i = 0; i < tabCartes.length; i++)
        {
            this.tabLblCartes[i].setIcon(ImageUtils.resizeImage(tabCartes[i].getUrl(), FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
            this.tabLblCartes[i].setName(tabCartes[i].getUrl());
            this.tabLblCartes[i].addMouseListener(new GererSouris(this));
        }
    }

    public boolean isCarte(Object object)
    {
        if(object instanceof JLabel)
        {
            for(JLabel l : this.tabLblCartes)
            {
                if(l == object)
                    return true;
            }
        }

        return false;
    }

    public void resetBorderLblCartes()
    {
        for(JLabel lbl : this.tabLblCartes)
        {
            lbl.setBorder(null);
        }
    }

    public int getIndexOfLblCarte(JLabel lblCarte)
    {
        for(int i = 0; i < this.tabLblCartes.length; i++)
        {
            if(this.tabLblCartes[i] == lblCarte)
                return i;
        }

        return 0;
    }

    public void setCarteSelectionnee(int carteSelectionnee) { this.carteSelectionnee = carteSelectionnee; }

}
