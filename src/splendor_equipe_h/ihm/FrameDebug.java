package splendor_equipe_h.ihm;

import splendor_equipe_h.Controleur;
import splendor_equipe_h.utils.Message;
import splendor_equipe_h.utils.SplendorFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("serial")
public class FrameDebug extends JFrame implements ActionListener
{

    private Controleur controleur;

    private JPanel            panelPrinc;

    private JLabel            lblDebug;
    private JLabel            lblScenario;
    private JComboBox<String> cbxScenario;
    private JButton           btnCharger;
    private JButton           btnSauvegarder;

    private JButton btnPasserTour;
    private JButton btnJetonInfini;
    private JButton btnModifierJetons;

    private JButton btnLancement2J;
    private JButton btnLancement3J;
    private JButton btnLancement4J;


    public FrameDebug(Controleur controleur)
    {
        this.controleur = controleur;

        this.setFocusable(false);
        this.setResizable(false);

        this.setTitle("Debug");
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);

        this.panelPrinc = new JPanel(new GridLayout(15, 1));
        this.panelPrinc.setBackground(Controleur.COULEUR_FOND);

        this.lblDebug = new JLabel("Menu debug", JLabel.CENTER);
        this.lblDebug.setFont(SplendorFont.SEGOE_SMALL.getFont());
        this.lblDebug.setForeground(Controleur.COULEUR_TEXTE);

        this.lblScenario = new JLabel("Scénario :", JLabel.CENTER);
        this.lblScenario.setFont(SplendorFont.SEGOE_SMALL.getFont());
        this.lblScenario.setForeground(Controleur.COULEUR_TEXTE);

        File dir = new File("../scenarios");
        if(!dir.exists())
        {
            dir.mkdirs();
        }

        File folder = new File("../scenarios");
        File[] files = folder.listFiles();

        String[] scenarios = new String[files.length];

        int i = 0;
        for (File file : files)
        {
            scenarios[i] = file.getName().replace(".data", "");
            i++;
        }

        this.cbxScenario = new JComboBox<>(scenarios);

        this.btnCharger = new JButton("Charger");
        this.btnCharger.addActionListener(this);

        this.btnSauvegarder = new JButton("Sauvegarder");
        this.btnSauvegarder.addActionListener(this);

        this.btnPasserTour = new JButton("Passer le tour du joueur actuel");
        this.btnPasserTour.addActionListener(this);

        this.btnJetonInfini = new JButton("Donner max de jeton au joueur actuel");
        this.btnJetonInfini.addActionListener(this);

        this.btnModifierJetons = new JButton("Modifier jetons du joueur actuel");
        this.btnModifierJetons.addActionListener(this);

        this.btnLancement2J = new JButton("Relancer une partie à 2 joueurs");
        this.btnLancement2J.addActionListener(this);
        this.btnLancement3J = new JButton("Relancer une partie à 3 joueurs");
        this.btnLancement3J.addActionListener(this);
        this.btnLancement4J = new JButton("Relancer une partie à 4 joueurs");
        this.btnLancement4J.addActionListener(this);

        this.panelPrinc.add(this.lblDebug);
        this.panelPrinc.add(new JLabel(""));
        this.panelPrinc.add(this.lblScenario);
        this.panelPrinc.add(this.cbxScenario);
        this.panelPrinc.add(this.btnCharger);
        this.panelPrinc.add(this.btnSauvegarder);
        this.panelPrinc.add(new JLabel(""));
        this.panelPrinc.add(this.btnLancement2J);
        this.panelPrinc.add(this.btnLancement3J);
        this.panelPrinc.add(this.btnLancement4J);
        this.panelPrinc.add(new JLabel(""));
        this.panelPrinc.add(this.btnPasserTour);
        this.panelPrinc.add(this.btnJetonInfini);
        this.panelPrinc.add(this.btnModifierJetons);
        this.panelPrinc.add(new JLabel(""));

