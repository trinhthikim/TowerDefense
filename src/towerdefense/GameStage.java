package towerdefense;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

public class GameStage {
    Button startGame;
    Image image_menu;
    boolean isShow_imageMenu;
    public GameStage()
    {
        isShow_imageMenu = true;
        startGame = new Button();
        startGame.setLayoutX(407);
        startGame.setLayoutY(420);
        ImageView imageView = new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/startGame.png");
        startGame.setGraphic(imageView);
        startGame.setBackground(Background.EMPTY);
    }

    void render(GraphicsContext gc)
    {
        gc.drawImage( new Image("file:src/resources/AssetsKit_2/PNG/Default size/menu_game.png"), 0, 0);
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isShow_imageMenu = false;
            }
        });
    }
    void update()
    {

    }
}
