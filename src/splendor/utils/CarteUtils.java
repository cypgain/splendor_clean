package splendor.utils;

import splendor.metier.Carte;

import java.util.ArrayList;

public class CarteUtils
{

    public static ArrayList<Carte> getDeckOne()
    {
        ArrayList<Carte> deckOne = new ArrayList<Carte>()
        {
            private static final long serialVersionUID = 5047753364239890219L;

            {
            add(new Carte("ressources/dev_I_01.png", 0, Couleur.BLANC, 0, 0, 0, 1, 2));
            add(new Carte("ressources/dev_I_02.png", 0, Couleur.BLANC, 0, 0, 3, 0, 0));
            add(new Carte("ressources/dev_I_03.png", 0, Couleur.BLANC, 0, 0, 2, 2, 0));
            add(new Carte("ressources/dev_I_04.png", 0, Couleur.BLANC, 0, 3, 1, 1, 0));
            add(new Carte("ressources/dev_I_05.png", 0, Couleur.BLANC, 2, 0, 2, 1, 0));
            add(new Carte("ressources/dev_I_06.png", 0, Couleur.BLANC, 1, 0, 1, 1, 1));
            add(new Carte("ressources/dev_I_07.png", 0, Couleur.BLANC, 2, 0, 1, 1, 1));
            add(new Carte("ressources/dev_I_08.png", 1, Couleur.BLANC, 4, 0, 0, 0, 0));
            add(new Carte("ressources/dev_I_09.png", 0, Couleur.BLEU, 0, 1, 0, 2, 0));
            add(new Carte("ressources/dev_I_10.png", 0, Couleur.BLEU, 0, 0, 0, 3, 0));
            add(new Carte("ressources/dev_I_11.png", 0, Couleur.BLEU, 2, 0, 0, 2, 0));
            add(new Carte("ressources/dev_I_12.png", 0, Couleur.BLEU, 3, 0, 1, 0, 1));
            add(new Carte("ressources/dev_I_13.png", 0, Couleur.BLEU, 2, 1, 0, 0, 2));
            add(new Carte("ressources/dev_I_14.png", 0, Couleur.BLEU, 1, 1, 0, 1, 1));
            add(new Carte("ressources/dev_I_15.png", 0, Couleur.BLEU, 1, 1, 0, 1, 2));
            add(new Carte("ressources/dev_I_16.png", 1, Couleur.BLEU, 0, 0, 0, 0, 4));
            add(new Carte("ressources/dev_I_17.png", 0, Couleur.VERT, 0, 2, 1, 0, 0));
            add(new Carte("ressources/dev_I_18.png", 0, Couleur.VERT, 0, 0, 0, 0, 3));
            add(new Carte("ressources/dev_I_19.png", 0, Couleur.VERT, 0, 0, 2, 0, 2));
            add(new Carte("ressources/dev_I_20.png", 0, Couleur.VERT, 1, 1, 3, 0, 0));
            add(new Carte("ressources/dev_I_21.png", 0, Couleur.VERT, 0, 0, 1, 2, 2));
            add(new Carte("ressources/dev_I_22.png", 0, Couleur.VERT, 0, 1, 1, 1, 1));
            add(new Carte("ressources/dev_I_23.png", 0, Couleur.VERT, 0, 1, 1, 2, 1));
            add(new Carte("ressources/dev_I_24.png", 1, Couleur.VERT, 0, 0, 0, 4, 0));
            add(new Carte("ressources/dev_I_25.png", 0, Couleur.ROUGE, 1, 0, 2, 0, 0));
            add(new Carte("ressources/dev_I_26.png", 0, Couleur.ROUGE, 0, 3, 0, 0, 0));
            add(new Carte("ressources/dev_I_27.png", 0, Couleur.ROUGE, 0, 2, 0, 0, 2));
            add(new Carte("ressources/dev_I_28.png", 0, Couleur.ROUGE, 0, 1, 0, 3, 1));
            add(new Carte("ressources/dev_I_29.png", 0, Couleur.ROUGE, 1, 2, 0, 2, 0));
            add(new Carte("ressources/dev_I_30.png", 0, Couleur.ROUGE, 1, 1, 1, 1, 0));
            add(new Carte("ressources/dev_I_31.png", 0, Couleur.ROUGE, 1, 2, 1, 1, 0));
            add(new Carte("ressources/dev_I_32.png", 1, Couleur.ROUGE, 0, 4, 0, 0, 0));
            add(new Carte("ressources/dev_I_33.png", 0, Couleur.MARRON, 2, 0, 0, 0, 1));
            add(new Carte("ressources/dev_I_34.png", 0, Couleur.MARRON, 3, 0, 0, 0, 0));
            add(new Carte("ressources/dev_I_35.png", 0, Couleur.MARRON, 2, 2, 0, 0, 0));
            add(new Carte("ressources/dev_I_36.png", 0, Couleur.MARRON, 1, 0, 0, 1, 3));
            add(new Carte("ressources/dev_I_37.png", 0, Couleur.MARRON, 0, 2, 2, 0, 1));
            add(new Carte("ressources/dev_I_38.png", 0, Couleur.MARRON, 1, 1, 1, 0, 1));
            add(new Carte("ressources/dev_I_39.png", 0, Couleur.MARRON, 1, 1, 2, 0, 1));
            add(new Carte("ressources/dev_I_40.png", 1, Couleur.MARRON, 0, 0, 4, 0, 0));
        }};

        return deckOne;
    }

