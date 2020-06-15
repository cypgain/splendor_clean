package splendor_equipe_h.ihm;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.ihm.listeners.frameplateau.GererBoutons;
import splendor_equipe_h.ihm.listeners.frameplateau.GererFenetre;
import splendor_equipe_h.ihm.listeners.frameplateau.GererSouris;
import splendor_equipe_h.metier.Carte;
import splendor_equipe_h.metier.Joueur;
import splendor_equipe_h.metier.Noble;
import splendor_equipe_h.utils.Couleur;
import splendor_equipe_h.utils.ImageUtils;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class FramePlateau extends JFrame
{

    /*----------------------
           Constantes
     -----------------------*/
    
    private static final int TAILLE_IMAGE_CARTE_X = 130;
    private static final int TAILLE_IMAGE_CARTE_Y = 200;

    private static final int TAILLE_IMAGE_NOBLE_X = 125;
    private static final int TAILLE_IMAGE_NOBLE_Y = 125;

    private static final int TAILLE_IMAGE_JETON_X = 75;
    private static final int TAILLE_IMAGE_JETON_Y = 75;

    /*----------------------
            Attributs
     -----------------------*/

    private Controleur controleur;

    private JLabel lblTitre;

    // Nobles
    private JPanel   panelNobles;
    private JLabel[] tabLblNobles;

    // Cartes
    private JPanel   panelCartes;
    private JLabel[] tabLblCartes;
    private int      carteSelectionnee;

    // Dos de cartes
    private JPanel   panelDosCartes;
    private JLabel[] tabLblDosCartes;
    private int      dosCarteSelectionnee;

    // Jetons
    private JPanel   panelJetons;
    private JLabel[] tabLblJetons;
    private JLabel[] tabLblNbJetons;
    private JLabel[] tabLblNbJetonsChoisis;

    // Bouttons
    private JButton btnAcheter;
    private JButton btnReserve;

    public FramePlateau(Controleur controleur)
    {
        this.controleur = controleur;

        this.setTitle(Message.TITRE_FRAME_PLATEAU.getLib());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setResizable(false);

        this.addComponentListener(new GererFenetre(this));
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);

        this.loadRegionNobles();
        this.loadRegionCartes();
        this.loadRegionDosCartes();
        this.loadRegionJetons();

        this.add(this.panelNobles,    BorderLayout.NORTH);
        this.add(this.panelCartes,    BorderLayout.CENTER);
        this.add(this.panelDosCartes, BorderLayout.WEST);
        this.add(this.panelJetons,    BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    /*----------------------
       Gestion des Jetons
     -----------------------*/

    private void loadRegionJetons()
    {
        this.panelJetons  = new JPanel(new BorderLayout());

        JPanel panelJetonsTop    = new JPanel();
        JPanel panelJetonsBottom = new JPanel();

        // Jetons
        this.tabLblJetons          = new JLabel[Couleur.values().length    ];
        this.tabLblNbJetons        = new JLabel[Couleur.values().length    ];
        this.tabLblNbJetonsChoisis = new JLabel[Couleur.values().length - 1];

        JPanel panelTemp;

        for(int i = 0; i < Couleur.values().length - 1; i++)
        {
            panelTemp = new JPanel(new GridLayout(2, 1, 10, 0));

            this.tabLblJetons[i] = new JLabel();
            this.tabLblJetons[i].setIcon(ImageUtils.resizeImage("ressources/jeton_" + Couleur.values()[i].toString().toLowerCase() + ".png", TAILLE_IMAGE_JETON_X, TAILLE_IMAGE_JETON_Y));
            this.tabLblJetons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.tabLblJetons[i].addMouseListener(new GererSouris(this));

            this.tabLblNbJetons       [i] = new JLabel("" + this.controleur.getTabJetons       ()[i] + " x ", JLabel.RIGHT);
            this.tabLblNbJetonsChoisis[i] = new JLabel("" + this.controleur.getNbJetonsChoisis (i)   + " x ", JLabel.RIGHT);

            this.tabLblNbJetonsChoisis[i].setForeground(Color.RED);

            panelTemp.add(tabLblNbJetonsChoisis[i]);
            panelTemp.add(tabLblNbJetons[i]);

            panelJetonsTop.add(panelTemp);
            panelJetonsTop.add(this.tabLblJetons[i]);
        }

        this.tabLblNbJetons[5] = new JLabel("" + this.controleur.getTabJetons()[5] + " x ", JLabel.RIGHT);
        this.tabLblJetons  [5] = new JLabel();
        this.tabLblJetons  [5].setIcon(ImageUtils.resizeImage("ressources/jeton_" + Couleur.values()[5].toString().toLowerCase() + ".png",TAILLE_IMAGE_JETON_X, TAILLE_IMAGE_JETON_Y));

        panelJetonsTop.add(this.tabLblNbJetons[5]);
        panelJetonsTop.add(this.tabLblJetons  [5]);

        // Boutons
        this.btnAcheter = new JButton(Message.BUTTON_BUY_CARD.getLib());
        this.btnReserve = new JButton(Message.BUTTON_RESERVE_CARD.getLib());

        this.btnAcheter.addActionListener(new GererBoutons(this));
        this.btnReserve.addActionListener(new GererBoutons(this));

        panelJetonsBottom.add(this.btnAcheter);
        panelJetonsBottom.add(this.btnReserve);

        // Ajout au panel
        this.panelJetons.add(panelJetonsTop,    BorderLayout.NORTH);
        this.panelJetons.add(panelJetonsBottom, BorderLayout.SOUTH);
    }

    public boolean isJeton(Object object)
    {
        if(object instanceof JLabel)
        {
            for(JLabel l : this.tabLblJetons)
            {
                if(l == object)
                    return true;
            }
        }

        return false;
    }

    public int getIndexOfLblJeton(JLabel lblJeton)
    {
        for(int i = 0; i < this.tabLblJetons.length; i++)
        {
            if(this.tabLblJetons[i] == lblJeton)
                return i;
        }

        return 0;
    }

    public void resetLabelJetonsChoisis()
    {
        int i = 0;
        for(JLabel label : this.tabLblNbJetonsChoisis)
        {
            label.setText("" + this.controleur.getNbJetonsChoisis(i) + " x ");
            i++;
        }
    }

    public boolean isJetonsSelectedFull()
    {
        return this.controleur.isJetonsSelectedFull();
    }

    public boolean addSelectedJeton(int indexJeton)
    {
        return this.controleur.addSelectedJeton(indexJeton);
    }

    public void resetJetonsChoisis()
    {
        this.controleur.resetJetonsChoisis();
    }

    public void reposerJetonChoisis()
    {
        this.controleur.reposerJetonChoisis();
    }
    public void ajouterJetonJoueur()
    {
        this.controleur.ajouterJetonJoueur();
    }
    
    /*----------------------
       Gestion des Nobles
     -----------------------*/

    private void loadRegionNobles()
    {
        JPanel panelNoblesIn = new JPanel();

        this.panelNobles  = new JPanel(new BorderLayout());
        this.tabLblNobles = new JLabel[this.controleur.getTabNobles().size()];
        this.lblTitre     = new JLabel(Message.TOUR_JOUEUR.getLib().replace("{NUM}", "1"), JLabel.CENTER);

        this.lblTitre.setFont(SplendorFont.SEGOE_BIG.getFont());

        int i = 0;
        for(Noble noble : this.controleur.getTabNobles())
        {
            this.tabLblNobles[i] = new JLabel();
            this.tabLblNobles[i].setIcon(ImageUtils.resizeImage(noble.getUrl(), FramePlateau.TAILLE_IMAGE_NOBLE_X, FramePlateau.TAILLE_IMAGE_NOBLE_Y));
            panelNoblesIn.add(this.tabLblNobles[i]);
            i++;
        }

        this.panelNobles.add(this.lblTitre, BorderLayout.NORTH);
        this.panelNobles.add(panelNoblesIn, BorderLayout.SOUTH);
    }

    /*----------------------
    Gestion des Dos de Cartes
     -----------------------*/

    private void loadRegionDosCartes()
    {
        this.panelDosCartes       = new JPanel(new GridLayout(3, 1, 10, 10));
        this.tabLblDosCartes      = new JLabel[3];
        this.dosCarteSelectionnee = -1;

        for(int i = 0; i < this.tabLblDosCartes.length; i++)
        {
            this.tabLblDosCartes[i] = new JLabel();
            this.tabLblDosCartes[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.tabLblDosCartes[i].addMouseListener(new GererSouris(this));
            this.panelDosCartes.add(this.tabLblDosCartes[i]);
        }

        this.tabLblDosCartes[0].setIcon(ImageUtils.resizeImage("ressources/dev_III_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[0].setName("ressources/dev_III_dos.png");

        this.tabLblDosCartes[1].setIcon(ImageUtils.resizeImage("ressources/dev_II_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[1].setName("ressources/dev_II_dos.png");

        this.tabLblDosCartes[2].setIcon(ImageUtils.resizeImage("ressources/dev_I_dos.png", FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
        this.tabLblDosCartes[2].setName("ressources/dev_I_dos.png");
    }

    public boolean isDosCarte(Object object)
    {
        if(object instanceof JLabel)
        {
            for(JLabel l : this.tabLblDosCartes)
            {
                if(l == object)
                    return true;
            }
        }

        return false;
    }

    public void resetBorderLblDosCartes()
    {
        for(JLabel lbl : this.tabLblDosCartes)
        {
            lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }
    }

    public int getIndexOfLblDosCarte(JLabel lblDosCarte)
    {
        for(int i = 0; i < this.tabLblDosCartes.length; i++)
        {
            if(this.tabLblDosCartes[i] == lblDosCarte)
                return i;
        }

        return 0;
    }

    public void resetDosCarteSelectionnee()
    {
        if(this.dosCarteSelectionnee != -1)
        {
            this.tabLblDosCartes[this.dosCarteSelectionnee].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.dosCarteSelectionnee = -1;
        }
    }

    public void setDosCarteSelectionnee(int dosCarteSelectionnee) { this.dosCarteSelectionnee = dosCarteSelectionnee; }

    /*----------------------
        Gestion des Cartes
     -----------------------*/

    private void loadRegionCartes()
    {
        this.panelCartes       = new JPanel(new GridLayout(3, 4, 10, 10));
        this.tabLblCartes      = new JLabel[12];
        this.carteSelectionnee = -1;

        this.panelCartes.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        Carte[] tabCartes = this.controleur.getTabCartes();

        for (int i = 0; i < tabCartes.length; i++)
        {
            this.tabLblCartes[i] = new JLabel();
            this.tabLblCartes[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.panelCartes.add(this.tabLblCartes[i]);
        }

        this.updateCartes();
    }

    public boolean isCarte(Object object)
    {
        if(object instanceof JLabel)
        {
            for(JLabel l : this.tabLblCartes)
            {
                if(l == object)
                    return true;
            }
        }

        return false;
    }

    public void resetBorderLblCartes()
    {
        for(JLabel lbl : this.tabLblCartes)
        {
            lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }
    }

    public int getIndexOfLblCarte(JLabel lblCarte)
    {
        for(int i = 0; i < this.tabLblCartes.length; i++)
        {
            if(this.tabLblCartes[i] == lblCarte)
                return i;
        }

        return 0;
    }

    public void resetCarteSelectionnee()
    {
        if(this.carteSelectionnee != -1)
        {
            this.tabLblCartes[this.carteSelectionnee].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.carteSelectionnee = -1;
        }
    }

    public boolean reserverCarte(Joueur joueur, int deck)
    {
        return this.controleur.reserverCarte(joueur, deck);
    }

    public boolean reserverCarte(Joueur joueur, Carte carte)
    {
        return this.controleur.reserverCarte(joueur, carte);
    }

    public boolean acheterCarte(Joueur joueur, Carte carte)
    {
        return this.controleur.acheterCarte(joueur, carte);
    }

    public void setCarteSelectionnee(int carteSelectionnee) { this.carteSelectionnee = carteSelectionnee; }

    /*-----------------------
             Updates
    ---------------------- */

    public void updateFramesPosition()
    {
        this.controleur.updateFramesPosition();
    }

    public void updateLabelTourJoueur()
    {
        this.lblTitre.setText(Message.TOUR_JOUEUR.getLib().replace("{NUM}", "" +this.controleur.getCurrentJoueur().getNum()));
    }

    public void updateCartes()
    {
        Carte[] tabCartes = this.controleur.getTabCartes();

        for (int i = 0; i < tabCartes.length; i++)
        {
            this.tabLblCartes[i].setIcon(ImageUtils.resizeImage(tabCartes[i].getUrl(), FramePlateau.TAILLE_IMAGE_CARTE_X, FramePlateau.TAILLE_IMAGE_CARTE_Y));
            this.tabLblCartes[i].setName(tabCartes[i].getUrl());
            this.tabLblCartes[i].addMouseListener(new GererSouris(this));
        }
    }

    public void updateJetons()
    {
        for(int i = 0; i < this.tabLblNbJetons.length; i++)
        {
            this.tabLblNbJetons[i].setText("" + this.controleur.getTabJetons()[i] + " x ");
        }

        this.resetLabelJetonsChoisis();

        for(int i=0; i < this.controleur.getTabJetonsChoisis().length; i++)
        {
            if (this.controleur.getTabJetonsChoisis()[i] == -1)
                continue;

            this.tabLblNbJetonsChoisis[this.controleur.getTabJetonsChoisis()[i]].setText(this.controleur.getNbJetonsChoisis(this.controleur.getTabJetonsChoisis()[i]) + " x ");
        }
    }

    public void updateNobles()
    {
        for (int i = 0; i < this.tabLblNobles.length; i++)
        {
            this.tabLblNobles[i].setIcon(null);
        }

        int i = 0;
        for (Noble noble : this.controleur.getTabNobles())
        {
            this.tabLblNobles[i].setIcon(ImageUtils.resizeImage(noble.getUrl(), FramePlateau.TAILLE_IMAGE_NOBLE_X, FramePlateau.TAILLE_IMAGE_NOBLE_Y));
            i++;
        }
    }

    public void updateGraphics()
    {
        this.controleur.updateGraphics();
    }

    /*-----------------------
             Autres
    ---------------------- */

    public void finTourJoueur()
    {
        this.controleur.finTourJoueur();
    }


    /*-----------------------
             Getters
    ---------------------- */

    public JButton  getBtnAcheter          () { return this.btnAcheter;                           }
    public JButton  getBtnReserve          () { return this.btnReserve;                           }
    public Joueur   getCurrentJoueur       () { return this.controleur.getCurrentJoueur();        }
    public int[]    getTabJetons           () { return this.controleur.getTabJetons();            }
    public int      getAmountJetonsSelected() { return this.controleur.getAmountJetonsSelected(); }
    public int[]    getTabJetonsChoisis    () { return this.controleur.getTabJetonsChoisis();     }
    public JLabel[] getTabLblJetons        () { return this.tabLblJetons;                         }
    public int      getCarteSelectionnee   () { return this.carteSelectionnee;                    }
    public Carte[]  getTabCartes           () { return this.controleur.getTabCartes();            }
    public JLabel[] getTabLblCartes        () { return this.tabLblCartes;                         }
    public JLabel[] getTabLblDosCartes     () { return this.tabLblDosCartes;                      }
    public int      getDosCarteSelectionnee() { return this.dosCarteSelectionnee;                 }

}
