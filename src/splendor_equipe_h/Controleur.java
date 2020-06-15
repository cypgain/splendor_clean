package splendor_equipe_h;

import splendor_equipe_h.ihm.*;
import splendor_equipe_h.metier.Carte;
import splendor_equipe_h.metier.Jeu;
import splendor_equipe_h.metier.Joueur;
import splendor_equipe_h.metier.Noble;
import splendor_equipe_h.utils.Message;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class Controleur
{

    public static String langue;

    private Jeu metier;

    private FramePlateau      framePlateau;
    private FrameDebug        frameDebug;
    private List<FrameJoueur> tabFrameJoueurs;

    private boolean changementMetier;
    private boolean finDuTourJoueur;
    private boolean forcedEndGame;
    private boolean finDuTour;

    public Controleur()
    {
        new FrameLancement(this);


        new Thread(() ->
        {
            while (true)
            {
                try { Thread.sleep(100); } catch (Exception e) {}

                if (this.changementMetier)
                {
                    this.updateGraphics();
                    this.changementMetier = false;
                    this.nouvellePartie();
                }
            }
        }).start();
    }

    /*-----------------------
             Init
    ---------------------- */

    public void lancementPartie(String langue, int nbJoueurs)
    {
        Controleur.langue = langue;

        this.metier       = new Jeu(nbJoueurs);

        if (this.framePlateau != null)
        {
            this.framePlateau.dispose();
        }
        this.framePlateau = new FramePlateau(this);
        if (this.tabFrameJoueurs!=null)
        {
            for (FrameJoueur frameJoueur : this.tabFrameJoueurs)
            {
                frameJoueur.dispose();
            }
        }
        this.tabFrameJoueurs = new ArrayList<>();

        this.framePlateau.addKeyListener(new KeyAdapter()
        {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_D)
                {
                    if(Controleur.this.frameDebug == null)
                    {
                        Controleur.this.frameDebug = new FrameDebug(Controleur.this);
                    }
                    else
                    {
                        Controleur.this.frameDebug.dispose();
                        Controleur.this.frameDebug = null;
                    }
                }
            }

        });
        this.forceEndGame();
        this.initJoueurs(nbJoueurs);
        this.changementMetier=true;
    }

    private void initJoueurs(int nbJoueurs)
    {
        Joueur joueurTemp;

        Joueur.resetNbJoueurs();

        for(int i = 0; i < nbJoueurs; i++)
        {
            joueurTemp = new Joueur();

            this.metier.addJoueur(joueurTemp);
            this.tabFrameJoueurs.add(new FrameJoueur(this, this.framePlateau, joueurTemp));
        }

        this.metier.setCurrentJoueur(this.metier.getTabJoueurs().get(0));
    }

    public void changementScenario()
    {
        this.framePlateau.dispose();
        this.framePlateau = new FramePlateau(this);
        
        for(FrameJoueur frameJoueur : new ArrayList<>(this.tabFrameJoueurs))
        {
            frameJoueur.dispose();
        }

        for(Joueur joueur : this.metier.getTabJoueurs())
        {
            this.tabFrameJoueurs.add(new FrameJoueur(this, this.framePlateau, joueur));
        }
    }

    /*-----------------------
            Updates
    ---------------------- */

    public void updateFramesPosition()
    {
        for (FrameJoueur frameJoueur : this.tabFrameJoueurs)
        {
            frameJoueur.updateFramePosition();
        }
    }

    public void updateGraphics()
    {
        this.framePlateau.updateCartes();
        this.framePlateau.updateJetons();
        this.framePlateau.updateLabelTourJoueur();
        this.framePlateau.updateNobles();

        for(FrameJoueur frameJoueur : this.tabFrameJoueurs)
        {
            frameJoueur.update();
        }
    }

    public void setEnabled(boolean b)
    {
        this.framePlateau.setEnabled(b);
        for (FrameJoueur frameJoueur : this.tabFrameJoueurs )
            frameJoueur.setEnabled(b);
    }

    /*-----------------------
            Partie
    ---------------------- */

    public boolean prendreCarte(Joueur joueur, Carte carte)
    {
        if (!(joueur.peutPrendreCarte(carte)))
            return false;

        this.reposerJeton(carte);
        joueur.ajouterCarte(carte);

        return true;
    }

    public boolean acheterCarte(Joueur joueur, Carte carte)
    {
        if (!(joueur.peutPrendreCarte(carte)))
            return false;

        this.reposerJeton(carte);
        joueur.ajouterCarte(carte);

        int deck;
        for (int index = 0 ; index < this.metier.getTabCartes().length ; index ++)
        {
            deck = 3 - ( (index / (this.metier.getTabCartes().length / 3)));
            if (this.metier.getTabCartes()[index] == carte)
                this.metier.getTabCartes()[index] = this.metier.tirerCarte(deck);
        }

        return true;
    }

    public void nouvellePartie()
    {
        this.forcedEndGame = false;

        new Thread(() ->
        {
            while (!(this.finDePartie()) && !(this.forcedEndGame))
            {
                this.nouveauTour();
            }
        }).start();
    }

    public void finTourJoueur()
    {
        this.finDuTourJoueur = true;
    }

    private void quitterLaPartie(Joueur winner)
    {
        JOptionPane.showMessageDialog(this.framePlateau, Message.WINNER.getLib().replace("{NUM}", "" + winner.getNum()));
        System.exit(0);
    }

    private boolean finDePartie()
    {
        int amount = 0;

        for (Joueur j : this.metier.getTabJoueurs())
        {
            if (j.getPrestige() >= 15)
                amount++;
        }

        if (amount == 1)
        {
            for (Joueur j : this.metier.getTabJoueurs())
            {
                if (j.getPrestige() >= 15)
                {
                    this.quitterLaPartie(j);
                    return true;
                }
            }
        }
        else if (amount > 1)
        {
            boolean equals = false;
            int best = 0;

            for (Joueur j : this.metier.getTabJoueurs())
            {
                if (j.getPrestige() > best)
                {
                    best = j.getPrestige();
                }
                else if (best == j.getPrestige())
                {
                    equals = true;
                }
            }

            if (equals)
            {
                best = 0;
                Joueur bestJoueur = null;

                for (Joueur j : this.metier.getTabJoueurs())
                {
                    if (j.getPrestige() >= best)
                    {
                        if (bestJoueur == null || bestJoueur.getTabCartes().size() > j.getTabCartes().size())
                        {
                            best = j.getPrestige();
                            bestJoueur = j;
                        }
                    }
                }

                this.quitterLaPartie(bestJoueur);

                return true;
            }
            else
            {
                best = 0;
                Joueur bestJoueur = null;

                for (Joueur j : this.metier.getTabJoueurs())
                {
                    if (j.getPrestige() > best)
                    {
                        best = j.getPrestige();
                        bestJoueur = j;
                    }
                }

                this.quitterLaPartie(bestJoueur);

                return true;
            }
        }

        return false;
    }

    public void nouveauTour()
    {
        this.finDuTour = false;
        while (!(this.finDuTour) && this.metier.getTabJoueurs().indexOf(this.metier.getCurrentJoueur()) < this.metier.getTabJoueurs().size())
        {
            this.finDuTourJoueur = false;

            this.updateGraphics();

            while (!(this.finDuTourJoueur))
            {
                try
                {
                    Thread.sleep(100);
                    if (this.forcedEndGame)
                        return;
                } catch (Exception e) { e.printStackTrace(); }
            }

            // Visite des nobles
            if (this.getNoblesPossible() != null)
            {
                Noble[] ensNoblePossible = this.getNoblesPossible();
                Noble   n                = null;

                if (ensNoblePossible.length == 1) n = ensNoblePossible[0];
                else                              n = this.choisirNoble(ensNoblePossible);

                this.getCurrentJoueur().ajouterNoble(n);
                this.metier.getTabNobles().remove(n);
            }

            this.getCurrentJoueur().updatePointsPrestiges();

            this.updateGraphics();
            this.finDuTourJoueur = false;
            this.metier.joueurSuivant();

            if(this.getCurrentJoueur().getNum() == 1)
                this.finDuTour = true;
        }
    }

    public Noble choisirNoble(Noble[] ensNoble)
    {
        FrameNoble frameNoble = new FrameNoble(this, ensNoble);

        this.setEnabled(false);

        while (frameNoble.getNobleChoisi() == null)
        {
            try { Thread.sleep(100); } catch (Exception e) {}
        }

        this.setEnabled(true);
        return frameNoble.getNobleChoisi();
    }

    public boolean haveMoneyForNoble(Noble noble)
    {
        for(int i = 0; i < noble.getPrix().length; i++)
        {
            if (this.metier.getCurrentJoueur().getNbCarte(i) < noble.getPrix()[i])
                return false;
        }

        return true;
    }

    public Noble[] getNoblesPossible()
    {
        Noble[] nobles = new Noble[this.metier.getTabNobles().size()];

        int amount = 0;
        for(int i = 0; i < this.metier.getTabNobles().size(); i++)
        {
            if(this.metier.getTabNobles().get(i) != null && this.haveMoneyForNoble(this.metier.getTabNobles().get(i)))
            {
                nobles[amount] = this.metier.getTabNobles().get(i);
                amount++;
            }
        }

        if(amount == 0) return null;

        Noble[] nb = new Noble[amount];
        for (int i = 0; i < nb.length; i++)
        {
            nb[i] = nobles[i];
        }

        return nb;
    }

    /*-----------------------
            Jetons
    ---------------------- */

    public void reposerJeton(Carte c)
    {
        Joueur j = this.getCurrentJoueur();
        for (int i = 0; i < this.metier.getTabJetons().length-1; i++)
        {

            if (c.getPrix()[i] <= j.getNbCarte(i))
                continue;
            if (c.getPrix()[i] - j.getNbCarte(i) <= j.getNbJetons(i))
            {
                this.metier.getTabJetons()[i] += c.getPrix()[i] - j.getNbCarte(i);
            } 
            else 
            {
                this.metier.getTabJetons()[i] += j.getNbJetons(i);

                this.metier.getTabJetons()[5] += c.getPrix()[i] - j.getNbCarte(i) - j.getNbJetons(i);
            }

        }
    }

    public int  getNbJetonADeposer()
    {
        return this.getAmountJetonsSelected()-(10-this.getCurrentJoueur().getNbJetons());
    }

    public boolean isJetonsSelectedFull()
    {
        int amount = this.metier.getAmountJetonsSelected();

        if(amount != 2 && amount != 3)
        {
            return false;
        }

        // Si deux des trois jetons choisis sont les mêmes
        if (amount == 3 && (this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                        || this.metier.getTabJetonsChoisis()[0]  == this.metier.getTabJetonsChoisis()[2]
                        || this.metier.getTabJetonsChoisis()[1]  == this.metier.getTabJetonsChoisis()[2]))
        {
            this.resetJetonsChoisis();
            this.framePlateau.resetLabelJetonsChoisis();
            JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_PRISE_JETON_INVALIDE.getLib());
            return false;
        }

        // Si les deux jetons sont les mêmes et qu'il y en a moins de 4
        if (amount == 2 && this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                        && metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] < 4)
        {
            this.resetJetonsChoisis();
            this.framePlateau.resetLabelJetonsChoisis();
            JOptionPane.showMessageDialog(this.framePlateau, Message.ERR_PRISE_JETON_INVALIDE.getLib());
            return false;
        }

        // Si les 3 jetons sont différents et qu'il en reste au moins un de chaque
        if(amount == 3 && this.metier.getTabJetonsChoisis()[0] != this.metier.getTabJetonsChoisis()[1]
                       && this.metier.getTabJetonsChoisis()[0] != this.metier.getTabJetonsChoisis()[2]
                       && this.metier.getTabJetonsChoisis()[1] != this.metier.getTabJetonsChoisis()[2]
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] >= 1
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[1]] >= 1
                       && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[2]] >= 1)
        {
            return true;
        }

        // Si c'est les mêmes jetons et qu'il y a plus de 4 jetons de cette sorte sur le plateau
        if(amount == 2 && this.metier.getTabJetonsChoisis()[0] == this.metier.getTabJetonsChoisis()[1]
                && this.metier.getTabJetons()[this.metier.getTabJetonsChoisis()[0]] >= 4)
        {
            return true;
        }

        return false;
    }

    public boolean addSelectedJeton(int indexJeton)
    {
        for(int i = 0; i < this.metier.getTabJetonsChoisis().length; i++)
        {
            if(this.metier.getTabJetonsChoisis()[i] == -1)
            {
                this.metier.getTabJetonsChoisis()[i] = indexJeton;
                return true;
            }
        }

        return false;
    }

    public int getAmountJetonsSelected()
    {
        int amount = 0;

        for(int i = 0; i < this.metier.getTabJetonsChoisis().length; i++)
        {
            if(this.metier.getTabJetonsChoisis()[i] != -1) amount++;
        }

        return amount;
    }

    public void resetJetonsChoisis()
    {
        this.metier.resetJetonsChoisis();
    }

    public int getNbJetonsChoisis(int indexJeton)
    {
        int amount = 0;

        for (int i = 0; i < this.getTabJetonsChoisis().length; i++)
        {
            if (indexJeton == this.getTabJetonsChoisis()[i]) amount++;
        }

        return amount;
    }

    public void ajouterJetonJoueur()
    {
        for (int i = 0; i < this.getTabJetonsChoisis().length; i++) 
        {
            if (this.getTabJetonsChoisis()[i] != -1) 
            {
                this.getCurrentJoueur().ajouterJeton(this.getTabJetonsChoisis()[i], 1);
                this.getTabJetons()[this.getTabJetonsChoisis()[i]] -= 1;
            }
        }
    }

    public void reposerJetonChoisis()
    {
        this.setEnabled(false);

        FrameChoixJeton frameChoixJeton = new FrameChoixJeton(this, this.getTabJetonsChoisis());

        while (frameChoixJeton.getJetonReposerChoisis() == null) 
        {
            try {Thread.sleep(100);} catch (Exception e) {}
        }

        frameChoixJeton.dispose();

        this.reposerJetonChoisis(frameChoixJeton.getJetonReposerChoisis());

        this.setEnabled(true);

        if (this.getAmountJetonsSelected()==0)
        {
            this.framePlateau.resetJetonsChoisis();
            this.framePlateau.updateGraphics();
            return;
        }
        else
        {
            this.ajouterJetonJoueur();
            this.finTourJoueur();
            this.framePlateau.resetJetonsChoisis();
            this.framePlateau.updateGraphics();

        }
        
    }

    public void reposerJetonChoisis(int[] tabJetonReposer)
    {
        for (int ind : getTabJetonsChoisis())
            System.out.print(ind + " ");
        System.out.println();
        for (int i=0 ; i < getTabJetonsChoisis().length ; i++ )
        {
            for (int j = 0; j < tabJetonReposer.length; j++)
            {
                if (tabJetonReposer[j] == getTabJetonsChoisis()[i])
                {
                    this.metier.removeJetonChoisi(tabJetonReposer[j]);
                }

                for (int ind : getTabJetonsChoisis())
                    System.out.print(ind + " ");
                System.out.println();
            }
        }
    }


    /*-----------------------
             Cartes
    ---------------------- */

    public boolean reserverCarte(Joueur joueur, int deck)
    {
        return this.reserverCarte(joueur, this.metier.tirerCarte(deck));
    }

    public boolean reserverCarte(Joueur joueur, Carte carte)
    {
        if (!(joueur.reserverCarte(carte)))
            return false;

        this.prendreJeton(joueur, 5);

        int deck;

        for (int index = 0; index < this.metier.getTabCartes().length; index++)
        {
            deck = 3 - (index / (this.metier.getTabCartes().length / 3));

            if (this.metier.getTabCartes()[index] == carte)
                this.metier.getTabCartes()[index] = this.metier.tirerCarte(deck);
        }

        return true;
    }

    /*-----------------------
           Joueur
    ---------------------- */

    public boolean prendreJeton(Joueur joueur, int couleur)
    {
        if (this.getTabJetons()[couleur] == 0)
            return false;

        if (joueur.getNbJetons() >= 10)
            return false;

        joueur.ajouterJeton(couleur, 1);
        this.getTabJetons()[couleur]--;

        return true;
    }

    public void setValeurJeton(int[] newValeurs)
    {
        this.getCurrentJoueur().setValeurJeton(newValeurs);
    }

    /*-----------------------
          Serialization
    ---------------------- */

    public boolean sauvegarder(String nom)
    {
        if (nom == null || nom.equalsIgnoreCase(""))
            return false;

        try
        {
            File file = new File("../scenarios/" + nom + ".data");
            file.createNewFile();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        ObjectOutputStream oos = null;

        try
        {
            final FileOutputStream fichier = new FileOutputStream("../scenarios/" + nom + ".data");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this.metier);
            oos.flush();
        }
        catch (final java.io.IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (oos != null)
                {
                    oos.flush();
                    oos.close();
                }
            }
            catch (final IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return true;
    }

    public boolean charger(String nom)
    {
        String selectedFileName = nom + ".data";
        ObjectInputStream ois   = null;

        try
        {
            final FileInputStream fichier = new FileInputStream("../scenarios/" + selectedFileName);
            ois = new ObjectInputStream(fichier);
            final Jeu metier = (Jeu) ois.readObject();
            this.changementMetier(metier);
        }
        catch (final IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (ois != null)
                {
                    ois.close();
                }
            }
            catch (final IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return true;
    }

    public void forceEndGame()
    {
        this.forcedEndGame   = true;
        this.finDuTourJoueur = true;
    }

    public void changementMetier(Jeu metier)
    {
        this.forceEndGame();
        this.metier           = metier;
        this.changementScenario();

        this.changementMetier = true;
    }

    /*-----------------------
             Getters
    ---------------------- */

    public Carte[]     getTabCartes()        { return this.metier.getTabCartes();        }
    public List<Noble> getTabNobles()        { return this.metier.getTabNobles();        }
    public int[]       getTabJetons()        { return this.metier.getTabJetons();        }
    public int[]       getTabJetonsChoisis() { return this.metier.getTabJetonsChoisis(); }
    public Joueur      getCurrentJoueur()    { return this.metier.getCurrentJoueur();    }

    public static void main(String[] args)
    {
        System.out.println("Copyright Projet tutoré 2020 Equipe H : Mathieu BARTON, Tom BRULIN, Kevin BLONDEL, Jean-Bernard CAVELIER, Romuald NISS ");
        new Controleur();
    }

}
