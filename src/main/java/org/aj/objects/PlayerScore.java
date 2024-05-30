package org.aj.objects;

import java.util.List;

public class PlayerScore {

    private String name;

    private List<Frame> frames;

    public PlayerScore( String name, List<Frame> frames ) {
        this.name = name;
        this.frames = frames;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Frame> getFrames() { return frames; }

    public void setFrames(List<Frame> frames) { this.frames = frames; }

}
