
package towerdefense;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.*;
import java.util.logging.Handler;

public class GameTile{
    Button nextLevel;
    Button pause;
    Button save;
    Button upgrade;
    Button sell;
    Button restart;
    Button resume;
    Button load;

    Button NormalTower;
    Button SniperTower;
    Button MachineGunTower;

    boolean showNextLevel = true;
    boolean showFause = false;
    int numberSellOrUpgrade = -1;
    boolean isShowNextLevel = false;
    int isCreateTower = -1;
    boolean stop = false;

    int lives = 10;
    int cash = 50;
    int levels = 0;

    public static int NUMBER_TOWER_X = 17;
    public static int NUMBER_TOWER_Y = 8;

    int save_lives = 0;
    int save_cash = 0;
    int save_leves = 0;
    List <Enemy> save_tanks = new ArrayList<>();
    List <Tower> save_towers = new ArrayList<>();
    int[][] save_TOWER_SPRITES1 = new int[8][17] ;

    int[][] TOWER_SPRITES = new int[][] {
            {14 * 64, 1 * 64, 180, 249},
            {15 * 64, 1 * 64, 181, 206},
            {16 * 64, 1 * 64, 182, 203},
    };

    public static final String[][] MAP_SPRITES = new String[][] {//bản đồ
            { "024", "024", "024", "024", "003", "047", "047", "047", "047", "004", "024" ,"024" ,"024", "024", "103", "103", "103"},
            { "024", "024", "024", "024", "025", "299", "001", "001", "002", "023", "024" ,"024" ,"024", "024", "103", "103", "103"},
            { "024", "024", "024", "024", "025", "023", "024", "024", "242", "240", "241" ,"241" ,"241", "241", "103", "103", "103"},
            { "024", "024", "024", "024", "025", "023", "024", "024", "242", "240", "241" ,"241" ,"241", "241", "103", "103", "103"},
            { "024", "003", "047", "047", "048", "023", "024", "024", "242", "240", "241" ,"241" ,"241", "241", "103", "103", "103"},
            { "024", "025", "299", "001", "001", "027", "024", "024", "242", "263", "264" ,"264" ,"264", "264", "103", "103", "103"},
            { "024", "025", "023", "024", "024", "024", "024", "024", "243", "218", "218" ,"218" ,"218", "218", "103", "103", "103"},
            { "024", "025", "023", "024", "024", "024", "024", "024", "241", "241", "241" ,"241" ,"241", "241", "103", "103", "103"},
    };

    public  static int[][]  TOWER_SPRITES1 = new int[][]
            {
                    {-1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-2, -1, -1, -1, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -2, -2, -1, -2, -2, -1, -1, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -1, -2, -1, -2, -2, -1, -1, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -2, -2, -2, -2, -2, -1, -1, -2, -2, -1, -1, -2, -1, -2, -2, -2},
                    {-1, -2, -2, -2, -2, -2, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2},
                    {-1, -2, -2, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2},
                    {-1, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2, -2}
            };
    public static final int[][] TOWER_SPRITES_CONSTAN = new int[][]
            {
                    {-1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-2, -1, -1, -1, -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -2, -2, -1, -2, -2, -1, -1, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -1, -2, -1, -2, -2, -1, -1, -2, -2, -1, -1, -1, -1, -2, -2, -2},
                    {-1, -2, -2, -2, -2, -2, -1, -1, -2, -2, -1, -1, -2, -1, -2, -2, -2},
                    {-1, -2, -2, -2, -2, -2, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2},
                    {-1, -2, -2, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2},
                    {-1, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2, -2}
            };

