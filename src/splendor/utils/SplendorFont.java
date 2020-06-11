package splendor.utils;

import java.awt.*;

public enum SplendorFont
{
    SEGOE_BIG("segoe script", 40),
    SEGOE_SMALL("segoe script", 16),

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
