package splendor_equipe_h.utils;

import static splendor_equipe_h.Controleur.langue;

public enum Message
{

    TITRE_FRAME_PLATEAU("Splendor", "Splendor"),

    BUTTON_BUY_CARD("Acheter la carte",  "Buy card"),
    BUTTON_BUY_CARD_RESERVE("Acheter la carte reservée",  "Buy booked card"),
    BUTTON_RESERVE_CARD("Reserver la carte", "Book card"),

    PLAYER("Joueur n°{NUM}", "Player No {NUM}"),

    TOUR_JOUEUR("Au tour du joueur n°{NUM}","Player {NUM}'s turn"),

    ERR_PRISE_JETON_INVALIDE("Vous n'avez pas le droit de prendre cette combinaison de jetons", "You are not allowed to take this tokens combination"),

    ERR_JETON("Vous n'avez pas assez de jetons","You do not have enough Tokens"),

    ERR_JETON_FULL("Vous possédez déjà trop de jetons", "You already have max tokens"),

    ERR_NOT_ENOUGH_JETON("Vous n'avez pas assez de jetons", "You do not have enough Tokens"),

    ERR_RESERVATION("Vous ne pouvez pas reserver cette carte", "You can't book this card"),

    CHOIX_NOBLE("Veuillez choisir le noble que vous souhaitez prendre", "Please select the noble you want"),

    BOUTON_VALIDER_NOBLE("Selectionner ce noble", "Select this noble"),

    WINNER("Le joueur {NUM} à gagné la partie", "Player's {NUM} has won the game"),

    ERR_SAUVEGARDE_NOM_SCENARIO("Vous n'avez pas entré de nom", "You didn't enter a name"),

    ACTION_SAVE_NOM_SCENARIO("Veuillez entrer le nom du scénario", "Please enter the name of the script"),

    ACTION_SAVE_SCENARIO("Vous avez sauvegardé le scénario", "You saved the script"),

    NOT_YOUR_TURN("Ce n'est pas votre tour !", "It's not your turn !"),

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
