package splendor_equipe_h.utils;

import splendor_equipe_h.metier.Noble;

import java.util.ArrayList;

public class NobleUtils
{

    public static ArrayList<Noble> getListeNobles()
    {
        ArrayList<Noble> nobles = new ArrayList<>();

        int[][] firstGem =
        {
                {0,4},
                {3,4},
                {1,3},
                {0,2},
                {1,2}
        };

        int[][] secondGem =
        {
                {0,2,4},
                {0,3,4},
                {1,3,4},
                {0,1,2},
                {1,2,3}
        };

        for(int i = 1 ; i <= 10 ; i++)
        {
            int[] res = {0,0,0,0,0};

            if (i<=5)
            {
                for (int j = 0 ; j< res.length; j++)
                {
                    if (j == firstGem[i-1][0] || j == firstGem[i-1][1])
                    {
                        res[j] = 4;
                    }
                }
            }
            else
            {
                for (int k = 0 ; k< res.length ; k++)
                {
                    if (k == secondGem[i-6][0] || k == secondGem[i-6][1] || k == secondGem[i-6][2])
                    {
                        res[k] = 3;
                    }
                }
            }

            nobles.add(new Noble( i < 10 ? "../ressources/noble_0" + i + ".png" : "../ressources/noble_" + i + ".png", 3, Couleur.BLANC, res[0], res[1], res[2], res[3], res[4]));
        }

        return nobles;
    }

}
