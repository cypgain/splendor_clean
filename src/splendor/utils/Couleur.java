package splendor.utils;


import java.awt.*;

public enum Couleur
{

    VERT  (Color.GREEN),
    BLANC (Color.WHITE),
    BLEU  (Color.BLUE),
    MARRON(Color.BLACK),
    ROUGE (Color.RED),
    JAUNE (Color.YELLOW)

    ;

    private Color awtCouleur;

    private Couleur(Color awtCouleur)
    {
        this.awtCouleur = awtCouleur;
    }

    public Color getAwtCouleur()
    {
        return this.awtCouleur;
    }
}