    public static ArrayList<Carte> getDeckTwo()
    {
        ArrayList<Carte> deckTwo = new ArrayList<Carte>()
        {
            
            private static final long serialVersionUID = 5047753364239890219L;

            {
            add(new Carte("ressources/dev_II_01.png", 1, Couleur.BLANC, 3, 0, 0, 2, 2));
            add(new Carte("ressources/dev_II_02.png", 1, Couleur.BLANC, 0, 2, 3, 0, 3));
            add(new Carte("ressources/dev_II_03.png", 2, Couleur.BLANC, 1, 0, 0, 2, 4));
            add(new Carte("ressources/dev_II_04.png", 2, Couleur.BLANC, 0, 0, 0, 3, 5));
            add(new Carte("ressources/dev_II_05.png", 2, Couleur.BLANC, 0, 0, 0, 0, 5));
            add(new Carte("ressources/dev_II_06.png", 2, Couleur.BLANC, 0, 6, 0, 0, 0));
            add(new Carte("ressources/dev_II_07.png", 1, Couleur.BLEU, 2, 0, 2, 0, 3));
            add(new Carte("ressources/dev_II_08.png", 1, Couleur.BLEU, 3, 0, 2, 3, 0));
            add(new Carte("ressources/dev_II_09.png", 2, Couleur.BLEU, 0, 2, 0, 4, 1));
            add(new Carte("ressources/dev_II_10.png", 2, Couleur.BLEU, 0, 5, 3, 0, 0));
            add(new Carte("ressources/dev_II_11.png", 2, Couleur.BLEU, 0, 0, 5, 0, 0));
            add(new Carte("ressources/dev_II_12.png", 2, Couleur.BLEU, 0, 0, 6, 0, 0));
            add(new Carte("ressources/dev_II_13.png", 1, Couleur.VERT, 0, 2, 3, 2, 0));
            add(new Carte("ressources/dev_II_14.png", 1, Couleur.VERT, 2, 3, 0, 0, 3));
            add(new Carte("ressources/dev_II_15.png", 2, Couleur.VERT, 0, 4, 2, 1, 0));
            add(new Carte("ressources/dev_II_16.png", 2, Couleur.VERT, 3, 0, 5, 0, 0));
            add(new Carte("ressources/dev_II_17.png", 2, Couleur.VERT, 5, 0, 0, 0, 0));
            add(new Carte("ressources/dev_II_18.png", 2, Couleur.VERT, 6, 0, 0, 0, 0));
            add(new Carte("ressources/dev_II_19.png", 1, Couleur.ROUGE, 0, 2, 0, 3, 2));
            add(new Carte("ressources/dev_II_20.png", 1, Couleur.ROUGE, 0, 0, 3, 3, 0));
            add(new Carte("ressources/dev_II_21.png", 2, Couleur.ROUGE, 2, 1, 4, 0, 0));
            add(new Carte("ressources/dev_II_22.png", 2, Couleur.ROUGE, 0, 3, 0, 5, 0));
            add(new Carte("ressources/dev_II_23.png", 2, Couleur.ROUGE, 0, 0, 0, 5, 0));
            add(new Carte("ressources/dev_II_24.png", 2, Couleur.ROUGE, 0, 0, 0, 0, 6));
            add(new Carte("ressources/dev_II_25.png", 1, Couleur.MARRON, 2, 3, 2, 0, 0));
            add(new Carte("ressources/dev_II_26.png", 1, Couleur.MARRON, 3, 2, 0, 2, 0));
            add(new Carte("ressources/dev_II_27.png", 2, Couleur.MARRON, 4, 0, 1, 0, 2));
            add(new Carte("ressources/dev_II_28.png", 2, Couleur.MARRON, 5, 0, 0, 0, 3));
            add(new Carte("ressources/dev_II_29.png", 2, Couleur.MARRON, 0, 5, 0, 0, 0));
            add(new Carte("ressources/dev_II_30.png", 2, Couleur.MARRON, 0, 0, 0, 6, 0));
        }};


        return deckTwo;
    }

