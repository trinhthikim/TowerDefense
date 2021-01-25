package towerdefense;

import javafx.scene.canvas.GraphicsContext;

class Bullet extends MovableObject{
    Point tank = new Point(0, 0);
    boolean delete;
    public void setDelete(boolean delete){
        this.delete = delete;
    }
    public void setTank(Point p){
        this.tank = p;
    }
    @Override
    void render(GraphicsContext gc){
        if(!this.delete){
            gc.drawImage(img, x, y);
        }
    }
    void calculateDirection(){
        int xxx = x - tank.x;
        int yyy = y - tank.y;
        double xy = Math.sqrt(Math.pow(xxx, 2) + Math.pow(yyy, 2));
        if(xy>100+45||xy<45){
            setDelete(true);
            return;
        }
        this.x -= speed*xxx/xy;
        this.y -= speed*yyy/xy;
    }
    @Override
    void update(){
        calculateDirection();
    }
}
