package splendor.utils;

import java.awt.*;

public enum SplendorFont
{
    FRAME_PLATEAU_TITRE("segoe script", 60),

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
