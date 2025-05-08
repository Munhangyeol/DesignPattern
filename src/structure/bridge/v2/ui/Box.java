package src.structure.bridge.v2.ui;

import src.structure.bridge.v2.render.Render;

public class Box extends UiComponent {
    public Box(Render render) {
        super(render);
    }

    @Override
    public void draw() {
        render.checkBox();

    }
}
