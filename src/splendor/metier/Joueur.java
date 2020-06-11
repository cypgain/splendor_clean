package splendor.metier;

import splendor.utils.Couleur;

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


}
