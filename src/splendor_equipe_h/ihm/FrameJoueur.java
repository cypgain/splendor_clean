package splendor_equipe_h.ihm;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.ihm.listeners.framejoueur.GererBoutons;
import splendor_equipe_h.ihm.listeners.framejoueur.GererSouris;
import splendor_equipe_h.metier.Carte;
import splendor_equipe_h.metier.Joueur;
import splendor_equipe_h.metier.Noble;
import splendor_equipe_h.utils.Couleur;
import splendor_equipe_h.utils.ImageUtils;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class FrameJoueur extends JFrame
{

    /*----------------------
           Constantes
     -----------------------*/

    private static final int TAILLE_FRAME_X = 270;
    private static final int TAILLE_FRAME_Y = 900;

    private static final int TAILLE_IMAGE_CARTE_X = 80;
    private static final int TAILLE_IMAGE_CARTE_Y = 125;

    private static final int TAILLE_IMAGE_NOBLE_X = 75;
    private static final int TAILLE_IMAGE_NOBLE_Y = 75;

    private static final int TAILLE_IMAGE_JETON_X = 75;
    private static final int TAILLE_IMAGE_JETON_Y = 75;

    /*----------------------
            Attributs
     -----------------------*/

    private Controleur   controleur;
    private FramePlateau framePlateau;
    private Joueur       joueur;

    private JPanel panelPrincipal;
    private JPanel panelMiddle;

    // Informations
    private JPanel panelInfos;
    private JLabel lblJoueur;
    private JLabel lblPrestige;

    // Jetons
    private JPanel   panelJetons;
    private JLabel[] tabLblJetons;

    // Bonus
    private JPanel           panelBonus;
    private BonusComponent[] tabBonusCpnt;

    // Nobles
    private JPanel   panelNobles;
    private JLabel[] tabLblNobles;

    // Reserve
    private JPanel   panelReserve;
    private JLabel[] tabLblReserve;
    private JLabel   carteReserveChoisie;

    // Boutons
    private JButton btnAcheter;

    public FrameJoueur(Controleur controleur, FramePlateau framePlateau, Joueur joueur)
    {
        this.controleur   = controleur;
        this.framePlateau = framePlateau;
        this.joueur       = joueur;

        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setSize((int) (FrameJoueur.TAILLE_FRAME_X * Controleur.echelleWidth ), (int) (FrameJoueur.TAILLE_FRAME_Y * Controleur.echelleHeight));
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);
        this.setTitle(Message.PLAYER.getLib().replace("{NUM}", "" + this.joueur.getNum()));

        this.panelPrincipal = new JPanel(new BorderLayout());
        this.panelPrincipal.setBorder(new EmptyBorder((int) (10 * Controleur.echelleWidth ), (int) (10 * Controleur.echelleHeight), (int) (10 * Controleur.echelleWidth ), (int) (10 * Controleur.echelleHeight)));

        this.panelMiddle = new JPanel(new GridLayout(2,1));

        this.initInformations();
        this.initJetons();
        this.initBonus();
        this.initNobles();
        this.initReserve();
        this.initBoutons();

        this.panelPrincipal.add(this.panelMiddle,BorderLayout.CENTER);

        this.add(this.panelInfos,     BorderLayout.NORTH);
        this.add(this.panelPrincipal, BorderLayout.CENTER);
        this.add(this.btnAcheter,     BorderLayout.SOUTH);

        // Affichage de la frame
        this.setUndecorated(true);
        this.setLocation((int) (this.framePlateau.getX() + this.framePlateau.getSize().getWidth() + (this.getWidth() + 5) *  (this.joueur.getNum() - 1)), this.framePlateau.getY());
        this.setVisible(true);
    }


    private void initInformations() 
    {
        this.panelInfos = new JPanel(new GridLayout(2, 1));

        this.lblPrestige = new JLabel("0", SwingConstants.CENTER);
        this.lblJoueur   = new JLabel(Message.PLAYER.getLib().replace("{NUM}", "" + this.joueur.getNum()), SwingConstants.CENTER);

        this.lblPrestige.setFont(SplendorFont.SEGOE_BIG.getFont());
        this.lblJoueur  .setFont(SplendorFont.SEGOE_BIG.getFont());

        this.panelInfos.add(this.lblJoueur);
        this.panelInfos.add(this.lblPrestige);
    }

    private void initJetons()
    {
        this.panelJetons  = new JPanel(new GridLayout(3, 2));
        this.tabLblJetons = new JLabel[Couleur.values().length];

        for(int i = 0; i < Couleur.values().length; i++)
        {
            this.tabLblJetons[i] = new JLabel();
            this.tabLblJetons[i].setIcon(ImageUtils.resizeImage("../ressources/jeton_" + Couleur.values()[i].toString().toLowerCase() + ".png", 
                                         (int) (FrameJoueur.TAILLE_IMAGE_JETON_X * Controleur.echelleWidth ),
                                         (int) (FrameJoueur.TAILLE_IMAGE_JETON_Y * Controleur.echelleHeight)));
            this.tabLblJetons[i].setText("0");
            this.tabLblJetons[i].setFont(SplendorFont.SEGOE_SMALL.getFont());
            this.panelJetons.add(this.tabLblJetons[i]);
        }

        this.panelPrincipal.add(this.panelJetons, BorderLayout.NORTH);
    }

    private void initBonus()
    {
        this.panelBonus   = new JPanel(new GridLayout(1, 5));
        this.tabBonusCpnt = new BonusComponent[Couleur.values().length - 1];

        for(int i = 0; i < (Couleur.values().length - 1); i++)
        {
            this.tabBonusCpnt[i] = new BonusComponent(Couleur.values()[i].getAwtCouleur(), this.joueur.getNbCarte(Couleur.values()[i]));
            this.panelBonus.add(this.tabBonusCpnt[i]);
        }

        this.panelMiddle.add(this.panelBonus);
    }

    private void initNobles()
    {
        this.panelNobles  = new JPanel();
        this.tabLblNobles = new JLabel[this.controleur.getTabNobles().size()];

        for (int i = 0 ; i < this.tabLblNobles.length ; i++)
        {
            this.tabLblNobles[i] = new JLabel();
            this.panelNobles.add(this.tabLblNobles[i]);
        }

        this.panelMiddle.add(this.panelNobles);
    }

    private void initReserve()
    {
        this.panelReserve  = new JPanel(new GridLayout(1, 3));
        this.tabLblReserve = new JLabel[3];

        for (int i = 0; i < this.tabLblReserve.length; i++)
        {
            this.tabLblReserve[i] = new JLabel();
            this.tabLblReserve[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            this.tabLblReserve[i].setIcon(ImageUtils.resizeImage("../ressources/dev_III_dos.png", 
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_X * Controleur.echelleWidth ),
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_Y * Controleur.echelleHeight)));
            this.tabLblReserve[i].addMouseListener(new GererSouris(this));
            this.panelReserve.add(this.tabLblReserve[i]);
        }

        this.panelPrincipal.add(this.panelReserve, BorderLayout.SOUTH);
    }

    private void initBoutons()
    {
        this.btnAcheter = new JButton(Message.BUTTON_BUY_CARD_RESERVE.getLib());
        this.btnAcheter.addActionListener(new GererBoutons(this));
    }

    public void updateFramePosition()
    {
        this.setLocation((int) (this.framePlateau.getX() + this.framePlateau.getSize().getWidth() + (this.getWidth() + 5) *  (this.joueur.getNum() - 1)), this.framePlateau.getY());
    }

    public void update()
    {
        // Joueur actuel
        if (this.joueur==this.controleur.getCurrentJoueur())
        {
            this.lblJoueur.setForeground(Color.RED);
        }
        else
        {
            this.lblJoueur.setForeground(null);
        }

        // Jetons
        for(int i = 0; i < this.tabLblJetons.length; i++)
        {
            this.tabLblJetons[i].setText("" + this.joueur.getTabJetons()[i]);
        }

        // Bonus
        for(int i = 0; i < this.tabBonusCpnt.length; i++)
        {
            this.tabBonusCpnt[i].setAmount(this.joueur.getNbCarte(i));
            this.tabBonusCpnt[i].repaint();
        }

        // Nobles
        for (int i = 0 ; i < this.tabLblNobles.length ; i++)
        {
            this.tabLblNobles[i].setIcon(null);
        }

        int z = 0;
        for(Noble noble : this.getJoueur().getTabNobles())
        {
            this.tabLblNobles[z].setIcon(ImageUtils.resizeImage(noble.getUrl(), 
                                         (int) (FrameJoueur.TAILLE_IMAGE_NOBLE_X * Controleur.echelleWidth ),
                                         (int) (FrameJoueur.TAILLE_IMAGE_NOBLE_Y * Controleur.echelleHeight)));
            z++;
        }

        // Carte reservée
        for(int i = 0; i < this.tabLblReserve.length; i++)
        {
            this.tabLblReserve[i].setIcon(ImageUtils.resizeImage("../ressources/dev_III_dos.png", 
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_X * Controleur.echelleWidth ),
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_Y * Controleur.echelleHeight)));
        }

        int i = 0;
        for(Carte carteReserve : this.joueur.getTabCartesReserve())
        {
            this.tabLblReserve[i].setIcon(ImageUtils.resizeImage(carteReserve.getUrl(), 
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_X * Controleur.echelleWidth ),
                                          (int) (FrameJoueur.TAILLE_IMAGE_CARTE_Y * Controleur.echelleHeight)));
            i++;
        }

        // Points prestiges
        this.lblPrestige.setText("" + this.joueur.getPrestige());
    }

    public void updateGraphics()
    {
        this.controleur.updateGraphics();
    }

    public void resetCarteReserveChoisie()
    {
        if(this.carteReserveChoisie != null)
            this.carteReserveChoisie.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        this.carteReserveChoisie = null;
        this.update();
    }

    public JLabel getCarteReserveChoisie() { return this.carteReserveChoisie; }

    public Carte getCarteReserve()
    {
        if (this.carteReserveChoisie == null)
            return null;

        int index = -1;

        for(int i = 0; i < this.tabLblReserve.length; i++)
        {
            if(this.tabLblReserve[i] == this.carteReserveChoisie)
            {
                index = i;
                break;
            }
        }

        if(index >= this.joueur.getTabCartesReserve().size())
        {
            return null;
        }

        return this.joueur.getTabCartesReserve().get(index);
    }

    public void setCarteReserveChoisie(JLabel carteReserveChoisie) { this.carteReserveChoisie = carteReserveChoisie; }

    public boolean prendreCarte(Joueur joueur, Carte carte)
    {
        return this.controleur.prendreCarte(joueur, carte);
    }

    public Joueur getJoueur()
    {
        return this.joueur;
    }

    public Joueur getCurrentJoueur()
    {
        return this.controleur.getCurrentJoueur();
    }

    public void finTourJoueur()
    {
        this.controleur.finTourJoueur();
    }

}

@SuppressWarnings("serial")
class BonusComponent extends JPanel
{

    private Color color;
    private int amount;

    public BonusComponent(Color color, int amount)
    {
        this.color = color;
        this.amount = amount;
        this.setPreferredSize(new Dimension((int) (50 * Controleur.echelleWidth ), (int) (50 * Controleur.echelleHeight)));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(color);
        g2.fillRect(0, (int) (50 * Controleur.echelleHeight), (int) (50 * Controleur.echelleWidth ), (int) (50 * Controleur.echelleHeight));
        g2.setPaint(Color.GRAY);
        g2.setFont(SplendorFont.SEGOE_SMALL.getFont());
        g2.drawString("" + this.amount, (int) ((this.getWidth() / 2 - 8) * Controleur.echelleHeight), (int) (140 * Controleur.echelleWidth ));
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

}
