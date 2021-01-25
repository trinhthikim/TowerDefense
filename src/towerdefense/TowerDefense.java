package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


//import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TowerDefense extends Application {

    GraphicsContext gc;

    public static List<GameObject> gameObjects = new ArrayList<>();
    GameTile menu = new GameTile();
    public static List<Tower> towers = new ArrayList<>();
    //boolean stop;
    GameStage gameStage = new GameStage();

    int cash = menu.cash;
    int lives = menu.lives;
    public static int mouseX_Moved = -64;
    public static int mouseY_Moved = -64;

    public static int mouseX_Clicked = 1000;
    public static int mouseY_Clicked = 1000;
    Timeline t = new Timeline();

    //public final int soTank = 5;
    public static List<Enemy> tanks = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Canvas canvas = new Canvas(64 * GameTile.NUMBER_TOWER_X, 64 * GameTile.NUMBER_TOWER_Y);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(gameStage.startGame);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!menu.stop && lives > 0) {
                    update(root);
                } //else stop();
                render(root);

                menu.nextLevel.setOnAction(
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                {
                                    if(tanks.isEmpty()) {
                                        menu.levels++;
                                        int x = menu.levels % 4;
                                        switch (x) {
                                            case 1:
                                                tanks = NormalEnemy.createNormalEnemy();
                                                break;
                                            case 2:
                                                tanks = SmallerEnemy.createSmallerEnemy();
                                                break;
                                            case 3:
                                                tanks = TankerEnemy.createTankerEnemy();
                                                break;
                                            case 0:
                                                tanks = BossEnemy.createBossEnemy();
                                                break;
                                        }
                                        for (int i = 0; i < tanks.size(); i++)
                                            gameObjects.add(tanks.get(i));
                                    }
                                }

                            }
                        });

                isBuyTower(gc, scene, root);
            }
        };
        timer.start();
    }

    public void tinhGoc(Tower tower, Enemy tank) {
        double d = Point.distance(tank.x, tank.y, tower.x, tower.y);
        if (d > tower.radius + tank.radius) {
            tower.degree = 0;
            return;
        }
        int xx = tank.x - tower.x;
        int yy = tank.y - tower.y;
        double radian = Math.atan2(yy, xx);
        double dg = radian * 180 / (Math.PI) + 90;
        tower.degree = dg;
    }

    public void banDan(Tower tower, Enemy tank) {
        tower.tg_nap_dan--;
        if (tower.tg_nap_dan == 0) {
            tower.makeBullet();
            tower.bullet.setTank(tank.getPointTank());
            tower.tg_nap_dan = 5;
        }
        tower.bullet.update();
        xuLyVaCham(tower, tank);
    }

    public void xuLyVaCham(Tower tower, Enemy tank) {
        double d = Point.distance(tower.bullet.x, tower.bullet.y, tank.x, tank.y);
        double b = tank.health;
        if (d < tank.radius) {
            tank.health -= tower.damage;
            String uriString = new File("src/Arrow-Swoosh-1.mp3").toURI().toString();
            MediaPlayer player = new MediaPlayer( new Media(uriString));
            player.play();
            tank.a = (double) (tank.health / b);
        }
    }

    public static final Point[] wayPoints = new Point[]{//điểm chỉ đường
            new Point(1 * 64 + 32, 4 * 64 + 32),
            new Point(4 * 64 + 32, 4 * 64 + 32),
            new Point(4 * 64 + 32, 0 * 64 + 32),
            new Point(8 * 64 + 32, 0 * 64 + 32),
            new Point(8 * 64 + 32, 5 * 64 + 32),
            new Point(14 * 64 + 32, 5 * 64 - 32),

    };

    public void update(Group root) {
        menu.update(root);
        for (int i = 0; i < towers.size(); i++) {
            if (!tanks.isEmpty() && !towers.isEmpty()) {
                tinhGoc(towers.get(i), tanks.get(0));
                banDan(towers.get(i), tanks.get(0));
                if (tanks.get(0).health <= 0) {
                    tanks.get(0).setDelete(true);
                    tanks.remove(0);
                    cash += 2;
                }
                if (towers.get(i).degree == 0) {
                    for (int j = 0; j < tanks.size(); j++) {
                        if (Point.distance(towers.get(i).x, towers.get(i).y, tanks.get(j).x, tanks.get(j).y) <= towers.get(i).radius + tanks.get(j).radius) {
                            tinhGoc(towers.get(i), tanks.get(j));
                            banDan(towers.get(i), tanks.get(j));
                            if (tanks.get(j).health <= 0) {
                                tanks.get(j).setDelete(true);
                                tanks.remove(j);
                                //menu.setCash(cash += 2);
                                cash += 2;
                            }
                            break;
                        }
                    }
                }
            } else {
                for (int j = 0; j < towers.size(); j++) {
                    towers.get(j).bullet.setDelete(true);
                    towers.get(j).degree = 0;
                }
            }
        }
        gameObjects.forEach(GameObject::update);
    }

    public void render(Group root) {
        if (gameStage.isShow_imageMenu == true)
            gameStage.render(gc);
        else {

            menu.render(gc, root);
            gameObjects.forEach(g -> g.render(gc));
            gameStage.startGame.setLayoutY(64 * 13);
            if (!tanks.isEmpty()) {
                if (tanks.get(0).x >= 64 * 13 && !tanks.isEmpty()) {
                    lives--;
                    tanks.remove(0);
                    menu.setLives(lives);
                }
                if(tanks.get(0).health == 0)
                {
                    tanks.remove(0);
                }
            }
            if(menu.stop==true){
                gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/menu.png"), 64*5, 64*1);
                if(menu.showFause == false)root.getChildren().addAll(menu.resume, menu.restart, menu.save, menu.load);
                menu.showFause = true;
            }
            else
            {
                root.getChildren().remove(menu.resume);
                root.getChildren().remove(menu.restart);
                root.getChildren().remove(menu.save);
                root.getChildren().remove(menu.load);
                menu.showFause = false;
            }
        }
    }

    public void isBuyTower(GraphicsContext gc, Scene scene, Group root) {
        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {

                        int i = (int) e.getX() / 64;
                        int j = (int) e.getY() / 64;


                        if (menu.TOWER_SPRITES1[j][i] == -1 && menu.isCreateTower != -1) {
                            Tower tower = new Tower();
                            switch (menu.isCreateTower) {
                                case 0:
                                    tower = new NormalTower(i * 64, j * 64);
                                    menu.TOWER_SPRITES1[j][i] = 0;
                                    break;
                                case 1:
                                    tower = new SniperTower(i * 64, j * 64);
                                    menu.TOWER_SPRITES1[j][i] = 1;
                                    break;
                                case 2:
                                    tower = new MachineGunTower(i * 64, j * 64);
                                    menu.TOWER_SPRITES1[j][i] = 2;
                                    break;
                            }
                            towers.add(tower);
                            gameObjects.add(tower);
                            menu.isCreateTower = -1;
                        } else {
                            if (menu.TOWER_SPRITES1[j][i] >= 0) {
                                if (menu.numberSellOrUpgrade < 0) {
                                    menu.numberSellOrUpgrade = menu.TOWER_SPRITES1[j][i];
                                    mouseX_Clicked = (int) e.getX();
                                    mouseY_Clicked = (int) e.getY();
                                    menu.isShowNextLevel = true;
                                } else {
                                    menu.numberSellOrUpgrade = -1;
                                    mouseX_Clicked = 1000;
                                    mouseY_Clicked = 1000;
                                    menu.isShowNextLevel = false;
                                }
                            }

                        }

                    }
                }
        );
        scene.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mouseX_Moved = (int) event.getX();
                        mouseY_Moved = (int) event.getY();
                    }
                }
        );

        if (menu.isCreateTower == 0) {
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile180.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile249.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.strokeOval(mouseX_Moved - NormalTower.NORMAL_RADIUS, mouseY_Moved - NormalTower.NORMAL_RADIUS,
                    NormalTower.NORMAL_RADIUS * 2, NormalTower.NORMAL_RADIUS * 2);
        }
        if (menu.isCreateTower == 1) {
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile181.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile206.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.strokeOval(mouseX_Moved - SniperTower.SNIPER_RADIUS, mouseY_Moved - SniperTower.SNIPER_RADIUS,
                    SniperTower.SNIPER_RADIUS * 2, SniperTower.SNIPER_RADIUS * 2);
        }
        if (menu.isCreateTower == 2) {
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile182.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile203.png"), mouseX_Moved - 32, mouseY_Moved - 32);
            gc.strokeOval(mouseX_Moved - MachineGunTower.MACHINE_GUN_RADIUS, mouseY_Moved - MachineGunTower.MACHINE_GUN_RADIUS,
                    MachineGunTower.MACHINE_GUN_RADIUS * 2, MachineGunTower.MACHINE_GUN_RADIUS * 2);
        }
        if (gameStage.isShow_imageMenu == false) {
            for (int k = 0; k < towers.size(); k++) {
                if ((mouseX_Moved / 64 * 64) <= towers.get(k).x && towers.get(k).x <= (mouseX_Moved / 64 * 64 + 64) &&
                        (mouseY_Moved / 64 * 64) <= towers.get(k).y && towers.get(k).y <= (mouseY_Moved / 64 * 64 + 64))
                {
                    if (menu.TOWER_SPRITES1[mouseY_Moved / 64][mouseX_Moved / 64] == 0) {
                        gc.strokeOval(mouseX_Moved / 64 * 64 + 32 - towers.get(k).radius, mouseY_Moved / 64 * 64 + 32 - towers.get(k).radius,
                                towers.get(k).radius * 2, towers.get(k).radius * 2);
                        break;
                    }

                    if (menu.TOWER_SPRITES1[mouseY_Moved / 64][mouseX_Moved / 64] == 1) {
                        gc.strokeOval(mouseX_Moved / 64 * 64 + 32 - towers.get(k).radius, mouseY_Moved / 64 * 64 + 32 - towers.get(k).radius,
                                towers.get(k).radius * 2, towers.get(k).radius * 2);
                        break;
                    }

                    if (menu.TOWER_SPRITES1[mouseY_Moved / 64][mouseX_Moved / 64] == 2) {
                        gc.strokeOval(mouseX_Moved / 64 * 64 + 32 - towers.get(k).radius, mouseY_Moved / 64 * 64 + 32 - towers.get(k).radius,
                                towers.get(k).radius * 2, towers.get(k).radius * 2);
                        break;
                    }
                }
            }
        }

    }
}