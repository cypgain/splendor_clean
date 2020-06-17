package splendor_equipe_h.utils;

import static splendor_equipe_h.Controleur.langue;

public enum Message
{
    LANCEMENT("Lancement","Starting"),

    LANCEMENT_RULES("Règles du jeu", "Game's rules"),

    LANCEMENT_VALIDER("Valider votre choix", "Validate your choice"),

    LANCEMENT_PLAYER("Nombre de joueurs", "Numbers of players"),

    LANCEMENT_LANGUE("Langue", "Language"),

    TITRE_FRAME_PLATEAU("Splendor", "Splendor"),

    BUTTON_BUY_CARD("Acheter la carte",  "Buy card"),
    BUTTON_BUY_CARD_RESERVE("Acheter la carte reservée",  "Buy booked card"),
    BUTTON_RESERVE_CARD("Reserver la carte", "Book card"),

    PLAYER("Joueur n°{NUM}", "Player No {NUM}"),

    TOUR_JOUEUR("Au tour du joueur n°{NUM}","Player {NUM}'s turn"),

    ERR_PRISE_JETON_INVALIDE("Vous n'avez pas le droit de prendre cette combinaison de jetons", "You are not allowed to take this tokens combination"),

    ERR_JETON("Vous n'avez pas assez de jetons","You do not have enough chips"),

    ERR_JETON_FULL("Vous possédez déjà trop de jetons", "You already have max tokens"),

    ERR_NOT_ENOUGH_JETON("Vous n'avez pas assez de jetons", "You do not have enough chips"),

    ERR_RESERVATION("Vous ne pouvez pas reserver cette carte", "You can't book this card"),

    ERR_PDF("Fichier introuvable ou incompatible", "File not found or not compatible"),

    CHOIX_NOBLE("Veuillez choisir le noble que vous souhaitez prendre", "Please select the noble you want"),
    CHOIX_DEPOSER_JETON("Vous devez deposer {NUM} ou tous vos Jetons choisis", "You must put {NUM} or all the choosen token back"),

    FRAME_JETON("Jetons", "Tokens"),

    BOUTON_VALIDER_NOBLE("Selectionner ce noble", "Select this noble"),
    BOUTON_DEPOSER_JETON("Déposer ces jetons", "Put back these tokens"),

    WINNER("Le joueur {NUM} a gagné la partie", "Player's {NUM} has won the game"),

    ERR_SAUVEGARDE_NOM_SCENARIO("Vous n'avez pas entré de nom", "You didn't enter a name"),

    ACTION_SAVE_NOM_SCENARIO("Veuillez entrer le nom du scénario", "Please enter the name of the script"),

    SAUVEGARDE("Sauvegarde", "Save"),

    ACTION_SAVE_SCENARIO("Vous avez sauvegardé le scénario", "You saved the script"),

    NOT_YOUR_TURN("Ce n'est pas votre tour !", "It's not your turn !"),

    DEBUG_MENU("Menu debug", "Debug menu"),

    DEBUG_SCENARIO("Scénario :", "Scenario :"),

    DEBUG_CHARGER("Charger", "Load"),

    DEBUG_SAUVER("Sauvegarder", "Save"), 

    DEBUG_PASS("Passer le tour du joueur actuel", "Skip the current player's turn"),

    DEBUG_MAX_JETON("Donner max de jeton au joueur actuel", "Give max token to the current player"),

    DEBUG_JETON("Modifier jetons du joueur actuel", "Edit current player's tokens"),

    DEBUG_NEW_2P("Relancer une partie à 2 joueurs", "Relaunch a game with 2 players"),

    DEBUG_NEW_3P("Relancer une partie à 3 joueurs", "Relaunch a game with 3 players"),

    DEBUG_NEW_4P("Relancer une partie à 4 joueurs", "Relaunch a game with 4 players"),

    ERR("Erreur", "Error"),

    ERR_CHARGER("Erreur chargement fichier", "Failed to load"),

    CHARGER_SUCCESS("Scenario chargé avec succès", "Scenario loaded successfully"),

    INFORMATION("Information", "Information"),

    DEBUG_EDIT_JETON("Entrez les nouvelles valeurs", "Enter the new values"),

    JETON_VERT  ("Vert"  , "Green" ),
    JETON_BLANC ("Blanc" , "White" ),
    JETON_BLEU  ("Bleu"  , "Blue"  ),
    JETON_MARRON("Marron", "Brown" ),
    JETON_ROUGE ("Rouge" , "Red"   ),
    JETON_JAUNE ("Jaune" , "Yellow"),

    DEBUG_VALIDER("Valider", "Ok"),

    ;

    private String libFrancais;
    private String libAnglais;

    private Message(String libFrancais , String libAnglais)
    {
        this.libFrancais = libFrancais;
        this.libAnglais  = libAnglais;
    }

    public String getLib()
    {
        String sRet;

        switch ( langue )
        {
            case "ENG" : sRet = this.libAnglais;   break;
            default    : sRet = this.libFrancais;  break;
        }

        sRet = sRet.replaceAll ( "@", "" );

        return sRet;
    }


    public String getLib(boolean bPluriel)
    {
        String sRet;

        String sing, plur;
        int posDeb, posFin, posSlash;

        switch ( langue )
        {
            case "ENG" : sRet = this.libAnglais;  break;
            default    : sRet = this.libFrancais; break;
        }

        sRet = sRet.replaceAll ( "@", "" );

        posDeb = sRet.indexOf ( '#');

        while (posDeb != -1)
        {
            posSlash = sRet.indexOf ( '/', posDeb+1 );
            posFin   = sRet.indexOf ( '#', posDeb+1 );

            sing = sRet.substring ( posDeb+1, posSlash );
            plur = sRet.substring ( posSlash+1, posFin );

            if (bPluriel) sRet = sRet.substring ( 0, posDeb ) + plur + sRet.substring ( posFin+1 );
            else          sRet = sRet.substring ( 0, posDeb ) + sing + sRet.substring ( posFin+1 );

            posDeb = sRet.indexOf ( '#' );
        }

        return sRet;
    }

}
