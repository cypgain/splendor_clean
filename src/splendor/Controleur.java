package splendor;

import splendor.ihm.FrameJoueur;
import splendor.ihm.FramePlateau;
import splendor.metier.Carte;
import splendor.metier.Jeu;
import splendor.metier.Joueur;
import splendor.metier.Noble;

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

    private void initJoueurs()
    {
        Joueur joueurTemp;

        for(int i = 0; i < Controleur.NB_JOUEURS; i++)
        {
            joueurTemp = new Joueur();

            this.metier.addJoueur(joueurTemp);
            this.tabFrameJoueurs.add(new FrameJoueur(this, this.framePlateau, joueurTemp));
        }
    }

    public void updateFramesPosition()
    {
        for(FrameJoueur frameJoueur : this.tabFrameJoueurs)
        {
            frameJoueur.updateFramePosition();
        }
    }

    public Carte[]     getTabCartes()        { return this.metier.getTabCartes();        }
    public List<Noble> getTabNobles()        { return this.metier.getTabNobles();        }
    public int[]       getTabJetons()        { return this.metier.getTabJetons();        }
    public int[]       getTabJetonsChoisis() { return this.metier.getTabJetonsChoisis(); }

    public static void main(String[] args)
    {
        new Controleur();
    }

}
