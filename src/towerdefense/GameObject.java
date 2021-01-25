package towerdefense;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

abstract class GameObject {
    int i, j;
    int x;
    int y;
    Image img;

    abstract void render(GraphicsContext gc);
    abstract void update();

}
