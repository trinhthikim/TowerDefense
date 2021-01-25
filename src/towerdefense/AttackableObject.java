package towerdefense;

abstract class AttackableObject extends VulnerableObject {//đối tượng tấn công
    double damage;
    double fireRate;
    double fireRange;
    int raduis ;//bán kính quay
    public void xuLyVaCham(Tower tower, Enemy tank){
        double d = Point.distance(tower.bullet.x, tower.bullet.y, tank.x, tank.y);
        if(d<tank.radius){
            tank.damage++;
        }
    }
}