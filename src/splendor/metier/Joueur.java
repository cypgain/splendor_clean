package splendor.metier;

import splendor.ihm.FrameJoueur;
import splendor.utils.Couleur;
import splendor.utils.NobleUtils;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{

    private static int nbJoueur;

    private int         num;
    private int         prestige;
    private int[]       tabJetons;
    private List<Noble> tabNobles;
    private List<Carte> tabCartes;
    private List<Carte> tabCartesReserve;

    public Joueur()
    {
        this.num       = ++Joueur.nbJoueur;
        this.prestige  = 0;
        this.tabJetons = new int[Couleur.values().length];
        this.tabNobles = new ArrayList<>();
        this.tabCartes = new ArrayList<>();
    }

    public int getNbCarte(Couleur couleur)
    {
        int amount = 0;

        for(Carte carte : this.tabCartes)
        {
            if(carte.getCouleur() == couleur)
                amount++;
        }

        return amount;
    }

    public int         getNum()       { return this.num;  }
    public List<Noble> getTabNobles() { return tabNobles; }
}
