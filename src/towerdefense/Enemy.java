package towerdefense;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sun.dc.pr.Rasterizer;

import java.util.ArrayList;
import java.util.List;

class Enemy extends AttackableObject {
    Image gunImg;
    int m;
    int Armor;//giáp
    int Reward;//phần thưởng
    int radius = 15;
    boolean delete;
    double k;
    public void setDelete(boolean delete){
        this.delete = delete;
    }
    public boolean getDelete(){
        return delete;
    }
    public void setPointTank(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point getPointTank(){
        Point pt = new Point(x, y);
        return pt;
    }
    public Enemy()
    {

    }

    Layer layer = new Layer(50,50);

    public Enemy(int i,int j)
    {
        this.i = i;
        this.j = j;
        x =this.i * 64 + 32;
        y = this.j* 64;
        direction = Direction.UP;
    }
    int wayPointIndex = 0;

    public Point getNextWayPoint() {
        if (wayPointIndex < TowerDefense.wayPoints.length - 1)
            return TowerDefense.wayPoints[++wayPointIndex];
        return null;
    }

    @Override
    void render(GraphicsContext gc) {//hàm hiển thị
        layer.health = layer.health*a;
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        iv.setRotate(this.direction.getDegree());
        Image base = iv.snapshot(params, null);

        ImageView iv2 = new ImageView(gunImg);
        iv2.setRotate(this.direction.getDegree());
        Image gun = iv2.snapshot(params, null);


        ImageView iv3 = new ImageView(layer.Red);//ảnh thanh máu
        iv3.setRotate(this.direction.getDegree());
        Image img1 = iv3.snapshot(params, null);

        ImageView iv4 = new ImageView(layer.Green);
        iv4.setRotate(this.direction.getDegree());
        Image img2 = iv4.snapshot(params, null);
        if( x <= 64*13 && health >0 && delete  == false) {
            gc.drawImage(base, x, y);
            gc.drawImage(gun, x, y);
            gc.drawImage(img1, x, y);

            if ((x <= 288 && y == 288) || (x <= 544 && y == 32) )
                gc.drawImage(img2, x, y, layer.tile()*50, 16);
            else if (x>= 544&&x <= 928 && y == 352 )
            {
                gc.drawImage(img2, x, y, layer.tile()* 50, 16);
            }
            else gc.drawImage(img2, x, y, 16, layer.tile()* 50);
        }
        if(health == 0)
        {
            ImageView iv5 = new ImageView("file:src/resources/AssetsKit_2/PNG/Default size/54.png");
            Image m = iv5.snapshot(params, null);
            gc.drawImage(m, x, y);
            health = -1;

        }


        gc.setFill(Color.TRANSPARENT);
        gc.fillOval(TowerDefense.wayPoints[wayPointIndex].x,TowerDefense.wayPoints[wayPointIndex].y,10, 10);

        gc.setFill(Color.TRANSPARENT);
        gc.fillOval(x, y,10, 10);

        gc.rect(x,y,64, 8);
        gc.fillRect(0,0,64,64);
    }

    void calculateDirection() {
        if (wayPointIndex >= TowerDefense.wayPoints.length) {
            return ;
        }
        Point currentWP = TowerDefense.wayPoints[wayPointIndex];
        if (Point.distance(x, y, currentWP.x, currentWP.y) <= speed) {
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null )
            {
                setDelete(true);
                return ;
            }
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed) direction = Direction.RIGHT;
            else if (deltaX < -speed) direction = Direction.LEFT;
            else if (deltaY > speed) direction = Direction.DOWN;
            else if (deltaY <= -speed) direction = Direction.UP;
        }
        return ;
    }


    @Override
    void update() {

        calculateDirection();

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }
}
