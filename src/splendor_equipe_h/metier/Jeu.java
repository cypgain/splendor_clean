package splendor_equipe_h.metier;

import splendor_equipe_h.utils.CarteUtils;
import splendor_equipe_h.utils.Couleur;
import splendor_equipe_h.utils.NobleUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jeu implements Serializable
{

    private static final long serialVersionUID = 5047753364239890219L;

    private int nbJoueurs;

    private List<Carte> deckLevelOne;
    private List<Carte> deckLevelTwo;
    private List<Carte> deckLevelThree;

    private List<Noble>  tabNobles;
    private List<Joueur> tabJoueurs;

    private int[] tabJetons;
    private int[] tabJetonsChoisis;

    private Carte[] tabCartes;

    private Joueur currentJoueur;

    /*-----------------------
          Constructeur
    ---------------------- */

    public Jeu(int nbJoueurs)
    {
        this.nbJoueurs = nbJoueurs;

        this.deckLevelOne     = new ArrayList<>();
        this.deckLevelTwo     = new ArrayList<>();
        this.deckLevelThree   = new ArrayList<>();
        this.tabNobles        = new ArrayList<>();
        this.tabJoueurs       = new ArrayList<>();
        this.tabJetonsChoisis = new int[] { -1, -1, -1 };
        this.tabCartes        = new Carte[12];

        this.initDeck();
        this.initNobles();
        this.initCartes();
        this.initJetons();
    }

    /*-----------------------
         Initialisation
    ---------------------- */

    private void initJetons()
    {
        this.tabJetons = new int[Couleur.values().length];

        // Chargement du nombre de jetons
        Arrays.fill(this.tabJetons, (this.nbJoueurs == 4 ? 7 : 2 + this.nbJoueurs));

        // Joker
        this.tabJetons[5] = 5;
    }

    public void addJoueur(Joueur joueur)
    {
        this.tabJoueurs.add(joueur);
    }

    public void setCurrentJoueur(Joueur currentJoueur)
    {
        this.currentJoueur = currentJoueur;
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

    /*-----------------------
             Jetons
    ---------------------- */

    public void resetJetonsChoisis()
    {
        this.tabJetonsChoisis = new int[] { -1, -1, -1 };
    }

    public int getAmountJetonsSelected()
    {
        int amount = 0;

        for(int i = 0; i < this.tabJetonsChoisis.length; i++)
        {
            if(this.tabJetonsChoisis[i] != -1) amount++;
        }

        return amount;
    }
    

    /*-----------------------
             Partie
    ---------------------- */

    public Carte tirerCarte(int level)
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

    public void joueurSuivant()
    {
        if(this.tabJoueurs.indexOf(this.currentJoueur) == (this.tabJoueurs.size() - 1))
        {
            this.currentJoueur = this.tabJoueurs.get(0);
        }
        else
        {
            this.currentJoueur = this.tabJoueurs.get(this.tabJoueurs.indexOf(this.currentJoueur) + 1);
        }
    }

    /*-----------------------
             Getters
    ---------------------- */

    public Carte[]      getTabCartes       () { return this.tabCartes;        }
    public List<Noble>  getTabNobles       () { return this.tabNobles;        }
    public int[]        getTabJetons       () { return this.tabJetons;        }
    public int[]        getTabJetonsChoisis() { return this.tabJetonsChoisis; }
    public List<Joueur> getTabJoueurs      () { return this.tabJoueurs;       }
    public Joueur       getCurrentJoueur   () { return currentJoueur;         }

}
