package splendor.utils;

import static splendor.Controleur.LANGUE;

public enum Message
{

    TITRE_FRAME_PLATEAU("Splendor", "Splendor"),

    BUTTON_BUY_CARD    ("Acheter la carte",  "Buy card"),
    BUTTON_RESERVE_CARD("Reserver la carte", "Book card"),

    PLAYER("Joueur n°{NUM}", "Player No {NUM}"),

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

        switch ( LANGUE )
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

        switch ( LANGUE )
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
