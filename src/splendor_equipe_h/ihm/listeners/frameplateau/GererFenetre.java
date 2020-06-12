package splendor_equipe_h.ihm.listeners.frameplateau;

import splendor_equipe_h.ihm.FramePlateau;

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
