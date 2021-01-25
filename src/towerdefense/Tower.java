package towerdefense;

import javafx.geometry.Point3D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

class Tower extends AttackableObject {
    Image gunImg;
    int radius = 200 ;//bán kính
    double degree ;//góc
    boolean delete = false;

    List<Enemy> tanks = new ArrayList<>();
    Bullet bullet = new Bullet();
    int tg_nap_dan;
    public Tower(){

    }
    public void setDelete(boolean delete) {this.delete = delete;}
    @Override
    void render(GraphicsContext gc) {
        if(!this.delete)
        {
            gc.drawImage(img, x, y);
            //.drawImage(gun, x, y);
        }
        SnapshotParameters  pa = new SnapshotParameters();
        pa.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(gunImg);
        //iv.setRotate(degree);
        Image gun = iv.snapshot(pa, null);

        gc.save();
        Rotate r = new Rotate(degree);
        r.setPivotX(x + 32);
        r.setPivotY(y + 32);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        if(!this.delete)
        {
            //gc.drawImage(img, x, y);
            gc.drawImage(gun, x, y);
        }
        gc.restore();

        gc.setFill(Color.TRANSPARENT);
        gc.fillOval(x, y,200, 200);
        bullet.render(gc);

    }
    void makeBullet(){
        bullet = new Bullet();
        bullet.img = new Image("file:src/resources/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");
        bullet.x = x;
        bullet.y = y;
        bullet.speed = this.speed;
    }

    @Override
    void update() {

    }
    public void upgrade()
    {

    }
}
