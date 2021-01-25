package towerdefense;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

class NormalTower extends Tower{
    public static int NORMAL_SPEED = 30;
    public static int TG_NAP_DAN_NORMAL = 1;
    public static int NORMAL_RADIUS = 100;
    public static int NORMAL_DAMAGE = 1;
    public NormalTower(int xx, int yy) {
        x = xx;
        y = yy;
        radius = NormalTower.NORMAL_RADIUS;
        damage = NormalTower.NORMAL_DAMAGE;
        speed = NormalTower.NORMAL_SPEED;
        tg_nap_dan = NormalTower.TG_NAP_DAN_NORMAL;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile180.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
    }
    public void upgrade()
    {
        radius = radius + 20;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile180.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile250.png");
    }
    void makeBullet(){
        bullet = new Bullet();
        ImageView iv = new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile295.png");
        SnapshotParameters pa = new SnapshotParameters();
        pa.setFill(Color.TRANSPARENT);
        iv.setRotate(degree);
        bullet.img = iv.snapshot(pa, null);//new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile295.png");
        bullet.x = x;
        bullet.y = y;
        bullet.speed = this.speed;
    }
}