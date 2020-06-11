package splendor.utils;

public enum Couleur
{
    VERT(0),
    BLANC(1),
    BLEU(2),
    NOIR(3),
    ROUGE(4),
    OR(5)

    ;

    private int index;

    private Couleur(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }

}
