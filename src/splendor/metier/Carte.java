package splendor.metier;

import java.io.Serializable;

import splendor.utils.Couleur;

public class Carte implements Serializable
{

    private static final long serialVersionUID = 5047753364239890219L;
    
    private String url;
    private int     prestige;
    private Couleur couleur;
    private int     prixVert;
    private int     prixBlanc;
    private int     prixBleu;
    private int     prixNoir;
    private int     prixRouge;

    public Carte(String url, int prestige, Couleur couleur, int prixVert, int prixBlanc, int prixBleu, int prixNoir, int prixRouge)
    {
        this.url       = url;
        this.prestige  = prestige;
        this.couleur   = couleur;
        this.prixVert  = prixVert;
        this.prixBlanc = prixBlanc;
        this.prixBleu  = prixBleu;
        this.prixNoir  = prixNoir;
        this.prixRouge = prixRouge;
    }

    public Carte(String url)
    {
        this(url, 0, Couleur.BLANC, 0, 0, 0, 0, 0);
    }

    public int[] getPrix()
    {
        int[] tabPrix = new int[5];

        tabPrix[0] = this.prixVert;
        tabPrix[1] = this.prixBlanc;
        tabPrix[2] = this.prixBleu;
        tabPrix[3] = this.prixNoir;
        tabPrix[4] = this.prixRouge;

        return tabPrix;
    }

    public Couleur getCouleur () { return this.couleur; }
    public String  getUrl     () { return this.url;     }
    public int     getPrestige() { return prestige;     }

    public String toString()
    {
        return url + " " + prixVert + " " + prixBlanc + " " + prixBleu + " " + prixNoir + " " + prixRouge;
    }

}
