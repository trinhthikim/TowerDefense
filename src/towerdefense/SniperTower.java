package towerdefense;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

class SniperTower extends Tower{
    public static final int SNIPER_SPEED =  15;
    public static final int TG_NAP_DAN_SNIPER = 1;
    public static final int SNIPER_RADIUS = 120;
    public static final int SNIPER_DAMAGE = 3;
    public SniperTower(int xx, int yy)
    {
        x = xx;
        y = yy;
        radius = SniperTower.SNIPER_RADIUS;
        damage = SniperTower.SNIPER_DAMAGE;
        speed = SniperTower.SNIPER_SPEED;
        tg_nap_dan = SniperTower.TG_NAP_DAN_SNIPER;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
    }
    public void upgrade()
    {
        radius = radius + 20;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile205.png");
    }
    void makeBullet(){
        bullet = new Bullet();
        ImageView iv = new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile251.png");
        SnapshotParameters pa = new SnapshotParameters();
        pa.setFill(Color.TRANSPARENT);
        iv.setRotate(degree);
        bullet.img = iv.snapshot(pa, null);
        bullet.x = x;
        bullet.y = y;
        bullet.speed = this.speed;
    }
}
