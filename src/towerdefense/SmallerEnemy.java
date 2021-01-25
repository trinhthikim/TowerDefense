package towerdefense;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class SmallerEnemy extends Enemy
{
    private ArrayList<SmallerEnemy> tanks= new ArrayList<>();
    public SmallerEnemy(int i,int j)
    {
        super(i,j);
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile248.png");//268
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile248.png");//291
        speed = 5;
        Armor = 20;//khi bắn chết sẽ nhận dc phần thưởng
        health = 10;
        radius = 45;
        k = (double) health/2;
        //System.out.println(k);
        direction = Direction.UP;
    }
    public static List<Enemy> createSmallerEnemy() {
        List<Enemy> a = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            Enemy tank = new SmallerEnemy(1, k + 6);
            a.add(tank);
        }
        return a;
    }
}