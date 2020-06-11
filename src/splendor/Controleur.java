package splendor;

import splendor.ihm.FrameJoueur;
import splendor.ihm.FramePlateau;
import splendor.metier.Carte;
import splendor.metier.Jeu;
import splendor.metier.Joueur;
import splendor.metier.Noble;
import splendor.utils.Message;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controleur
{

    public static final String LANGUE     = "FR";
    public static final int    NB_JOUEURS = 2;

    private Jeu metier;

    private FramePlateau      framePlateau;
    private List<FrameJoueur> tabFrameJoueurs;

    public Controleur()
    {
        this.metier       = new Jeu(Controleur.NB_JOUEURS);
        this.framePlateau = new FramePlateau(this);

        this.tabFrameJoueurs = new ArrayList<>();

        this.initJoueurs();
    }

    /*-----------------------
             Init
    ---------------------- */

    private void initJoueurs()
    {
        Joueur joueurTemp;

        for(int i = 0; i < Controleur.NB_JOUEURS; i++)
        {
            joueurTemp = new Joueur();

            this.metier.addJoueur(joueurTemp);
            this.tabFrameJoueurs.add(new FrameJoueur(this, this.framePlateau, joueurTemp));
        }

        this.metier.setCurrentJoueur(this.metier.getTabJoueurs().get(0));
    }


    /*-----------------------
            Updates
    ---------------------- */

    public void updateFramesPosition() 
    {
        for (FrameJoueur frameJoueur : this.tabFrameJoueurs) 
        {
            frameJoueur.updateFramePosition();
        }
    }

    public void updateGraphics()
    {
        this.framePlateau.updateCartes();
        this.framePlateau.updateJetons();

        for(FrameJoueur frameJoueur : this.tabFrameJoueurs)
        {
            frameJoueur.updateGraphics();
        }
    }

    /*-----------------------
            Partie
    ---------------------- */

    public boolean acheterCarte(Joueur joueur, Carte carte)
    {
        if (!(joueur.peutPrendreCarte(carte)))
            return false;

        this.reposerJeton(carte);
        joueur.ajouterCarte(carte);

        int deck;
        for (int index = 0 ; index < this.metier.getTabCartes().length ; index ++)
        {
            deck = 3 - ((int) (index / (this.metier.getTabCartes().length / 3)));
            if (this.metier.getTabCartes()[index] == carte)
                this.metier.getTabCartes()[index] = this.metier.tirerCarte(deck);
        }

        return true;
    }

    /*-----------------------
            Jetons
    ---------------------- */

    public void reposerJeton(Carte c)
    {
        for (int i = 0; i < this.metier.getTabJetons().length-1; i++)
        {
            if (c.getPrix()[i] <= this.metier.getCurrentJoueur().getNbCarte(i))
                continue;

            if (c.getPrix()[i] <= Math.abs(this.metier.getCurrentJoueur().getNbJetons(i) - this.metier.getCurrentJoueur().getNbCarte(i)))
            {
                this.metier.getTabJetons()[i] += c.getPrix()[i] - this.getCurrentJoueur().getNbCarte(i);
            }
            else
            {
                this.metier.getTabJetons()[i] += this.getCurrentJoueur().getNbJetons(i);
                this.metier.getTabJetons()[5] += c.getPrix()[i] - this.getCurrentJoueur().getNbJetons(i);
            }
        }
    }

    public boolean isJetonsSelectedFull()
    {
        int amount = this.metier.getAmountJetonsSelected();

        if(amount != 2 && amount != 3)
        {
            return false;
        }

        if (amount == 3 && (this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                        || this.metier.getTabJetonsChoisis()[0]  == this.metier.getTabJetonsChoisis()[2]
                        || this.metier.getTabJetonsChoisis()[1]  == this.metier.getTabJetonsChoisis()[2]))
        {
            this.resetJetonsChoisis();
            this.framePlateau.resetLabelJetonsChoisis();
            JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_PRISE_JETON_INVALIDE.getLib());
            return false;
        }

        if (amount == 2 && this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                        && metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] < 4)
        {
            this.resetJetonsChoisis();
            this.framePlateau.resetLabelJetonsChoisis();
            JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_PRISE_JETON_INVALIDE.getLib());
            return false;
        }

        if(amount == 3 && this.metier.getTabJetonsChoisis()[0] != this.metier.getTabJetonsChoisis()[1]
                       && this.metier.getTabJetonsChoisis()[0] != this.metier.getTabJetonsChoisis()[2]
                       && this.metier.getTabJetonsChoisis()[1] != this.metier.getTabJetonsChoisis()[2]
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] >= 1
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[1]] >= 1
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[2]] >= 1)
        {
            return true;
        }

        if(amount == 2 && this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] >= 4)
        {
            return true;
        }

        return false;
    }

    public boolean addSelectedJeton(int indexJeton)
    {
        for(int i = 0; i < this.metier.getTabJetonsChoisis().length; i++)
        {
            if(this.metier.getTabJetonsChoisis()[i] == -1)
            {
                this.metier.getTabJetonsChoisis()[i] = indexJeton;
                return true;
            }
        }

        return false;
    }

    public int getAmountJetonsSelected()
    {
        int amount = 0;

        for(int i = 0; i < this.metier.getTabJetonsChoisis().length; i++)
        {
            if(this.metier.getTabJetonsChoisis()[i] != -1) amount++;
        }

        return amount;
    }

    public void resetJetonsChoisis()
    {
        this.metier.resetJetonsChoisis();
    }

    public int getNbJetonsChoisis(int indexJeton)
    {
        int amount = 0;

        for (int i = 0; i < this.getTabJetonsChoisis().length; i++)
        {
            if (indexJeton == this.getTabJetonsChoisis()[i]) amount++;
        }

        return amount;
    }

    /*-----------------------
          Serialization
    ---------------------- */


    /*-----------------------
             Getters
    ---------------------- */

    public Carte[]     getTabCartes()        { return this.metier.getTabCartes();        }
    public List<Noble> getTabNobles()        { return this.metier.getTabNobles();        }
    public int[]       getTabJetons()        { return this.metier.getTabJetons();        }
    public int[]       getTabJetonsChoisis() { return this.metier.getTabJetonsChoisis(); }
    public Joueur      getCurrentJoueur()    { return this.metier.getCurrentJoueur();    }

    public static void main(String[] args)
    {
        new Controleur();
    }

}
