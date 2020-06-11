package splendor.ihm.listeners.frameplateau;

import splendor.ihm.FramePlateau;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GererFenetre extends ComponentAdapter
{

    private FramePlateau framePlateau;

    public GererFenetre(FramePlateau framePlateau)
    {
        this.framePlateau = framePlateau;
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
        this.framePlateau.updateFramesPosition();
    }

}
