package splendor;

import splendor.ihm.FramePlateau;
import splendor.metier.Carte;
import splendor.metier.Jeu;
import splendor.metier.Noble;

import java.util.List;

public class Controleur
{

    public static final String LANGUE = "FR";

    private Jeu metier;

    private FramePlateau framePlateau;

    public Controleur()
    {
        this.metier       = new Jeu();
        this.framePlateau = new FramePlateau(this);
    }

    public Carte[]     getTabCartes() { return this.metier.getTabCartes(); }
    public List<Noble> getTabNobles() { return this.metier.getTabNobles(); }

    public static void main(String[] args)
    {
        new Controleur();
    }

}
