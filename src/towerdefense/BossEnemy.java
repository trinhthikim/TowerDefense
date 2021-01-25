package towerdefense;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

class BossEnemy extends Enemy
{
    private ArrayList<BossEnemy> tanks= new ArrayList<>();
    public BossEnemy(int i, int j) {
        super(i, j);
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile271.png");//268
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile294.png");//291
        speed = 5;
        //Blood = 30;//khởi tạo chỉ số máu  của quân địch là 10 đơn vị
        Reward = 30;//
        Armor = 50;//khi bắn chết sẽ nhận dc phần thưởng
        health = 400;
        radius = 45;
        direction = Direction.UP;
    }
    public static List<Enemy> createBossEnemy() {
        List<Enemy> a = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            Enemy bossEnemy = new BossEnemy(1, k + 7);
            a.add(bossEnemy);
        }
        return a;
    }
}

