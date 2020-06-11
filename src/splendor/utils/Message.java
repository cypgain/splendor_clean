package splendor.utils;

import static splendor.Controleur.langue;

public enum Message
{

    TITRE_FRAME_PLATEAU("Splendor", "Splendor"),

    BUTTON_BUY_CARD    ("Acheter la carte",  "Buy card"),
    BUTTON_RESERVE_CARD("Reserver la carte", "Book card"),

    PLAYER("Joueur n°{NUM}", "Player No {NUM}"),

    ERR_PRISE_JETON_INVALIDE("Vous n'avez pas le droit de prendre cette combinaison de jetons", "You are not allowed to take this tokens combination"),

    ERR_JETON_FULL("Vous possédez déjà trop de jetons", "You already have max tokens"),

    ERR_NOT_ENOUGH_JETON("Vous n'avez pas assez de jetons", "You do not have enough Tokens"),

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
