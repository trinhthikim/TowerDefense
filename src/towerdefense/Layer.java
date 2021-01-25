package towerdefense;

import javafx.scene.image.Image;

public class Layer {

        Image Green;
        Image Red;
        double health;
        double startHealth;
        public Layer(double health, double startHealth) {
            this.health= health;
            this.startHealth = startHealth;
            Green = new Image("file:src/resources/AssetsKit_2/PNG/Default size/barrelGreen.png");
            Red = new Image("file:src/resources/AssetsKit_2/PNG/Default size/barrelRed.png");
        }

        public double tile()
        {
            double a = (health/startHealth);
            return a;

        }
}
