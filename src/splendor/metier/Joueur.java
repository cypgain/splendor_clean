package splendor.metier;

import splendor.utils.Couleur;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{

    private static final int MAX_JETONS = 10;

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

    public int getNbJetons()
    {
        int amount = 0;

        for (int i = 0; i < this.tabJetons.length; i++)
        {
            amount += this.tabJetons[i];
        }

        return amount;
    }

    public boolean ajouterJeton(int numJeton, int amount)
    {
        if(this.getNbJetons() + amount <= Joueur.MAX_JETONS)
        {
            this.tabJetons[numJeton] += amount;
            return true;
        }

        return false;
    }

    public int         getNum      () { return this.num;       }
    public List<Noble> getTabNobles() { return this.tabNobles; }
    public int[]       getTabJetons() { return this.tabJetons; }
}
