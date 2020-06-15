package splendor_equipe_h.utils;

import java.awt.*;

import static splendor_equipe_h.Controleur.echelleHeight;

public enum SplendorFont
{
    SEGOE_BIG  ("segoe script", (int) (40 * echelleHeight)),
    SEGOE_SMALL("segoe script", (int) (16 * echelleHeight)),

    ;

    private String name;
    private int size;

    private SplendorFont(String name, int size)
    {
        this.name = name;
        this.size = size;
    }

    public Font getFont()
    {
        return new Font(this.name, Font.PLAIN, this.size);
    }

}