        this.setContentPane(this.panelPrinc);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnSauvegarder)
        {
            String nom = (String) JOptionPane.showInputDialog(this, Message.ACTION_SAVE_NOM_SCENARIO.getLib(), "Sauvegarde", JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (!(this.controleur.sauvegarder(nom)))
            {
                JOptionPane.showMessageDialog(this, Message.ERR_SAUVEGARDE_NOM_SCENARIO.getLib(), "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.cbxScenario.addItem(nom);

            JOptionPane.showMessageDialog(this, Message.ACTION_SAVE_SCENARIO.getLib(), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == this.btnCharger)
        {
            if (this.cbxScenario.getSelectedItem() == null || this.cbxScenario.getSelectedItem().toString().equalsIgnoreCase(""))
                return;

            if (!(this.controleur.charger(this.cbxScenario.getSelectedItem().toString())))
            {
                JOptionPane.showMessageDialog(this, "Erreur chargement fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Scenario chargé avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == this.btnPasserTour)
        {
            this.controleur.finTourJoueur();
        }
        else if (e.getSource() == this.btnJetonInfini)
        {
            this.controleur.setValeurJeton(new int[] { 999, 999, 999, 999, 999, 999 });
            this.controleur.updateGraphics();
        }
        else if (e.getSource() == this.btnModifierJetons)
        {
            new FrameDebugValeurJeton(this.controleur);
        }
        else if (e.getSource() == this.btnLancement2J)
        {
            this.controleur.lancementPartie(Controleur.langue, 2);
        }
        else if (e.getSource() == this.btnLancement3J) 
        {
            this.controleur.lancementPartie(Controleur.langue, 3);
        }
        else if (e.getSource() == this.btnLancement4J) 
        {
            this.controleur.lancementPartie(Controleur.langue, 4);
        }
    }

}

@SuppressWarnings("serial")
class FrameDebugValeurJeton extends JFrame implements ActionListener
{
   
    private Controleur   controleur;

    private JPanel       panelPrincipal;

    private JLabel       lblTexte;
    private JPanel       panelTxtField;
    private JTextField[] txtValeurJeton;
    private JLabel[]     lblJeton;
    private JButton      btnValider;

    public FrameDebugValeurJeton(Controleur controleur)
    {
        Image icon = Toolkit.getDefaultToolkit().getImage("../ressources/boite.jpg");
        this.setIconImage(icon);

        this.controleur = controleur;
        this.panelPrincipal = new JPanel(new BorderLayout());
        this.panelPrincipal.setBackground(Controleur.COULEUR_FOND);

        this.lblTexte = new JLabel("Entrez les nouvelles valeurs", JLabel.CENTER);
        this.lblTexte.setForeground(Controleur.COULEUR_TEXTE);
        this.lblTexte.setFont(SplendorFont.SEGOE_SMALL.getFont());

        this.panelTxtField = new JPanel(new GridLayout(2, 6, 20, 20));

        this.lblJeton = new JLabel[6];
        this.lblJeton[0] = new JLabel("Vert");
        this.lblJeton[1] = new JLabel("Blanc");
        this.lblJeton[2] = new JLabel("Bleu");
        this.lblJeton[3] = new JLabel("Noir");
        this.lblJeton[4] = new JLabel("Rouge");
        this.lblJeton[5] = new JLabel("Or");

        for (JLabel lblJ : this.lblJeton)
        {
            lblJ.setForeground(Controleur.COULEUR_TEXTE);
            lblJ.setFont(SplendorFont.SEGOE_SMALL.getFont());
        }

        this.btnValider = new JButton("Valider");
        this.btnValider.addActionListener(this);

        this.txtValeurJeton = new JTextField[6];
        for (int i = 0; i < this.txtValeurJeton.length; i++)
        {
            this.txtValeurJeton[i] = new JTextField("0");
        }

        for(int i=0; i < this.lblJeton.length ; i++)
        {
            this.panelTxtField.add(this.lblJeton[i]);
        }

        for (int i = 0; i < this.txtValeurJeton.length; i++)
        {
            this.panelTxtField.add(this.txtValeurJeton[i]);
        }

        this.panelTxtField.setOpaque(false);

        this.panelPrincipal.add(this.lblTexte     , BorderLayout.NORTH );
        this.panelPrincipal.add(this.panelTxtField, BorderLayout.CENTER);
        this.panelPrincipal.add(this.btnValider   , BorderLayout.SOUTH );

        this.add(this.panelPrincipal);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnValider)
        {
            int[] newValeurs = new int[this.txtValeurJeton.length];

            for (int i = 0; i < this.txtValeurJeton.length; i++)
            {
                try
                {
                    newValeurs[i] = Integer.parseInt(this.txtValeurJeton[i].getText());
                }
                catch (Exception ex)
                {
                    newValeurs[i] = 0;
                }
            }

            this.controleur.setValeurJeton(newValeurs);
            this.controleur.updateGraphics();
            this.dispose();
        }
    }
}
