package towerdefense;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class TankerEnemy extends Enemy
{

    public TankerEnemy(int i,int j)
    {
        super(i,j);
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile268.png");//268
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile291.png");//291
        speed = 2;
        health = 20;
        Reward = 20;
        radius = 45;
        Armor = 40;//khi bắn chết sẽ nhận dc phần thưởng
        direction = Direction.UP;
    }
    public static List<Enemy> createTankerEnemy() {
        List<Enemy> a = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            TankerEnemy tankerEnemy = new TankerEnemy(1, k + 6);
            a.add(tankerEnemy);
        }
        return a;
    }
}

