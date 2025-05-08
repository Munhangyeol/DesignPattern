package src.structure.bridge.v2.ui;

import src.structure.bridge.v2.render.Render;

public abstract class UiComponent {
    protected Render render;
    public UiComponent(Render render){
        this.render = render;
    }
    abstract public void draw();
}