    public GameTile()
    {
        nextLevel = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_next.png"));
        nextLevel.setLayoutX(64 * 15 - 10);
        nextLevel.setLayoutY(64 * 3 + 20);
        nextLevel.setBackground(Background.EMPTY);

        pause = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_pause.png"));
        pause.setLayoutX(64 * 15 - 10);
        pause.setLayoutY(64 * 6 + 20);
        pause.setBackground(Background.EMPTY);

        upgrade = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_upgrade.png"));
        upgrade.setLayoutX(64 * 5);
        upgrade.setLayoutY(64 * 1 + 20);
        upgrade.setBackground(Background.EMPTY);

        sell = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_sell.png"));
        sell.setLayoutX(64 * 5);
        sell.setLayoutY(64 * 1 + 20);
        sell.setBackground(Background.EMPTY);

        resume = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_resume.png"));
        resume.setLayoutX(64 * 6 + 20);
        resume.setLayoutY(64 * 2);
        resume.setBackground(Background.EMPTY);

        restart = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_restart.png"));
        restart.setLayoutX(64 * 6 + 20);
        restart.setLayoutY(64 * 3 + 5);
        restart.setBackground(Background.EMPTY);

        save = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_save.png"));
        save.setLayoutX(64 * 6 + 20);
        save.setLayoutY(64 * 4 + 10);
        save.setBackground(Background.EMPTY);

        load = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/button_load.png"));
        load.setLayoutX(64 * 6 + 20);
        load.setLayoutY(64 * 5 + 15);
        load.setBackground(Background.EMPTY);

        NormalTower = new Button("" , new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/NormalTower.png"));
        NormalTower.setLayoutX(64 * 15);
        NormalTower.setLayoutY(64 * 0);
        //NormalTower.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));

        SniperTower = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/SniperTower.png"));
        SniperTower.setLayoutX(64 * 15);
        SniperTower.setLayoutY(64 * 1 + 5);
        //SniperTower.setBackground(Background.EMPTY);

        MachineGunTower = new Button("", new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/MachineGunTower.png"));
        MachineGunTower.setLayoutX(64 * 15);
        MachineGunTower.setLayoutY(64 * 2 + 10);
        //MachineGunTower.setBackground(Background.EMPTY);

    }
    public void setCash(int x) {cash = x;}
    public void setLives(int x) {lives= x;}
    void render(GraphicsContext gc, Group root)
    {
        if (showNextLevel == true) {
            root.getChildren().addAll(nextLevel, pause);
            showNextLevel = false;
            root.getChildren().addAll(NormalTower, SniperTower, MachineGunTower);
        }
        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile" +
                        MAP_SPRITES[i][j] + ".png"), j * 64, i * 64);
            }
        }

        if(numberSellOrUpgrade >= 0)
        {
            if(isShowNextLevel == true)root.getChildren().addAll(sell, upgrade);
            sell.setLayoutX(TowerDefense.mouseX_Clicked / 64 * 64 - 64);
            sell.setLayoutY(TowerDefense.mouseY_Clicked / 64 * 64);
            upgrade.setLayoutX(TowerDefense.mouseX_Clicked / 64 * 64 + 64);
            upgrade.setLayoutY(TowerDefense.mouseY_Clicked / 64 * 64);
            isShowNextLevel = false;
        }
        else
        {
            root.getChildren().remove(sell);
            root.getChildren().remove(upgrade);
        }

        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile135.png"), 2 * 64, 0);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile137.png"), 3 * 64, 0);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile020.png"), 0 * 64, 1 * 64);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile130.png"), 1 * 64, 2 * 64);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile019.png"), 2 * 64, 2 * 64);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile131.png"), 2 * 64, 3 * 64);
        gc.drawImage(new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile132.png"), 12 * 64, 4 * 64);

        gc.setFill( Color.BLACK );
        gc.setStroke( Color.PAPAYAWHIP );
        gc.setLineWidth(1);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 20 );
        gc.setFont( theFont );

        gc.fillText("Lives: " + lives, 64 * 14 + 50, 64 * 5);
        gc.fillText("Cash: " + cash, 64 * 14 + 50, 64 * 5 + 40);
        gc.fillText("Level: " + levels, 64 * 14 + 50, 64 * 5 + 80);
        if(lives == 0)
        {
            gc.setFill( Color.RED );
            gc.setStroke( Color.BLACK );
            gc.setLineWidth(1);
            theFont = Font.font( "Times New Roman", FontWeight.BOLD, 60 );
            gc.setFont( theFont );
            gc.fillText("GAME OVER", 64 * 6, 64 * 4);
        }

    }

    void update(Group root) {
        DropShadow shadow = new DropShadow();

        NormalTower.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (cash >= 5) {
                            isCreateTower = 0;
                            cash = cash - 5;

                        }
                    }
                }
        );
        SniperTower.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (cash >= 10) {
                            isCreateTower = 1;
                            cash = cash - 10;
                        }
                    }
                }
        );
        MachineGunTower.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (cash >= 10) {
                            isCreateTower = 2;
                            cash = cash - 10;
                        }
                    }
                }
        );

        nextLevel.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //levels++;
                        nextLevel.setEffect(shadow);
                    }
                }
        );
        nextLevel.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        nextLevel.setEffect(null);
                    }
                }
        );
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stop = !stop;
            }
        });
        sell.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int i = 0; i < TowerDefense.towers.size(); i++)
                            if (TowerDefense.towers.get(i).x == TowerDefense.mouseX_Clicked / 64 * 64 &&
                                TowerDefense.towers.get(i).y == TowerDefense.mouseY_Clicked / 64 * 64)
                            {
                                TowerDefense.towers.get(i).setDelete(true);
                                TowerDefense.towers.remove(i);
                                if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 0)
                                {
                                    cash = cash + 5;
                                }
                                if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 1)
                                {
                                    cash = cash + 7;
                                }
                                if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 2)
                                {
                                    cash = cash + 9;
                                }
                                TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] = -1;
                                root.getChildren().remove(sell);
                                root.getChildren().remove(upgrade);
                                numberSellOrUpgrade = - 1;
                            }
                    }
                });
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i = 0; i < TowerDefense.towers.size(); i++)
                    if (TowerDefense.towers.get(i).x == TowerDefense.mouseX_Clicked / 64 * 64 &&
                            TowerDefense.towers.get(i).y == TowerDefense.mouseY_Clicked / 64 * 64)
                    {
                        TowerDefense.towers.get(i).upgrade();
                        if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 0)
                        {
                            cash = cash - 8;
                        }
                        if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 1)
                        {
                            cash = cash - 10;
                        }
                        if(TOWER_SPRITES1[TowerDefense.mouseY_Clicked / 64][TowerDefense.mouseX_Clicked / 64] == 2)
                        {
                            cash = cash - 12;
                        }
                        root.getChildren().remove(sell);
                        root.getChildren().remove(upgrade);
                        numberSellOrUpgrade = - 1;
                    }
            }
        });

        resume.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stop = false;
                    }
                }
        );

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                while(!TowerDefense.towers.isEmpty())
                {
                    TowerDefense.towers.get(0).setDelete(true);
                    TowerDefense.towers.remove(0);
                }
                while(!TowerDefense.tanks.isEmpty())
                {
                    TowerDefense.tanks.get(0).setDelete(true);
                    TowerDefense.tanks.remove(0);
                }
                lives = 10;
                levels = 0;
                cash = 50;
                stop = false;
                for(int i = 0; i < 8; i++)
                    for(int j = 0; j < 17; j++)
                        TOWER_SPRITES1[i][j] = TOWER_SPRITES_CONSTAN[i][j];

            }
        });
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save_cash = cash;
                save_leves = levels;
                save_lives = lives;
                while (!save_towers.isEmpty())
                    save_towers.remove(0);
                while (!save_tanks.isEmpty())
                    save_tanks.remove(0);
                for(int i = 0; i < TowerDefense.tanks.size(); i++)
                    save_tanks.add(TowerDefense.tanks.get(i));
                for(int i = 0; i < TowerDefense.towers.size(); i++)
                    save_towers.add(TowerDefense.towers.get(i));
                for(int i = 0; i < 8; i++)
                    for(int j = 0; j < 17; j++)
                        save_TOWER_SPRITES1[i][j] = TOWER_SPRITES1[i][j];
                stop = false;

            }
        });
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                while(!TowerDefense.towers.isEmpty())
                {
                    TowerDefense.towers.get(0).setDelete(true);
                    TowerDefense.towers.remove(0);
                }
                while(!TowerDefense.tanks.isEmpty())
                {
                    TowerDefense.tanks.get(0).setDelete(true);
                    TowerDefense.tanks.remove(0);
                }
                cash = save_cash;
                levels = save_leves;
                lives = save_lives;
                for(int i = 0; i < save_tanks.size(); i++)
                {
                    save_tanks.get(i).setDelete(false);
                    TowerDefense.tanks.add(save_tanks.get(i));
                    TowerDefense.gameObjects.add(save_tanks.get(i));
                }

                for(int i = 0; i < save_towers.size(); i++){
                    save_towers.get(i).setDelete(false);
                    TowerDefense.towers.add(save_towers.get(i));
                    TowerDefense.gameObjects.add(save_towers.get(i));
                }

                System.out.println(save_tanks + " " + save_towers);
                for(int i = 0; i < 8; i++)
                    for(int j = 0; j < 17; j++)
                        TOWER_SPRITES1[i][j] = save_TOWER_SPRITES1[i][j];
                stop = false;
            }
        });

    }
}