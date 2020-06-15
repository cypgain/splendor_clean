package splendor_equipe_h.metier;

import splendor_equipe_h.utils.Couleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Joueur implements Serializable
{

    private static final long serialVersionUID = 5047753364239890219L;

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
        this.num              = ++Joueur.nbJoueur;
        this.prestige         = 0;
        this.tabJetons        = new int[Couleur.values().length];
        this.tabNobles        = new ArrayList<>();
        this.tabCartes        = new ArrayList<>();
        this.tabCartesReserve = new ArrayList<>();
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

    public static void resetNbJoueurs()
    {
        Joueur.nbJoueur=0;
    }

    public void setValeurJeton(int[] newValeurs)
    {
        this.tabJetons = newValeurs;
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

    public int getNbCarte(int coul)
    {
        int nbCarte = 0;

        for (Carte carte : this.tabCartes)
        {
            if (carte.getCouleur().ordinal() == coul)
                nbCarte++;
        }

        return nbCarte;
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

    public int getNbJetons(int coul)
    {
        return this.tabJetons[coul];
    }

    public void retirerJeton(int numJeton)
    {
        this.tabJetons[numJeton]--;
    }


    public void ajouterCarte(Carte carte)
    {

        int[] tabPrix = carte.getPrix();
        int coutJeton;
        int jetonsJoueur;

        for (int indexPrix = 0; indexPrix < tabPrix.length; indexPrix++)
        {
            if (this.getNbCarte(indexPrix)>=tabPrix[indexPrix])
                continue;

            coutJeton = tabPrix[indexPrix] - this.getNbCarte(indexPrix);

            if (coutJeton <= this.tabJetons[indexPrix])
            {
                for (int i = 0; i < coutJeton; i++)
                {
                    this.retirerJeton(indexPrix);
                }
            }
            else
            {
                jetonsJoueur = this.tabJetons[indexPrix];

                for (int i = 0; i < jetonsJoueur ; i++)
                {
                    this.retirerJeton(indexPrix);
                }

                for (int i = 0; i < coutJeton - jetonsJoueur; i++)
                {
                    this.retirerJeton(5);
                }
            }
        
        }

        this.tabCartes.add(carte);

    }

    public boolean reserverCarte(Carte carte)
    {
        if (this.tabCartesReserve.size() >= 3 || carte == null)
            return false;

        this.tabCartesReserve.add(carte);
        return true;
    }

    public boolean peutPrendreCarte(Carte carte)
    {
        if (carte == null)
            return false;

        int[] tabPrix = carte.getPrix();
        int jetonsOrUsed = 0;

        for (int i = 0 ; i < tabPrix.length ; i++)
        {
            if (this.getNbCarte(i) >= tabPrix[i])
                continue;
            if (tabPrix[i] > (this.getNbCarte(i) + this.tabJetons[i] + this.tabJetons[5] - jetonsOrUsed))
            {
                return false;
            }
            
            if (tabPrix[i] > (this.getNbCarte(i) + this.tabJetons[i]))
            {
                jetonsOrUsed += (tabPrix[i] - this.tabCartes.size() - this.tabJetons[i]);
            }
            if (jetonsOrUsed > this.tabJetons[5]) 
            {
                return false;
            }
        }

        return true;
    }

    public void updatePointsPrestiges()
    {
        this.prestige = 0;

        for(Carte carte : this.tabCartes)
        {
            this.prestige += carte.getPrestige();
        }

        for(Noble noble : this.tabNobles)
        {
            this.prestige += noble.getPrestige();
        }
    }

    public void ajouterNoble(Noble noble)
    {
        this.tabNobles.add(noble);
    }

    public int         getNum             () { return this.num;         }
    public List<Noble> getTabNobles       () { return this.tabNobles;   }
    public int[]       getTabJetons       () { return this.tabJetons;   }
    public List<Carte> getTabCartesReserve() { return tabCartesReserve; }
    public int         getPrestige        () { return this.prestige;    }
    public List<Carte> getTabCartes       () { return this.tabCartes;   }

}
