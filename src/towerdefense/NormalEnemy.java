package towerdefense;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class NormalEnemy extends Enemy
{
    public NormalEnemy(int i, int j) {
        super(i, j);
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile245.png");//268
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile245.png");//291
        speed = 3;
        Reward = 10;//
        Armor = 20;//khi bắn chết sẽ nhận dc phần thưởng
        health = 30;
        k = (double)health/2;
        radius = 45;
        direction = Direction.UP;

    }
    public NormalEnemy()
    {

    }
    public static List<Enemy> createNormalEnemy() {
        List<Enemy> a = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            Enemy normalEnemy = new NormalEnemy(1, k + 7);
            a.add(normalEnemy);
        }
        return a;
    }
}