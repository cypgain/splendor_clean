package splendor_equipe_h.metier;

import splendor_equipe_h.utils.Couleur;

public class Noble extends Carte
{

    private static final long serialVersionUID = 5047753364239890219L;

    public Noble(String url)
    {
        super(url, 0, Couleur.BLANC, 0, 0, 0, 0, 0);
    }

    public Noble(String url, int prestige, Couleur couleur, int prixVert, int prixBlanc, int prixBleu, int prixNoir, int prixRouge)
    {
        super(url, prestige, couleur, prixVert, prixBlanc, prixBleu, prixNoir, prixRouge);
    }

}