    public static ArrayList<Carte> getDeckThree()
    {
        ArrayList<Carte> deckThree = new ArrayList<Carte>()
        {
            
            private static final long serialVersionUID = 5047753364239890219L;

            {
            add(new Carte("ressources/dev_III_01.png", 3, Couleur.BLANC, 3, 0, 3, 3, 5));
            add(new Carte("ressources/dev_III_02.png", 4, Couleur.BLANC, 0, 3, 0, 6, 3));
            add(new Carte("ressources/dev_III_03.png", 4, Couleur.BLANC, 0, 0, 0, 7, 0));
            add(new Carte("ressources/dev_III_04.png", 5, Couleur.BLANC, 0, 3, 0, 7, 0));
            add(new Carte("ressources/dev_III_05.png", 3, Couleur.BLEU, 3, 3, 0, 5, 3));
            add(new Carte("ressources/dev_III_06.png", 4, Couleur.BLEU, 0, 6, 3, 3, 0));
            add(new Carte("ressources/dev_III_07.png", 4, Couleur.BLEU, 0, 7, 0, 0, 0));
            add(new Carte("ressources/dev_III_07.png", 4, Couleur.BLEU, 0, 7, 0, 0, 0));
            add(new Carte("ressources/dev_III_08.png", 5, Couleur.BLEU, 0, 7, 3, 0, 0));
            add(new Carte("ressources/dev_III_09.png", 3, Couleur.VERT, 0, 5, 3, 3, 3));
            add(new Carte("ressources/dev_III_10.png", 4, Couleur.VERT, 3, 3, 6, 0, 0));
            add(new Carte("ressources/dev_III_11.png", 4, Couleur.VERT, 0, 0, 7, 0, 0));
            add(new Carte("ressources/dev_III_12.png", 5, Couleur.VERT, 3, 0, 7, 0, 0));
            add(new Carte("ressources/dev_III_13.png", 3, Couleur.ROUGE, 3, 3, 5, 3, 0));
            add(new Carte("ressources/dev_III_14.png", 4, Couleur.ROUGE, 6, 0, 3, 0, 3));
            add(new Carte("ressources/dev_III_15.png", 4, Couleur.ROUGE, 7, 0, 0, 0, 0));
            add(new Carte("ressources/dev_III_18.png", 4, Couleur.MARRON, 3, 0, 0, 3, 6));
            add(new Carte("ressources/dev_III_19.png", 4, Couleur.MARRON, 0, 0, 0, 0, 7));
            add(new Carte("ressources/dev_III_16.png", 5, Couleur.ROUGE, 7, 0, 0, 0, 3));
            add(new Carte("ressources/dev_III_17.png", 3, Couleur.MARRON, 5, 3, 3, 0, 3));
            add(new Carte("ressources/dev_III_20.png", 5, Couleur.MARRON, 0, 0, 0, 3, 7));
        }};

        return deckThree;
    }

}
