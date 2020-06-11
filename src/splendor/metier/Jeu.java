package splendor.metier;

import splendor.utils.CarteUtils;
import splendor.utils.Couleur;
import splendor.utils.NobleUtils;

import java.util.ArrayList;
import java.util.List;

public class Jeu
{

    private int nbJoueurs;

    private List<Carte> deckLevelOne;
    private List<Carte> deckLevelTwo;
    private List<Carte> deckLevelThree;

    private List<Noble>  tabNobles;
    private List<Joueur> tabJoueurs;

    private int[] tabJetons;
    private int[] tabJetonsChoisis;

    private Carte[] tabCartes;

    public Jeu(int nbJoueurs)
    {
        this.nbJoueurs = nbJoueurs;

        this.deckLevelOne     = new ArrayList<>();
        this.deckLevelTwo     = new ArrayList<>();
        this.deckLevelThree   = new ArrayList<>();
        this.tabNobles        = new ArrayList<>();
        this.tabJoueurs       = new ArrayList<>();
        this.tabJetons        = new int[Couleur.values().length];
        this.tabJetonsChoisis = new int[Couleur.values().length];
        this.tabCartes        = new Carte[12];

        this.initDeck();
        this.initNobles();
        this.initCartes();
    }

    public void addJoueur(Joueur joueur)
    {
        this.tabJoueurs.add(joueur);
    }

    private void initDeck()
    {
        this.deckLevelOne   = CarteUtils.getDeckOne();
        this.deckLevelTwo   = CarteUtils.getDeckTwo();
        this.deckLevelThree = CarteUtils.getDeckThree();
    }

    private void initNobles()
    {
        ArrayList<Noble> nobles = NobleUtils.getListeNobles();

        Noble nobleTemp;

        for(int i = 0; i < (this.nbJoueurs + 1); i++)
        {
            nobleTemp = nobles.get((int) (Math.random() * nobles.size()));
            nobles.remove(nobleTemp);
            this.tabNobles.add(nobleTemp);
        }
    }

    private void initCartes()
    {
        for (int i = 0; i < this.tabCartes.length ; i++)
        {
            if      (i < 4)  this.tabCartes[i] = this.tirerCarte(3);
            else if (i < 8)  this.tabCartes[i] = this.tirerCarte(2);
            else if (i < 12) this.tabCartes[i] = this.tirerCarte(1);
        }
    }

    private Carte tirerCarte(int level)
    {
        List<Carte> deck;

        switch (level)
        {
            case 1 : deck = this.deckLevelOne;   break;
            case 2 : deck = this.deckLevelTwo;   break;
            default: deck = this.deckLevelThree; break;
        }

        Carte carte;

        if (deck.size() == 0)
        {
            carte = new Carte("");
        }
        else
        {
            carte = deck.get((int) (Math.random() * deck.size()));
            deck.remove(carte);
        }

        return carte;
    }

    public Carte[]     getTabCartes       () { return this.tabCartes;        }
    public List<Noble> getTabNobles       () { return this.tabNobles;        }
    public int[]       getTabJetons       () { return this.tabJetons;        }
    public int[]       getTabJetonsChoisis() { return this.tabJetonsChoisis; }

}
