package towerdefense;

import javafx.scene.image.Image;

class MachineGunTower extends Tower{

    public static final int MACHINE_GUN_SPEED = 45;
    public static final int TG_NAP_DAN_MACHINE_GUN = 1;
    public static final int MACHINE_GUN_RADIUS = 80;
    public static final int MACHINE_GUN_DAMAGE = 1;
    public MachineGunTower(int xx, int yy) {
        x = xx;
        y = yy;
        radius = MachineGunTower.MACHINE_GUN_RADIUS;
        damage = MachineGunTower.MACHINE_GUN_DAMAGE;
        speed = MachineGunTower.MACHINE_GUN_SPEED;
        tg_nap_dan = MachineGunTower.TG_NAP_DAN_MACHINE_GUN;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
    }
    public void upgrade()
    {
        radius = radius + 20;
        img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        gunImg = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile204.png");
    }
    void makeBullet(){
        bullet = new Bullet();
        bullet.img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile274.png");
        bullet.x = x;
        bullet.y = y;
        bullet.speed = this.speed;
    }
}