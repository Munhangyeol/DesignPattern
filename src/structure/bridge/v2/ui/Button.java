package src.structure.bridge.v2.ui;

import src.structure.bridge.v2.render.Render;

public class Button extends UiComponent{
    public Button(Render render) {
        super(render);
    }

    @Override
    public void draw() {
        render.checkButton();
    }
}
