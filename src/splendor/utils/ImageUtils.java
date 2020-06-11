package splendor.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils
{

    public static ImageIcon resizeImage(String url, int width, int height)
    {
        BufferedImage img = null;

        try
        {
            img = ImageIO.read(new File(url));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

}
