package towerdefense;

abstract class VulnerableObject extends MovableObject {//dễ bị tổn thương
    int health=1;//trạng thái
    double b = 1000;
    double a=1;
    double reward;//phần thưởng
}

