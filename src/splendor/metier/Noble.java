package splendor.metier;

import splendor.utils.Couleur;

public class Noble extends Carte
{

    public Noble(String url)
    {
        super(url, 0, Couleur.BLANC, 0, 0, 0, 0, 0);
    }

    public Noble(String url, int prestige, Couleur couleur, int prixVert, int prixBlanc, int prixBleu, int prixNoir, int prixRouge)
    {
        super(url, prestige, couleur, prixVert, prixBlanc, prixBleu, prixNoir, prixRouge);
    }

}
